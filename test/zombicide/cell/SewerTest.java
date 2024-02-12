package zombicide.cell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class SewerTest {

	private Sewer sewer;

    
    @BeforeEach
    void setUp() {
    	sewer = new Sewer(5, 5);
    }
    
    @Test
    void testProductionZombie() {
    	assertEquals(0, sewer.getAllZombies().size());
    	
    	sewer.ProductionZombie(3);
    	
    	assertEquals(3, sewer.getAllZombies().size());
    }
    

}
