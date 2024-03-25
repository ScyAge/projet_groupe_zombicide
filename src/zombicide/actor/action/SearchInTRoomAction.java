package zombicide.actor.action;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;
import zombicide.item.Item;
import zombicide.util.listchooser.InteractiveListChooser;
import zombicide.util.listchooser.ListChooser;

public class SearchInTRoomAction implements Actions{
    @Override
    public void action(Player p) {
        ListChooser<Item> listChoose= new InteractiveListChooser<>();
        Item item =listChoose.choose("veuiller choisir un item a recuperer dans la piece",p.getCurrentCell().getAllItems());
        p.getCurrentCell().removeItem(item);
        if(!p.IsBackPackFull()){
            p.putItemInBackPack(item);
        }
        else{
            Item item_a_jeter =listChoose.choose("votre sac est plein qu'elle equipement souhaitez vous jeter",p.getBackPack());
            int index = p.getBackPack().indexOf(item_a_jeter);
            p.getCurrentCell().addItem(p.takeItemInTheBackPack(index));
            p.putItemInBackPack(item);
        }
        int actionPoints= p.getAction_points();
		p.setAction_points(actionPoints-1);
    }

	@Override
	public void action(Zombies z) {
	}
}
