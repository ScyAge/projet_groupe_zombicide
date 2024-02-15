package zombicide.item.equipment;

import zombicide.item.*;

public abstract class  Equipment extends Item {

	public Equipment(String title) {
		super(title);
	}
	
	public abstract void equipementEffect();

}
