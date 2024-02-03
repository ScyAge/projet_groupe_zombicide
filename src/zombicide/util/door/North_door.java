package zombicide.util.door;
import zombicide.util.Door;
public class North_door extends Door{
	
	public North_door(boolean breakable) {
		super(breakable);
	}
	
	public String toString(){
		if(this.isBreak()) {
			return "   ";
		}
		return "---";
	}
}
