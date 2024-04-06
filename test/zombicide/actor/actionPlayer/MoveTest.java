package zombicide.actor.actionPlayer;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.player.Player;
import zombicide.board.Board;
import zombicide.util.listchooser.RandomListChooser;


public class MoveTest {

    private Board board;
    private Player player;
    private Move move;
    
    @BeforeEach
    void setUp() {

        board = new Board(5,5);
        player = new Player(5,board.getCellBoard(2, 2),1,5);
        move = new Move(board,new RandomListChooser<>());
        board.getCellBoard(2, 2).addPlayers(player);
    }

    @Test
    void testAction() {

        
        move.action(player);

        assertEquals(board.getCellBoard(2, 2).getAllPlayers(),new ArrayList<Player>());
        assertEquals(2, player.getAction_points());
        Player player2 = new Player(5,board.getCellBoard(0, 0),1,5);
        board.getCellBoard(0, 0).addPlayers(player2);
        List<Player> a = board.getCellBoard(0, 0).getAllPlayers();
        move.action(player2);
        assertEquals(board.getCellBoard(0, 0).getAllPlayers(),a);
    }


}
