package zombicide.actor.action;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;
import zombicide.exeption.ItemDoesNotExistExeption;
import zombicide.util.listchooser.*;
import zombicide.item.*;
public class TakeInHandAction implements Actions{

    private ListChooser<Item> chooser;
    public TakeInHandAction(ListChooser<Item> chooser){
        this.chooser = chooser;
    }
    public TakeInHandAction(){
        this.chooser = new InteractiveListChooser<>();
    }
    @Override
    public void action(Player p) {
         Item item = this.chooser.choose("\"Choose the object you want to take from your bag\"",p.getBackPack());
         int index = p.getBackPack().indexOf(item);
         try {
             p.takeInHandFromBackPack(index);
         }
         catch (ItemDoesNotExistExeption error){
             System.out.println("error item does not exist");
         }
         int actionPoints= p.getAction_points();
 		 p.setAction_points(actionPoints-1);
    }

	
}
