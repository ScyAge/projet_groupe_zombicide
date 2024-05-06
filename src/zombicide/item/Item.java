package zombicide.item;

import zombicide.actor.player.Player;


/**
 * Class Item
 */
public abstract class Item implements Cloneable{

    /**
	 * the Item is use or not 
	 */
	protected boolean Use;
	
	/**
	 * can break door
	 */
	protected boolean breakDoor;

	/**
	 * if the item is noisy
	 */
	protected boolean noisy;
	
	/**
	 * if the item can attack
	 */
	protected boolean canAttack;
	
	/**
	 * Builder of Item
	 * @param canAttack if the item can attack or not
	 * @param noisy if the item is noisy or not
	 * @param breakDoor can break door
	 */
	public Item(boolean breakDoor,boolean canAttack ,boolean noisy) {

		this.Use = false;
		this.breakDoor= breakDoor;
		this.noisy = noisy;
		this.canAttack = canAttack;
	}
	

	/**
	 * says whether the item is used or not
	 * @return true if the item is used, false if not
	 * */
	public boolean isUsed() {
		return this.Use;
	}
	
	/**
	 * Item can attack or not
	 * @return if it can attack
	 */
	public boolean canAttack(){
		return this.canAttack;
	}
	
	
	/**
	 * says whether the weapon breaks a door or not
	 * @return boolean
	 *   */
	public boolean getBreakDoor() {
		return this.breakDoor;
	}
	
	
	/**
	 * realizes the item effect 
	 * @param player who uses the equipment 
	 * */
	public abstract void ItemEffect(Player player);
	
	/**
	 * use the item
	 */
	public void Used() {
		this.Use =true;
	}
	
	/**
	 * set not use item
	 */
	public void NotUsed() {
		this.Use =false;
	}
	
	/**
	 * return true if the equipment is noisy else false
	 * @return true if the equipment is noisy else false
	 */
	public boolean isNoisy() {
		return this.noisy;
	}
	

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

	@Override
	public Item clone() throws CloneNotSupportedException {
		return (Item )super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Item){
			Item i = (Item)obj;
			return (this.Use == i.Use) && (this.breakDoor == i.breakDoor)&& (this.noisy == i.noisy)&& (this.canAttack == i.canAttack);
		}
		return false;
	}
	
	/**
	 * method that prints a short description of the item effect
	 * @return the description
	 * */
	public abstract String itemDescription();
}
