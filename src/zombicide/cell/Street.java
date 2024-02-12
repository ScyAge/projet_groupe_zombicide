package zombicide.cell;

import zombicide.util.Direction;
import zombicide.util.door.East_west_door;
import zombicide.util.door.North_South_door;

/**
 * class of Street
 */
public class Street extends Cell{
	
	/** Constructor of the class Street
	 * @param x coordinates of the cell
	 * @param y coordinates of the cell
	 */
	public Street(int x, int y) {
		super(x, y);
		for (Direction direction : Direction.values()) {
	        if(direction == Direction.East || direction == Direction.West){
				this.doors.put(direction, new East_west_door(true,true));
			}
			else{
				this.doors.put(direction, new North_South_door(true,true));
			}
		}
    }

	/**
	 * @param ligne ligne to display
	 * @return display of the Street
	 */
	@Override
	public String toString() {
		return"\u001B[33m" + "S" + "\u001B[0m";
		}
		
		
}
