package zombicide.cell;

import zombicide.util.Direction;

public class Continental extends Room {

	public Continental(int x, int y) {
		super(x, y);
	}
	
	@Override
	public boolean canLook() {
		return false;
	}

	
		public String toString(int ligne) {
		if(ligne ==0) {
			return this.getDoor(Direction.North).toString();
		}
		else if(ligne ==1){
			return this.getDoor(Direction.East).toString()+"Cz0"+this.getDoor(Direction.West).toString();
		}
		else if(ligne ==2){
			return this.getDoor(Direction.East).toString()+"s0 "+this.getDoor(Direction.West).toString();
		}
		else if(ligne ==3){
			return this.getDoor(Direction.South).toString();
		}
		else {
			return "";
		}
	}
	
}
