package zombicide.item.weapons;

import org.junit.jupiter.api.BeforeEach;

import zombicide.actor.zombie.Gigantomachia;
import zombicide.actor.zombie.Zombies;
import zombicide.board.*;


public class WeaponsTest {
	
	private Board b ;
	private Weapon w;
	private Zombies z;
    @BeforeEach
    void setUp() {
    	b= new Board(5,5);
		w = new Gun(b);
		z = new Gigantomachia(b.getCellBoard(2, 2),1);

    }
}
