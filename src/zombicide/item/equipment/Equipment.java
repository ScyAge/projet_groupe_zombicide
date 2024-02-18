package zombicide.item.equipment;

import zombicide.item.*;
import zombicide.actor.player.*;

public abstract class  Equipment extends Item {

	public Equipment(String title) {
		super(title);
	}
	
	/**
	 * realizes the equipment effect 
	 * @param player who uses the equipment 
	 * */
	public abstract void equipmentEffect(Player player);

}
