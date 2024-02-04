package zombicide.cell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.item.HealingVial;
import zombicide.item.Item;
import zombicide.util.Door;

class DrugStoreTest {
	
	private DrugStore drugStore;
    private Player player;
    private Zombies zombie;
    private Item item;
    private HealingVial healingVial;
    
    @BeforeEach
    void setUp() {
    	drugStore = new DrugStore(1, 1);
        player = new Player();
        zombie = new Zombies();
        item = new Item();
        healingVial = new HealingVial();
    }
    
    @Test
    void testAddAndGetHealingVial() {
    	HealingVial healingVial2 = new HealingVial();
    	
    	drugStore.addHealingVial(healingVial);
    	drugStore.addHealingVial(healingVial2);
    	
    	assertEquals(2, drugStore.getHealingVials().size());
    	assertTrue(drugStore.getHealingVials().contains(healingVial));
    	assertTrue(drugStore.getHealingVials().contains(healingVial2));
    }

    @Test
    void testAddPlayer() {
    	assertEquals(0, drugStore.getAllPlayers().size());
        assertEquals(0, drugStore.getHealingVials().size());
        
        drugStore.addPlayers(player);
        
        assertEquals(1, drugStore.getAllPlayers().size());
        assertEquals(1, drugStore.getHealingVials().size());
    }
}

















