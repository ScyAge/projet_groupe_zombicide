package zombicide.item;

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
	 * Builder of Item
	 * @param title of the item
	 */
	public Item(String title) {
		this.title = title;
		this .Use = false;
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

	@Override
	public String toString() {
		return this.title;
	}
}
