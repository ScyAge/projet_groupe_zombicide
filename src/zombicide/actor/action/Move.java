package zombicide.actor.action;

import zombicide.actor.player.Player;
import zombicide.board.Board;
import zombicide.util.Direction;

/**
 * Class Move
 */
public class Move implements Actions {
	
	private Direction direction;
	
	/**
	 * Builder of Move
	 */
	public Move(Direction d) {
		this.direction =d;
	}
	
	
	
	public void action(Player p, Board b) {
		b.movePlayer(p, this.direction);
	}
}

