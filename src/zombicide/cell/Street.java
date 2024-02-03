package zombicide.cell;

import zombicide.util.Direction;

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
	public String toString(int ligne) {
		if(ligne ==0) {
			return "     ";
		}
		else if(ligne ==1){
			return " "+"\u001B[33m" + "R"+ "\u001B[0m"+"z0 ";
		}
		else if(ligne ==2){
			return " s0  ";
		}
		else if(ligne ==3){
			return "     ";
		}
		else {
			return "";
		}
		
		
	}
}
