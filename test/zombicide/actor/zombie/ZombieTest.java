package zombicide.actor.zombie;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.cell.Room;

public class ZombieTest {
	
	private Room room;
	private Zombie z1;
	private Zombie z2;
	private Zombie z3;
	private Zombie z4;
	private Zombie z5;

	
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
		assertEquals(this.z1.toString(),"Gigantomachia has 1000 life Points and is at the cell (1,1)");
		assertEquals(this.z2.toString(),"Walker has 1 life Points and is at the cell (1,1)");
		assertEquals(this.z3.toString(),"Runner has 2 life Points and is at the cell (1,1)");
		assertEquals(this.z4.toString(),"Broom has 4 life Points and is at the cell (1,1)");
		assertEquals(this.z5.toString(),"Abominations has 6 life Points and is at the cell (1,1)");
		Zombie z = new Zombie(3,3,this.room,3,3);
		assertEquals(z.toString(),"Zombie has 3 life Points and is at the cell (1,1)");
	}
    
    
    
    
    
}
