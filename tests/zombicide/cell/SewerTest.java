package zombicide.cell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.util.Direction;

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
    
    @Test
    void testToStringLine1() {
    	assertEquals(sewer.toString(0),"     ");
    }
    
    

    void testToStringLine2SomeBodyInCell() {
    	sewer.addZombies(zombie);
    	assertEquals(sewer.toString(1)," "+"\u001B[33m" + "E" + "\u001B[0m"+"z1 ");
    }

	
    	
    @Test
    void testToStringLine2NoBodyInCell() {	
    	assertEquals(sewer.toString(1)," "+"\u001B[33m" + "E" + "\u001B[0m"+"   ");
    }
    
    
    
    void testToStringLine3SomeBodyInCell() {
    	sewer.addZombies(zombie);
    	assertEquals(sewer.toString(2)," "+"\u001B[33m" + "E" + "\u001B[0m"+"s1 ");

    }
    	
    @Test
    void testToStringLine3NoBodyInCell() {	
    	assertEquals(sewer.toString(2),"     ");

    
    }
    
    @Test
    void testToStringLine4() {
    	assertEquals(sewer.toString(3),"     ");
    }

}
