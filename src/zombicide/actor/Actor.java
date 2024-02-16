package zombicide.actor;

import java.util.List;

import zombicide.cell.Cell;

public abstract class Actor {
	
	/**
	 * 
	 */
	protected int lifePoints;
	protected int action_points; 
	protected Cell cell;
	
	public Actor(int lifePoints, int action_points, Cell cell){
		this.lifePoints = lifePoints;
		this.action_points = action_points;
		this.cell=cell;
	}
	
	
	/**
	 * get Heal point of the actor
	 * @return the lifePoints
	 */
	public int getLifePoints() {
		return lifePoints;
	}
	
	/**
	 * Set heal point to the actor
	 * @param lifePoints of the actor
	 */
	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}
	
	/**
	 * @return true if the actor have 0 lifePoints
	 */
	public boolean isDead() {
		return this.lifePoints == 0;
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
	
	
	/**
	 * gives the cell where the actor is located
	 * @return the cell where the actor is.
	 * */
	public Cell getCurrentCell() {
		return this.cell;
	}
}
