package zombicide.actor.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.actionPlayer.*;
import zombicide.actor.actionPlayer.roles.Fouineur;
import zombicide.actor.zombie.Broom;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.cell.Cell;
import zombicide.cell.Continental;
import zombicide.cell.Room;
import zombicide.cell.Street;
import zombicide.exeption.ItemDoesNotExistExeption;
import zombicide.item.Item;
import zombicide.item.weapons.*;

import java.util.ArrayList;
import java.util.List;

public class PlayerTest {
    private Player testP;
    private Item testI;
    private Item testI2;
    private ActionsPlayer r1;
    private Cell testCell;

    @BeforeEach
    public void initAtt() {
        this.testCell = new Room(5, 5);
        this.testP = new Player(5, this.testCell, 1, 6);
        this.testCell.addPlayers(this.testP);
        this.testI = new Axe();
        this.testI2 = new Gun();
        this.r1 = new Fouineur();
    }

    @Test
    public void TestUpOneExperitseLevel() {
        assertEquals(this.testP.getExpertiseLevel(), 1);
        this.testP.UpOneExpertiseLevel();
        assertEquals(this.testP.getExpertiseLevel(), 2);
    }

    @Test
    public void TestPutItemInBackPack() {
        this.testP.putItemInBackPack(this.testI);
        assertTrue(this.testP.getBackPack().contains(this.testI));
    }

    @Test
    public void TestPutItemInBackPackIfIsFull() {
        for (int i = 0; i < 6; i++) {
            this.testP.putItemInBackPack(this.testI);
        }
        this.testP.putItemInBackPack(this.testI2);
        assertFalse(this.testP.getBackPack().contains(this.testI2));
    }

    @Test
    public void takeItemInTheBackPack() throws ArrayIndexOutOfBoundsException, ItemDoesNotExistExeption {
        //on remplit le sac
        for (int i = 0; i < 6; i++) {
            this.testP.putItemInBackPack(this.testI);
        }
        assertEquals(this.testP.takeItemInTheBackPack(4), this.testI);
        //test if the item has been revome from list
        assertEquals(this.testP.getBackPack().size(), 5);
    }

    @Test
    public void takeItemInTheBackPackOutOfBoundExept() throws ArrayIndexOutOfBoundsException, ItemDoesNotExistExeption {
        //on remplit le sac
        for (int i = 0; i < 6; i++) {
            this.testP.putItemInBackPack(this.testI);
        }
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> this.testP.takeItemInTheBackPack(10));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> this.testP.takeItemInTheBackPack(-10));
    }




    @Test
    public void TestSetRoles() {
        this.testP.setAction(this.r1);
        assertTrue(this.testP.getAllAction().contains(this.r1));
    }

    @Test
    public void TestTakeInHandFromBackPack() throws ItemDoesNotExistExeption {
        this.testP.putItemInBackPack(this.testI);
        this.testP.takeInHandFromBackPack(0);
        assertEquals(this.testP.getItemInHand(), this.testI);

    }

    @Test
    public void TestTakeInHandFromBackPackIfAnItemIsAlreadyInYourHand() throws ItemDoesNotExistExeption {
        Item testI2 = new Gun();
        this.testP.setItemInHand(testI2);
        this.testP.putItemInBackPack(this.testI);
        this.testP.takeInHandFromBackPack(0);
        assertTrue(this.testP.getBackPack().contains(testI2));
        assertEquals(this.testP.getItemInHand(), this.testI);

    }

    @Test
    public void testPutItemInHandInBackPackSucces() {
        this.testP.setItemInHand(this.testI);
        this.testP.PutItemInHandInBackPack();
        assertTrue(this.testP.getBackPack().contains(testI));
        assertNull(this.testP.getItemInHand());

    }

    @Test
    public void testPutItemInHandInBackPackFull() {
        //on remplit le sac
        for (int i = 0; i < 6; i++) {
            this.testP.putItemInBackPack(this.testI);
        }
        this.testP.setItemInHand(this.testI2);
        this.testP.PutItemInHandInBackPack();
        assertFalse(this.testP.getBackPack().contains(this.testI2));
        assertEquals(this.testP.getItemInHand(), this.testI2);

    }

    @Test
    public void testPutItemInHandInCell() {
        this.testP.setItemInHand(this.testI);
        this.testP.PutItemInHandInCell();
        assertNull(this.testP.getItemInHand());
        assertTrue(this.testCell.getAllItems().contains(this.testI));

    }

    @Test
    public void testPutItemInHandInCellIfItsAStreet() {
        this.testP.setItemInHand(this.testI);
        Street s1 = new Street(5, 5);
        Player p2 = new Player(3, s1, 4, 6);
        p2.PutItemInHandInCell();
        assertNull(p2.getItemInHand());
        assertFalse(s1.getAllItems().contains(this.testI));

    }

    @Test
    public void TestplayerTakeDamage() {
        this.testP.takeDamage(1);
        assertEquals(this.testP.getLifePoints(), 4);
    }

    @Test
    public void TestPlayerTakeDamageHpBelow0() {
        //on remplit le sac
        for (int i = 0; i < 6; i++) {
            this.testP.putItemInBackPack(this.testI);
        }
        this.testP.setItemInHand(this.testI);
        this.testP.takeDamage(1000);
        assertTrue(this.testCell.getAllItems().size() >= 7);
        assertTrue(this.testP.isDead());
    }

    @Test
    public void TestGetIdf() {
        assertEquals(this.testP.getId(), 1);
    }

    @Test
    public void TestgetActionOfThePlayer() {
        //creation of a board
        Board b = new TrainingBoard();
        b.initBoard();
        //creation of a list with all the Actions
        List<ActionsPlayer> actions = new ArrayList<>();
        ActionsPlayer a1 = new Move(b);
        ActionsPlayer a2 = new TakeInHandAction();
        ActionsPlayer a3 = new SearchInTRoomAction();
        ActionsPlayer a4 = new OpenDoor(b);
        ActionsPlayer a5 = new LookAround(b);
        ActionsPlayer a6 = new MakeNoise();
        ActionsPlayer a7 = new Attack(b);

        actions.add(a1);
        actions.add(a2);
        actions.add(a3);
        actions.add(a4);
        actions.add(a5);
        actions.add(a6);
        actions.add(a7);

        //creation of a player
        Player p = new Player(3, b.getCellBoard(2, 1), 3, 5, actions);

        assertEquals(p.getAllAction(), actions);

        //only Move , lookAround and MakeNoise are in the list because the condition for the other action are not complete
        List<ActionsPlayer> actionsPlayers = p.getActionOfThePlayer();
        assertTrue(actionsPlayers.contains(a1));
        assertFalse(actionsPlayers.contains(a2));
        assertFalse(actionsPlayers.contains(a3));
        assertFalse(actionsPlayers.contains(a4));
        assertTrue(actionsPlayers.contains(a5));
        assertTrue(actionsPlayers.contains(a6));
        assertFalse(actionsPlayers.contains(a7));

        //if the room contain a zombie and an item the actione SearchInRoom and attack are here
        p.setCell(b.getCellBoard(1,0));
        b.getCellBoard(1, 0).addZombies(new Broom(b.getCellBoard(1, 0), 1));
        b.getCellBoard(1, 0).addItem(new Axe());
        p.setItemInHand(new Axe());
        List<ActionsPlayer> actionsPlayers2 = p.getActionOfThePlayer();
        assertTrue(actionsPlayers2.contains(a3));
        if(b.getCellBoard(1, 0) instanceof Continental){
            assertFalse(actionsPlayers2.contains(a7));
        }
        else{
            assertTrue(actionsPlayers2.contains(a7));
        }

    }


}
