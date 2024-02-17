package zombicide.item.equipment;

import zombicide.actor.player.*;
import zombicide.cell.Cell;
import zombicide.util.Direction;
import zombicide.util.Door;
import java.util.*;


public class MasterKey extends Equipment {

	public MasterKey(String title) {
		super(title);
	}
	
	/**
	 * Opens the cell's door where the player is according to the user's choice
	 * @param: player who has the MasterKey
	 * */
	public void equipmentEffect(Player player) {
		System.out.println(" Enter the direction of the door you want to open");
		System.out.println("choose between North, South, East, West:");
		Scanner scanner= new Scanner(System.in);
		String d = scanner.nextLine();
		
		Direction direction= Direction.valueOf(d);
		Cell cell= player.getCurrentCell();
		Door doorToOpen= cell.getDoor(direction);
		doorToOpen.Break();
			
	}

}
