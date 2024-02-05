package zombicide.cell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.item.HealingVial;
import zombicide.item.Item;
import zombicide.util.Direction;
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
    
    @Test
    void testToStringLine1() {
    	/*Door north not break*/
    	assertEquals(drugStore.toString(0),"-----");
    	/*Door north  break*/
    	drugStore.getDoor(Direction.North).Break();
    	assertEquals(drugStore.toString(0),"     ");
    }
    
    

    void testToStringLine2SomeBodyInCell() {
    	drugStore.addZombies(zombie);
    	/*Door north not break*/
    	assertEquals(drugStore.toString(1),"|"+"\u001B[31m" +"D"+ "\u001B[0m"+"z1|");
    	/*Door north  break*/
    	drugStore.getDoor(Direction.East).Break();
    	drugStore.getDoor(Direction.West).Break();
    	assertEquals(drugStore.toString(1)," "+"\u001B[31m" +"D"+ "\u001B[0m"+"z1 ");
    }

	
    	
    @Test
    void testToStringLine2NoBodyInCell() {	
    	/*Door north not break*/
    	assertEquals(drugStore.toString(1),"|"+"\u001B[31m" +"D"+ "\u001B[0m"+"  |");
    	/*Door north  break*/
    	drugStore.getDoor(Direction.East).Break();
    	drugStore.getDoor(Direction.West).Break();
    	assertEquals(drugStore.toString(1)," "+"\u001B[31m" +"D"+ "\u001B[0m"+"   ");
    
    }
    
    
    
    void testToStringLine3SomeBodyInCell() {
    	drugStore.addZombies(zombie);
    	/*Door north not break*/
    	assertEquals(drugStore.toString(2),"|s1 |");
    	/*Door north  break*/
    	drugStore.getDoor(Direction.East).Break();
    	drugStore.getDoor(Direction.West).Break();
    	assertEquals(drugStore.toString(2)," s1  ");
    }
    	
    @Test
    void testToStringLine3NoBodyInCell() {	
    	/*Door north not break*/
    	assertEquals(drugStore.toString(2),"|   |");
    	/*Door north  break*/
    	drugStore.getDoor(Direction.East).Break();
    	drugStore.getDoor(Direction.West).Break();
    	assertEquals(drugStore.toString(2),"     ");
    
    }
    
    @Test
    void testToStringLine4() {
    	/*Door north not break*/
    	assertEquals(drugStore.toString(3),"-----");
    	/*Door north  break*/
    	drugStore.getDoor(Direction.South).Break();
    	assertEquals(drugStore.toString(3),"     ");
    }
}

















