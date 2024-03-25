package zombicide.actor.actionPlayer;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.player.Player;
import zombicide.cell.*;
import zombicide.item.weapons.Axe;
import zombicide.item.weapons.Carabine;
import zombicide.util.listchooser.RandomListChooser;

public class TakeInHandActionTest {
    private ActionsPlayer action;
    private Player p;
    private Cell testCell;

    @BeforeEach
    public void init(){
        this.testCell = new Room(3,3);
        this.action = new TakeInHandAction(new RandomListChooser<>());
        this.p = new Player(3,this.testCell,1,6);
        this.p.putItemInBackPack(new Carabine());
        this.p.putItemInBackPack(new Axe());

    }

    @Test
    public void TestTakeInHandAction(){
        assertNull(this.p.getItemInHand());
        this.action.action(this.p);
        assertNotNull(this.p.getItemInHand());
        //before backpack size was 2
        assertEquals(1, this.p.getBackPack().size());
    }

}
