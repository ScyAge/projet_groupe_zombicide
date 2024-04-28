package zombicide.item.equipment;

import zombicide.actor.player.Player;

/**
 * class HealingVial
 */
public class HealingVial extends Equipment{

	/**
	 * Builder of HealingVial
	 * @param title of the item
	 */
	public HealingVial(String title) {
		super(title, false);
	}
	
	/**
	 * adds one lifepoint to the players who use it
	 * @param player to give lifePoints
	 */
	public void effectOfTheEquip(Player player) {
		player.setLifePoints(1);
	}

}
