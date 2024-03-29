package zombicide.actor.actionPlayer;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;

/**
 * interface Action Player
 */
public interface ActionsPlayer {
	
	/**
	 * Player actions
	 * @param p player who use  the action
	 */
	public void action(Player p);
	
	
}
