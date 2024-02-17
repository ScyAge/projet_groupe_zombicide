package zombicide.item;

import zombicide.actor.player.Player;

public class HealingVial extends Item {

	public HealingVial(String title) {
		super(title);
	}
	
	/**
	 * */
	public void equipmentEfffect(Player player) {
		int lifepoints= player.getLifePoints();
		player.setLifePoints(lifepoints+1);
	}

}
