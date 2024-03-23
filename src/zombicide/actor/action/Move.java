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
	public Move() {
	}
	
	/**
	 * Move a player in a direction 
	 * @param p Player who want to move
	 * @param b the board of the player
	 */
	public void action(Player p, Board b) {
		System.out.println("enter the Direction to move");
		List<Direction> directions= List.of(Direction.North, Direction.South, Direction.East, Direction.West);
		ListChooser<Direction> listChooser = new InteractiveListChooser<>();
		Direction D= listChooser.choose("choose a direction: ", directions);
		b.movePlayer(p, D);
	}


	private Direction choiceDirectionNoise(Zombies z,Board b ) {
		int Noise = -1;
		Direction res = null;
		for(Direction D : Direction.values()) {
			if(b.getCellDirection(D, z)!= null && b.getCellDirection(D, z).getNoise() > Noise) {
				res = D;
				Noise = b.getCellDirection(D, z).getNoise();
			}
		}
		return res;
	}
	/**
	 * Move a zombies  in a direction 
	 * @param z zombies who want to move
	 * @param b the board of the zombie
	 */
	@Override
	public void action(Zombies z, Board b) {
		Direction d = choiceDirectionNoise(z,b);
		if(d != null) {
			b.moveZombie(z, choiceDirectionNoise(z,b));
		}
	}
}

