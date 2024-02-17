package zombicide.cell;
import zombicide.item.*;



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

	public void addItem(Item i){

	}

	/**
	 * @return display of the Street
	 */
	@Override
	public String toString() {
		return colorYellow + "S" + colorWhite;
		}
		
		
}
