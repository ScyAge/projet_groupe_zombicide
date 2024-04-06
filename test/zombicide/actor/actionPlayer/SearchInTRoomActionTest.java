package zombicide.actor.actionPlayer;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.player.Player;
import zombicide.board.TrainingBoard;
import zombicide.cell.Cell;
import zombicide.cell.Room;
import zombicide.item.Item;
import zombicide.item.equipment.FirstAidKit;
import zombicide.item.weapons.*;
import zombicide.util.listchooser.ListChooser;
import zombicide.util.listchooser.RandomListChooser;

import java.util.ArrayList;
import java.util.List;

public class SearchInTRoomActionTest {

    private ActionsPlayer act;
    private ListChooser<Item> chooser;

    private Player p;
    private TrainingBoard board;
    private Cell cell;
    private Item wp1;
    private Item wp2;

    @BeforeEach
    public void init(){
        this.chooser = new RandomListChooser<>();
        this.board = new TrainingBoard();
        this.cell = this.board.getCellBoard(0,0);
        this.p = new Player(3,this.cell,1,5);
        this.cell.addPlayers(this.p);
        this.act = new SearchInTRoomAction(this.chooser);
        this.wp1 = new Carabine();
        this.wp2 = new Chainsaw();
        this.cell.addItem(this.wp1);
        this.cell.addItem(this.wp2);
        this.p.putItemInBackPack(new Carabine());
        this.p.putItemInBackPack(new Axe());
    }

    @Test
    public void TestSearchInRoomAction(){
        //test de la coherence des item present dans la cell et dans le sac
        List<Item> testItem = new ArrayList<>();
        testItem.addAll(this.p.getBackPack());
        testItem.addAll(this.cell.getAllItems());

        assertEquals(2, this.cell.getAllItems().size());
        this.act.action(this.p);
        assertEquals(1, this.cell.getAllItems().size());
        assertTrue(this.p.getBackPack().contains(wp1)||this.p.getBackPack().contains(wp2));

        //test de la coherence des item present dans la cell et dans le sac
        List<Item> testItemAfterAction = new ArrayList<>();
        testItemAfterAction.addAll(this.p.getBackPack());
        testItemAfterAction.addAll(this.cell.getAllItems());

        assertTrue(testItem.containsAll(testItemAfterAction));
    }

    @Test
    public  void TestSearchInRoomActionWhenBackPackFull(){
        this.p.putItemInBackPack(new Crowbar());
        this.p.putItemInBackPack(new Gun());
        this.p.putItemInBackPack(new FirstAidKit("TEST"));

        //test de la coherence des item present dans la cell et dans le sac
        List<Item> testItem = new ArrayList<>();
        testItem.addAll(this.p.getBackPack());
        testItem.addAll(this.cell.getAllItems());

        assertEquals(2, this.cell.getAllItems().size());
        this.act.action(this.p);
        //echange d'un item du sac avec un item de la room
        assertEquals(2, this.cell.getAllItems().size());
        assertTrue(this.p.getBackPack().contains(wp1)||this.p.getBackPack().contains(wp2));

        //test de la coherence des item present dans la cell et dans le sac
        List<Item> testItemAfterAction = new ArrayList<>();
        testItemAfterAction.addAll(this.p.getBackPack());
        testItemAfterAction.addAll(this.cell.getAllItems());

        assertTrue(testItem.containsAll(testItemAfterAction));
    }
}
