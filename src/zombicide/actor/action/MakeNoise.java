package zombicide.actor.action;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;
import zombicide.cell.Cell;

public class MakeNoise implements Actions{
	
	public MakeNoise() {	
	}
	
	@Override
	public void action(Player p, Board b ) {
		Cell c= p.getCurrentCell();
		c.setNoise(c.getNoise()+1);
	}

	@Override
	public void action(Zombies z, Board b) {
		Cell c= z.getCurrentCell();
		c.setNoise(c.getNoise()+1);
		
	}
	
}
