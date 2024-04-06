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
	 * Param
	 */
	private final ListChooser<Player> chooser;
	
	/**
	 * Builder of FirstAidKit
	 * @param title of the item
	 * @param chooser ListChooser of the player to heal
	 */
	public FirstAidKit( String title, ListChooser<Player> chooser) {
		super(title, false);
		this.chooser=chooser;
	}
	
	/**
	 * Builder of FirstAidKit
	 * @param title of the item
	 */
	public FirstAidKit( String title) {
		super(title, false);
		this.chooser= new InteractiveListChooser<>();
	}
	
	/**
	 * heals a player in the same area as the player  
	 * who has the FirstAidKit
	 * @param player who has the FirstAidkit
	 * */
	public void ItemEffect(Player player ) {
		Cell cell= player.getCurrentCell();
		List<Player> players= cell.getAllPlayers();
		
		if(!players.isEmpty()) {
			super.ItemEffect(player);
			Player target = chooser.choose("choose a player to heal: ", players );
			
			int targetLifePoints= target.getLifePoints();
			target.setLifePoints(targetLifePoints+1);
			System.out.println("the player "+ target.getId()+ " is healed" );
			
		}else {
			System.out.println("No player in the same area");
			
		}
	}

}
