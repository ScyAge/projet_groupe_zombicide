package zombicide.board;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.item.Item;
import zombicide.item.equipment.*;
import zombicide.util.Direction;


public class BoardTest {
	
	private Board board;
	private Item i;
	private Direction d;
	private Player p1;
	private Player p2;
	private Zombies z1;
	private Zombies z2;
	
	
    @BeforeEach
    void setUp() {
    	board = new Board(5,5);
    	i = new MapCard("mp",board);
    	d = Direction.North;
    	p1 = new Player(5,board.getCellBoard(0, 0),5,6);
    	p2 = new Player(5,board.getCellBoard(2, 2),5,6);
    	z1 = new Zombies(5,2,board.getCellBoard(0, 0),5,5);
    	z2 = new Zombies(5,2,board.getCellBoard(2, 2),5,5);
    }
	
    @Test
	public void testInitBoard() {
		for(int i = 0 ; i<board.getBoard().length;i++) {
			for(int j = 0 ; j<board.getBoard()[i].length;j++) {
				assertNotNull(board.getBoard()[i][j]);
			}
		}
	}
    
    @Test
    public void testAddItem() {
    	board.addItem(i);
    	assertEquals(i, board.getItem().get(0));
    }
    
    @Test
    public void testMovePlayer() {
    	board.getCellBoard(0, 0).addPlayers(p1);
    	board.movePlayer(p1, d);
    	assertEquals(p1.getCurrentCell(),board.getCellBoard(0, 0));
    	board.getCellBoard(2, 2).addPlayers(p2);
    	board.movePlayer(p2, d);
    	assertEquals(p2.getCurrentCell(),board.getCellBoard(1, 2));
    }
    
    @Test
    public void testMoveZombie() {
    	board.getCellBoard(0, 0).addZombies(z1);
    	board.moveZombie(z1, d);
    	assertEquals(z1.getCurrentCell(),board.getCellBoard(0, 0));
    	board.getCellBoard(2, 2).addZombies(z2);
    	board.moveZombie(z2, d);
    	assertEquals(z2.getCurrentCell(),board.getCellBoard(1, 2));
    }
    
    @Test
    public void testBreakDoor() {
    	board.BreakDoor(d, 0, 0);
    	assertEquals(false,board.getCellBoard(0, 0).getDoor(d).isBreak());
    	board.BreakDoor(d, 2, 2);
    	assertEquals(true,board.getCellBoard(2, 2).getDoor(d).isBreak());
    }
    
    @Test
    public void testGetCellWithDirection() {
    	assertEquals(board.getCellBoard(1, 2),board.getCellDirection(d, p2));
    	assertEquals(null,board.getCellDirection(d, p1));
    }
    

}
