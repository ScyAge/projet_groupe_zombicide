package zombicide.cell;
import java.util.*;

import zombicide.item.*;
import zombicide.util.*;

public class Room extends Cell {
	private List<Item> items;
	private Map<Direction, Door> doors;
	
	/** Constructor of the class Room
	 * @param x coordinates of the cell
	 * @param y coordinates of the cell
	 * @param type the type of the cell 
	 */
	public Room(int x, int y) {
		super(x, y);
		this.items = new ArrayList<>();
		this.doors = new HashMap<>();
		for (Direction direction : Direction.values()) {
            doors.put(direction, null);
        }
	}
	
	/**
	 * set a door with a direction
	 * @param direction
	 * @param door
	 */
	public void setDirection(Direction direction, Door door) {
        doors.put(direction, door);
    }
	
	/**
	 * return the door
	 * @return the door
	 */
	public Map<Direction, Door> getDoors() {
	    return this.doors;
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
	public Door getDoor(Direction direction) {
		return this.doors.get(direction);
	}
	
	
	@Override
	public String toString() {
		return this.doors.get(Direction.West).toString()+"R"+this.doors.get(Direction.East).toString(); 
		
	}

}
