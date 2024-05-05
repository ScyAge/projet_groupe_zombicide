package zombicide.actor.actionPlayer;

import java.util.ArrayList;
import java.util.List;

import zombicide.actor.player.Player;
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
	private final Board board;
	private final ListChooser<Direction> chooser;

	/**
	 * Builder of Move action with list chooser
	 * @param board where move
	 * @param chooser the listchooser of the action
	 */
	public Move(Board board,ListChooser<Direction> chooser) {

		this.board = board;
		this.chooser = chooser;
	}
	
	/**
	 * Builder of Move action
	 * @param board where move
	 */
	public Move(Board board){
		this.board = board;
		this.chooser = new InteractiveListChooser<>();
	}

	/**
	 *method used to fill in a list of directions in which the player will be able to move
	 * @param player the player who execute the action
	 * @return
	 */
	private List<Direction> DirectionToMove(Player player){
		List<Direction> directionCanMove = new ArrayList<>();
		for(Direction direct : Direction.values()) {
			if(this.board.canMove(player,direct)){
				directionCanMove.add(direct);
			}
		}
		return directionCanMove;
	}
	
	/**
	 * Move a player in a direction 
	 * @param player Player who want to move
	 */
	public void action(Player player) {
		System.out.println("La move action va être éxecuter");
		List<Direction> directions=  DirectionToMove(player);
		Direction D= this.chooser.choose("choose a direction: ", directions);
		this.board.movePlayer(player, D);
		int actionPoints= player.getAction_points();
		player.setAction_points(actionPoints-1);
	}

	@Override
	public boolean IsActionPlayable(Player player) {
		List<Boolean> directionCanMove = new ArrayList<>();
		for(Direction direct : Direction.values()) {
			directionCanMove.add(this.board.canMove(player,direct));
		}
		return directionCanMove.contains(true);
	}

	@Override
	public String toString() {
		return "Move action";
	}
}

