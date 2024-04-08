package zombicide.actor.actionPlayer.roles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import zombicide.actor.player.Player;
import zombicide.actor.zombie.Walker;
import zombicide.actor.zombie.Zombies;
import zombicide.board.TrainingBoard;
import zombicide.cell.Cell;
import zombicide.item.Item;
import zombicide.item.weapons.Carabine;
import zombicide.item.weapons.Gun;
import zombicide.item.weapons.Weapon;
import zombicide.util.listchooser.ListChooser;
import zombicide.util.listchooser.RandomListChooser;

public class CombattantTest {
	
	
	private Player player;
	private Zombies z1;
	private Zombies z2;
	private Combattant action;
    private ListChooser<Zombies> chooser;
    private TrainingBoard board;
    private Cell cell;
    private Item wp1;
    private Item wp2;
    
    @BeforeEach
	public void init() {
		 this.chooser = new RandomListChooser<>();
		 this.board = new TrainingBoard();
	     this.cell = this.board.getCellBoard(1,1);
	     this.player = new Player(3,this.cell,1,5);
	  
	     this.cell.addPlayers(this.player);
	     this.action= new Combattant(this.chooser, this.board );
	     this.wp1 = new Carabine();
	     this.wp2 = new Weapon("gun",4,0,1,0,false,true,1);
	     this.z1= new Walker(this.cell, 1);
	     this.z2= new Walker(this.cell, 2);
		 this.cell.addZombies(z1);
		 this.cell.addZombies(z2);
	}
	
    @Test
    public void ActionTestWithCarabine() {
    	this.player.setItemInHand(wp1);
    	int LP1 = this.z1.getLifePoints();
    	int LP2 = this.z2.getLifePoints();
    	this.action.action(this.player);
    	
    	
    	assertTrue(LP1== z1.getLifePoints() || LP2== z2.getLifePoints());
    	
    	
    }
    
    @Test 
    public void ActionTestWithGun() {
    	this.player.setItemInHand(wp2);
    	int LP1 = this.z1.getLifePoints();
    	int LP2 = this.z2.getLifePoints();
    	action.action(this.player);
    	
    	
    	assertTrue(LP1-1== z1.getLifePoints() || LP2-1== z2.getLifePoints());
    	assertTrue(z1.isDead()||z2.isDead());
    }
	
}
