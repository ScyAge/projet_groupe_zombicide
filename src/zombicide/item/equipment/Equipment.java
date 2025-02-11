package zombicide.item.equipment;

import zombicide.item.*;
import zombicide.actor.player.*;
import zombicide.cell.Cell;

/**
 * class Equipment
 */
public abstract class  Equipment extends Item {

	
	/**
	 * Builder of Equipment
	 * @param noisy if it's noisy or not
	 */
	public Equipment(boolean noisy) {
		super( false,false,noisy);
	}

	/**
	 * realizes the equipment effect 
	 * @param player who uses the equipment 
	 * */
	public void ItemEffect(Player player) {
		this.effectOfTheEquip(player);
		this.Used();
		if(this.isNoisy()){
			Cell c = player.getCurrentCell();
			c.setNoise(c.getNoise()+1);
		}
	}

	/**
	 * method used to trigger the specific effect of a piece of equipment
	 * @param p the player who use it
	 */
	protected abstract void effectOfTheEquip(Player p);
	
	
	

}
