package zombicide.actor.actionPlayer.roles;

import zombicide.actor.actionPlayer.ActionsPlayer;
import zombicide.actor.actionPlayer.Attack;
import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;
import zombicide.util.listchooser.ListChooser;

/**
 * Class Combattant
 */
public class Combattant extends Attack implements ActionsPlayer {
	
	
	/**
	 * Builder of Combattant with ListChooser in param to test
	 * @param chooser the listchooser of the action
	 */
	public Combattant(ListChooser<Zombies> chooser, Board b) {
		super(chooser,b);
	}
	
	
	/**
	 * Builder of Combattant
	 */
	public Combattant(Board b) {
		super(b);
	}
	
    /**
     * method that allows a survivor to perform the special action related to its roles
     * @param p the survivor who use the action
     */
    @Override
    public void action(Player p) {
		super.attack(p, 0,1);
		p.setAction_points(p.getAction_points()+1);
    }
 
}
