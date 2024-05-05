package zombicide.item.equipment;

import zombicide.actor.player.Player;

/**
 * class HealingVial
 */
public class HealingVial extends Equipment{

	/**
	 * Builder of HealingVial
	 */
	public HealingVial() {
		super(false);
	}
	
	/**
	 * adds one lifepoint to the players who use it
	 * @param player to give lifePoints
	 */
	public void effectOfTheEquip(Player player) {
		player.setLifePoints(1);
	}
	
	@Override
	public String itemDescription() {
		String res= ("gain a lifePoint");
		return res;
	}

}
