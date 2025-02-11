package zombicide.actor.zombie;

import zombicide.cell.Cell;

/**
 * class Runner
 */
public class Runner extends Zombie {
	/**
	 * constant lifepoint of runner 
	 * */
	private final static int LIFEPOINT=2;
	
	/**
	 * constant action point of the runner
	 * */
	private final static int ACTION_POINT=2;
	
	/**
	 * constant damage pojnt of the damage point 
	 * */
	
	private final static int DAMAGE_POINT=1;
	
	/**
	 * Builder of the Runner
	 * @param cell where the Runner is
	 * @param id of the Runner
	 */
	public Runner(Cell cell, int id) {
		super(LIFEPOINT, ACTION_POINT,cell, id, DAMAGE_POINT);

	}

}
