package zombicide.cell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.util.Direction;
import zombicide.util.Door;
import zombicide.util.door.North_South_door;

class StreetTest {
	
	private Street street;
    private Player player;
    private Door door;
    private Zombies zombie;
    
    @BeforeEach
    void setUp() {
        street = new Street(5, 5);
        player = new Player(8);
        zombie = new Zombies(8,5,1);
        door =  new North_South_door(true,true);
    }
    
    @Test
    void testAddRemovePlayer() {
    	Player player1 = new Player(8);
    	street.addPlayers(player);
    	street.addPlayers(player1);
    	
    	assertEquals(2, street.getAllPlayers().size());
    	assertTrue(street.getAllPlayers().contains(player));
    	assertTrue(street.getAllPlayers().contains(player1));
    	
    	street.RemovePlayer(player1);
    	assertEquals(1, street.getAllPlayers().size());
    	assertFalse(street.getAllPlayers().contains(player1));
    	
    }
    
    @Test
    void testAddRemoveZombie() {
    	Zombies zombie1 = new Zombies(8,5,1);
    	street.addZombies(zombie);
    	street.addZombies(zombie1);
    	
    	assertEquals(2, street.getAllZombies().size());
    	assertTrue(street.getAllZombies().contains(zombie));
    	assertTrue(street.getAllZombies().contains(zombie1));
    	
    	street.RemoveZombie(zombie1);
    	assertEquals(1, street.getAllZombies().size());
    	assertFalse(street.getAllZombies().contains(zombie1));
    	
    }
    
    @Test
    void testCoordinates() {
    	Street street1 = new Street(4,6);
    	assertEquals(4, street1.getX());
    	assertEquals(6, street1.getY());
    }
    
    @Test
    void testCanLook() {
    	assertTrue(street.canLook());
    }
    void testGetDoors() {
    	assertEquals(door, street.getDoors().get(Direction.North));
    }
   
}
