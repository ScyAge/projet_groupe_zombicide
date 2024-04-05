package zombicide.actor.zombie;

import zombicide.cell.Cell;

/**
 * class Abominations
 */
public class Abominations extends Zombies {
	/**
	 * constant lifepoint of Abominations
	 * */
	private final static int LIFEPOINT=6;
	
	/**
	 * constant action point of Abominations 
	 * */
	private final static int ACTION_POINT=3;
	
	/**
	 * constant damage point of Abominations 
	 * */
	private final static int DAMAGE_POINT=3;
	
	/**
	 * Builder of the Abominations 
	 * @param cell where the Abominations is
	 * @param id of the Abominations
	 */
	public Abominations(Cell cell, int id) {
		super(LIFEPOINT, ACTION_POINT,cell,id, DAMAGE_POINT);
	}

	@Override
	public String toString() {
		return String.format("Abominations have %d damage Points",this.damagePoints);
	}
}
