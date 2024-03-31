package zombicide.actor.actionPlayer;

import java.util.List;
import java.util.Map;
import java.util.Random;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.*;
import zombicide.cell.Cell;
import zombicide.item.Item;
import zombicide.item.weapons.Weapon;
import zombicide.util.Direction;
import zombicide.util.Door;
import zombicide.util.listchooser.*;

/**
 * Class OpenDoor implementation  of ActionsPlayer
 */
public class OpenDoor implements ActionsPlayer {
	
	/**
	 * Param
	 */
    private ListChooser<Direction> chooser;
    
    /**
     * Builder of SearchIntRoomAction with ListChooser to test
     * @param chooser ListChooser of the Action
     */
    public OpenDoor(ListChooser<Direction> chooser){
        this.chooser = chooser;
    }
    /**
     * Builder of SearchInTRoomAction 
     */
    public OpenDoor(){
        this.chooser = new InteractiveListChooser<>();
    }
	
	@Override 
	public void action(Player p) {
		Item item= p.getItemInHand();
		Cell c= p.getCurrentCell();
		if(item != null && item.getBreakDoor()) {
			System.out.println("enter the Direction of the door you want to open");
			
			List<Direction> directions= List.of(Direction.North, Direction.South, Direction.East, Direction.West);
			ListChooser<Direction> listChooser = new InteractiveListChooser<>();
			Direction targetD= listChooser.choose("choose a direction: ", directions);
			
			if(targetD != null) {
				Door doorToOpen= c.getDoor(targetD);
				if(doorToOpen!=null) {
					doorToOpen.SetNotBreakble();
					if (item instanceof Weapon) {
						c.setNoise(c.getNoise() + 1);
					}
					generateZombies(p);
					System.out.println("Door opened in the "+ targetD + " direction");
				}else {
					System.out.println("No door found in the "+ targetD+ " direction ");
				}
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
	 * @param player who's opening the door*/
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
	
	
	

}
