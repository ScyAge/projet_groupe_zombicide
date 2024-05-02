package zombicide.item.equipment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.cell.Cell;
import zombicide.cell.Continental;


public class GlassesTest extends EquipmentTest{
	
	private Glasses glasses;
	private Player player;
	private Board board;
	private Cell cell;


	@Override
	protected Equipment createEquip() {
		return new Glasses(this.board);
	}

	@BeforeEach
	public void initG() {
        this.board = new TrainingBoard();
		this.board.initBoard();
        this.cell = this.board.getCellBoard(1,1);
        this.player= new Player(3,cell,0,5);
        this.cell.addPlayers(player);
        this.glasses= new Glasses(this.board);
	}
	
	@Test
	public void TestActorsAround() {
		Cell adjCell1 = this.board.getCellBoard(1, 0);
		Cell adjCell2 = this.board.getCellBoard(1, 2);
		Cell adjCell3 = this.board.getCellBoard(0, 1);
		
		Player p1 = new Player(3, adjCell1,1,5);
		Player p2 = new Player(3,adjCell2,2,5);
		Player p3 = new Player(3,adjCell3,3,5);
		
		Zombies z1 = new Zombies(5,2,adjCell1,1,5);
		Zombies z2 = new Zombies(5,2,adjCell2,2,5);
		Zombies z3 = new Zombies(5,2,adjCell3,3,5);
		
		adjCell1.addPlayers(p1);
		adjCell1.addZombies(z1);
		
		adjCell2.addPlayers(p2);
		adjCell2.addZombies(z2);
		
		adjCell3.addPlayers(p3);
		adjCell3.addZombies(z3);
		
		
		String got= this.glasses.actorsAround(this.player);
		System.out.println(got);
		
		assertTrue(got.contains("Players in cell (1, 0): \nP" + p1.getId()) || got.contains("You can't look in the room"));
		assertTrue(got.contains("Zombies in cell (1, 0): \nZ" + z1.getId())|| got.contains("You can't look in the room"));
		
		assertTrue(got.contains("Players in cell (1, 2): \nP" + p2.getId())|| got.contains("You can't look in the room"));
		assertTrue(got.contains("Zombies in cell (1, 2): \nZ" + z2.getId())|| got.contains("You can't look in the room"));
		
		assertTrue(got.contains("Players in cell (0, 1): \nP" + p3.getId()) || got.contains("You can't look in the room"));
		assertTrue(got.contains("Zombies in cell (0, 1): \nZ" + z3.getId())|| got.contains("You can't look in the room"));
	}

	@Test
	public void TestEffectOfTheEquip() {
		Cell adjCell1 = this.board.getCellBoard(1, 0);
		Cell adjCell2 = this.board.getCellBoard(1, 2);
		Cell adjCell3 = this.board.getCellBoard(0, 1);

		Player p1 = new Player(3, adjCell1,1,5);
		Player p2 = new Player(3,adjCell2,2,5);
		Player p3 = new Player(3,adjCell3,3,5);

		Zombies z1 = new Zombies(5,2,adjCell1,1,5);
		Zombies z2 = new Zombies(5,2,adjCell2,2,5);
		Zombies z3 = new Zombies(5,2,adjCell3,3,5);

		adjCell1.addPlayers(p1);
		adjCell1.addZombies(z1);

		adjCell2.addPlayers(p2);
		adjCell2.addZombies(z2);

		adjCell3.addPlayers(p3);
		adjCell3.addZombies(z3);


		this.glasses.ItemEffect(this.player);

	}

	@Test
	public void TestActorsAroundWithContinentalNear(){
		this.board.getBoard()[1][2] = new Continental(1,2);

		Cell adjCell1 = this.board.getCellBoard(1, 0);
		Cell adjCell2 = this.board.getCellBoard(1, 2);
		Cell adjCell3 = this.board.getCellBoard(0, 1);

		Player p1 = new Player(3, adjCell1,1,5);
		Player p2 = new Player(3,adjCell2,2,5);
		Player p3 = new Player(3,adjCell3,3,5);

		Zombies z1 = new Zombies(5,2,adjCell1,1,5);
		Zombies z2 = new Zombies(5,2,adjCell2,2,5);
		Zombies z3 = new Zombies(5,2,adjCell3,3,5);

		adjCell1.addPlayers(p1);
		adjCell1.addZombies(z1);

		adjCell2.addPlayers(p2);
		adjCell2.addZombies(z2);

		adjCell3.addPlayers(p3);
		adjCell3.addZombies(z3);


		String got= this.glasses.actorsAround(this.player);
		System.out.println(got);

		assertTrue(got.contains("Players in cell (1, 0): \nP" + p1.getId()) || got.contains("You can't look in the room"));
		assertTrue(got.contains("Zombies in cell (1, 0): \nZ" + z1.getId())|| got.contains("You can't look in the room"));

		//for cell (1,2)
		assertTrue(got.contains("You can't look in the room"));

		assertTrue(got.contains("Players in cell (0, 1): \nP" + p3.getId()) || got.contains("You can't look in the room"));
		assertTrue(got.contains("Zombies in cell (0, 1): \nZ" + z3.getId())|| got.contains("You can't look in the room"));
	}
	
}
