package zombicide.util.door;
import zombicide.util.Door;

public class East_west_door extends Door{
	
	public East_west_door(boolean breakable) {
		super(breakable);
	}
	
	public String toString(){
		if(this.isBreak()) {
			return " ";
		}
		return "|";
	}
}
