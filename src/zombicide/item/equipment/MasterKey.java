package zombicide.item.equipment;

import zombicide.actor.player.*;
import zombicide.cell.*;
import zombicide.util.*;
import java.util.*;
import zombicide.util.listchooser.InteractiveListChooser;
import zombicide.util.listchooser.ListChooser;

/**
 * class MasterKey
 */
public class MasterKey extends Equipment {
	
	/**
	 * Builder of MasterKey
	 * @param title of the item
	 */
	public MasterKey(String title) {
		super(title);
	}
	
	/**
	 * Opens the cell's door where the player is according to the user's choice
	 * @param player who has the MasterKey
	 * */
	public void equipmentEffect(Player player) {
		super.equipmentEffect(player);
		Cell cell= player.getCurrentCell();
		System.out.println("enter the Direction of the door you want to open");
		
		List<Direction> directions= List.of(Direction.North, Direction.South, Direction.East, Direction.West);
		ListChooser<Direction> listChooser = new InteractiveListChooser<>();
		Direction targetD= listChooser.choose("choose a direction: ", directions);
		
		if(targetD != null) {
			Door doorToOpen= cell.getDoor(targetD);
			if(doorToOpen!=null) {
				doorToOpen.Break();
				System.out.println("Door opened in the "+ targetD + " direction");
			}else {
				System.out.println("No door found in the "+ targetD+ " direction ");
			}
		}else {
			System.out.println("No direction chosen");
		}
			
	}
	
	

}
