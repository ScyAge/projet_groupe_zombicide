package zombicide.cell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.player.Player;
import zombicide.item.equipment.HealingVial;



class DrugStoreTest {
	
	private DrugStore drugStore;
    private Player player;

    private HealingVial healingVial;
    
    @BeforeEach
    void setUp() {
    	drugStore = new DrugStore(1, 1);
        player = new Player(8,drugStore,1,6);

        healingVial = new HealingVial("potion");;
    }
    
    @Test
    void testAddAndGetHealingVial() {
    	HealingVial healingVial2 = new HealingVial("potion");
    	
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

















