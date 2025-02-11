package zombicide.cell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombie;
import zombicide.item.Item;
import zombicide.item.weapons.Axe;

class StreetTest {
	
	private Street street;
    private Player player;
    private Zombie zombie;
    
    @BeforeEach
    void setUp() {
        street = new Street(5, 5);
        player = new Player(8,street,1,6);
        zombie = new Zombie(8,5,street,1,1);
    }
    
    @Test
    void testAddRemovePlayer() {
    	Player player1 = new Player(8,street,1,6);
    	street.addPlayers(player);
    	street.addPlayers(player1);
    	
    	assertEquals(2, street.getAllPlayers().size());
    	assertTrue(street.getAllPlayers().contains(player));
    	assertTrue(street.getAllPlayers().contains(player1));
    	
    	street.remove(player1);
    	assertEquals(1, street.getAllPlayers().size());
    	assertFalse(street.getAllPlayers().contains(player1));
    	
    }
    
    @Test
    void testAddRemoveZombie() {
    	Zombie zombie1 = new Zombie(8,5,street,1,1);
    	street.addZombies(zombie);
    	street.addZombies(zombie1);
    	
    	assertEquals(2, street.getAllZombies().size());
    	assertTrue(street.getAllZombies().contains(zombie));
    	assertTrue(street.getAllZombies().contains(zombie1));
    	
    	street.remove(zombie1);
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

	@Test
	public void TestRemoveItem(){
		Item i = new Axe();
		assertNull(this.street.removeItem(i));

	}

   
}
