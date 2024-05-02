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
	
	private List<Direction> DirectionToMove(Player player) {
		List<Direction> d = new ArrayList<>();
		Cell c= player.getCurrentCell();
		for(Direction D : Direction.values()) {
			if( D == Direction.West) {
				if(c.getY()>0 &&  this.board.getCellBoard(c.getX(), c.getY()-1).getDoor(Direction.East).isBreak()&&this.board.getCellBoard(c.getX(), c.getY()).getDoor(D).isBreak()){
					d.add(D);
				}
			}
			if( D == Direction.East) {
				if(c.getY()<this.board.getBoard()[0].length-1&&  this.board.getCellBoard(c.getX(), c.getY()+1).getDoor(Direction.West).isBreak()&&this.board.getCellBoard(c.getX(), c.getY()).getDoor(D).isBreak()){
					d.add(D);
				}
			}
			if( D == Direction.South) {
				if(c.getX() < this.board.getBoard().length-1 && this.board.getCellBoard(c.getX()+1, c.getY()).getDoor(Direction.North).isBreak() && this.board.getCellBoard(c.getX(), c.getY()).getDoor(D).isBreak()){
					d.add(D);
				}

			}
			if( D == Direction.North ) {
				if(c.getX()>0 &&  this.board.getCellBoard(c.getX()-1, c.getY()).getDoor(Direction.South).isBreak()&&this.board.getCellBoard(c.getX(), c.getY()).getDoor(D).isBreak() ){
					d.add(D);
				}
			}
		}
		return d;
	}
	
	/**
	 * Move a player in a direction 
	 * @param p Player who want to move
	 */
	public void action(Player p) {
		System.out.println("La move action va être éxecuter");
		List<Direction> directions=  DirectionToMove(p);
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

	@Override
	public String toString() {
		return "Move action";
	}
}

