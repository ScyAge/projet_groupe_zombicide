package zombicide.item.weapons;


import zombicide.actor.player.Player;
import zombicide.item.*;

/**
 * class weapons
 */
public  class Weapon extends Item {
	

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
	 *
	 * @param rangeMax  of the weapons
	 * @param rangeMin  of the weapons
	 * @param damage    of the weapons
	 * @param threshold of the weapons
	 * @param breakDoor can break door or not
	 * @param noisy     if the weapon is noisy
	 * @param nbDice    of the weapon
	 */
	public Weapon(int rangeMax, int rangeMin, int damage, int threshold, boolean breakDoor, boolean noisy, int nbDice) {
		super(breakDoor,true,noisy);
		this.damage =damage;
		this.rangeMax =rangeMax;
		this.rangeMin =rangeMin;
		this.threshold = threshold;
		this.noisy = noisy;
		this.nbDice= nbDice;
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
	

	@Override
	public void ItemEffect(Player player) {
		this.Used();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Weapon){
			Weapon i = (Weapon) obj;
			return super.equals(i) && (this.noisy == i.noisy) && (this.rangeMax == i.rangeMax) && (this.rangeMin == i.rangeMin) &&(this.threshold == i.threshold) &&(this.nbDice == i.nbDice) &&(this.damage == i.damage);
		}
		return false;
	}
	
	@Override
	public String itemDescription() {
		String res= "damageValue="+ this.getDamage()+ ", canBreakDoor="+ this.getBreakDoor();
		return res;
	}
}
