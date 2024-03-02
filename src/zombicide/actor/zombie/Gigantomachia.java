package zombicide.actor.zombie;

import zombicide.cell.Cell;

/**
 * class Gigantomachia
 */
public class Gigantomachia extends Zombies {

	/**
	 * Builder of the Gigantomachia 
	 * @param cell where the Gigantomachia is
	 * @param id of the Gigantomachia
	 */
	public Gigantomachia(Cell cell,int id) {
		super(1000,100,cell,id, 1);

	}

}
