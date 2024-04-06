package zombicide.cell;

import zombicide.item.*;
import zombicide.util.*;
import zombicide.util.door.*;

/**
 * class of Room
 */
public class Room extends Cell {
	
	/** Constructor for the Class Room
	 * @param x coordinates of the cell
	 * @param y coordinates of the cell
	 */
	public Room(int x, int y) {
		super(x, y);
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
	 * adds an item to the list
	 * @param  i Item,
	 */
	public void addItem(Item i) {
		this.items.add(i) ;
	}
	
	/** 
	 * removes the given item from the list of items
	 * @param i Item 
	 * @return the item
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
