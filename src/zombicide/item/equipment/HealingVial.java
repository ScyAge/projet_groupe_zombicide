package zombicide.item.equipment;

import zombicide.actor.player.Player;
import zombicide.item.Item;

/**
 * class HealingVial
 */
public class HealingVial extends Equipment{

	/**
	 * Builder of HealingVial
	 * @param title of the item
	 */
	public HealingVial(String title) {
		super(title);
	}
	
	/**
	 * adds one lifepoint to the players who uses it 
	 * @param player to give lifePoints
	 */
	public void equipmentEffect(Player player) {
		super.equipmentEffect(player);
		int lifepoints= player.getLifePoints();
		player.setLifePoints(lifepoints+1);
	}

	/**
	 * return true if the equipment is noisy else false
	 * @return true if the equipment is noisy else false
	 */
	public boolean isNoisy() {
		return false;
	}

}
