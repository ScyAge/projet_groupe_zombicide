package zombicide.item.weapons;

import zombicide.item.*;

public class Weapon extends Item {
	

	private WeaponType type;
	private int threshold;
	private int range;
	private int damage;
	private boolean breakDoor;
	
	public Weapon(String title,int range,int damage, int threshold, boolean breakDoor, WeaponType type) {
		super(title);
		this.damage =damage;
		this.range =range;
		this.threshold = threshold;
		this.breakDoor= breakDoor;
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
	 * says whether the weapon breaks a door or not
	 * @return boolean
	 *   */
	public boolean getBreakDoor() {
		return this.breakDoor;
	}
	/**
	 * gives the type of the weapon 
	 * @return weapon's type 
	 * */
	public WeaponType getType() {
		return type;
	}
	
}
