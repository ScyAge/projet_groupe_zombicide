package zombicide.actor.actionPlayer;

import zombicide.actor.player.Player;
import zombicide.item.Item;

public class UseEquipmentAction implements ActionsPlayer {

    @Override
    public void action(Player p) {
        if(this.IsActionPlayable(p)){
            Item equipment = p.getItemInHand();
            equipment.ItemEffect(p);
        }
    }

    @Override
    public boolean IsActionPlayable(Player p) {
        return p.getItemInHand() != null && !p.getItemInHand().cantAttack();
    }
}
