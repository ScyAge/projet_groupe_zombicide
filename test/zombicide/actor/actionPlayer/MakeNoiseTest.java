package zombicide.actor.actionPlayer;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.player.Player;
import zombicide.cell.Cell;
import zombicide.cell.Room;



public class MakeNoiseTest {
	private ActionsPlayer action;
    private Player p;
    private Cell cell;
    
    @BeforeEach
    public void init(){
    	
        this.cell = new Room(3,3);
        this.p = new Player(3,this.cell,1,6);
        this.action=new MakeNoise();
    }
    
    @Test
    public void TestMakeNoise() {
    	int noise= this.cell.getNoise();
    	this.action.action(p);
    	assertEquals(noise+1, this.cell.getNoise());
    }
    
    @Test
    public void TestDecreaseActionPoints() {
    	int actionPoints= this.p.getAction_points();
    	this.action.action(p);
    	assertEquals(actionPoints-1, this.p.getAction_points());
    }

    @Test
    public void testToString(){
        assertEquals(this.action.toString(),"MakeNoise action");
    }
}
