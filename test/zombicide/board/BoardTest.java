package zombicide.board;

import static org.junit.jupiter.api.Assertions.*;

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
    	i = new MapCard("mp",false, board);
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
    public void testMovePlayerNorth() {
    	board.getCellBoard(0, 0).addPlayers(p1);
    	board.movePlayer(p1, d);
    	assertEquals(p1.getCurrentCell(),board.getCellBoard(0, 0));
    	board.getCellBoard(2, 2).addPlayers(p2);
    	board.movePlayer(p2, d);
    	assertEquals(p2.getCurrentCell(),board.getCellBoard(1, 2));
    }
	@Test
	public void testMovePlayerSouth() {
		board.getCellBoard(0, 0).addPlayers(p1);
		board.movePlayer(p1, Direction.South);
		//Door is not break
		assertEquals(p1.getCurrentCell(),board.getCellBoard(0, 0));
		//Door is Break
		p1.getCurrentCell().breakDoor(Direction.South);
		board.movePlayer(p1, Direction.South);
		assertEquals(p1.getCurrentCell(),board.getCellBoard(1, 0));

		//move in the street
		board.getCellBoard(2, 2).addPlayers(p2);
		board.movePlayer(p2, Direction.South);
		assertEquals(p2.getCurrentCell(),board.getCellBoard(3, 2));
	}
	@Test
	public void testMovePlayerEast() {
		board.getCellBoard(0, 0).addPlayers(p1);
		board.movePlayer(p1, Direction.East);
		//Door is not break
		assertEquals(p1.getCurrentCell(),board.getCellBoard(0, 0));
		//Door is Break
		p1.getCurrentCell().breakDoor(Direction.East);
		board.movePlayer(p1, Direction.East);
		assertEquals(p1.getCurrentCell(),board.getCellBoard(0, 1));


		//move in the street
		board.getCellBoard(2, 2).addPlayers(p2);
		board.movePlayer(p2, Direction.East);
		assertEquals(p2.getCurrentCell(),board.getCellBoard(2, 3));
	}
	@Test
	public void testMovePlayerWest() {
		board.getCellBoard(0, 0).addPlayers(p1);
		board.movePlayer(p1, Direction.West);
		//Door is Unbreakable
		assertEquals(p1.getCurrentCell(),board.getCellBoard(0, 0));

		//move in street
		board.getCellBoard(2, 2).addPlayers(p2);
		board.movePlayer(p2, Direction.West);
		assertEquals(p2.getCurrentCell(),board.getCellBoard(2, 1));
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
	public void testMoveZombieSouth() {
		board.getCellBoard(0, 0).addZombies(z1);
		board.moveZombie(z1, Direction.South);
		//Door is not break
		assertEquals(z1.getCurrentCell(),board.getCellBoard(0, 0));
		//Door is Break
		p1.getCurrentCell().breakDoor(Direction.South);
		board.moveZombie(z1, Direction.South);
		assertEquals(z1.getCurrentCell(),board.getCellBoard(1, 0));

		//move in the street
		board.getCellBoard(2, 2).addZombies(z2);
		board.moveZombie(z2, Direction.South);
		assertEquals(z2.getCurrentCell(),board.getCellBoard(3, 2));
	}
	@Test
	public void testMoveZombieEast() {
		board.getCellBoard(0, 0).addZombies(z1);
		board.moveZombie(z1, Direction.East);
		//Door is not break
		assertEquals(z1.getCurrentCell(),board.getCellBoard(0, 0));
		//Door is Break
		p1.getCurrentCell().breakDoor(Direction.East);
		board.moveZombie(z1, Direction.East);
		assertEquals(z1.getCurrentCell(),board.getCellBoard(0, 1));


		//move in the street
		board.getCellBoard(2, 2).addZombies(z2);
		board.moveZombie(z2, Direction.East);
		assertEquals(z2.getCurrentCell(),board.getCellBoard(2, 3));
	}
	@Test
	public void testMoveZombieWest() {
		board.getCellBoard(0, 0).addZombies(z1);
		board.moveZombie(z1, Direction.West);
		//Door is Unbreakable
		assertEquals(z1.getCurrentCell(),board.getCellBoard(0, 0));

		//move in street
		board.getCellBoard(2, 2).addZombies(z1);
		board.moveZombie(z2, Direction.West);
		assertEquals(z2.getCurrentCell(),board.getCellBoard(2, 1));
	}

    
    @Test
    public void testBreakDoor() {
    	board.BreakDoor(d, 0, 0);
        assertFalse(board.getCellBoard(0, 0).getDoor(d).isBreak());
    	board.BreakDoor(d, 2, 2);
        assertTrue(board.getCellBoard(2, 2).getDoor(d).isBreak());
    }
    
    @Test
    public void testGetCellWithDirectionNorth() {
    	assertEquals(board.getCellBoard(1, 2),board.getCellDirection(d, p2));
		//test if the player is in cell 0,0 you can't get de cell at is top because she not exists
        assertNull(board.getCellDirection(d, p1));
    }
	@Test
	public void testGetCellWithDirectionSouth() {
		assertEquals(board.getCellBoard(3, 2),board.getCellDirection(Direction.South, p2));
	}
	@Test
	public void testGetCellWithDirectionWest() {
		assertEquals(board.getCellBoard(2, 1),board.getCellDirection(Direction.West, p2));
		//test if the player is in cell 0,0 you can't get de cell at is left because she not exists
		assertNull(board.getCellDirection(Direction.West, p1));
	}
	@Test
	public void testGetCellWithDirectionEast() {
		assertEquals(board.getCellBoard(2, 3),board.getCellDirection(Direction.East, p2));
	}

    @Test
	public void testCellIsOnSameLineOrColumn() {
        assertTrue(board.cellIsOnSameLineOrColumn(board.getCellBoard(0, 4), board.getCellBoard(0, 1)));
        assertTrue(board.cellIsOnSameLineOrColumn(board.getCellBoard(2, 1), board.getCellBoard(0, 1)));

        assertFalse(board.cellIsOnSameLineOrColumn(board.getCellBoard(0, 4), board.getCellBoard(3, 2)));
        assertFalse(board.cellIsOnSameLineOrColumn(board.getCellBoard(2, 1), board.getCellBoard(3, 2)));

	}
    
    @Test
	public void testGetDirectionBetweenCells() {
		assertEquals(Direction.South, board.getDirectionBetweenCells(board.getCellBoard(1,1), board.getCellBoard(1,4)));
		assertEquals(Direction.East, board.getDirectionBetweenCells(board.getCellBoard(1,1), board.getCellBoard(4,1)));
		assertEquals(Direction.West, board.getDirectionBetweenCells(board.getCellBoard(3,1), board.getCellBoard(0,1)));
		assertEquals(Direction.North, board.getDirectionBetweenCells(board.getCellBoard(3,4), board.getCellBoard(3,1)));
		
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> board.getDirectionBetweenCells(board.getCellBoard(3,4), board.getCellBoard(2,1)));
	    assertEquals("Cells are not on the same line or column", exception.getMessage());	}
    
    @Test
    public void testCheckOpenDoorsBetweenCells() {
        for(int i=4; i>=2; i--) {
            board.BreakDoor(d, 1, i);  
        }
        assertTrue(board.checkOpenDoorsBetweenCells(board.getCellBoard(1, 4), board.getCellBoard(1, 1)));
        assertTrue(board.checkOpenDoorsBetweenCells(board.getCellBoard(1, 4), board.getCellBoard(1, 2)));
        assertFalse(board.checkOpenDoorsBetweenCells(board.getCellBoard(1, 3), board.getCellBoard(1, 0)));
        assertFalse(board.checkOpenDoorsBetweenCells(board.getCellBoard(0, 0), board.getCellBoard(0, 3)));
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> board.checkOpenDoorsBetweenCells(board.getCellBoard(3, 2), board.getCellBoard(1, 4)));
        assertEquals("Direction cannot be determined", exception.getMessage());    	
    }

    

}
