package zombicide.cell;

import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;

/**
 * class of Sewer
 */
public class Sewer extends Street{
	
	private Board b ;
	
	/** Constructor of the class Sewer
	 * @param x coordinates of the cell
	 * @param y coordinates of the cell
	 */
	public Sewer(int x, int y,Board b ) {
		super(x, y);
		this.b = b ;
	}
	 
	/** 
	 * generate x zombies in this cell 
	 * @param nb the number of zombies 
	 * @param Z type of Zombies to spawn 
	 */ 
	public void ProductionZombie(int nb,Zombies Z) {
		for (int i = 0; i < nb; i++) {
            this.addZombies(Z);
            this.b.addZombieList(Z);
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
