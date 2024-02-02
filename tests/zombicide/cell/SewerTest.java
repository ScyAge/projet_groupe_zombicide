package zombicide.cell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;

class SewerTest {

	private Sewer sewer;
    private Player player;
    private Zombies zombie;
    
    @BeforeEach
    void setUp() {
    	sewer = new Sewer(5, 5);
        player = new Player();
        zombie = new Zombies();
    }
    
    @Test
    void testProductionZombie() {
    	assertEquals(0, sewer.getAllZombies().size());
    	
    	sewer.ProductionZombie(3);
    	
    	assertEquals(3, sewer.getAllZombies().size());
    }

}
