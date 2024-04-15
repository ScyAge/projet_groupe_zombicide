package zombicide.actor.actionPlayer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.player.Player;
import zombicide.board.TrainingBoard;
import zombicide.cell.Cell;
import zombicide.item.equipment.HealingVial;
import zombicide.item.equipment.MasterKey;
import zombicide.util.Direction;
import zombicide.util.listchooser.*;

public class UseEquipmentActionTest {
	private ListChooser<Direction> chooser;
	private TrainingBoard b;
	private Cell cell;
	private Player player;
	private MasterKey equipment;
	private UseEquipmentAction action;

	@BeforeEach
	public void init() {
		this.chooser= new RandomListChooser<>();
		this.b= new TrainingBoard();
		this.cell= b.getCellBoard(1, 1);
		this.player = new Player(3,this.cell,1,5);
		this.equipment= new MasterKey("MasterKey", true, this.chooser);
		this.action= new UseEquipmentAction();
	}
	
	@Test
	public void ActionNotPlayableTestNoItemInHand() {
		
		assertNull(this.player.getItemInHand());
		assertFalse(action.IsActionPlayable(this.player));
	}
	
	@Test
	public void ActionPlayableTestCanAttack() {
		this.player.setItemInHand(equipment);
		assertTrue(this.action.IsActionPlayable(this.player));
	}
	
	@Test
	public void ItemEffectTest() {
		this.equipment.ItemEffect(this.player);
		assertTrue(this.cell.getDoor(Direction.North).isBreak()|| this.cell.getDoor(Direction.South).isBreak() || 
				this.cell.getDoor(Direction.East).isBreak() || this.cell.getDoor(Direction.West).isBreak());
	}
	
	@Test
	public void TestAction(){
		this.player.setItemInHand(this.equipment);
		this.action.action(this.player);
		assertTrue(this.cell.getDoor(Direction.North).isBreak()|| this.cell.getDoor(Direction.South).isBreak() ||
				this.cell.getDoor(Direction.East).isBreak() || this.cell.getDoor(Direction.West).isBreak());
		assertEquals(this.player.getItemInHand(),null);
	}

	@Test
	public void TestToString(){
		assertEquals(this.action.toString(),"UseEquipment action");
	}
	
}
