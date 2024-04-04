package zombicide.actor.actionPlayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.player.Player;
import static org.junit.jupiter.api.Assertions.*;
import zombicide.actor.zombie.Gigantomachia;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;
import zombicide.item.weapons.Weapon;
import zombicide.util.listchooser.RandomListChooser;

public class AttackTest {
	
	private Weapon w;
	private Attack a;
	private Player p;
	private Board b ;
	private Zombies z;
	private TakeInHandAction t;
	
	@BeforeEach
	void setUp() {
		b = new Board(5,5);
		z = new Gigantomachia(b.getCellBoard(0, 0),1);
		p = new Player(5,b.getCellBoard(0, 0),1,1);
		w = new Weapon("gun",4,2,0,false,true,b,1,new RandomListChooser<>());
		a = new Attack(new RandomListChooser<>());
		b.getCellBoard(0, 0).addPlayers(p);
		b.getCellBoard(0, 0).addZombies(z);
		t = new TakeInHandAction(new RandomListChooser<>());
	}
	
	@Test
	public void testAction() {
		a.action(p);
		assertTrue(z.getLifePoints() == 1000);
		p.putItemInBackPack(w);
		t.action(p);
		a.action(p);
		assertTrue(z.getLifePoints()==998);
		assertTrue(p.getCurrentCell().getNoise() == 2);
	}
}
