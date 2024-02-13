package zombicide.cell;

import zombicide.actor.zombie.Zombies;

/**
 * class of Sewer
 */
public class Sewer extends Street{
	
	/** Constructor of the class Room
	 * @param x coordinates of the cell
	 * @param y coordinates of the cell
	 */
	public Sewer(int x, int y) {
		super(x, y);
	}
	
	/**
	 * generate x zombies in this cell
	 * @param nb the number of zombies
	 */
	public void ProductionZombie(int nb) {
		for (int i = 0; i < nb; i++) {
			Zombies zombie = new Zombies();
			addZombies(zombie);
		}
	}
	
	/**
	 * @return display of the Sewer
	 */
	@Override
	public String toString() {
		return "\u001B[33m" + "E" + "\u001B[0m";
		
	}

}
