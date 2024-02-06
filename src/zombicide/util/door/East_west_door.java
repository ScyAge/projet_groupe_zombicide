package zombicide.util.door;
import zombicide.util.Door;

/**
 * class for East_west_door
 */
public class East_west_door extends Door{
	
	/**
	 * Contructor of East_west_door
	 * @param breakable true if the door is breakable
	 */
	public East_west_door(boolean breakable) {
		super(breakable);
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
