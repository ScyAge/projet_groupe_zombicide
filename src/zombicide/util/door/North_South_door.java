package zombicide.util.door;
import zombicide.util.Door;
/**
 * class for North_South_door
 */
public class North_South_door extends Door{
	
	/**
	 * Contructor of North_South_door
	 * @param breakable true if the door is breakable
	 */
	public North_South_door(boolean breakable, boolean Break) {
		super(breakable,Break);
	}
	
	/**
	 * return representation of string for the graphic
	 * @return representation of string for the graphic
	 */
	public String toString(){
		if(this.isBreak()) {
			return "     ";
		}
		return "-----";
	}
}
