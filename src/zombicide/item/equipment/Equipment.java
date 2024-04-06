package zombicide.item.equipment;

import zombicide.item.*;
import zombicide.actor.player.*;
import zombicide.cell.Cell;

/**
 * class Equipment
 */
public class  Equipment extends Item {

	
	/**
	 * Builder of Equipment
	 * @param title of the item
	 */
	public Equipment(String title, boolean breakDoor) {
		super(title, breakDoor);
	}

	/**
	 * realizes the equipment effect 
	 * @param player who uses the equipment 
	 * */
	public void ItemEffect(Player player) {
		this.Used();
		if(this.isNoisy()){
			Cell c = player.getCurrentCell();
			c.setNoise(c.getNoise()+1);
		}
	}


}
