package zombicide.actor.actionPlayer;

import java.util.List;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;
import zombicide.util.Direction;
import zombicide.util.listchooser.InteractiveListChooser;
import zombicide.util.listchooser.ListChooser;

public class Attack implements ActionsPlayer{
	
	/**
	 * param 
	 */
	private ListChooser<Direction> chooser;
	
	/**
	 * Builder of Attack with ListChooser in param to test
	 * @param chooser the listchooser of the action
	 */
	public Attack(ListChooser<Direction> chooser) {
		this.chooser = chooser;
	}
	
	
	/**
	 * Builder of Attack
	 */
	public Attack() {
		this.chooser = new InteractiveListChooser<>();
	}
	
	
	/**
	 * player attack zombies
	 * @param p player who attack a zombie
	 */
	public void action(Player p) {
		if(p.getItemInHand()!=null) {
			p.getItemInHand().ItemEffect(p);
		}
	}
}
