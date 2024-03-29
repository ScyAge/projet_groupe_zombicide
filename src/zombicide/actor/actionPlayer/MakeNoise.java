package zombicide.actor.actionPlayer;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.cell.Cell;

/**
 * Class MakeNoise implementation  of ActionsPlayer
 */
public class MakeNoise implements ActionsPlayer {
	
	/**
	 * Builder of MakeNoise
	 */
	public MakeNoise() {	
	}
	
	@Override
	public void action(Player p) {
		Cell c= p.getCurrentCell();
		c.setNoise(c.getNoise()+1);
		int actionP= p.getAction_points();
		p.setAction_points(actionP-1);
	}

	
	
}
