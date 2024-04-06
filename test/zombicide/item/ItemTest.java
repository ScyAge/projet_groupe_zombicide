package zombicide.item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.player.Player;
import zombicide.cell.Cell;
import zombicide.cell.Room;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

   private Item i;
   private Player p;
   private Cell cell;
    @BeforeEach
    public void init(){
        this.i = new Item("test",false);
        this.cell = new Room(2,2);
        this.p = new Player(3,this.cell,2,5);
    }
    @Test
    void TestItemInitialization() {
        Item item = new Item("test1", false);
        assertEquals("test1", item.getTitle());
        assertFalse(item.isUsed());
    }

    @Test
    public void TestUseItem(){
        Item item = new Item("test1", false);
        Room room = new Room(5,5);
        Player player = new Player(5,room,1,6);

        assertFalse(item.isUsed());
        item.Used();
        assertTrue(item.isUsed());
    }

    @Test
    public void testCantAttack(){
        assertFalse(this.i.cantAttack());
    }

    @Test
    public void TestItemEffect(){
        assertFalse(this.i.isUsed());
        this.i.ItemEffect(this.p);
        assertTrue(this.i.isUsed());
    }

    @Test
    public void TestNotUsed(){
        this.i.ItemEffect(this.p);
        assertTrue(this.i.isUsed());
        this.i.NotUsed();
        assertFalse(this.i.isUsed());
    }

    @Test
    public void TestSetTittle(){
        String t = "OUIII";
        this.i.setTitle(t);
        assertEquals(this.i.getTitle(),t);
    }

}