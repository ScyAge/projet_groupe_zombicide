package zombicide.item;

public class Item {
	
	
	private String title ;
	private boolean Use;
	
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
	
	public boolean isUsed(Item item) {
		return this.Use;
		
	}
	public void Used(Item item) {
		this.Use =true;
	}

	public void NotUsed(Item item) {
		this.Use =false;
	}
	
}
