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

public class FirstAidKitTest {
	private ListChooser<Player> chooser;
	private Player player;
	private Board board;
	private Cell cell;
	private FirstAidKit firstAidKit;
	
	@BeforeEach
	public void init() {
		this.chooser = new RandomListChooser<>();
		this.firstAidKit= new FirstAidKit("FirstAidKit", false, chooser);
        this.board = new TrainingBoard();
        this.cell = this.board.getCellBoard(1,1);
        this.player= new Player(3,cell,0,5);
        this.cell.addPlayers(player);
	}
	
	@Test
	public void TestItemEffect() {
		Player p1= new Player(3,this.cell, 1,5);
		Player p2= new Player(3,this.cell, 2,5);
		int LP1= p1.getLifePoints();
		int LP2= p2.getLifePoints();
		this.cell.addPlayers(p1);
		this.cell.addPlayers(p2);
		this.firstAidKit.ItemEffect(this.player);
		assertTrue((LP1 +1) == p1.getLifePoints() || (LP2+1)==p2.getLifePoints());
		
	}
	
	@Test
	public void TestItemEffectWhenNoPlayerInCell() {
		int lp=this.player.getLifePoints();
		this.firstAidKit.ItemEffect(player);
		assertEquals(lp , this.player.getLifePoints() );
		assertFalse(firstAidKit.isUsed());
	}
	
}
