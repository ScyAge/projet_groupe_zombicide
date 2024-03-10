package zombicide.item.equipment;

import java.util.List;
import zombicide.actor.player.*;
import zombicide.cell.*;
import zombicide.util.listchooser.*;


/**
 * class FirstAidKit
 */
public class FirstAidKit extends Equipment {
	
	/**
	 * Builder of FirstAidKit
	 * @param title of the item
	 */
	public FirstAidKit( String title) {
		super(title);
	}
	
	/**
	 * heals a player in the same area as the player  
	 * who has the FirstAidKit
	 * @param player who has the FirstAidkit
	 * */
	public void equipmentEffect(Player player ) {
		Cell cell= player.getCurrentCell();
		List<Player> players= player.getCurrentCell().getAllPlayers();
		
		if(!players.isEmpty()) {
			ListChooser<Player> listChooser = new InteractiveListChooser<>();
			Player target = listChooser.choose("choose a player to heal: ", players );
			
			int targetLifePoints= target.getLifePoints();
			target.setLifePoints(targetLifePoints+1);
			System.out.println("the player "+ target.getId()+ "is healed" );
			
		}else {
			System.out.println("No player in the same area");
		}
	}
}
