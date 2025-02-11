package zombicide.cell;

import zombicide.actor.zombie.Zombie;

/**
 * class of Sewer
 */
public class Sewer extends Street{
	
	
	/** Constructor of the class Sewer
	 * @param x coordinates of the cell
	 * @param y coordinates of the cell
	 */
	public Sewer(int x, int y) {
		super(x, y);
	}
	 
	/** 
	 * generate x zombies in this cell 
	 * @param nb the number of zombies 
	 * @param Z type of Zombie to spawn
	 */ 
	public void ProductionZombie(int nb, Zombie Z) {
		for (int i = 0; i < nb; i++) {
            this.addZombies(Z);
		}
	}

	
	/**
	 * @return display of the Sewer
	 */
	@Override
	public String toString() {
		return colorYellow + "E" + colorWhite;
		
	}

}
