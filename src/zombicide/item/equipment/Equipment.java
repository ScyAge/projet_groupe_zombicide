package zombicide.item.equipment;

import zombicide.item.*;
import zombicide.actor.player.*;

/**
 * class Equipment
 */
public abstract class  Equipment extends Item {

	
	/**
	 * Builder of Equipment
	 * @param title of the item
	 */
	public Equipment(String title) {
		super(title);
	}
	
	/**
	 * use the equipment
	 */
	public void Used() {
		this.Use =true;
		this.equipmentEffect(null);
	}

	
	
	/**
	 * realizes the equipment effect 
	 * @param player who uses the equipment 
	 * */
	public abstract void equipmentEffect(Player player);

}
