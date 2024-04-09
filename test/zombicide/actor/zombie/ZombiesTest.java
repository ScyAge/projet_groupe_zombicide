package zombicide.actor.zombie;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.cell.Room;

public class ZombiesTest {
	
	private Room room;
	private Zombies z1;
	private Zombies z2;
	private Zombies z3;
	private Zombies z4;
	private Zombies z5;

	
    @BeforeEach
    void setUp() {
    	room = new Room(1, 1 );
        z1 = new Gigantomachia(room,1);
		z2 = new Walker(room,2);
		z3 = new Runner(room,3);
		z4 = new Broom(room,4);
		z5 = new Abominations(room,5);

    }
    
    @Test
    void testTakeDommage() {
    	assertEquals(1000, z1.getLifePoints());
    	z1.takeDamage(100);
    	z4.takeDamage(1);
    	z5.takeDamage(1);
    	assertEquals(900, z1.getLifePoints());
    	assertEquals(4, z4.getLifePoints());
    	assertEquals(6, z5.getLifePoints());
    	z4.takeDamage(2);
    	z5.takeDamage(2);
    	assertEquals(2, z4.getLifePoints());
    	assertEquals(4, z5.getLifePoints());
    }
    
    @Test
    void testIsDead() {
    	assertEquals(1, z2.getLifePoints());
    	z2.takeDamage(1000);
    	assertTrue(z2.isDead());
    }
    
    @Test
    void testSetHP() {
    	assertEquals(2, z3.getLifePoints());
    	z3.takeDamage(1);
    	assertEquals(1, z3.getLifePoints());
		z3.setLifePoints(1);
    	assertEquals(2, z3.getLifePoints());
		z3.setLifePoints(91);
    	assertEquals(2, z3.getLifePoints());
		z3.takeDamage(1000);
    	assertEquals(-998, z3.getLifePoints());
    	assertTrue(z3.isDead());
    }
	@Test
	public void TestGetdamagePoint(){
		assertEquals(z4.getDamagePoints(),2);
	}
	@Test
	public void  TestSetDamagePoint(){
		z5.setDamagePoints(100);
		assertEquals(z5.getDamagePoints(),100);
	}
	@Test
	public void testToString(){
		assertEquals(this.z1.toString(),"Gigantomachia have 1000 life Points");
		assertEquals(this.z2.toString(),"Walker have 1 life Points");
		assertEquals(this.z3.toString(),"Runner have 2 life Points");
		assertEquals(this.z4.toString(),"Broom have 4 life Points");
		assertEquals(this.z5.toString(),"Abominations have 6 life Points");
		Zombies z = new Zombies(3,3,this.room,3,3);
		assertEquals(z.toString(),"Zombie have 3 life Points");
	}
    
    
    
    
    
}
