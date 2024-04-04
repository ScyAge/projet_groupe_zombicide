package zombicide.actor.actionPlayer;

import java.util.List;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;
import zombicide.cell.Cell;
import zombicide.util.Direction;
import zombicide.util.listchooser.InteractiveListChooser;
import zombicide.util.listchooser.ListChooser;

/**
 * Class Move implementation  of ActionsPlayer
 */
public class Move implements ActionsPlayer {
	
	/**
	 * Builder of Move
	 */
	private Board board;
	private ListChooser<Direction> chooser;

	public Move(Board board,ListChooser<Direction> chooser) {

		this.board = board;
		this.chooser = chooser;
	}
	public Move(Board board){
		this.board = board;
		this.chooser = new InteractiveListChooser<>();
	}
	
	/**
	 * Move a player in a direction 
	 * @param p Player who want to move
	 */
	public void action(Player p) {
		List<Direction> directions= List.of(Direction.North, Direction.South, Direction.East, Direction.West);
		Direction D= this.chooser.choose("choose a direction: ", directions);
		this.board.movePlayer(p, D);
		int actionPoints= p.getAction_points();
		p.setAction_points(actionPoints-1);
	}

	@Override
	public boolean IsActionPlayable(Player p) {
		Cell c = p.getCurrentCell();
		return (c.getDoor(Direction.North).isBreak() ||c.getDoor(Direction.South).isBreak() ||c.getDoor(Direction.East).isBreak() ||c.getDoor(Direction.West).isBreak());
	}
}

