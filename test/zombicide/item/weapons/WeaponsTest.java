package zombicide.item.weapons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import zombicide.actor.player.Player;
import zombicide.actor.zombie.Gigantomachia;
import zombicide.actor.zombie.Zombies;
import zombicide.board.*;
import zombicide.util.listchooser.RandomListChooser;


public class WeaponsTest {
	
	private Board b ;
	private Weapon w;
	private Zombies z;
	private Player p; 
	private Weapon wTest;
	
	
	@BeforeEach
    void setUp() {
    	b= new Board(5,5);
		w = new Weapon("gun",1,1,4,false,true,b);
		z = new Gigantomachia(b.getCellBoard(2, 2),1);
		p= new Player(5,b.getCellBoard(2, 2),1,1);
		b.getCellBoard(2, 2).addZombies(z);
		b.getCellBoard(2, 2).addPlayers(p);
		wTest = new Weapon("gun",4,2,0,false,true,b,new RandomListChooser<>());
    }
    
    @Test
    public void testGetter() {
    	assertTrue(w.getDamage()==1) ;
    	assertTrue(w.getRange()==1) ;
    	assertTrue(w.getTitle()=="gun") ;
    	assertTrue(w.getThreshold()==4) ;
    	assertTrue(w.getBreakDoor()==false) ;
    	assertTrue(w.isUsed()==false) ;
    }
    
    @Test
    public void testWhoCanAttack() {
    	assertTrue(w.WhoCanAttack(p).get(0)==z);
    	assertTrue(w.WhoCanAttack(p).size() == 1);
    }
    
    @Test
    public void testItemEffect() {
    	assertTrue(z.getLifePoints() == 1000);
    	wTest.ItemEffect(p);
		assertTrue(z.getLifePoints()==998);
		assertTrue(p.getCurrentCell().getNoise() == 2);
    }
    
    
}
