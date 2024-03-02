package zombicide.cell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.zombie.*;


class SewerTest {

	private Sewer sewer;
	private Zombies z;
    
    @BeforeEach
    void setUp() {
    	sewer = new Sewer(5, 5);
    	z= new Gigantomachia(sewer,1);
    }
    
    @Test
    void testProductionZombie() {
    	assertEquals(0, sewer.getAllZombies().size());
    	
    	sewer.ProductionZombie(3,z);
    	
    	assertEquals(3, sewer.getAllZombies().size());
    }
    

}
