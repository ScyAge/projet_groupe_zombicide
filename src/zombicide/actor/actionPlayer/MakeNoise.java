package zombicide.actor.actionPlayer;

import zombicide.actor.player.Player;
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
		System.out.println("-[MakeNoise action]-");
		Cell c= p.getCurrentCell();
		c.setNoise(c.getNoise()+1);
		System.out.printf("-[The noise in now at %d in this cell]-\n",c.getNoise());
		int actionP= p.getAction_points();
		p.setAction_points(actionP-1);
	}

	@Override
	public boolean IsActionPlayable(Player p) {
		return true;
	}

	@Override
	public String toString() {
		return "MakeNoise action";
	}
}
