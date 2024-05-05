package zombicide.util;


/**
 * class of Door
 */
public abstract class Door {

	/**Param Door*/
	private boolean Breakable;
	private boolean Break; 
	
	/**
	 * Door Builder
	 * @param Breakable if a door is unbreakable or not 
	 * @param Break if it's break or not
	 */
	public Door(boolean Breakable,boolean Break){
		this.Break = Break;
		this.Breakable = Breakable;
	}
	
	/**
	 * set door unbreakble
	 */
	public void SetNotBreakble() {
		this.Breakable = false;
	}
	
	/**
	 * return true if we can break the door and else false
	 * @return true if we can break the door and else false
	 */
	public boolean isBreakable() {
		return this.Breakable;
	}
	
	/**
	 * return true if the door is break and else false
	 * @return true if the door is break and else false
	 */
	public boolean isBreak() {
		return this.Break;
	}

	/**
	 * Break a door if we can 
	 */
	public void Break() {
		if(this.isBreakable()) {
			this.Break = true;
		}
	}
	
	/**
	 * Close the door
	 */
	public void setNotBreak() {
		this.Break = false;
	}

	@Override
	public abstract String toString();
		
	
	
	
} 


