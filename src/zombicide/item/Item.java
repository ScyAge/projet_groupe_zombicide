package zombicide.item;

import zombicide.actor.player.Player;


/**
 * Class Item
 */
public class Item {
	
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
	/**
	 * Builder of Item
	 * @param title of the item
	 */
	public Item(String title, boolean breakDoor) {
		this.title = title;
		this.Use = false;
		this.breakDoor= breakDoor;
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
	public boolean cantAttack(){
		return false;
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
	public void ItemEffect(Player player) {
		this.Used();
	}
	
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
		return false;
	}
	

	@Override
	public String toString() {
		return this.title;
	}
}
