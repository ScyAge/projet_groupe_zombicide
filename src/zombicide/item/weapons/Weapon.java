package zombicide.item.weapons;

import zombicide.item.*;

public class Weapon extends Item {
	

	private WeaponType type;
	private int threshold;
	private int range;
	private int domage;
	
	public Weapon(String title,int range,int domage, int threshold,WeaponType type) {
		super(title);
		this.domage =domage;
		this.range =range;
		this.threshold = threshold;
		this.type =type;
	}
	
	public int getThreshold() {
		return this.threshold;
	}

	public int getRange() {
		return this.range;
	}

	public int getDomage() {
		return this.domage;
	}

	public WeaponType getType() {
		return type;
	}
	
}
