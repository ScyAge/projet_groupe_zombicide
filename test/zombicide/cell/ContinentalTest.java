package zombicide.cell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.item.Item;
import zombicide.util.Direction;
import zombicide.util.Door;

class ContinentalTest {

	private Continental continental;
    private Player player;
    private Zombies zombie;
    private Item item;
    
    
    @BeforeEach
    void setUp() {
        continental = new Continental(1, 1);
        player = new Player();
        zombie = new Zombies();
        item = new Item();
    }
    
    @Test
    void testCanLook() {
    	assertFalse(continental.canLook());
    }
    
    @Test
    void testToStringLine1() {
    	/*Door north not break*/
    	assertEquals(continental.toString(0),"-----");
    	/*Door north  break*/
    	continental.getDoor(Direction.North).Break();
    	assertEquals(continental.toString(0),"     ");
    }
    
    

    void testToStringLine2SomeBodyInCell() {
    	continental.addZombies(zombie);
    	/*Door north not break*/
    	assertEquals(continental.toString(1),"|"+"\u001B[32m" +"C"+ "\u001B[0m"+"z1|");
    	/*Door north  break*/
    	continental.getDoor(Direction.East).Break();
    	continental.getDoor(Direction.West).Break();
    	assertEquals(continental.toString(1)," "+"\u001B[32m" +"C"+ "\u001B[0m"+"z1 ");
    }

	
    	
    @Test
    void testToStringLine2NoBodyInCell() {	
    	/*Door north not break*/
    	assertEquals(continental.toString(1),"|"+"\u001B[32m" +"C"+ "\u001B[0m"+"  |");
    	/*Door north  break*/
    	continental.getDoor(Direction.East).Break();
    	continental.getDoor(Direction.West).Break();
    	assertEquals(continental.toString(1)," "+"\u001B[32m" +"C"+ "\u001B[0m"+"   ");
    
    }
    
    
    
    void testToStringLine3SomeBodyInCell() {
    	continental.addZombies(zombie);
    	/*Door north not break*/
    	assertEquals(continental.toString(2),"|s1 |");
    	/*Door north  break*/
    	continental.getDoor(Direction.East).Break();
    	continental.getDoor(Direction.West).Break();
    	assertEquals(continental.toString(2)," s1  ");
    }
    	
    @Test
    void testToStringLine3NoBodyInCell() {	
    	/*Door north not break*/
    	assertEquals(continental.toString(2),"|   |");
    	/*Door north  break*/
    	continental.getDoor(Direction.East).Break();
    	continental.getDoor(Direction.West).Break();
    	assertEquals(continental.toString(2),"     ");
    
    }
    
    @Test
    void testToStringLine4() {
    	/*Door north not break*/
    	assertEquals(continental.toString(3),"-----");
    	/*Door north  break*/
    	continental.getDoor(Direction.South).Break();
    	assertEquals(continental.toString(3),"     ");
    }
    

}
