package zombicide.item.equipment;

import zombicide.item.*;
import zombicide.player.*;
import zombicide.actor.*;


public class FirstAidKit extends Equipment {
	
	public FirstAidKit(title: String) {
		super(title);
	}
	
	/**
	 * heals the target player in the same area as the player 
	 * who has the FirstAidKit
	 * @param player who has the FirstAidKit
	 * @param the target who'll be healed
	 * */
	public void equipmentEffect(Player player, Player target) {
		if(target!=null){
			if( target.getCurrentCell()==player.getCurrentCell()) {
				target.heal(1);
			}
		}
	}
}
