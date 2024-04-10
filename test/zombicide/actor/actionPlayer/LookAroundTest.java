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
        this.p = new Player(5,b.getCellBoard(1,1),1,5);
        this.z = new Broom(this.b.getCellBoard(1,1),1);
        this.b.getCellBoard(1,1).addPlayers(this.p);
        this.b.getCellBoard(1,1).addZombies(this.z);
        this.act = new LookAround(b);
    }


    @Test
    public void testDisplayLookAround(){
        this.act.action(this.p);
        this.b.BreakDoor(Direction.North, p.getCurrentCell().getX(), p.getCurrentCell().getY());
        this.b.BreakDoor(Direction.West, p.getCurrentCell().getX(), p.getCurrentCell().getY());
        String res = this.act.display(this.p);
        System.out.println(res);
        b.Display();
        assertTrue(res.contains("Player of id 1"));
        assertTrue(res.contains("Zombie of id 1"));
        assertTrue(res.contains("the door at the direction North is opened"));
        assertTrue(res.contains("the door at the direction West is opened"));


    }


}
