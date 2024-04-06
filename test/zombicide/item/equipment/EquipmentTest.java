package zombicide.item.equipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.player.Player;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.cell.Cell;
import zombicide.cell.Room;
import zombicide.item.Item;

import static org.junit.jupiter.api.Assertions.*;
public class EquipmentTest {
    private Item i;
    private Player p;
    private Cell cell;
    private Board b;

    @BeforeEach
    public void init(){
        this.b = new TrainingBoard();
        this.cell = b.getCellBoard(2,2);
        this.p = new Player(3,this.cell,3,5);
        this.i = new MapCard("test",false,b);
        this.cell.addPlayers(this.p);
    }

    @Test
    public void testItemEffect(){
        int noise = this.cell.getNoise();
        this.i.ItemEffect(this.p);
        assertEquals(this.cell.getNoise(),noise+1);
    }
}
