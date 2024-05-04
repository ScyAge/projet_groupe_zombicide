package zombicide.actor.actionZombie;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.zombie.Gigantomachia;
import zombicide.actor.zombie.Zombie;
import zombicide.board.Board;
import zombicide.util.Direction;

public class MoveTest {
	
	private Board b ;
	private Zombie z;
	private MoveZ a;
	
	@BeforeEach
	void setUp() {
		b = new Board(5,5);
		this.b.initBoard();
		z = new Gigantomachia(b.getCellBoard(2, 2),1);
		a = new MoveZ(b);
		b.getCellBoard(0, 0).addZombies(z);
	}
	
	@Test
	public void moveTest() {
		assertTrue(a.IsActionPlayable(z));
		b.getCellBoard(1, 1).breakDoor(Direction.East);
		b.getCellBoard(1, 1).breakDoor(Direction.South);
		b.getCellBoard(1, 1).setNoise(50);
		a.action(z);
		assertEquals(1, z.getCurrentCell().getX());
		assertEquals(2, z.getCurrentCell().getY());
		a.action(z);
		assertEquals(1, z.getCurrentCell().getY());
		assertEquals(1, z.getCurrentCell().getX());
		b.getCellBoard(2, 2).setNoise(5000);
		a.action(z);
		assertEquals(2, z.getCurrentCell().getX());
		assertEquals(1, z.getCurrentCell().getY());
		a.action(z);
		assertEquals(2, z.getCurrentCell().getX());
		assertEquals(2, z.getCurrentCell().getY());
	}
	
	
	
}
