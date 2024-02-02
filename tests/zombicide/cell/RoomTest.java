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
        player = new Player();
        zombie = new Zombies();
        item = new Item();
        door = new Door(true);
    }
    
    @Test
    void testSetDirection() {
        room.setDirection(Direction.North, door);
        assertEquals(door, room.getDoors().get(Direction.North));
    }
    
    @Test
    void testGetAllsItems() {
    	Item item2 = new Item();
    	Item item3 = new Item();
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
    	Item item2 = new Item(); 
    	room.addItem(item);
    	room.addItem(item2);
    	assertTrue(room.getAllItems().contains(item));
    	assertTrue(room.getAllItems().contains(item2));
    	room.removeItem(item);
    	assertFalse(room.getAllItems().contains(item));
    	assertTrue(room.getAllItems().contains(item2));
    }
}
