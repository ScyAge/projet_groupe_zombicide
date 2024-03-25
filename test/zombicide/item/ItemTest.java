package zombicide.item;
import org.junit.jupiter.api.Test;
import zombicide.actor.player.Player;
import zombicide.cell.Room;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
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

}