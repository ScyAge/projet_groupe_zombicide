package cell;
import java.util.*;
import util.*;
import item.*;

public class Room extends Cell {
	private List<Item> items;
	
	/** Constructor of the class Room
	 * @param x coordinates of the cell
	 * @param y coordinates of the cell
	 * @param type the type of the cell 
	 */
	public Room(int x, int y, String type) {
		super(x, y, type);
		this.items = new ArrayList<>();
	}
	
	/**
	 * Return the list of all the items in the room
	 * @return List of items
	 */
	public List<Item> getAllItems() {
		return this.items;
	}
	
	/**
	 * adds an item to the list
	 * @param  i Item,
	 */
	public void addItem(Item i) {
		this.items.add(i) ;
	}
	
	/** 
	 * removes the given item from the list of items
	 * @param i Item 
	 * @return i the item 
	 */
	public Item removeItem(Item i){
		this.items.remove(i);
		return i;
	}
	
	
	@Override
	public String toString() {
		return "this room has "+this.getAllItems().size()+ " items"; 
		
	}

}
