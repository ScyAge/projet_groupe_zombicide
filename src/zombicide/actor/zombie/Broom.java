package zombicide.actor.zombie;

import zombicide.cell.Cell;

/**
 * class Broom
 */
public class Broom extends Zombie {
	/**
	 * constant lifepoint of the Broom
	 * */
	private final static int LIFEPOINT=4;
	
	/**
	 * constant action point of the Broom
	 * */
	private final static int ACTION_POINT=1;
	
	/**
	 * constant damage point of the Broom
	 * */
	private final static int DAMAGE_POINT=2;
	
	/**
	 * Builder of the Broom 
	 * @param cell where the Broom is
	 * @param id of the Broom
	 */
	public Broom(Cell cell, int id) {
		super(LIFEPOINT, ACTION_POINT,cell,id, DAMAGE_POINT);
	}


	
	
	public void takeDamage(int damage) {
		if(damage > 1) {
			super.takeDamage(damage);
		}
	}
	

}
