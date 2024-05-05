package zombicide.actor.actionZombie;

import java.util.ArrayList;
import java.util.List;

import zombicide.actor.zombie.Zombie;
import zombicide.board.Board;
import zombicide.cell.Cell;
import zombicide.util.Direction;
import zombicide.util.listchooser.ListChooser;
import zombicide.util.listchooser.RandomListChooser;

/**
 * Class Move implementation  of ActionZombie
 */
public class MoveZ implements ActionZombie {

	private final Board board;

	/**
	 * Builder of MoveZ
	 * @param board the board to move
	 */
	public MoveZ(Board board){
		this.board = board;
	}


	/**
	 * method that returns the noisiest cell from the board
	 * @return the cell
	 */
	private Cell choiceCellNoise() {
		int NoiseMax = -1 ;
		Cell res =null;
		for(int i = 0;i<this.board.getBoard().length;i++) {
			for(int j =0 ; j< this.board.getBoard()[0].length;j++) {
				if(this.board.getBoard()[i][j].getNoise() > NoiseMax) {
					NoiseMax = this.board.getBoard()[i][j].getNoise();
					res = this.board.getBoard()[i][j];
				}
			}
		}
		return res;
	}

	/**
	 * a method that returns a list of Direction the zombie can take to get to the noisiest cell
	 * @param z the  zombie
	 * @return Directions
	 */
	private List<Direction> choiceDirectionNoise(Zombie z) {
		List<Direction> d = new ArrayList<>();
		Cell c = choiceCellNoise(); 
		if(z.getCurrentCell().getX()> c.getX() && this.board.canMove(z, Direction.North)) {
			d.add(Direction.North) ;
		}
		else if(z.getCurrentCell().getX()< c.getX() && this.board.canMove(z, Direction.South)) {
			d.add(Direction.South);
		}
		else if(z.getCurrentCell().getY()< c.getY() && this.board.canMove(z, Direction.East)) {
			d.add(Direction.East);
		}
		else if(z.getCurrentCell().getY()> c.getY() && this.board.canMove(z, Direction.West)) {
			d.add(Direction.West);
		}
		return d;
	}
	
	/**
	 * Move a zombies  in a direction 
	 * @param z zombies who want to move
	 */
	public void action(Zombie z) {
		ListChooser<Direction> l = new RandomListChooser<Direction>();
		Direction d = l.choose("choose the direction", this.choiceDirectionNoise(z));
		int actionPoints= z.getAction_points();
		z.setAction_points(actionPoints-1);
		if(d != null) {
			this.board.moveZombie(z, d);
		}
	}

	@Override
	public boolean IsActionPlayable(Zombie zombie) {
		List<Boolean> directionCanMove = new ArrayList<>();
		for(Direction direct : Direction.values()) {
			directionCanMove.add(this.board.canMove(zombie,direct));
		}
		return directionCanMove.contains(true);
	}
}
