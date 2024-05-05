package zombicide.actor.actionPlayer.roles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.actionPlayer.ActionsPlayer;
import zombicide.actor.player.Player;
import zombicide.cell.Cell;
import zombicide.cell.Room;
import zombicide.util.listchooser.ListChooser;
import zombicide.util.listchooser.RandomListChooser;
import static org.junit.jupiter.api.Assertions.*;

public class SoigneurTest {
    Player p1;
    Player p2;
    Player p3;
    Cell cell;
    ActionsPlayer act;

    @BeforeEach
    public void init(){
        this.cell = new Room(3,3);
        this.p1 = new Player(3,this.cell,1,5);
        this.p2 = new Player(3,this.cell,2,5);
        this.p3 = new Player(3,this.cell,3,5);
        this.act = new Soigneur(new RandomListChooser<>());
        this.cell.addPlayers(p1);
        this.cell.addPlayers(p2);
        this.cell.addPlayers(p3);
        Soigneur bis = new Soigneur();
    }


    @Test
    public void TestSoigneurActionAllThePlayerHaveLessOfTheirMaxHeath(){
        this.p1.takeDamage(1);
        this.p2.takeDamage(1);
        this.p3.takeDamage(1);
        this.act.action(this.p1);
        //test if one of the 3 player has been heal
        assertTrue(p1.getLifePoints() ==3 ||p2.getLifePoints() ==3 ||p3.getLifePoints() ==3);
    }

    @Test
    public void TestSoigneurActionIfOnePlayerHaveAllIsHp(){
        this.p2.takeDamage(1);
        this.p3.takeDamage(1);
        //you have only the choice between the player who have less than their max else
        this.act.action(p1);
        assertTrue(p2.getLifePoints() ==3 ||p3.getLifePoints() ==3);
    }

    @Test
    public void TestIsActionPlayable(){
        //because all the player are full hp
        assertFalse(this.act.IsActionPlayable(p1));
        this.p2.takeDamage(1);
        this.p3.takeDamage(1);
        assertTrue(this.act.IsActionPlayable(p1));

    }

    @Test
    public void TestToString(){
        assertEquals(this.act.toString(),"Soigneur action");
    }
}
