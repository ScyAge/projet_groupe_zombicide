package zombicide.item.equipment;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.player.Player;
import zombicide.cell.Cell;
import zombicide.cell.Room;
import zombicide.item.Item;
import zombicide.item.ItemTest;

public class EquipmentTest extends ItemTest {

    private Player p;
    private Cell c;

    @Override
    protected Item createItem() {
        System.out.println("TEST");
        return (new MockEquip());
    }

    @BeforeEach
    public void init(){
        this.c = new Room(2,2);
        this.p = new Player(3,this.c,5,5);
    }
    @Test
    public void testItemEffect(){
        MockEquip i = new MockEquip();
        assertFalse(i.isUsed());

        i.ItemEffect(this.p);
        //item is used
        assertTrue(i.isUsed());
        //effectOfTheEquip is use
        assertEquals(1,i.applyCalled);

    }
}
