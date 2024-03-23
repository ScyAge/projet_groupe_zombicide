package zombicide.actor.action;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;

public interface Actions {
	
	
	public void action(Player p);
	
	public void action(Zombies z);
}
