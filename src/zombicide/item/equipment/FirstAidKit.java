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
	 *
	 * @param chooser ListChooser of the player to heal
	 */
	public FirstAidKit( ListChooser<Player> chooser) {
		super(false);
		this.chooser=chooser;
	}
	
	/**
	 * Builder of FirstAidKit
	 */
	public FirstAidKit() {
		super(false);
		this.chooser= new InteractiveListChooser<>();
	}
	
	/**
	 * heals a player in the same area as the player  
	 * who has the FirstAidKit
	 * @param player who has the FirstAidkit
	 * */
	public void effectOfTheEquip(Player player ) {
		Cell cell= player.getCurrentCell();
		List<Player> players= cell.getAllPlayers();

		Player target = chooser.choose("choose a player to heal: ", players );
			
		int targetLifePoints= target.getLifePoints();
		target.setLifePoints(targetLifePoints+1);
		System.out.println("the player "+ target.getId()+ " is healed" );
			
		
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof FirstAidKit){
			FirstAidKit i = (FirstAidKit) obj;
			return super.equals(i) && (this.chooser == i.chooser);
		}
		return false;
	}
	
	@Override
	public String itemDescription() {
		String res=("heal one of the survivors in your zone");
		return res;
	}
}
