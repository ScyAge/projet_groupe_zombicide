package zombicide.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.util.door.*;


class DoorTest {
	
	private Door dew;
	private Door dew2;
	private Door dns;
	private Door dns2;
	
	@BeforeEach
    void setUp() {
		dew =new East_west_door(true,false);
        dew2 =new East_west_door(false,false);
        dns =new North_South_door(true,false);
        dns2 =new North_South_door(false,false);
    }
    
    @Test
    public void TestBreakDoor() {
    	// for dew
    	assertFalse(dew.isBreak());
    	dew.Break();
    	assertTrue(dew.isBreak());
    	// for dew 2
    	assertFalse(dew2.isBreak());
    	dew2.Break();
    	assertFalse(dew2.isBreak());
    	// for dns 
    	assertFalse(dns.isBreak());
    	dns.Break();
    	assertTrue(dns.isBreak());
    	//for dns 2
    	assertFalse(dns2.isBreak());
    	dns2.Break();
    	assertFalse(dns2.isBreak());
    }
    @Test
    public void TestIsBreakeable() {
    	assertFalse(dew2.isBreakable());
    	assertTrue(dew.isBreakable());
    	assertFalse(dns2.isBreakable());
    	assertTrue(dns.isBreakable());
    }

	@Test
	public void testSetNotBreak(){
		dew.Break();
		assertTrue(dew.isBreak());
		dew.setNotBreak();
		assertFalse(dew.isBreak());
	}

}
