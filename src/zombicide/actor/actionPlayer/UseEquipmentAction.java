package zombicide.actor.actionPlayer;

import zombicide.actor.player.Player;
import zombicide.item.Item;

/**
 * class UseEquipmentAction 
 */
public class UseEquipmentAction implements ActionsPlayer {
	
	
	/**
	 * Builder of UseEquipmentAction
	 */
	public UseEquipmentAction() {
		
	}
	
    @Override
    public void action(Player p) {
        if(this.IsActionPlayable(p)){
        	System.out.println("L'equipement est utiliser !");
            Item equipment = p.getItemInHand();
            equipment.ItemEffect(p);
            if(equipment.isUsed()) {
            	p.setItemInHand(null);
            }
        }
    }

    @Override
    public boolean IsActionPlayable(Player p) {
        return p.getItemInHand() != null && !p.getItemInHand().cantAttack();
    }

    @Override
    public String toString() {
        return "UseEquipment action";
    }
}
