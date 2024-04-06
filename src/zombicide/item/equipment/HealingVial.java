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
	public HealingVial(String title, boolean breakDoor) {
		super(title, breakDoor);
	}
	
	/**
	 * adds one lifepoint to the players who use it
	 * @param player to give lifePoints
	 */
	public void ItemEffect(Player player) {
		super.ItemEffect(player);
		player.setLifePoints(1);
	}

}
