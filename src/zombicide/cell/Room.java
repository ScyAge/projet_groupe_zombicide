package zombicide.cell;
import java.util.*;

import zombicide.item.*;
import zombicide.util.*;
import zombicide.util.door.*;

/**
 * class of Room
 */
public class Room extends Cell {
	private List<Item> items;
	
	/** Constructor of the class Room
	 * @param x coordinates of the cell
	 * @param y coordinates of the cell
	 */
	public Room(int x, int y) {
		super(x, y);
		this.items = new ArrayList<>();
		for (Direction direction : Direction.values()) {
            if(direction == Direction.East || direction == Direction.West){
				this.doors.put(direction, new East_west_door(true,false));
			}
			else{
				this.doors.put(direction, new North_South_door(true,false));
			}
        }
	}
	
	/**
	 * set a door with a direction
	 * @param direction the direction of the door
	 * @param door the door selected
	 */
	public void setDirection(Direction direction, Door door) {
        this.doors.put(direction, door);
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

	/**
	 * @return display of the room
	 */
	@Override
	public String toString() {
		return "R";
	}

}
