package zombicide.actor.actionZombie;

import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;
import zombicide.cell.Cell;
import zombicide.util.Direction;

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
	
	private Direction choiceDirectionNoise(Zombies z) {
		Direction d = null;
		Cell c = choiceCellNoise(); 
		if(z.getCurrentCell().getX()> c.getX() && this.board.getCellBoard(z.getCurrentCell().getX()-1,z.getCurrentCell().getY()).getDoor(Direction.South).isBreak()) {
			d = Direction.North;
		}
		else if(z.getCurrentCell().getX()< c.getX() && this.board.getCellBoard(z.getCurrentCell().getX()+1,z.getCurrentCell().getY()).getDoor(Direction.North).isBreak()) {
			d = Direction.South;
		}
		else if(z.getCurrentCell().getY()< c.getY() && this.board.getCellBoard(z.getCurrentCell().getX(),z.getCurrentCell().getY()+1).getDoor(Direction.West).isBreak()) {
			d = Direction.East;
		}
		else if(z.getCurrentCell().getY()> c.getY() && this.board.getCellBoard(z.getCurrentCell().getX(),z.getCurrentCell().getY()-1).getDoor(Direction.East).isBreak()) {
			d = Direction.West;
		}
		return d;
	}
	
	/**
	 * Move a zombies  in a direction 
	 * @param z zombies who want to move
	 */
	public void action(Zombies z) {
		Direction d = choiceDirectionNoise(z);
		if(d != null) {
			this.board.moveZombie(z, d);
		}
		int actionPoints= z.getAction_points();
		z.setAction_points(actionPoints-1);
	}

	@Override
	public boolean IsActionPlayable(Zombies z) {
		Cell c = z.getCurrentCell();
		return (c.getDoor(Direction.North).isBreak() ||c.getDoor(Direction.South).isBreak() ||c.getDoor(Direction.East).isBreak() ||c.getDoor(Direction.West).isBreak());
	}
}
