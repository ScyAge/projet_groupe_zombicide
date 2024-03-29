package zombicide.actor.actionPlayer;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.item.Item;
import zombicide.util.listchooser.InteractiveListChooser;
import zombicide.util.listchooser.ListChooser;

/**
 * Class SearchInTRoomAction implementation  of ActionsPlayer
 */
public class SearchInTRoomAction implements ActionsPlayer {
	
	/**
	 * Param
	 */
    private ListChooser<Item> chooser;
    
    /**
     * Builder of SearchIntRoomAction with ListChooser to test
     * @param chooser ListChooser of the Action
     */
    public SearchInTRoomAction(ListChooser<Item> chooser){
        this.chooser = chooser;
    }
    /**
     * Builder of SearchInTRoomAction 
     */
    public SearchInTRoomAction(){
        this.chooser = new InteractiveListChooser<>();
    }

    @Override
    public void action(Player p) {
        Item item =this.chooser.choose("veuiller choisir un item a recuperer dans la piece",p.getCurrentCell().getAllItems());
        p.getCurrentCell().removeItem(item);
        if(!p.IsBackPackFull()){
            p.putItemInBackPack(item);
        }
        else{
            Item item_a_jeter =this.chooser.choose("votre sac est plein qu'elle equipement souhaitez vous jeter",p.getBackPack());
            int index = p.getBackPack().indexOf(item_a_jeter);
            p.getCurrentCell().addItem(p.takeItemInTheBackPack(index));
            p.putItemInBackPack(item);
        }
        int actionPoints= p.getAction_points();
		p.setAction_points(actionPoints-1);
    }

	
}
