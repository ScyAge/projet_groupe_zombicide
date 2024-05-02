package zombicide.item.equipment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.player.Player;
import zombicide.actor.zombie.Abominations;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.cell.Cell;
import zombicide.item.Item;

import static org.junit.jupiter.api.Assertions.*;

public class MapCardTest extends EquipmentTest{
    private Item i;
    private Player p;
    private Cell cell;
    private Board b;


    @Override
    protected Equipment createEquip() {
        return new MapCard(this.b);
    }

    @BeforeEach
    public void initM(){
        this.b = new TrainingBoard();
        this.cell = b.getCellBoard(2,2);
        this.p = new Player(3,this.cell,3,5);
        this.i = new MapCard(b);
        this.cell.addPlayers(this.p);
    }

    @Test
    public void testEffectOfTheEquip(){
        int noise = this.cell.getNoise();
        this.i.ItemEffect(this.p);
        assertEquals(this.cell.getNoise(),noise+1);
    }
    @Test
    public void testEffectOfTheEquipWithZombieInTheCell(){
        this.cell.addZombies(new Abominations(this.cell,1));
        int noise = this.cell.getNoise();
        this.i.ItemEffect(this.p);
        assertEquals(this.cell.getNoise(),noise+1);
    }

    @Test
    public void testCloneEquipment(){
        Item map = new MapCard(b);
        try {
            Item mapBis = map.clone();
            assertNotSame(map, mapBis);
            assertSame(map.getClass(),mapBis.getClass());
            assertEquals(map, mapBis);
            map.Used();
            assertNotEquals(map.isUsed(),mapBis.isUsed());
        }
        catch (CloneNotSupportedException err){
            System.out.println(System.err);
        }
    }
}
