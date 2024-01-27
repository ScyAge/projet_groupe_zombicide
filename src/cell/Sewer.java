package cell;

import actor.zombie.Zombies;

public class Sewer extends Street{
	
	/** Constructor of the class Room
	 * @param x coordinates of the cell
	 * @param y coordinates of the cell
	 * @param type the type of the cell 
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
	
	@Override
	public String toString() {
		return "Se";
	}

}
