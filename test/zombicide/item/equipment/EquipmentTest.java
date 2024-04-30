package zombicide.item.equipment;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.player.Player;
import zombicide.cell.Cell;
import zombicide.cell.Room;
import zombicide.item.Item;

import zombicide.item.ItemTest;

public abstract class EquipmentTest extends ItemTest {

    private Player p;
    private Equipment eq;

    @Override
    protected Item createItem() {
        return this.createEquip();
    }

    //in the case where additional methode are add for equipment to be tested with the subclass of Equipment
    protected abstract Equipment createEquip();
    @BeforeEach
    public void init2(){
        this.eq = this.createEquip();
        Cell c = new Room(2, 2);
        this.p = new Player(3, c,5,5);
    }

    @Test
    public void testItemEffect(){
        int baseNoise = this.p.getCurrentCell().getNoise();
        MockEquip i = new MockEquip();
        assertFalse(i.isUsed());

        i.ItemEffect(this.p);
        //item is used
        assertTrue(i.isUsed());
        //the noise of the room go higher because the item is noisy
        assertEquals(baseNoise+1,this.p.getCurrentCell().getNoise());
        //effectOfTheEquip is use
        assertEquals(1,i.applyCalled);

    }


}
