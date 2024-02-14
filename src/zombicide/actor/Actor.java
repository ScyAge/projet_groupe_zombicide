package zombicide.actor;

public abstract class Actor {
	
	/**
	 * 
	 */
	protected int hp;
	protected int dgt;
	protected int action_points; 
	
	public Actor(int hp, int dgt, int action_points){
		this.hp = hp;
		this.dgt = dgt;
		this.action_points = action_points;
	}
	
	
	/**
	 * get Heal point of the actor
	 * @return the hp
	 */
	public int getHp() {
		return hp;
	}
	
	/**
	 * Set heal point to the actor
	 * @param hp of the actor
	 */
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	/**
	 *  get dommage of the actor
	 * @return the dgt of the actor
	 */
	public int getDgt() {
		return dgt;
	}
	
	/**
	 * Set Dommage to the actor
	 * @param dgt new dgt of the cator
	 */
	public void setDgt(int dgt) {
		this.dgt = dgt;
	}
	/**
	 * get Action point of the actor 
	 * @return action points of the actor 
	 */
	public int getAction_points() {
		return action_points;
	}
	/**
	 * Set action point to the actor
	 * @param action_point of the actor
	 */
	public void setAction_points(int action_points) {
		this.action_points = action_points;
	}
}
