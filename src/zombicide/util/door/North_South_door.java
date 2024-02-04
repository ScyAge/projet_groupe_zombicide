package zombicide.util.door;
import zombicide.util.Door;
public class North_South_door extends Door{
	
	public North_South_door(boolean breakable) {
		super(breakable);
	}
	
	public String toString(){
		if(this.isBreak()) {
			return "     ";
		}
		return "-----";
	}
}
