package zombicide.actor.actionZombie;



import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;
import zombicide.cell.Cell;
import zombicide.util.Direction;
import zombicide.util.listchooser.InteractiveListChooser;
import zombicide.util.listchooser.ListChooser;

/**
 * Class Move implementation  of ActionZombie
 */
public class Move implements ActionZombie{
	
	/**
	 * Builder of Move
	 */
	private final Board board;
	private final ListChooser<Direction> chooser;

	public Move(Board board,ListChooser<Direction> chooser) {

		this.board = board;
		this.chooser = chooser;
	}
	public Move(Board board){
		this.board = board;
		this.chooser = new InteractiveListChooser<>();
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
		Cell c = choiceCellNoise(); // to find the cell with the most Noise
		if(z.getCurrentCell().getX()> c.getX()) {
			d = Direction.North;
		}
		else if(z.getCurrentCell().getX()< c.getX()) {
			d = Direction.South;
		}
		else if(z.getCurrentCell().getY()< c.getY()) {
			d = Direction.East;
		}
		else if(z.getCurrentCell().getY()> c.getY()) {
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
			this.board.moveZombie(z, choiceDirectionNoise(z));
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


