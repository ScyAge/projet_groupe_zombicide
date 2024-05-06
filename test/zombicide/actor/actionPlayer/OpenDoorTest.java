package zombicide.actor.actionPlayer;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.player.Player;
import zombicide.board.*;
import zombicide.cell.Cell;
import zombicide.item.*;
import zombicide.item.weapons.Axe;
import zombicide.item.weapons.Carabine;
import zombicide.util.Direction;
import zombicide.util.Door;
import zombicide.util.listchooser.RandomListChooser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OpenDoorTest {
    private Board b;
    private Player p;
    private ActionsPlayer open;
    private Item cassePorte;

    @BeforeEach
    public void init(){
        this.b = new TrainingBoard();
        this.b.initBoard();
        this.p = new Player(3,b.getCellBoard(0,0),1,5);
        this.open = new OpenDoor(new RandomListChooser<>(),this.b);
        this.cassePorte = new Axe();
        this.p.setItemInHand(this.cassePorte);
        this.b.getCellBoard(1,1).addPlayers(this.p);
    }

    @Test
    public void TestOpenDoor(){
        int PastNoise = this.p.getCurrentCell().getNoise();
        for(Door door: this.p.getCurrentCell().getDoors().values()){
            assertFalse(door.isBreak());
        }
        this.open.action(this.p);
        //verify if one of the 4 doors of the cell has been break;
        Collection<Door> testBis =this.p.getCurrentCell().getDoors().values();
        List<Door> test = new ArrayList<>();
        test.addAll(testBis);
        assertTrue(test.get(0).isBreak() ||test.get(1).isBreak()||test.get(2).isBreak()||test.get(3).isBreak());

        //test if one of the door of cell around the current cell of the player has been break
        Door c01 = this.b.getCellBoard(0,1).getDoor(Direction.West);
        Door c21 = this.b.getCellBoard(2,1).getDoor(Direction.East);
        Door c10 = this.b.getCellBoard(1,0).getDoor(Direction.South);
        Door c12 = this.b.getCellBoard(1,2).getDoor(Direction.North);

        assertTrue(c12.isBreak() || c10.isBreak() || c21.isBreak() || c01.isBreak());
        assertEquals(PastNoise+1,this.p.getCurrentCell().getNoise());
    }
    @Test
    public void TestOpenDoorButTheWeaponCantOpenDoor(){
        this.p.setItemInHand(new Carabine());
        this.open.action(this.p);
        //verify if none of the 4 doors of the cell has been break;
        Collection<Door> testBis =this.p.getCurrentCell().getDoors().values();
        List<Door> test = new ArrayList<>();
        test.addAll(testBis);
        assertTrue(!test.get(0).isBreak() && !test.get(1).isBreak() && !test.get(2).isBreak()&& !test.get(3).isBreak());
    }
    

    @Test
    public void TestOpenDoorInStreet(){
    	Player p1 = new Player(3,b.getCellBoard(1,1),1,5);
    	open.action(p1);
    	open.action(p1);
    	open.action(p1);
    	open.action(p1);
    	assertTrue(b.getCellBoard(2, 1).getAllZombies().isEmpty()&&b.getCellBoard(1, 2).getAllZombies().isEmpty());
    	
    }

    @Test
    public void testToString(){
        assertEquals(this.open.toString(),"OpenDoor action");
    }
}
