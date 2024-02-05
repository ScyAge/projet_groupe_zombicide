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
			if(this.getAllZombies().size()==0) {
				return this.getDoor(Direction.East).toString()+"\u001B[32m" +"C"+ "\u001B[0m"+"  "+this.getDoor(Direction.West).toString();
			}
			else {
				return this.getDoor(Direction.East).toString()+"\u001B[31m" +"C"+ "\u001B[0m"+"z"+this.getAllZombies().size()+this.getDoor(Direction.West).toString();
			}
		}
		else if(ligne ==2){
			if(this.getAllPlayers().size()==0) {
				return this.getDoor(Direction.East).toString()+"   "+this.getDoor(Direction.West).toString();
			}
			else {
				return this.getDoor(Direction.East).toString()+"s"+this.getAllPlayers().size()+" "+this.getDoor(Direction.West).toString();
			}
		}
		else if(ligne ==3){
			return this.getDoor(Direction.South).toString();
		}
		else {
			return "";
		}
	}
	
}
