package zombicide.actor.zombie;

import zombicide.cell.Cell;

/**
 * class Abominations
 */
public class Abominations extends Zombies {

	/**
	 * Builder of the Abominations 
	 * @param cell where the Abominations is
	 * @param id of the Abominations
	 */
	public Abominations(Cell cell, int id) {
		super(6, 3,cell,id, 1);
	}

}
