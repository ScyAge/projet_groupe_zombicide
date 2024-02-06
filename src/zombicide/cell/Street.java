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

	/**
	 * @param ligne ligne to display
	 * @return display of the Street
	 */
	@Override
	public String toString(int ligne) {
		if(ligne ==0) {
			return "     ";
		}
		else if(ligne ==1){
			if(this.getAllZombies().size()==0) {
				return " "+"\u001B[33m" + "S" + "\u001B[0m"+"   ";
			}
			else {
				return " "+"\u001B[33m" + "S" + "\u001B[0m"+"s"+this.getAllZombies()+"  ";
			}
		}
		else if(ligne ==2){
			if(this.getAllPlayers().size()==0) {
				return "     ";
			}
			else {
				return " s"+this.getAllPlayers().size()+"  ";
			}
		}
		else if(ligne ==3){
			return "     ";
		}
		else {
			return "";
		}
		
		
	}
}
