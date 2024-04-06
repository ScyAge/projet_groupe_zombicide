package zombicide.actor.actionPlayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.player.Player;
import static org.junit.jupiter.api.Assertions.*;
import zombicide.actor.zombie.Gigantomachia;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;
import zombicide.item.weapons.Weapon;
import zombicide.util.Direction;
import zombicide.util.listchooser.RandomListChooser;

public class AttackTest {
	
	private Weapon w;
	private Weapon w2;
	private Attack a;
	private Player p;
	private Board b ;
	private Zombies z;
	private TakeInHandAction t;
	
	@BeforeEach
	void setUp() {
		b = new Board(5,5);
		z = new Gigantomachia(b.getCellBoard(2, 2),1);
		p = new Player(5,b.getCellBoard(2, 2),1,1);
		w = new Weapon("gun",4,0,2,0,false,true,1);
		w2 = new Weapon("gun",4,0,2,6,false,true,2);
		a = new Attack(new RandomListChooser<>(),b);
		b.getCellBoard(2, 2).addPlayers(p);
		b.getCellBoard(2, 2).addZombies(z);
		t = new TakeInHandAction(new RandomListChooser<>());
	}
	
	@Test
	public void testAction() {
		a.action(p);
		int noise = p.getCurrentCell().getNoise();
		assertEquals(z.getLifePoints() ,1000);
		p.putItemInBackPack(w);
		t.action(p);
		a.action(p);
		assertEquals(z.getLifePoints(),998);
		assertEquals(noise+1,p.getCurrentCell().getNoise());
		p.putItemInBackPack(w2);
		t.action(p);
		a.action(p);
		assertEquals(z.getLifePoints(),998);
		assertEquals(noise+1,p.getCurrentCell().getNoise());
	}
	
	
    @Test
    public void testWhoCanAttack() {   
    	b.getBoard()[2][2].breakDoor(Direction.North);
    	b.getBoard()[2][2].breakDoor(Direction.South);
    	b.getBoard()[2][2].breakDoor(Direction.East);
    	b.getBoard()[2][2].breakDoor(Direction.West);
    	
        assertSame(a.WhoCanAttack(p,w).get(0), z);
        assertEquals(1, a.WhoCanAttack(p,w).size());
        b.getCellBoard(2,3).addZombies(z);
        assertEquals(2, a.WhoCanAttack(p,w).size());
    }
}
