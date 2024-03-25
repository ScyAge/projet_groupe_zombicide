package zombicide.actor.actionPlayer;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.player.Player;
import zombicide.board.Board;
import zombicide.cell.*;
import zombicide.item.Item;
import zombicide.item.weapons.Axe;
import zombicide.item.weapons.Carabine;
import zombicide.item.weapons.Chainsaw;
import zombicide.util.listchooser.RandomListChooser;

import java.util.ArrayList;
import java.util.List;

public class TakeInHandActionTest {
    private ActionsPlayer action;
    private Player p;
    private Cell testCell;

    @BeforeEach
    public void init(){
    	Board board = new Board(5,5);
        this.testCell = new Room(3,3);
        this.action = new TakeInHandAction(new RandomListChooser<>());
        this.p = new Player(3,this.testCell,1,6);
        this.p.putItemInBackPack(new Carabine(board));
        this.p.putItemInBackPack(new Axe(board));

    }

    @Test
    public void TestTakeInHandActionNoItemInHand(){
        assertNull(this.p.getItemInHand());
        this.action.action(this.p);
        assertNotNull(this.p.getItemInHand());
        //before backpack size was 2
        assertEquals(1, this.p.getBackPack().size());
    }

    @Test
    public void TestTakeInHandActionItemInHand(){
        Item i = new Chainsaw();
        this.p.setItemInHand(i);
        List<Item> test= new ArrayList<>();
        test.add(i);
        test.addAll(this.p.getBackPack());
        assertNotNull(this.p.getItemInHand());
        this.action.action(this.p);
        assertTrue(this.p.getBackPack().contains(i));
        assertEquals(2, this.p.getBackPack().size());
        assertNotNull(this.p.getItemInHand());
    }

}
