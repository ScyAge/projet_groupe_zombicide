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
	 * Param
	 */
	private final ListChooser<Direction> chooser;
	
	
	/**
	 * Builder of MasterKey
	 * @param title of the item
	 * @param breakDoor true if masterkey can break a door, else return false
	 */
	public MasterKey(String title, boolean breakDoor) {
		super(title, breakDoor);
		this.chooser = new InteractiveListChooser<>();
	}
	
	
	/**
	 * Builder of MasterKey
	 * @param title of the item
	 * @param breakDoor true if masterkey can break a door, else return false
	 * @param chooser ListChooser of the masterkey
	 */
	public MasterKey(String title, boolean breakDoor, ListChooser<Direction> chooser) {
		super(title, breakDoor);
		this.chooser=chooser;
	}
	
	/**
	 * Opens the cell's door where the player is according to the user's choice
	 * @param player who has the MasterKey
	 * */
	public void ItemEffect(Player player) {
		super.ItemEffect(player);
		Cell cell= player.getCurrentCell();
		
		boolean doorsOpen=true;
		for(Direction d : Direction.values()) {
			if(!cell.getDoor(d).isBreak()){
				doorsOpen=false;
				break;
			}
		}
		if(doorsOpen) {
			this.Use = false;
			return;
		}
		System.out.println("enter the Direction of the door you want to open");
		
		List<Direction> directions= List.of(Direction.North, Direction.South, Direction.East, Direction.West);
		Direction targetD= chooser.choose("choose a direction: ", directions);
		
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
