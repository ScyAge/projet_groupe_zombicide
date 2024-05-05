package zombicide.item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.player.Player;
import zombicide.cell.Cell;
import zombicide.cell.Room;

import static org.junit.jupiter.api.Assertions.*;

public abstract class ItemTest {

   private Item i;
   private Item mi;
   private Player p;
   private Cell cell;

   protected abstract Item createItem();
   @BeforeEach
   public void init(){
       this.i = this.createItem();
       this.mi = new MockItem();
       this.cell = new Room(2,2);
       this.p = new Player(3,this.cell,2,5);
   }


    @Test
    public void TestUseItem(){
        assertFalse(this.i.isUsed());
        this.i.Used();
        assertTrue(this.i.isUsed());
    }

    @Test
    public void testCantAttack(){
        assertEquals(this.mi.canAttack(),MockItem.canAttack);
    }

    @Test
    public void TestNotUsed(){
        this.i.Used();
        assertTrue(this.i.isUsed());
        this.i.NotUsed();
        assertFalse(this.i.isUsed());
    }

    @Test
    public void TestNoisy(){
        assertEquals(this.mi.isNoisy(),MockItem.noisy);
    }
    @Test
    public void TestCanBreakDoor(){
        assertEquals(this.mi.getBreakDoor(),MockItem.breakDoor);
    }

    @Test
    public void TestEquals(){
       MockItem test = new MockItem();
       assertEquals(this.mi, test);
       Cell cell = new Room(5,5);
       assertNotEquals(this.mi,cell);

    }
}

