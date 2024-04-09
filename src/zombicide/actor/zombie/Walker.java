package zombicide.actor.zombie;

import zombicide.cell.Cell;

/**
 * class walker
 */
public class Walker extends Zombies {
	/**
	 * Constant lifepoint of walker 
	 * */
	private final static int LIFEPOINT=1;
	
	/**
	 * Constant action point of walker
	 * */
	private final static int ACTION_POINT=1;
	
	/**
	 * Constant damage point of walker 
	 * */
	private final static int DAMAGE_POINT=1;
	
	/**
	 * Builder of the walker 
	 * @param cell where the walker is
	 * @param id of the walker
	 */
	public Walker(Cell cell, int id) {
		super(LIFEPOINT, ACTION_POINT,cell, id ,DAMAGE_POINT);
	}

	@Override
	public String toString() {
		return String.format("Walker have %d life Points",this.lifePoints);
	}
}
