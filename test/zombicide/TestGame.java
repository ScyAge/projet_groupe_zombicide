package zombicide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.actionPlayer.*;
import zombicide.actor.player.Player;
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
    private Board b;
    @BeforeEach
    public void init(){
        this.b = new TrainingBoard();
        b.initBoard();
        this.p1= new Player(5, b.getCellBoard(0,0), 1, 6);
        this.p2= new Player(5, b.getCellBoard(0,0), 2, 6);
        this.p3 = new Player(5, b.getCellBoard(0,0), 3, 6);
        this.p4 = new Player(5, b.getCellBoard(0,0), 4, 6);
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
}
