package zombicide.actor.actionPlayer.roles;

import zombicide.actor.actionPlayer.ActionsPlayer;
import zombicide.actor.player.Player;
import zombicide.cell.Cell;
import zombicide.util.listchooser.InteractiveListChooser;
import zombicide.util.listchooser.ListChooser;
import zombicide.util.listchooser.RandomListChooser;

import java.util.ArrayList;
import java.util.List;

/**
 * class Soigneur
 */
public class Soigneur implements ActionsPlayer {

	private ListChooser<Player> chooser;
	/**
	 * Builder of Soigneur
	 */
	public Soigneur() {
		this.chooser = new InteractiveListChooser<>();
	}
	public Soigneur(ListChooser<Player> chooser){
		this.chooser = new RandomListChooser<>();
	}
    /**
     * method that allows a survivor to perform the special action related to its roles
     * @param p the survivor who use the action
     */
    public void action(Player p) {
		Cell cellCurrentPlayer = p.getCurrentCell();
		List<Player> playersToHeal = new ArrayList<>();
		for(Player pl :cellCurrentPlayer.getAllPlayers()){
			if(pl.getLifePoints() < pl.getMaxLifePoint()){
				System.out.println("test");
				playersToHeal.add(pl);
			}
		}
		Player pToHeal = this.chooser.choose("Choose a player to heal",playersToHeal);
		pToHeal.setLifePoints(1);
    }
}
