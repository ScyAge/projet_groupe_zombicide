package zombicide.item.weapons;

import zombicide.item.*;

public class Weapon extends Item {
	

	private WeaponType type;
	private int threshold;
	private int range;
	private int damage;
	
	public Weapon(String title,int range,int damage, int threshold,WeaponType type) {
		super(title);
		this.damage =damage;
		this.range =range;
		this.threshold = threshold;
		this.type =type;
	}
	
	
	/**returns the threshold of the Weapon 
	 * @return threshold of the given weapon
	 * */
	public int getThreshold() {
		return this.threshold;
	}

	
	/**gives the range of the given weapon
	 * @return range of the weapon 
	 * */
	public int getRange() {
		return this.range;
	}

	
	/**
	 * gives the damage caused by the given weapon 
	 * @return damage 
	 * */
	public int getDamage() {
		return this.damage;
	}

	
	/**
	 * gives the type of the weapon 
	 * @return weapon's type 
	 * */
	public WeaponType getType() {
		return type;
	}
	
}
