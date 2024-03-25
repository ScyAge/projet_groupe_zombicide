package zombicide.actor.actionZombie;


import java.util.List;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;
import zombicide.util.Direction;
import zombicide.util.listchooser.InteractiveListChooser;
import zombicide.util.listchooser.ListChooser;

/**
 * Class Move
 */
public class Move implements ActionZombie{
	
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
	


	private Direction choiceDirectionNoise(Zombies z) {
		int Noise = -1;
		Direction res = null;
		for(Direction D : Direction.values()) {
			if(this.board.getCellDirection(D, z)!= null && this.board.getCellDirection(D, z).getNoise() > Noise) {
				res = D;
				Noise = this.board.getCellDirection(D, z).getNoise();
			}
		}
		return res;
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
}


