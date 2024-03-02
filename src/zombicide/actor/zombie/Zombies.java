package zombicide.actor.zombie;

import java.util.ArrayList;
import java.util.List;

import zombicide.actor.*;
import zombicide.cell.*;

/**
 * Class Zombies
 */
public  class Zombies extends Actor {
	
	/** domage of the zombies*/
	protected int damagePoints;
	
	/**
	 * Builder of Zombies
	 * @param lifePoints of the Zombie
	 * @param action_points of the Zombie
	 * @param cell where the Zombie is
	 * @param id of the Zombie
	 * @param damagePoints of the Zombie
	 */
	public Zombies(int lifePoints,int action_points, Cell cell, int id, int damagePoints) {
		super(lifePoints, action_points, cell, id);
		this.damagePoints = damagePoints ;
	}

	/**
	 * gives the damagePoints of the Zombies
	 * @return damagePoints
	 * */
	public int getDamagePoints() {
		return damagePoints;
	}
	
	/**
	 * set the damage points
	 * @param damagePoints of the zombies
	 */
	public void setDamagePoints(int damagePoints) {
		this.damagePoints = damagePoints;
	}
	
	
	
	/**
	 * gives an information about how many damage points the zombie has
	 * @return Affichage of Zombies
	 * */
	public String toString() {
		return "Zombie have " + this.damagePoints+"damage Points";
	}
	
	
	/** gives the list of zombies located in the same cell
	 * @param cell where the zombies are
	 * @return Zombies in the same cell
	 * */
	public List<Zombies> getZombiesInArea(Cell cell){
		List<Zombies> zombies= new ArrayList<>();
		
		if(this.getCurrentCell()==cell) {
			zombies.add(this);
		}
		return zombies;
	}
	
}
