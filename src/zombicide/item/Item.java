package zombicide.item;

import zombicide.actor.player.Player;

/**
 * Class Item
 */
public class Item {
	
	/** Param of item */
	private String title ;
	private boolean Use;
	private Player player;
	
	/**
	 * Builder of Item
	 * @param title of the item
	 */
	public Item(String title) {
		this.title = title;
		this .Use = false;
		this.player = null ;
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
	 * Set a item on player
	 * @param p player who take item
	 */
	public void setPlayer(Player p) {
		this.player = p;
	}
	
	/**
	 * Get the player
	 * @return the player who take the item
	 */
	public Player getPlayer() {
		return this.player;
	}
	
	/**
	 * says whether the item is used or not 
	 * @param item to check if it's used or not
	 * @return true if the item is used, false if not
	 * */
	public boolean isUsed(Item item) {
		return this.Use;
		
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
	
}
