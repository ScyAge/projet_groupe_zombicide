package zombicide.actor.actionPlayer;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.player.Player;
import zombicide.actor.zombie.Broom;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.cell.Cell;
import zombicide.util.Direction;

public class LookAroundTest {

    private Board b;
    private Player p;
    private Zombies z;
    private LookAround act;
    @BeforeEach
    public void init(){
        this.b = new TrainingBoard();
        this.b.initBoard();
        this.p = new Player(5,b.getCellBoard(1,1),1,5);
        this.z = new Broom(this.b.getCellBoard(1,1),1);
        this.b.getCellBoard(1,1).addPlayers(this.p);
        this.b.getCellBoard(1,1).addZombies(this.z);
        this.act = new LookAround(b);
    }


    @Test
    public void testDisplayLookAround(){
        Cell cell = this.p.getCurrentCell();
        this.b.BreakDoor(Direction.North,1,1);
        this.b.BreakDoor(Direction.West,1,1);
        String res = this.act.display(this.p);
        assertTrue(res.contains("Player of id 1"));
        assertTrue(res.contains("Zombie of id 1"));
        assertTrue(res.contains("the door at the direction North is opened"));
        assertTrue(res.contains("the door at the direction West is opened"));


    }
    @Test
    public void testDisplayLookAroundInCell(){
        Player p = new Player(3,this.b.getCellBoard(0,2),3,4);
        Cell cell = p.getCurrentCell();
        cell.addPlayers(p);
        this.b.BreakDoor(Direction.West,0,2);
        this.act.action(p);
        String res = this.act.display(p);
        assertTrue(res.contains("Player of id 3"));
        assertTrue(res.contains("the door at the direction South is opened"));
        assertTrue(res.contains("the door at the direction West is opened"));
    }


}
