package zombicide.util.door;
import zombicide.util.Door;

/**
 * class for East_west_door
 */
public class East_west_door extends Door{
	
	/**
	 * Contructor of East_west_door
	 * @param breakable true if the door is breakable
	 * @param Break if it's break or not
	 */
	public East_west_door(boolean breakable, boolean Break) {
		super(breakable,Break);
	}
	
	/**
	 * return representation of string for the graphic
	 * @return representation of string for the graphic
	 */
	public String toString(){
		if(this.isBreak()) {
			return " ";
		}
		return "|";
	}
}
