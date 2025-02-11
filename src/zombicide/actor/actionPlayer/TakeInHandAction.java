package zombicide.actor.actionPlayer;

import zombicide.actor.player.Player;
import zombicide.util.listchooser.*;
import zombicide.item.*;

/**
 * Class TakeInHandAction implementation  of ActionsPlayer
 */
public class TakeInHandAction implements ActionsPlayer {
	
	/**
	 * Param
	 */
    private final ListChooser<Item> chooser;
    
    /**
     * Builder of TakeInHandAction with ListChooser to test
     * @param chooser ListChooser of the Action
     */
    public TakeInHandAction(ListChooser<Item> chooser){
        this.chooser = chooser;
    }
    
    /**
     * Builder of TakeInHandAction
     */
    public TakeInHandAction(){
        this.chooser = new InteractiveListChooser<>();
    }
    
    
    @Override
    public void action(Player p) {
         Item item = this.chooser.choose("-> Choose the object you want to take from your bag",p.getBackPack());
         if(item != null) {
             int index = p.getBackPack().indexOf(item);
             p.takeInHandFromBackPack(index);
         }
         int actionPoints = p.getAction_points();
 		 p.setAction_points(actionPoints-1);
    }

    @Override
    public boolean IsActionPlayable(Player p) {
        return  !p.getBackPack().isEmpty();
    }

    @Override
    public String toString() {
        return "TakeInHandAction action";
    }
}
