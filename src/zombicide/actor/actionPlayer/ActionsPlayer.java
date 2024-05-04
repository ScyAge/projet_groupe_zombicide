package zombicide.actor.actionPlayer;

import zombicide.actor.player.Player;

/**
 * interface Action Player
 */
public interface ActionsPlayer {
	
	/**
	 * allowed a player to perform an action
	 * @param p player who use  the action
	 */
    void action(Player p);

	/**
	 * verify if the player can use this action
	 * @param p the player tested
	 * @return boolean the response
	 */
    boolean IsActionPlayable(Player p);
	
}
