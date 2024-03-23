package zombicide.actor.action;

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
public class Move implements Actions {
	
	/**
	 * Builder of Move
	 */
	private Board board;
	public Move(Board board) {
		this.board = board;
	}
	
	/**
	 * Move a player in a direction 
	 * @param p Player who want to move
	 */
	public void action(Player p) {
		System.out.println("enter the Direction to move");
		List<Direction> directions= List.of(Direction.North, Direction.South, Direction.East, Direction.West);
		ListChooser<Direction> listChooser = new InteractiveListChooser<>();
		Direction D= listChooser.choose("choose a direction: ", directions);
		this.board.movePlayer(p, D);
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
	@Override
	public void action(Zombies z) {
		Direction d = choiceDirectionNoise(z);
		if(d != null) {
			this.board.moveZombie(z, choiceDirectionNoise(z));
		}
	}
}

