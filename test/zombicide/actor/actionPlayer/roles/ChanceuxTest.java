package zombicide.actor.actionPlayer.roles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Broom;

import zombicide.actor.zombie.Zombie;
import zombicide.board.TrainingBoard;
import zombicide.cell.Cell;
import zombicide.item.Item;

import zombicide.item.weapons.Weapon;
import zombicide.util.listchooser.ListChooser;
import zombicide.util.listchooser.RandomListChooser;

public class ChanceuxTest {
	
	private Player player;
	private Zombie z1;
	private Zombie z2;
	private Chanceux action;
    private ListChooser<Zombie> chooser;
    private TrainingBoard board;
    private Cell cell;
    private Item wp1;
    private Item wp2;
    
    @BeforeEach
	public void init() {
		 this.chooser = new RandomListChooser<>();
		 this.board = new TrainingBoard();
		 this.board.initBoard();
	     this.cell = this.board.getCellBoard(0,1);
	     this.player = new Player(3,this.cell,1,5);
	  
	     this.cell.addPlayers(this.player);
	     this.action= new Chanceux(this.chooser, this.board );
	     this.wp1 = new Weapon(0,0,2,0,false,true,1);
	     this.wp2 = new Weapon(4,0,1,0,false,true,1);
	     this.z1= new Broom(this.cell, 1);
	     this.z2= new Broom(this.cell, 2);
		 this.cell.addZombies(z1);
		 this.cell.addZombies(z2);
		 Chanceux bis = new Chanceux(this.board);
	}
	
    @Test
    public void ActionTestWithAxe() {
    	this.player.setItemInHand(wp1);
    	int LP1 = this.z1.getLifePoints();
    	int LP2 = this.z2.getLifePoints();
    	this.action.action(this.player);
    	
    	
    	assertTrue(LP1-4== z1.getLifePoints() || LP2-4== z2.getLifePoints() || (LP1-2== z1.getLifePoints() && LP2-2==z2.getLifePoints()) );
    	
    	
    }
    
    @Test 
    public void ActionTestWithGun() {
    	this.player.setItemInHand(wp2);
    	int LP1 = this.z1.getLifePoints();
    	int LP2 = this.z2.getLifePoints();
    	this.action.action(this.player);
    	
    	
    	assertEquals(z1.getLifePoints(), LP1 );
    	assertEquals(z2.getLifePoints(), LP2);
    	
    }

	@Test
	public void TestToString(){
		assertEquals(this.action.toString(),"Chanceux action");
	}
}
