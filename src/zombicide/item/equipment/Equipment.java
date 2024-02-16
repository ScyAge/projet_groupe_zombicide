package zombicide.item.equipment;

import zombicide.item.*;
import zombicide.actor.player.*;

public abstract class  Equipment extends Item {

	public Equipment(String title) {
		super(title);
	}
	
	public abstract void equipmentEffect(Player player);

}
