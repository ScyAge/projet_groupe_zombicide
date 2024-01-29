package util;

public class Door {

	private boolean Breakable;
	
	private boolean Break; 
	
	/**
	 * Door Builder
	 * @param Breakable if a door is unbreakable or not 
	 */
	public Door(boolean Breakable) {
		this.Break = false;
		this.Breakable = Breakable;
		
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
	
}
