package zombicide.actor.actionZombie;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Gigantomachia;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;


public class AttackZombieTest {
	private Board b ;
	private Zombies z;
	private AttackZombie a;
	private Player p;
	private Player p2;
	
	@BeforeEach
	void setUp() {
		b = new Board(5,5);
		this.b.initBoard();
		z = new Gigantomachia(b.getCellBoard(0, 0),1);
		p = new Player(1,b.getCellBoard(0, 0),1,5);
		p2 = new Player(1,b.getCellBoard(0, 0),1,5);
		a = new AttackZombie();
		b.getCellBoard(0, 0).addZombies(z);
	}
	
	@Test
	public void actionTest() {
		a.action(z);
		assertEquals(p.getLifePoints(),1);
		b.getCellBoard(0, 0).addPlayers(p);
		a.action(z);
		assertEquals(p.getLifePoints(),-999);
		assertTrue(p.isDead());
	}
	
	
	@Test
	public void action2PlayersTest() {
		b.getCellBoard(0, 0).addPlayers(p);
		b.getCellBoard(0, 0).addPlayers(p2);
		a.action(z);
		assertTrue(p.getLifePoints() == -999 || p2.getLifePoints() == -999 );
	}
}
