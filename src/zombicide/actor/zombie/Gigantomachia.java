package zombicide.actor.zombie;

import zombicide.cell.Cell;

/**
 * class Gigantomachia
 */
public class Gigantomachia extends Zombies {
	/**
	 * constant lifepoint of Gigantomachia
	 * */
	private final static int LIFEPOINT=1000;
	
	/**
	 * constant action point of Gigantomachia
	 * */
	private final static int ACTION_POINT=100;
	
	/**
	 * constant damage point of Gigantomachia
	 * */
	private final static int DAMAGE_POINT=1000;
	
	/**
	 * Builder of the Gigantomachia 
	 * @param cell where the Gigantomachia is
	 * @param id of the Gigantomachia
	 */
	public Gigantomachia(Cell cell,int id) {
		super(LIFEPOINT, ACTION_POINT,cell,id, DAMAGE_POINT);

	}

	@Override
	public String toString() {
		return String.format("Gigantomachia have %d damage Points",this.damagePoints);
	}

}
