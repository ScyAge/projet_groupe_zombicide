package zombicide.cell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Broom;
import zombicide.actor.zombie.Zombies;
import zombicide.item.Item;
import zombicide.item.equipment.HealingVial;
import zombicide.item.weapons.Axe;
import zombicide.item.weapons.Gun;
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
        player = new Player(8,room,1,6);
        zombie = new Zombies(8,5,room,1,1);
        item = new Gun();
    }
    
    @Test
    void testAddRemovePlayer() {
    	Player player1 = new Player(8,room,1,6);
    	room.addPlayers(player);
    	room.addPlayers(player1);
    	
    	assertEquals(2, room.getAllPlayers().size());
    	assertTrue(room.getAllPlayers().contains(player));
    	assertTrue(room.getAllPlayers().contains(player1));
    	
    	room.remove(player1);
    	assertEquals(1, room.getAllPlayers().size());
    	assertFalse(room.getAllPlayers().contains(player1));
    	
    }
    
    @Test
    void testAddRemoveZombie() {
    	Zombies zombie1 = new Zombies(8,5,room,1,1);
    	room.addZombies(zombie);
    	room.addZombies(zombie1);
    	
    	assertEquals(2, room.getAllZombies().size());
    	assertTrue(room.getAllZombies().contains(zombie));
    	assertTrue(room.getAllZombies().contains(zombie1));
    	
    	room.remove(zombie1);
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
    	Item item2 = new HealingVial("tt");
    	Item item3 = new Axe();
    	
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
    	Item item2 = new HealingVial("yy");
    	
    	room.addItem(item);
    	room.addItem(item2);
    	
    	assertTrue(room.getAllItems().contains(item));
    	assertTrue(room.getAllItems().contains(item2));
    	
    	room.removeItem(item);
    	
    	assertFalse(room.getAllItems().contains(item));
    	assertTrue(room.getAllItems().contains(item2));
    }

	@Test
	public void testResetNoise(){
		this.room.setNoise(4);
		assertEquals(this.room.getNoise(),4);
		this.room.resetNoise();
		assertEquals(this.room.getNoise(),0);
	}

	@Test
	public void testDescription(){
		this.room.addZombies(new Broom(this.room,2));
		String c = String.format("(%d,%d) %s [ Players : %d ; Zombies : %d]",this.room.getX(),this.room.getY(),this.room.getClass().getSimpleName(),this.room.getAllPlayers().size(),this.room.getAllZombies().size());
		assertEquals(this.room.description(),c);
	}
   
}
