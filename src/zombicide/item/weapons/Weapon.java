package zombicide.item.weapons;


import zombicide.item.*;

/**
 * class weapons
 */
public class Weapon extends Item {
	

	/**
	 * Param of weapons
	 */
	private final int threshold;
	private final int rangeMax;
	private final int rangeMin;
	private final int damage;
	private final boolean noisy;
	private final int nbDice;
	
	

	
	/**
	 * Builder of the weapons
	 * @param title of the item
	 * @param rangeMin of the weapons
	 * @param rangeMax of the weapons
	 * @param damage of the weapons
	 * @param threshold of the weapons
	 * @param breakDoor  can break door or not
	 * @param noisy if the weapon is noisy
	 * @param nbDice of the weapon
	 */
	public Weapon(String title,int rangeMax,int rangeMin,int damage, int threshold, boolean breakDoor, boolean noisy, int nbDice) {
		super(title, breakDoor);
		this.damage =damage;
		this.rangeMax =rangeMax;
		this.rangeMin =rangeMin;
		this.threshold = threshold;
		this.noisy = noisy;
		this.nbDice= nbDice;
	}
	
	/**
	 * Item can attack or not
	 * @return true if it can attack
	 */
	public boolean cantAttack(){
		return true;
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
		return this.rangeMax;
	}
	/**gives the min range of the given weapon
	 * @return min range of the weapon 
	 * */
	public int getMinRange() {
		return this.rangeMin;
	}

	
	/**
	 * gives the damage caused by the given weapon 
	 * @return damage 
	 * */
	public int getDamage() {
		return this.damage;
	}

	/**
	 * gives the number of dices 
	 * @return nbDice number of dices that a weapon allows to do */
	public int getNbDice() {
		return this.nbDice;
	}
	
	
	/**
	 * the weapon is used 
	 * */
	public void Used() {
		this.Use=true;
	}
	
	

	/**
	 * return true if the weapon is noisy else false (for attack)
	 * @return true if the weapon is noisy else false (for attack)
	 */
	public boolean isNoisy(){
		return this.noisy;
	}
	
	
	
	
}
