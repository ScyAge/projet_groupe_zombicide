package zombicide.util;

import java.util.*;

public class Door {

	private boolean Breakable;
	
	private boolean Break; 
	
	private Direction direction_door;
	private Map<Direction,String> direction_affichage;
	/**
	 * Door Builder
	 * @param Breakable if a door is unbreakable or not 
	 */
	public Door(boolean Breakable,Direction direction_door) {
		this.Break = false;
		this.Breakable = Breakable;
		this.direction_door = direction_door;
		this.direction_affichage = new HashMap<>();
		this.direction_affichage.put(Direction.North, "___");
		this.direction_affichage.put(Direction.South, "___");
		this.direction_affichage.put(Direction.East, "|\n|\n|");
		this.direction_affichage.put(Direction.West, "|\n|\n|");
	}
	
	/**
	 * @return true if we can break the door and else false
	 */
	public boolean isBreakable() {
		return Breakable;
	}
	/**
	 * @return true if the door is break and else false
	 */
	public boolean isBreak() {
		return Break;
	}

	/**
	 * Break a door if we can 
	 */
	public void Break() {
		if(this.isBreakable()) {
			Break = true;
		}
	}

	@Override
	public String toString() {
		if(this.isBreak()) {
			return "";
		}
		return this.direction_affichage.get(this.direction_door);
	}
	
	
} 


