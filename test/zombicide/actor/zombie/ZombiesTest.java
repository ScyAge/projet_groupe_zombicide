package zombicide.actor.zombie;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import zombicide.cell.SpawnPlayers;

public class ZombiesTest {
	
	private SpawnPlayers SpawnPlayers;
	private Zombies z;
	
	
    @BeforeEach
    void setUp() {
    	SpawnPlayers = new SpawnPlayers(1, 1);
        z = new Gigantomachia(SpawnPlayers,1);

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
}
