package zombicide.item.equipment;

import zombicide.actor.player.Player;
import zombicide.item.Item;

public class HealingVial extends Item {

	public HealingVial(String title) {
		super(title);
	}
	
	/**
	 * adds one lifepoint to the players who uses it 
	 */
	public void equipmentEfffect(Player player) {
		int lifepoints= player.getLifePoints();
		player.setLifePoints(lifepoints+1);
	}

}
