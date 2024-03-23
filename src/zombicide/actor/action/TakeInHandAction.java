package zombicide.actor.action;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;
import zombicide.exeption.ItemDoesNotExistExeption;
import zombicide.util.listchooser.*;
import zombicide.item.*;
public class TakeInHandAction implements Actions{
    @Override


    public void action(Player p) {
        ListChooser<Item> ItemChooser = new InteractiveListChooser<>();
         Item item = ItemChooser.choose("\"Choose the object you want to take from your bag\"",p.getBackPack());
         int index = p.getBackPack().indexOf(item);
         try {
             p.takeInHandFromBackPack(index);
         }
         catch (ItemDoesNotExistExeption error){
             System.out.println("error item does not exist");
         }
    }

	@Override
	public void action(Zombies z) {
		
	}
}
