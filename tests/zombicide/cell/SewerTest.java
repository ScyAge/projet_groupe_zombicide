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
    void testAddRemovePlayer() {
    	Player player1 = new Player();
    	sewer.addPlayers(player);
    	sewer.addPlayers(player1);
    	
    	assertEquals(2, sewer.getAllPlayers().size());
    	assertTrue(sewer.getAllPlayers().contains(player));
    	assertTrue(sewer.getAllPlayers().contains(player1));
    	
    	sewer.RemovePlayer(player1);
    	assertEquals(1, sewer.getAllPlayers().size());
    	assertFalse(sewer.getAllPlayers().contains(player1));
    	
    }
    
    @Test
    void testAddRemoveZombie() {
    	Zombies zombie1 = new Zombies();
    	sewer.addZombies(zombie);
    	sewer.addZombies(zombie1);
    	
    	assertEquals(2, sewer.getAllZombies().size());
    	assertTrue(sewer.getAllZombies().contains(zombie));
    	assertTrue(sewer.getAllZombies().contains(zombie1));
    	
    	sewer.RemoveZombie(zombie1);
    	assertEquals(1, sewer.getAllZombies().size());
    	assertFalse(sewer.getAllZombies().contains(zombie1));
    	
    }
    
    @Test
    void testCoordinates() {
    	Sewer sewer1 = new Sewer(4,6);
    	assertEquals(4, sewer1.getX());
    	assertEquals(6, sewer1.getY());
    }
    
    @Test
    void testCanLook() {
    	assertTrue(sewer.canLook());
    }
    
    @Test
    void testProductionZombie() {
    	assertEquals(0, sewer.getAllZombies().size());
    	
    	sewer.ProductionZombie(3);
    	
    	assertEquals(3, sewer.getAllZombies().size());
    }

}
