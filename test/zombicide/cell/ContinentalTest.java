package zombicide.cell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class ContinentalTest {

	private Continental continental;

    
    
    @BeforeEach
    void setUp() {
        continental = new Continental(1, 1);

    }
    
    @Test
    void testCanLook() {
    	assertFalse(continental.canLook());
    }
}
