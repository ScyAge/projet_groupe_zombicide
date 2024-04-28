package zombicide.item.weapons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import zombicide.actor.player.Player;
import zombicide.actor.zombie.Gigantomachia;
import zombicide.actor.zombie.Zombies;
import zombicide.board.*;
import zombicide.item.Item;
import zombicide.item.ItemTest;
import zombicide.util.listchooser.RandomListChooser;


public class WeaponsTest extends ItemTest {
	
	private Board b ;
	private Weapon w;
	private Zombies z;
	private Player p; 
	private Weapon wTest;

    @Override
    protected Item createItem() {
        return new Gun();
    }

    @BeforeEach
    void setUp() {
    	b= new Board(5,5);
		w = new Weapon("gun",1,0,1,4,false,true,2);
		z = new Gigantomachia(b.getCellBoard(2, 2),1);
		p= new Player(5,b.getCellBoard(2, 2),1,1);
		b.getCellBoard(2, 2).addZombies(z);
		b.getCellBoard(2, 2).addPlayers(p);

    }
    
    @Test
    public void testGetter() {
        assertEquals(1, w.getDamage());
        assertEquals(1, w.getRange());
        assertSame("gun", w.getTitle());
        assertEquals(4, w.getThreshold());
        assertFalse(w.getBreakDoor());
        assertFalse(w.isUsed());
    }
    
    @Test
    public void testGun() {
        wTest = new Gun();
        assertEquals("gun", wTest.getTitle());
        assertEquals(1, wTest.getRange());
        assertEquals(0, wTest.getMinRange());
        assertEquals(1, wTest.getDamage());
        assertEquals(4, wTest.getThreshold());
        assertTrue(wTest.getBreakDoor());
        assertTrue(wTest.isNoisy());
        assertEquals(1, wTest.getNbDice());
    }
    
    @Test
    public void testCrowbar() {
        wTest = new Crowbar();
        assertEquals("crowbar", wTest.getTitle());
        assertEquals(0, wTest.getRange());
        assertEquals(0, wTest.getMinRange());
        assertEquals(1, wTest.getDamage());
        assertEquals(4, wTest.getThreshold());
        assertTrue(wTest.getBreakDoor());
        assertFalse(wTest.isNoisy());
        assertEquals(1, wTest.getNbDice());
    }
    
    @Test
    public void testChainsaw() {
        wTest = new Chainsaw();
        assertEquals("chainsaw", wTest.getTitle());
        assertEquals(0, wTest.getRange());
        assertEquals(0, wTest.getMinRange());
        assertEquals(3, wTest.getDamage());
        assertEquals(5, wTest.getThreshold());
        assertTrue(wTest.getBreakDoor());
        assertTrue(wTest.isNoisy());
        assertEquals(2, wTest.getNbDice());
    }
    
    @Test
    public void testCarabine() {
        wTest = new Carabine();
        assertEquals("carabine", wTest.getTitle());
        assertEquals(3, wTest.getRange());
        assertEquals(1, wTest.getMinRange());
        assertEquals(1, wTest.getDamage());
        assertEquals(4, wTest.getThreshold());
        assertFalse(wTest.getBreakDoor());
        assertTrue(wTest.isNoisy());
        assertEquals(2, wTest.getNbDice());
    }
    
    @Test
    public void testAxe() {
        wTest = new Axe();
        assertEquals("axe", wTest.getTitle());
        assertEquals(0, wTest.getRange());
        assertEquals(0, wTest.getMinRange());
        assertEquals(2, wTest.getDamage());
        assertEquals(4, wTest.getThreshold());
        assertTrue(wTest.getBreakDoor());
        assertFalse(wTest.isNoisy());
        assertEquals(1, wTest.getNbDice());
    }

    @Test
    public void TestUsed(){
        assertFalse(this.w.isUsed());
        this.w.Used();
        assertTrue(this.w.isUsed());
    }

    @Test
    public void TestClone(){
        Item arme = new Gun();
        try {
            Item armeBis = arme.clone();
            assertNotSame(arme, armeBis);
            assertSame(arme.getClass(),armeBis.getClass());
            assertEquals(arme, armeBis);
            arme.Used();
            assertNotEquals(arme.isUsed(),armeBis.isUsed());

        }
        catch (CloneNotSupportedException err){
            System.out.println(System.err);
        }

    }
    
    
}

