package zombicide.item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.player.Player;
import zombicide.cell.Cell;
import zombicide.cell.Room;

import static org.junit.jupiter.api.Assertions.*;

public abstract class ItemTest {

   private Item i;
   private Player p;
   private Cell cell;

   protected abstract Item createItem();
   @BeforeEach
    public void init(){
        this.i = this.createItem();
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

