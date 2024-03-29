package zombicide.actor.actionPlayer;

import java.util.List;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;
import zombicide.util.Direction;
import zombicide.util.listchooser.InteractiveListChooser;
import zombicide.util.listchooser.ListChooser;

public class Attack implements ActionsPlayer{
	
	
	private ListChooser<Direction> chooser;
	
	
	public Attack(ListChooser<Direction> chooser) {
		this.chooser = chooser;
	}
	
	public Attack() {
		this.chooser = new InteractiveListChooser<>();
	}
	
	public void action(Player p) {
		if(p.getItemInHand()!=null) {
			p.getItemInHand().ItemEffect(p);
		}
	}
}
