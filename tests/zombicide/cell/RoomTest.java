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


}
