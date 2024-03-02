package zombicide.actor.zombie;

import zombicide.cell.Cell;

/**
 * class Runner
 */
public class Runner extends Zombies{

	/**
	 * Builder of the Runner
	 * @param cell where the Runner is
	 * @param id of the Runner
	 */
	public Runner(Cell cell, int id) {
		super(2,1,cell, id,2);

	}

}
