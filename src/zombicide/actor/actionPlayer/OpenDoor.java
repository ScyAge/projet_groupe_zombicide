package zombicide.actor.actionPlayer;

import java.util.List;
import java.util.Random;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.*;
import zombicide.board.Board;
import zombicide.cell.Cell;
import zombicide.item.Item;
import zombicide.util.Direction;
import zombicide.util.listchooser.*;

/**
 * Class OpenDoor implementation  of ActionsPlayer
 */
public class OpenDoor implements ActionsPlayer {
	
	/**
	 * Param
	 */
    private final ListChooser<Direction> chooser;
    private final Board board;
    
    /**
     * Builder of SearchIntRoomAction with ListChooser to test
     * @param chooser ListChooser of the Action
     */
    public OpenDoor(ListChooser<Direction> chooser,Board b){
        this.chooser = chooser;
		this.board = b;
    }
    /**
     * Builder of SearchInTRoomAction 
     */
    public OpenDoor(Board b){
		this.chooser = new InteractiveListChooser<>();
		this.board = b;
    }
	
	@Override 
	public void action(Player p) {
		Item item= p.getItemInHand();
		Cell c= p.getCurrentCell();
		if(item != null && item.getBreakDoor()) {
			System.out.println("enter the Direction of the door you want to open");
			
			List<Direction> directions= List.of(Direction.North, Direction.South, Direction.East, Direction.West);
			Direction targetD= this.chooser.choose("choose a direction: ", directions);
			
			if(targetD != null) {
				this.board.BreakDoor(targetD, c.getX(), c.getY());
				if (item.cantAttack()) {
					c.setNoise(c.getNoise() + 1);
				}
				generateZombies(p);
				
			}else {
				System.out.println("No direction chosen");
			}
		}
		else {
			System.out.println("The item in hand can't break the door");
		}
		
	}
	
	
	
	/**
	 * generates randomly zombies and special zombies 
	 * @param p player who's opening the door*/
	private void generateZombies(Player p) {
		Cell c= p.getCurrentCell();
		Random random= new Random();
		int numZ= random.nextInt(3)+1;
		for (int i=0; i<numZ; i++) {
			Walker z=new Walker(c,random.nextInt(100));
			c.addZombies(z);
		}
		ListChooser<String> zombieChooser = new RandomListChooser<>();
		List<String> specialZ= List.of("Abomination", "Broom");
		String zombieType= zombieChooser.choose("choose the special type of zombie", specialZ);
		switch(zombieType) {
			case "Abomination":
				Abominations a= new Abominations(c, random.nextInt(100));
				c.addZombies(a);
				break;
			case "Broom":
				Broom b= new Broom(c, random.nextInt(100));
				c.addZombies(b);
				break;
		}
	}

	@Override
	public boolean IsActionPlayable(Player p) {
		Cell c = p.getCurrentCell();
		return (p.getItemInHand() != null && p.getItemInHand().getBreakDoor()) && (!c.getDoor(Direction.North).isBreak() ||!c.getDoor(Direction.South).isBreak() ||!c.getDoor(Direction.East).isBreak() ||!c.getDoor(Direction.West).isBreak());
	}
}
