package zombicide.cell;


/**
 * class of Continental
 */
public class Continental extends Room {

	/**
	 * Constructor of the class Continental
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public Continental(int x, int y) {
		super(x, y);
	}
	
	@Override
	/**
	 * method that say if a player can look in the room
	 * @return true if a player can look in the room else false
	 */
	public boolean canLook() {
		return false;
	}

	/**
	 * @return display of the Continental
	 */
	public String toString() {
		return "\u001B[32m" +"C"+ "\u001B[0m";
	}
	
}
