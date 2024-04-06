package zombicide.actor.actionPlayer.roles;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.actionPlayer.ActionsPlayer;
import zombicide.actor.actionPlayer.SearchInTRoomAction;
import zombicide.actor.player.Player;
import zombicide.board.TrainingBoard;
import zombicide.cell.Cell;
import zombicide.item.weapons.Axe;
import zombicide.item.weapons.Carabine;
import zombicide.item.weapons.Chainsaw;
import zombicide.util.listchooser.ListChooser;
import zombicide.item.*;
import zombicide.util.listchooser.RandomListChooser;

import java.util.ArrayList;
import java.util.List;

public class FouineurTest {
    private ActionsPlayer act;
    private ListChooser<Item> chooser;

    private Player p;
    private TrainingBoard board;
    private Cell cell;
    private Item wp1;
    private Item wp2;
    @BeforeEach
    public void init(){
        this.chooser = new RandomListChooser<>();
        this.board = new TrainingBoard();
        this.cell = this.board.getCellBoard(0,0);
        this.p = new Player(3,this.cell,1,5);
        this.cell.addPlayers(this.p);
        this.act = new Fouineur(this.chooser);
        this.wp1 = new Carabine();
        this.wp2 = new Chainsaw();
        this.cell.addItem(this.wp1);
        this.cell.addItem(this.wp2);
        this.p.putItemInBackPack(new Carabine());
        this.p.putItemInBackPack(new Axe());
    }

    @Test
    public void TestActionFouineur(){
        int acp = this.p.getAction_points();
        this.act.action(this.p);
        assertEquals(this.p.getAction_points(), acp);
    }

}
