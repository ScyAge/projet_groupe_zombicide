package zombicide.item.equipment;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.player.Player;
import zombicide.board.TrainingBoard;
import zombicide.cell.Cell;
import zombicide.util.Direction;
import zombicide.util.listchooser.ListChooser;
import zombicide.util.listchooser.NoneListChooser;
import zombicide.util.listchooser.RandomListChooser;

public class MasterKeyTest extends EquipmentTest{
	
	private MasterKey masterkey;
	private TrainingBoard board;
    private Cell cell;
    private Player p;
	private ListChooser<Direction> chooser;

	private MasterKey masterkey2;

	@Override
	protected Equipment createEquip() {
		return new MasterKey(this.chooser);
	}

	@BeforeEach
    public void initMast(){
		this.chooser = new RandomListChooser<>();
		this.masterkey= new MasterKey(chooser);
		this.masterkey2= new MasterKey();
        this.board = new TrainingBoard();
        this.cell = this.board.getCellBoard(1,1);
        this.p= new Player(1,this.cell, 0,0);
        
	}
	
	@Test
	public void TestEffectOfTheEquip() {
		
		this.masterkey.ItemEffect(this.p);
		assertTrue(this.cell.getDoor(Direction.North).isBreak()|| this.cell.getDoor(Direction.South).isBreak() || 
				this.cell.getDoor(Direction.East).isBreak() || this.cell.getDoor(Direction.West).isBreak());
		
	}

	@Test
	public void TestEffectOfTheEquipNoDirection() {
		ListChooser<Direction> chose = new NoneListChooser<>();
		MasterKey m = new MasterKey(chose);
		m.ItemEffect(this.p);
	}
	
	@Test 
	public void TestEffectOfTheEquipWhenAllDoorsOpen() {
		for(Direction d: Direction.values()) {
			this.cell.getDoor(d).Break();
		}
		this.masterkey.ItemEffect(this.p);
		assertFalse(this.masterkey.isUsed());
	}
}
