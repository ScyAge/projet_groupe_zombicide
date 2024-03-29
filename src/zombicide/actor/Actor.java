package zombicide.actor;


import zombicide.cell.Cell;

/**
 * abstrac class actor
 */
public abstract class Actor {
	
	/**
	 * Param of Actor
	 */
	/** maxLifePoint of the Actor*/
	protected  int maxLifePoint;
		/** lifePoint of the Actor*/
	protected int lifePoints;
		/**action_point of the Actor */
	protected int action_points; 
		/** cell where the Actor is*/
	protected Cell cell;
		/** if the Actor is dead or not*/
	protected boolean isDead;
		/** id of the Actor*/
	protected int id;
	
	/**
	 * Builder of Actor
	 * @param lifePoints of the Actor
	 * @param action_points of the Actor
	 * @param cell where the Actor is
	 * @param id of the Actor
	 */
	public Actor(int lifePoints, int action_points, Cell cell, int id){
		this.lifePoints = lifePoints;
		this.maxLifePoint = lifePoints;
		this.action_points = action_points;
		this.cell=cell;
		this.isDead =false;
		this.id = id;
	}
	
	
	/**
	 * get Heal point of the actor
	 * @return the lifePoints
	 */
	public int getLifePoints() {
		return this.lifePoints;
	}

	/**
	 * get max life point of the actor
	 * @return max life point of the actor
	 */
	public int getMaxLifePoint(){
		return this.maxLifePoint;
	}
	
	/**
	 * Set heal point to the actor
	 * @param lifePoints of the actor
	 */
	public void setLifePoints(int lifePoints) {
		if(this.lifePoints + lifePoints > this.maxLifePoint){
			this.lifePoints = this.maxLifePoint;
		}
		else {
			this.lifePoints += lifePoints;
		}
	}
	
	abstract public void Dead();
	/**
	 * Get if the Player is dead or not
	 *@return true if the actor have 0 lifePoints
	 */
	public boolean isDead() {
		return this.isDead;
	}
	
	/**
	 * actor take damage
	 * @param damage taken by the actor
	 */
	public void takeDamage(int damage) {

		this.lifePoints -= damage;
		if(this.lifePoints <= 0){
			this.isDead = true;
		}
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
	 * @param action_points of the actor
	 */
	public void setAction_points(int action_points) {
		this.action_points = action_points;
	}
	
	
	/**
	 * gives the cell where the actor is located
	 * @return the cell where the actor is.
	 * */
	public Cell getCurrentCell() {
		return this.cell;
	}
	
	/**
	 *Set a cell
	 *@param cell where the Actor is
	 */
	public void setCell(Cell cell) {
		this.cell = cell;
	}
	
	/**
	 * gets the identificator of the actor
	 * @return id */
	public int getId() {
		return this.id;
	}
}
