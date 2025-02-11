package zombicide.actor.actionPlayer.roles;

import zombicide.actor.actionPlayer.Attack;
import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombie;
import zombicide.board.Board;
import zombicide.util.listchooser.ListChooser;

/**
 * Class Combattant
 */
public class Combattant extends Attack{
	
	
	/**
	 * Builder of Combattant with ListChooser in param to test
	 * @param chooser the ListChooser of the action
	 * @param b the board of the combattant
	 */
	public Combattant(ListChooser<Zombie> chooser, Board b) {
		super(chooser,b);
	}
	
	
	/**
	 * Builder of Combattant
	 * @param b the board of the combattant
	 */
	public Combattant(Board b) {
		super(b);
	}
	

    @Override
    public void action(Player p) {
		super.attack(p,1);
		
    }
	@Override
	public String toString() {
		return "Combattant action";
	}
 
}
