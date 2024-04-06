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
	private ListChooser<Player> chooser;
	
	/**
	 * Builder of FirstAidKit
	 * @param title of the item
	 * @param breakDoor false the firstAidKit can't break a door 
	 * @param chooser ListChooser of the masterkey
	 */
	public FirstAidKit( String title, boolean breakDoor, ListChooser<Player> chooser) {
		super(title, false);
		this.chooser=chooser;
	}
	
	/**
	 * Builder of FirstAidKit
	 * @param title of the item
	 * @param breakDoor false the firstAidKit can't break a door
	 */
	public FirstAidKit( String title, boolean breakDoor ) {
		super(title, false);
		this.chooser= new InteractiveListChooser<>();
	}
	
	/**
	 * heals a player in the same area as the player  
	 * who has the FirstAidKit
	 * @param player who has the FirstAidkit
	 * */
	public void ItemEffect(Player player ) {
		super.ItemEffect(player);
		Cell cell= player.getCurrentCell();
		List<Player> players= cell.getAllPlayers();
		
		if(!players.isEmpty()) {
			Player target = chooser.choose("choose a player to heal: ", players );
			
			int targetLifePoints= target.getLifePoints();
			target.setLifePoints(targetLifePoints+1);
			System.out.println("the player "+ target.getId()+ "is healed" );
			
		}else {
			System.out.println("No player in the same area");
			this.Use=false;
		}
	}

	/**
	 * return true if the equipment is noisy else false
	 * @return true if the equipment is noisy else false
	 */
	public boolean isNoisy() {
		return false;
	}
}
