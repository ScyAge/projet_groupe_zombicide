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

    
    
}
