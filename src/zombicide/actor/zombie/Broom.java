package zombicide.actor.zombie;

import zombicide.cell.Cell;

/**
 * class Broom
 */
public class Broom extends Zombies {

	/**
	 * Builder of the Broom 
	 * @param cell where the Broom is
	 * @param id of the Broom
	 */
	public Broom(Cell cell, int id) {
		super(4,2,cell,id, 1);
	}

}
