package zombicide.actor.action;

import java.util.List;

import zombicide.actor.player.Player;
import zombicide.board.Board;
import zombicide.util.Direction;
import zombicide.util.listchooser.InteractiveListChooser;
import zombicide.util.listchooser.ListChooser;

/**
 * Class Move
 */
public class Move implements Actions {
	
	/**
	 * Builder of Move
	 */
	public Move() {
	}
	
	
	
	public void action(Player p, Board b) {
		System.out.println("enter the Direction to move");
		List<Direction> directions= List.of(Direction.North, Direction.South, Direction.East, Direction.West);
		ListChooser<Direction> listChooser = new InteractiveListChooser<>();
		Direction D= listChooser.choose("choose a direction: ", directions);
		b.movePlayer(p, D);
	}
}

