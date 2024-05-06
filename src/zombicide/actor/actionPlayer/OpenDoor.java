package zombicide.actor.actionPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.*;
import zombicide.board.Board;
import zombicide.cell.Cell;
import zombicide.item.Item;
import zombicide.util.Direction;
import zombicide.util.Door;
import zombicide.util.listchooser.*;

/**
 * Class OpenDoor implementation  of ActionsPlayer
 */
public class OpenDoor implements ActionsPlayer {
	
	/**
	 * List chooser
	 */
    private final ListChooser<Direction> chooser;
    
    /**
     * the Board 
     */
    private final Board board;
    
    /**
     * Builder of SearchIntRoomAction with ListChooser to test
     * @param chooser ListChooser of the Action
     * @param b the board 
     */
    public OpenDoor(ListChooser<Direction> chooser,Board b){
        this.chooser = chooser;
		this.board = b;
    }
    /**
     * Builder of SearchInTRoomAction 
     * @param b the board 
     */
    public OpenDoor(Board b){
		this.chooser = new InteractiveListChooser<>();
		this.board = b;
    }
	

	/**
	 * method that fills in the list of directions where doors can be opened
	 * @param player the player in the board
	 * @return the list on direction
	 */
	private List<Direction> DirectionToOpen(Player player) {
		List<Direction> directionsRes = new ArrayList<>();
		for(Direction direction : Direction.values()) {
			if(this.board.canBreakDoor(direction,player)){
				directionsRes.add(direction);
			}
		}
		return directionsRes;
	}

	@Override
	public void action(Player p) {
		Item item= p.getItemInHand();
		Cell c= p.getCurrentCell();
		if(item != null && item.getBreakDoor()) {
			
			List<Direction> directions= DirectionToOpen(p);
			Direction targetD= this.chooser.choose("-> choose a direction: ", directions);
			if(targetD != null) {
				this.board.BreakDoor(targetD, c.getX(), c.getY());
				if (item.canAttack()) {
					c.setNoise(c.getNoise() + 1);
					System.out.println("Opening the door on the direction "+ targetD);
				}
				generateZombies(p, targetD);
				
				
			}else {
				System.out.println("No direction chosen");
			}
		}
		else {
			System.out.println("The item in hand can't break the door");
		}
		p.setAction_points(p.getAction_points()-1);
		
	}
	
	
	
	/**
	 * generates randomly zombies and special zombies 
	 * @param p player who's opening the door
	 * @param d the direction of the opened door*/
	private void generateZombies(Player p, Direction d) {
		Cell c= this.board.getCellDirection(d,p);
		if(c!=null) {
			if(!c.isCanSpawnInCell()){
				return;
			}
			Random random= new Random();
			int numZ= random.nextInt(3)+1;
			for (int i=0; i<numZ; i++) {
				Walker z=new Walker(c,random.nextInt(100));
				c.addZombies(z);
				this.board.addZombieList(z);
			}
	
			List<String> specialZ= List.of("Abomination", "Broom");
			String zombieType= specialZ.get(random.nextInt(specialZ.size()));
			switch(zombieType) {
				case "Abomination":
					Abominations a= new Abominations(c, random.nextInt(100));
					c.addZombies(a);
					this.board.addZombieList(a);
					break;
				case "Broom":
					Broom b= new Broom(c, random.nextInt(100));
					c.addZombies(b);
					this.board.addZombieList(b);
					break;
			}
		}
	}
	

	@Override
	public boolean IsActionPlayable(Player player) {
		List<Boolean> res = new ArrayList<>();
		for(Direction direction: Direction.values()) {
			res.add(this.board.canBreakDoor(direction,player));
		}
		return  player.getItemInHand() != null && player.getItemInHand().getBreakDoor() && res.contains(true);
	}

	@Override
	public String toString() {
		return "OpenDoor action";
	}
}
