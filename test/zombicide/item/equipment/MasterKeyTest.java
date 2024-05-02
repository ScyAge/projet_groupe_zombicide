package zombicide.item.equipment;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.player.Player;
import zombicide.board.TrainingBoard;
import zombicide.cell.Cell;
import zombicide.util.Direction;
import zombicide.util.Door;
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
		return new MasterKey(this.chooser,board);
	}

	@BeforeEach
    public void initMast(){
		this.board = new TrainingBoard();
		this.board.initBoard();
		this.chooser = new RandomListChooser<>();
		this.masterkey= new MasterKey(chooser, board);
		this.masterkey2= new MasterKey(board);
        this.cell = this.board.getCellBoard(1,1);
        this.p= new Player(1,this.cell, 0,0);
        
	}
	
	@Test
	public void TestEffectOfTheEquip() {
		
		this.masterkey.effectOfTheEquip(this.p);
		assertTrue(this.cell.getDoor(Direction.North).isBreak()|| this.cell.getDoor(Direction.South).isBreak() || 
				this.cell.getDoor(Direction.East).isBreak() || this.cell.getDoor(Direction.West).isBreak());
		Door nordOposeDoor = this.board.getCellDirection(Direction.North,this.p).getDoor(Direction.South);
		Door southOposeDoor = this.board.getCellDirection(Direction.South,this.p).getDoor(Direction.North);
		Door EastOposeDoor = this.board.getCellDirection(Direction.East,this.p).getDoor(Direction.West);
		Door WestOposeDoor = this.board.getCellDirection(Direction.West,this.p).getDoor(Direction.East);
		assertTrue(nordOposeDoor.isBreak() || southOposeDoor.isBreak() || EastOposeDoor.isBreak() || WestOposeDoor.isBreak());
	}

	@Test
	public void TestEffectOfTheEquipNoDirection() {
		ListChooser<Direction> chose = new NoneListChooser<>();
		MasterKey m = new MasterKey(chose,board );
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
