package zombicide.item;

public class Item {
	
	
	private String title ;
	private int range ;
	
	public Item(String title, int range) {
		this.title = title;
		this.range = range;
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
	 * get the range of the Item
	 * @return the range of the item
	 */
	public int getRange() {
		return range;
	}
	/**
	 * Set range to the Item
	 * @param range of the item
	 */
	public void setRange(int range) {
		this.range = range;
	}

}
