package cell;

public class Street extends Cell{
	
	/** Constructor of the class Street
	 * @param x coordinates of the cell
	 * @param y coordinates of the cell
	 * @param type the type of the cell 
	 */
	public Street(int x, int y) {
		super(x, y);
	}

	@Override
	public String toString() {
		return "\u001B[33m" + "S" + "\u001B[0m"; 
		
	}
}
