package zombicide.actor.zombie;

import java.util.ArrayList;
import java.util.List;

import zombicide.actor.*;
import zombicide.cell.*;

public  class Zombies extends Actor {
	
	protected int damagePoints;
	

	public Zombies(int lifePoints,int action_points, Cell cell,int damagePoints) {
		super(lifePoints, action_points, cell);
		this.damagePoints = damagePoints ;
	}

	/**
	 * gives the damagepoints of the zombie
	 * @return damagepoints
	 * */
	public int getDamagePoints() {
		return damagePoints;
	}
	
	/**
	 * set the damage points
	 * @param damagepoints
	 */
	public void setDamagePoints(int damagePoints) {
		this.damagePoints = damagePoints;
	}
	
	
	
	/**
	 * gives an information about how many damage points the zombie has
	 * @return damage points
	 * */
	public String toString() {
		return "Zombie have " + this.damagePoints+"damage Points";
	}
	
	/** gives the list of zombies located in the same cell
	 * @param the cell where the zombies are
	 * @return players in the same cell
	 * */
	public List<Zombies> getZombiesInArea(Cell cell){
		List<Zombies> zombies= new ArrayList<>();
		
		if(this.getCurrentCell()==cell) {
			zombies.add(this);
		}
		return zombies;
	}
	
}
