package zombicide.actor.actionPlayer.roles;

import zombicide.actor.actionPlayer.ActionsPlayer;
import zombicide.actor.actionPlayer.TakeInHandAction;
import zombicide.actor.player.Player;
import zombicide.item.Item;
import zombicide.util.listchooser.ListChooser;

/**
 * Class Fouineur
 */
public class Fouineur implements ActionsPlayer {
	
	/**
	 * Builder of Fouineur
	 */
	private TakeInHandAction act;
	public Fouineur(ListChooser<Item> li) {
		this.act = new TakeInHandAction(li);
	}
	public Fouineur() {
		this.act = new TakeInHandAction();
	}
	
	
    /**
     * method that allows a survivor to perform the special action related to its roles
     * @param p the survivor who use the action
     */
    public void action(Player p) {
	 this.act.action(p);
	 p.setAction_points(p.getAction_points()+1);
    }
}
