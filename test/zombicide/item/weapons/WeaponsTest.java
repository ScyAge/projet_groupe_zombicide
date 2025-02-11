package zombicide.item.weapons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import zombicide.actor.player.Player;
import zombicide.actor.zombie.Gigantomachia;
import zombicide.actor.zombie.Zombie;
import zombicide.board.*;
import zombicide.item.Item;
import zombicide.item.ItemTest;


public class WeaponsTest extends ItemTest {
	
	private Board b ;
	private Weapon w;
	private Zombie z;
	private Player p; 
	private Weapon wTest;

    @Override
    protected Item createItem() {
        return new Gun();
    }

    @BeforeEach
    void setUp() {
    	b= new Board(5,5);
        this.b.initBoard();
		w = new Weapon(1,0,1,4,false,true,2);
		z = new Gigantomachia(b.getCellBoard(2, 2),1);
		p= new Player(5,b.getCellBoard(2, 2),1,1);
		b.getCellBoard(2, 2).addZombies(z);
		b.getCellBoard(2, 2).addPlayers(p);

    }

    
    @Test
    public void testGetter() {
        assertEquals(1, w.getDamage());
        assertEquals(1, w.getMaxRange());
        assertEquals(4, w.getThreshold());
        assertFalse(w.getBreakDoor());
        assertFalse(w.isUsed());
    }
    
    @Test
    public void testGun() {
        wTest = new Gun();
        assertEquals(1, wTest.getMaxRange());
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
        assertEquals(0, wTest.getMaxRange());
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
        assertEquals(0, wTest.getMaxRange());
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
        assertEquals(3, wTest.getMaxRange());
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
        assertEquals(0, wTest.getMaxRange());
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
    
    @Test
    public void TestItemEffect() {
    	assertEquals(false, this.w.isUsed());
    	this.w.ItemEffect(p);
    	assertEquals(false, this.w.isUsed());
    }

    @Test
	public void itemDescriptionTest() {
		String expected = "damageValue=1, canBreakDoor=false";
		String res= this.w.itemDescription();
		assertEquals(expected, res);
	}
    
}

