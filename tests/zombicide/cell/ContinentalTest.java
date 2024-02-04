package zombicide.cell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.item.Item;
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

}
