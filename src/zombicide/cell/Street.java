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

	/**
	 * takes an item as a parameter and deletes it, because Street has no place to place items , so the function do nothing
	 * @param i the item you want to del
	 */
	public void addItem(Item i){}

	@Override
	public Item removeItem(Item i) {
		return null;
	}

	/**
	 * @return display of the Street
	 */
	@Override
	public String toString() {
		return colorYellow + "S" + colorWhite;
		}
		
		
}
