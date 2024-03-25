package zombicide.actor.player;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.player.roles.Combattant;
import zombicide.actor.player.roles.RolesIntrerface;
import zombicide.cell.Cell;
import zombicide.cell.Room;
import zombicide.cell.Street;
import zombicide.exeption.ItemDoesNotExistExeption;
import zombicide.item.Item;

public class PlayerTest {
    private Player testP;
    private Item testI;
    private Item testI2;
    private RolesIntrerface r1;
    private Cell testCell;
    @BeforeEach
    public void initAtt(){
        this.testCell = new Room(5,5);
        this.testP = new Player(5,this.testCell,1,6);
        this.testCell.addPlayers(this.testP);
        this.testI = new Item("Test", true);
        this.testI2 = new Item("Test2", true);
        this.r1 = new Combattant();
    }

    @Test
    public void TestUpOneExperitseLevel(){
        assertEquals(this.testP.getExpertiseLevel(),1);
        this.testP.UpOneExpertiseLevel();
        assertEquals(this.testP.getExpertiseLevel(),2);
    }

    @Test
    public void TestPutItemInBackPack(){
        this.testP.putItemInBackPack(this.testI);
        assertTrue(this.testP.getBackPack().contains(this.testI));
    }

    @Test
    public void TestPutItemInBackPackIfIsFull(){
        for(int i = 0;i<6;i++){
            this.testP.putItemInBackPack(this.testI);
        }
        this.testP.putItemInBackPack(this.testI2);
        assertFalse(this.testP.getBackPack().contains(this.testI2));
    }

    @Test
    public void takeItemInTheBackPack() throws ArrayIndexOutOfBoundsException, ItemDoesNotExistExeption {
        //on remplit le sac
        for(int i = 0;i<6;i++){
            this.testP.putItemInBackPack(this.testI);
        }
        assertEquals(this.testP.takeItemInTheBackPack(4),this.testI);
        //test if the item has been revome from list
        assertEquals(this.testP.getBackPack().size(),5);
    }
    @Test
    public void takeItemInTheBackPackOutOfBoundExept() throws ArrayIndexOutOfBoundsException, ItemDoesNotExistExeption {
        //on remplit le sac
        for(int i = 0;i<6;i++){
            this.testP.putItemInBackPack(this.testI);
        }
        assertThrows(ArrayIndexOutOfBoundsException.class,()->this.testP.takeItemInTheBackPack(10));
        assertThrows(ArrayIndexOutOfBoundsException.class,()->this.testP.takeItemInTheBackPack(-10));
    }


    @Test
    public void TestgetRolesExept(){
        assertThrows(IndexOutOfBoundsException.class,()->this.testP.getRoles(10));
    }
    @Test
    public void TestSetRoles(){
        this.testP.setRoles(this.r1);
        assertEquals(this.testP.getRoles(0),this.r1);
    }

    @Test
    public  void TestTakeInHandFromBackPack() throws ItemDoesNotExistExeption{
        this.testP.putItemInBackPack(this.testI);
        this.testP.takeInHandFromBackPack(0);
        assertEquals(this.testP.getItemInHand(),this.testI);

    }

    @Test
    public  void TestTakeInHandFromBackPackIfAnItemIsAlreadyInYourHand() throws ItemDoesNotExistExeption{
        Item testI2 = new Item("Test2", false);
        this.testP.setItemInHand(testI2);
        this.testP.putItemInBackPack(this.testI);
        this.testP.takeInHandFromBackPack(0);
        assertTrue(this.testP.getBackPack().contains(testI2));
        assertEquals(this.testP.getItemInHand(),this.testI);

    }

    @Test
    public void testPutItemInHandInBackPackSucces(){
        this.testP.setItemInHand(this.testI);
        this.testP.PutItemInHandInBackPack();
        assertTrue(this.testP.getBackPack().contains(testI));
        assertNull(this.testP.getItemInHand());

    }
    @Test
    public void testPutItemInHandInBackPackFull(){
        //on remplit le sac
        for(int i = 0;i<6;i++){
            this.testP.putItemInBackPack(this.testI);
        }
        this.testP.setItemInHand(this.testI2);
        this.testP.PutItemInHandInBackPack();
        assertFalse(this.testP.getBackPack().contains(this.testI2));
        assertEquals(this.testP.getItemInHand(),this.testI2);

    }

    @Test
    public void testPutItemInHandInCell(){
        this.testP.setItemInHand(this.testI);
        this.testP.PutItemInHandInCell();
        assertNull(this.testP.getItemInHand());
        assertTrue(this.testCell.getAllItems().contains(this.testI));

    }

    @Test
    public void testPutItemInHandInCellIfItsAStreet(){
        this.testP.setItemInHand(this.testI);
        Street s1 = new Street(5,5);
        Player p2 = new Player(3,s1,4,6);
        p2.PutItemInHandInCell();
        assertNull(p2.getItemInHand());
        assertFalse(s1.getAllItems().contains(this.testI));

    }

    @Test
    public void TestplayerTakeDamage(){
        this.testP.takeDamage(1);
        assertEquals(this.testP.getLifePoints(),4);
    }

    @Test
    public void TestPlayerTakeDamageHpBelow0(){
        //on remplit le sac
        for(int i = 0;i<6;i++){
            this.testP.putItemInBackPack(this.testI);
        }
        this.testP.setItemInHand(this.testI);
        this.testP.takeDamage(1000);
        assertTrue(this.testCell.getAllItems().size()>=7);
    }




}
