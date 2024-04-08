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
        this.act = new LookAround();
    }


    @Test
    public void testDisplayLookAround(){
        Cell cell = this.p.getCurrentCell();
        this.act.action(this.p);
        cell.breakDoor(Direction.North);
        cell.breakDoor(Direction.West);
        String res = this.act.display(this.p);
        System.out.println(res);
        assertTrue(res.contains("Player of id 1"));
        assertTrue(res.contains("Zombie of id 1"));
        assertTrue(res.contains("the door at the direction North is opened"));
        assertTrue(res.contains("the door at the direction West is opened"));


    }


}
