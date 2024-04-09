package zombicide.actor.actionPlayer.roles;

import java.util.Random;

import zombicide.actor.actionPlayer.ActionsPlayer;
import zombicide.actor.actionPlayer.Attack;
import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;
import zombicide.cell.Cell;
import zombicide.item.weapons.Weapon;
import zombicide.util.listchooser.InteractiveListChooser;
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
	public Chanceux(ListChooser<Zombies> chooser, Board b) {
		super(chooser,b);
	}
	
	
	/**
	 * Builder of Chanceux
	 * @param b the board of the player
	 */
	public Chanceux(Board b) {
		super(b);
	}
	
    /**
     * method that allows a survivor to perform the special action related to its roles
     * @param p the survivor who use the action
     */
    @Override
    public void action(Player p) {
    	super.attack(p, 0);
    	super.attack(p, 0);
		
    }
    

}
