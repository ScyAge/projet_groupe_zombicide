package zombicide.actor.actionPlayer;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;

/**
 * interface Action Player
 */
public interface ActionsPlayer {
	
	/**
	 * allowed a player to perform an action
	 * @param p player who use  the action
	 */
	public void action(Player p);

	/**
	 * verify if the player can use this action
	 * @param p the player tested
	 * @return boolean the response
	 */
	public boolean IsActionPlayable(Player p);
	
}
