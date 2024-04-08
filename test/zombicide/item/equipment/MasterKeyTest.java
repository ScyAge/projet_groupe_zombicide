package zombicide.item.equipment;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.player.Player;
import zombicide.board.TrainingBoard;
import zombicide.cell.Cell;
import zombicide.util.Direction;
import zombicide.util.Door;
import zombicide.util.listchooser.InteractiveListChooser;
import zombicide.util.listchooser.ListChooser;
import zombicide.util.listchooser.NoneListChooser;
import zombicide.util.listchooser.RandomListChooser;

public class MasterKeyTest {
	
	private MasterKey masterkey;
	private TrainingBoard board;
    private Cell cell;
    private Player p;
	private ListChooser<Direction> chooser;

	private MasterKey masterkey2;
	
	@BeforeEach
    public void init(){
		this.chooser = new RandomListChooser<>();
		this.masterkey= new MasterKey("MasterKey", true, chooser);
		this.masterkey2= new MasterKey("MasterKey2", true);
        this.board = new TrainingBoard();
        this.cell = this.board.getCellBoard(1,1);
        this.p= new Player(1,this.cell, 0,0);
        
	}
	
	@Test
	public void TestItemEffect() {
		
		this.masterkey.ItemEffect(this.p);
		assertTrue(this.cell.getDoor(Direction.North).isBreak()|| this.cell.getDoor(Direction.South).isBreak() || 
				this.cell.getDoor(Direction.East).isBreak() || this.cell.getDoor(Direction.West).isBreak());
		
	}

	@Test
	public void TestItemEffectNoDirection() {
		ListChooser<Direction> chose = new NoneListChooser<>();
		MasterKey m = new MasterKey("MasterKey", true, chose);
		m.ItemEffect(this.p);
	}
	
	@Test 
	public void TestItemEffectWhenAllDoorsOpen() {
		for(Direction d: Direction.values()) {
			this.cell.getDoor(d).Break();
		}
		this.masterkey.ItemEffect(this.p);
		assertFalse(this.masterkey.isUsed());
	}
}
