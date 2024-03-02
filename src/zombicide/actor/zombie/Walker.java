package zombicide.actor.zombie;

import zombicide.cell.Cell;

/**
 * class walker
 */
public class Walker extends Zombies {
	
	/**
	 * Builder of the walker 
	 * @param cell where the walker is
	 * @param id of the walker
	 */
	public Walker(Cell cell, int id) {
		super(1, 1,cell, id ,1);
	}
	

}
