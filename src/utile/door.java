package utile;

public class door {

	private boolean Breakable;
	
	private boolean Break; 
	
	/**
	 * @param Breakable
	 */
	public door(boolean Breakable) {
		this.Break = false;
		this.Breakable = Breakable;
		
	}
	
	/**
	 * @return if we can break the door
	 */
	public boolean isBreakable() {
		return Breakable;
	}
	/**
	 * @return if the door is break or not
	 */
	public boolean isBreak() {
		return Break;
	}

	public void Break() {
		if(this.isBreakable()) {
			Break = true;
		}
	}
	
}
