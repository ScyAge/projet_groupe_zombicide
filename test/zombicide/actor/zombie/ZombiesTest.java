package zombicide.actor.zombie;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.cell.Room;
import zombicide.cell.SpawnPlayers;

public class ZombiesTest {
	
	private Room room;
	private Zombies z;
	
	
    @BeforeEach
    void setUp() {
    	room = new Room(1, 1 );
        z = new Gigantomachia(room,1);

    }
    
    @Test
    void testTakeDommage() {
    	assertEquals(1000, z.getLifePoints());
    	z.takeDamage(100);
    	assertEquals(900, z.getLifePoints());
    }
    
    @Test
    void testIsDead() {
    	assertEquals(1000, z.getLifePoints());
    	z.takeDamage(1000);
    	assertTrue(z.isDead());
    }
    
    @Test
    void testSetHP() {
    	assertEquals(1000, z.getLifePoints());
    	z.takeDamage(100);
    	assertEquals(900, z.getLifePoints());
    	z.setLifePoints(10);
    	assertEquals(910, z.getLifePoints());
    	z.setLifePoints(91);
    	assertEquals(1000, z.getLifePoints());
    	z.takeDamage(1000);
    	assertEquals(0, z.getLifePoints());
    	assertTrue(z.isDead());
    }
    
    
    
    
    
}
