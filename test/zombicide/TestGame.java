package zombicide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.actionPlayer.*;
import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombie;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.util.listchooser.RandomListChooser;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class TestGame {

    private Game game;
    private Player p1;
    private Player p2;
    private Player p3;
    private Player p4;
	private Zombie z1;
	private Zombie z2;
    private Board b;
    @BeforeEach
    public void init(){
        this.b = new TrainingBoard();
        b.initBoard();
        this.p1= new Player(5, b.getCellBoard(0,0), 1, 6);
        this.p2= new Player(5, b.getCellBoard(0,0), 2, 6);
        this.p3 = new Player(5, b.getCellBoard(0,0), 3, 6);
        this.p4 = new Player(5, b.getCellBoard(0,0), 4, 6);
        z1 = new Zombie(5,2,b.getCellBoard(0, 0),5,5);
    	z2 = new Zombie(5,2,b.getCellBoard(2, 2),5,5);
        List<Player> AllP = new ArrayList<>();
        AllP.add(this.p1);
        AllP.add(this.p2);
        AllP.add(this.p3);
        AllP.add(this.p4);
       

        ActionsPlayer take = new TakeInHandAction(new RandomListChooser<>());
        ActionsPlayer LA =new LookAround(b);
        ActionsPlayer OD = new OpenDoor(new RandomListChooser<>(),b);
        ActionsPlayer move = new Move(b,new RandomListChooser<>());
        ActionsPlayer noise = new MakeNoise();
        ActionsPlayer useEquip = new UseEquipmentAction();
        ActionsPlayer attack = new Attack(new RandomListChooser<>(),b);
        ActionsPlayer search = new SearchInTRoomAction(new RandomListChooser<>());
        List<ActionsPlayer> actions = new ArrayList<>();
        actions.add(take);
        actions.add(LA);
        actions.add(OD);
        actions.add(move);
        actions.add(noise);
        actions.add(useEquip);
        actions.add(attack);
        actions.add(search);

        this.game = new Game(this.b,AllP,actions,null,new RandomListChooser<>());
    }


    @Test
    public void TestAreTheyAllAlive(){
        //no player dead
        assertTrue(this.game.AreTheyAllAlive());

        //one dead
        this.p1.takeDamage(10000);
        assertTrue(this.p1.isDead());
        assertTrue(this.game.AreTheyAllAlive());

        //All dead
        this.p2.takeDamage(10000);
        this.p3.takeDamage(10000);
        this.p4.takeDamage(10000);
        assertTrue(this.p2.isDead());
        assertTrue(this.p3.isDead());
        assertTrue(this.p4.isDead());
        assertFalse(this.game.AreTheyAllAlive());

    }
    
    @Test 
    public void totalXPTest() {
    	assertEquals(4, this.game.totalXP());
    	p1.UpOneExpertiseLevel();
    	p2.UpOneExpertiseLevel();
    	p2.UpOneExpertiseLevel();
    	assertEquals(7,this.game.totalXP());
    	
    }
    
    @Test
    public void areZombiesAllAliveTest() {
    	List<Zombie> AllZ= this.b.getAllZombies();
    	AllZ.add(this.z1);
    	AllZ.add(this.z2);
    	assertTrue(this.game.areZombiesAllALive());
    	
    	AllZ.get(0).takeDamage(100);
    	assertTrue(z1.isDead());
    	assertTrue(this.game.areZombiesAllALive());
    	this.z2.takeDamage(100);
    	assertTrue(z2.isDead());
    	assertFalse(this.game.areZombiesAllALive());
    	
    }
    
    @Test
    public void roundUpdateBoardTest() {
    	ActionsPlayer makeNoise = new MakeNoise();
    	int totNoise=0;
    	makeNoise.action(p1);
		for(int i = 0 ; i < this.b.getBoard().length ; i++){
			for(int j = 0; j < this.b.getBoard()[0].length; j++){
				totNoise+= this.b.getCellBoard(i, j).getNoise();
			}
		}
		assertFalse(0==totNoise);
		
		int nbZ= this.b.getAllZombies().size();
		this.game.roundUpdateBoard();
		totNoise=0;
		for(int i = 0 ; i < this.b.getBoard().length ; i++){
			for(int j = 0; j < this.b.getBoard()[0].length; j++){
				totNoise+= this.b.getCellBoard(i, j).getNoise();
			}
		}
		int nbZ_after= this.b.getAllZombies().size();
		assertEquals(0,totNoise);
		assertTrue(nbZ_after>nbZ);
		
		
    }
}
