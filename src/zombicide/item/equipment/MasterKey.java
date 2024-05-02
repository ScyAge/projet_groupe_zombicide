package zombicide.item.equipment;

import zombicide.actor.player.*;
import zombicide.board.Board;
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
	
	private final Board b;
	/**
	 * Builder of MasterKey
	 */
	public MasterKey(Board b) {
		super(false);
		this.chooser = new InteractiveListChooser<>();
		this.b = b;
	}
	
	
	/**
	 * Builder of MasterKey
	 *
	 * @param chooser ListChooser of the masterkey
	 * @param b
	 */
	public MasterKey(ListChooser<Direction> chooser, Board b) {
		super(false);
		this.chooser=chooser;
		this.b = b;
	}
	
	/**
	 * Opens the cell's door where the player is according to the user's choice
	 * @param player who has the MasterKey
	 * */
	public void effectOfTheEquip(Player player) {

		Cell cell= player.getCurrentCell();
		
		System.out.println("enter the Direction of the door you want to open");
		
		List<Direction> directions= List.of(Direction.North, Direction.South, Direction.East, Direction.West);
		Direction targetD= chooser.choose("choose a direction: ", directions);
		
		if(targetD != null) {
			Door doorToOpen= cell.getDoor(targetD);
			Door doorOfTheNearCell = this.b.getCellDirection(targetD,player).getDoor(Direction.oppose(targetD));
			if(!doorOfTheNearCell.isBreak() || !doorToOpen.isBreak()) {
				this.b.BreakDoor(targetD,cell.getX(),cell.getY());
				System.out.println("Door opened in the "+ targetD + " direction");
			}
		}else {
			System.out.println("No direction chosen");
		}
		
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MasterKey){
			MasterKey i = (MasterKey) obj;
			return super.equals(i) && (this.chooser == i.chooser);
		}
		return false;
	}
}
