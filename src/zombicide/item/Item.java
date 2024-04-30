package zombicide.item;

import zombicide.actor.player.Player;


/**
 * Class Item
 */
public abstract class Item implements Cloneable{
	
	/** Param of item */
	private String title ;
	
	/**
	 * the Item is use or not 
	 */
	protected boolean Use;
	/**
	 * can break door
	 */
	protected boolean breakDoor;

	protected boolean noisy;
	protected boolean canAttack;
	/**
	 * Builder of Item
	 * @param title of the item
	 * @param breakDoor can break door
	 */
	public Item(String title, boolean breakDoor,boolean canAttack ,boolean noisy) {
		this.title = title;
		this.Use = false;
		this.breakDoor= breakDoor;
		this.noisy = noisy;
		this.canAttack = canAttack;
	}
	
	/**
	 * get the title of the item
	 * @return the title of the item
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Set title to the Item
	 * @param title of the item
	 */
	public void setTitle(String title) {
		this.title = title;
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
		return this.title;
	}

	@Override
	public Item clone() throws CloneNotSupportedException {
		return (Item )super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Item){
			Item i = (Item)obj;
			return (this.Use == i.Use) && (this.title.equals(i.title)) && (this.breakDoor == i.breakDoor)&& (this.noisy == i.noisy)&& (this.canAttack == i.canAttack);
		}
		return false;
	}
}
