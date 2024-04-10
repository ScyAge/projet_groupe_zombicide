package zombicide.actor.actionPlayer.roles;

import zombicide.actor.actionPlayer.ActionsPlayer;
import zombicide.actor.actionPlayer.SearchInTRoomAction;
import zombicide.actor.actionPlayer.TakeInHandAction;
import zombicide.actor.player.Player;
import zombicide.item.Item;
import zombicide.util.listchooser.ListChooser;

/**
 * Class Fouineur
 */
public class Fouineur extends SearchInTRoomAction implements ActionsPlayer {
	
	/**
	 * Builder of Fouineur
	 * @param li list chooser to test
	 */
	public Fouineur(ListChooser<Item> li) {
		super(li);
	}
	/**
	 * Builder of Fouineur
	 */
	public Fouineur() {
		super();
	}
	
	
    /**
     * method that allows a survivor to perform the special action related to its roles
     * @param p the survivor who use the action
     */
    public void action(Player p) {
	 super.action(p);
	 p.setAction_points(p.getAction_points()+1);
    }

	@Override
	public String toString() {
		return "Fouineur action";
	}
}
