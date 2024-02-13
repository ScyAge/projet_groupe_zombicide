package zombicide.cell;




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
    }

	/**
	 * @return display of the Street
	 */
	@Override
	public String toString() {
		return"\u001B[33m" + "S" + "\u001B[0m";
		}
		
		
}
