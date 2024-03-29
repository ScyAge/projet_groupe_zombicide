package zombicide.actor.actionPlayer;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.item.Item;

/**
 * Class OpenDoor implementation  of ActionsPlayer
 */
public class OpenDoor implements ActionsPlayer {
	
	/**
	 * Builder of OpenDoor
	 */
	public OpenDoor() {
	}
	
	@Override 
	public void action(Player p) {
		Item item= p.getItemInHand();
		
	}
	
	

}
