package zombicide.actor.action;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.player.Player;
import zombicide.cell.*;
import zombicide.item.equipment.MapCard;
import zombicide.item.weapons.Axe;
import zombicide.item.weapons.Carabine;

public class TakeInHandActionTest {
    private Actions action;
    private Player p;
    private Cell testCell;

    @BeforeEach
    public void init(){
        this.testCell = new Room(3,3);
        this.action = new TakeInHandAction();
        this.p = new Player(3,this.testCell,1,6);
        this.p.putItemInBackPack(new Carabine());
        this.p.putItemInBackPack(new Axe());

    }

    @Test
    public void TestTakeInHandAction(){
        this.action.action(this.p,null);
    }

}
