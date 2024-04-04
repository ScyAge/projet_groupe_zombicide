package zombicide.actor.actionPlayer;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.player.Player;
import zombicide.board.Board;
import zombicide.cell.Cell;
import zombicide.cell.Room;
import zombicide.item.weapons.Axe;
import zombicide.item.weapons.Carabine;
import zombicide.util.listchooser.RandomListChooser;


public class MakeNoiseTest {
	private ActionsPlayer action;
    private Player p;
    private Cell cell;
    
    @BeforeEach
    public void init(){
    	Board board = new Board(5,5);
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
}
