package zombicide.cell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.item.Item;
import zombicide.util.Direction;
import zombicide.util.Door;

class RoomTest {
	
	private Room room;
    private Player player;
    private Zombies zombie;
    private Item item;
    private Door door;
    
    @BeforeEach
    void setUp() {
        room = new Room(1, 1);
        player = new Player(8,5);
        zombie = new Zombies();
        item = new Item("pistoler", 1);
    }
    
    @Test
    void testAddRemovePlayer() {
    	Player player1 = new Player(8,5);
    	room.addPlayers(player);
    	room.addPlayers(player1);
    	
    	assertEquals(2, room.getAllPlayers().size());
    	assertTrue(room.getAllPlayers().contains(player));
    	assertTrue(room.getAllPlayers().contains(player1));
    	
    	room.RemovePlayer(player1);
    	assertEquals(1, room.getAllPlayers().size());
    	assertFalse(room.getAllPlayers().contains(player1));
    	
    }
    
    @Test
    void testAddRemoveZombie() {
    	Zombies zombie1 = new Zombies();
    	room.addZombies(zombie);
    	room.addZombies(zombie1);
    	
    	assertEquals(2, room.getAllZombies().size());
    	assertTrue(room.getAllZombies().contains(zombie));
    	assertTrue(room.getAllZombies().contains(zombie1));
    	
    	room.RemoveZombie(zombie1);
    	assertEquals(1, room.getAllZombies().size());
    	assertFalse(room.getAllZombies().contains(zombie1));
    	
    }
    
    @Test
    void testCoordinates() {
    	Room room1 = new Room(4,6);
    	assertEquals(4, room1.getX());
    	assertEquals(6, room1.getY());
    }
    
    @Test
    void testCanLook() {
    	assertTrue(room.canLook());
    }
    
    @Test
    void testSetDirection() {
        room.setDirection(Direction.North, door);
        assertEquals(door, room.getDoors().get(Direction.North));
    }
    
    @Test
    void testGetAllAndAddItems() {
    	Item item2 = new Item("potion",1);
    	Item item3 = new Item("hache",1);
    	
    	room.addItem(item);
    	room.addItem(item2);
    	room.addItem(item3);
    	
    	assertEquals(3, room.getAllItems().size());
    	assertTrue(room.getAllItems().contains(item));
    	assertTrue(room.getAllItems().contains(item2));
    	assertTrue(room.getAllItems().contains(item3));
    }

    @Test
    void testGetDoors() {
    	room.setDirection(Direction.North, door);
    	assertEquals(door, room.getDoors().get(Direction.North));
    }
    
    @Test
    void testRemoveItem() {
    	Item item2 = new Item("potion",1); 
    	
    	room.addItem(item);
    	room.addItem(item2);
    	
    	assertTrue(room.getAllItems().contains(item));
    	assertTrue(room.getAllItems().contains(item2));
    	
    	room.removeItem(item);
    	
    	assertFalse(room.getAllItems().contains(item));
    	assertTrue(room.getAllItems().contains(item2));
    }
   
}
