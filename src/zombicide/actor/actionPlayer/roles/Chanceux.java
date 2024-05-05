package zombicide.actor.actionPlayer.roles;

import zombicide.actor.actionPlayer.Attack;
import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombie;
import zombicide.board.Board;
import zombicide.util.listchooser.ListChooser;

/**
 * class for Chanceux
 */
public class Chanceux extends Attack {
	
	
	/**
	 * Builder of Chanceux with ListChooser in param to test
	 * @param chooser the listchooser of the action
	 * @param b the board of the player
	 */
	public Chanceux(ListChooser<Zombie> chooser, Board b) {
		super(chooser,b);
	}
	
	
	/**
	 * Builder of Chanceux
	 * @param b the board of the player
	 */
	public Chanceux(Board b) {
		super(b);
	}
	

    @Override
    public void action(Player p) {
    	super.attack(p, 0);
    	super.attack(p, 0);
		
    }

	@Override
	public String toString() {
		return "Chanceux action";
	}
    

}
