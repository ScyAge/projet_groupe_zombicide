package zombicide.item.equipment;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import zombicide.actor.player.Player;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.cell.Cell;
import zombicide.util.listchooser.ListChooser;
import zombicide.util.listchooser.RandomListChooser;

public class FirstAidKitTest extends EquipmentTest{
	private static final ListChooser<Player> chooser = new RandomListChooser<>();
	private Player player;
	private Board board;
	private Cell cell;
	private FirstAidKit firstAidKit;


	@Override
	protected Equipment createEquip() {
		return new FirstAidKit(chooser);
	}

	@BeforeEach
	public void init3() {
		this.firstAidKit= new FirstAidKit(chooser);
        this.board = new TrainingBoard();
		this.board.initBoard();
        this.cell = this.board.getCellBoard(1,1);
        this.player= new Player(3,cell,0,5);
        this.cell.addPlayers(player);
	}
	
	@Test
	public void TestEffectOfTheEquip() {
		Player p1= new Player(3,this.cell, 1,5);
		Player p2= new Player(3,this.cell, 2,5);
		p1.takeDamage(1);
		p2.takeDamage(1);
		this.player.takeDamage(1);
		int LP1= p1.getLifePoints();
		int LP2= p2.getLifePoints();
		int LP0 = this.player.getLifePoints();
		this.cell.addPlayers(p1);
		this.cell.addPlayers(p2);
		this.firstAidKit.ItemEffect(this.player);
		assertTrue((LP1 +1) == p1.getLifePoints() || (LP2+1)==p2.getLifePoints() || (LP0 +1) == this.player.getLifePoints());
		
	}

	@Test
	public void TestEffectOfTheEquipWhenNoPlayerInCell() {
		int lp=this.player.getLifePoints();
		this.firstAidKit.effectOfTheEquip(player);
		assertEquals(lp , this.player.getLifePoints() );

	}

	
	@Test
	public void itemDescriptionTest() {
		String expected = "heal one of the survivors in your zone";
		String res= this.firstAidKit.itemDescription();
		assertEquals(expected, res);
	}
}
