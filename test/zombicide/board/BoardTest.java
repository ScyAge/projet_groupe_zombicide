package zombicide.board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Walker;
import zombicide.actor.zombie.Zombie;
import zombicide.cell.Cell;
import zombicide.cell.Room;
import zombicide.cell.Sewer;
import zombicide.item.Item;
import zombicide.item.equipment.*;
import zombicide.util.Direction;
import zombicide.item.weapons.*;
import java.util.ArrayList;
import java.util.List;

import zombicide.util.listchooser.RandomListChooser;

public class BoardTest {
	
	private Board board;
	private Item i;
	private Direction d;
	private Direction d2;
	private Player p1;
	private Player p2;
	private Zombie z1;
	private Zombie z2;
	
	private List<Item> items;
    @BeforeEach
    void setUp() {
    	board = new Board(5,5);
    	i = new MapCard(board);

		this.items = new ArrayList<>();
		this.items.add(new Axe());
		this.items.add(new Carabine());
		this.items.add(new Chainsaw());
		this.items.add(new Crowbar());
		this.items.add(new Gun());
		this.items.add(new FirstAidKit());
		//this.items.add(new Glasses(board));
		this.items.add(new HealingVial());
		//this.items.add(new MapCard(board));
		this.items.add(new MasterKey(new RandomListChooser<>(),board));
    	//i = new MapCard(board);
		board = new Board(5,5,this.items);
		board.initBoard();
    	d = Direction.North;
		d2 = Direction.East;
    	p1 = new Player(5,board.getCellBoard(0, 0),5,6);
    	p2 = new Player(5,board.getCellBoard(2, 2),5,6);
    	z1 = new Zombie(5,2,board.getCellBoard(0, 0),5,5);
    	z2 = new Zombie(5,2,board.getCellBoard(2, 2),5,5);
    }
	
    @Test
	public void testInitBoard() {
		List<Boolean> test = new ArrayList<>();
		for(int i = 0 ; i<board.getBoard().length;i++) {
			for(int j = 0 ; j<board.getBoard()[i].length;j++) {
				assertNotNull(board.getBoard()[i][j]);
				test.add(!this.board.getBoard()[i][j].getAllItems().isEmpty());
			}
		}
		assertTrue(test.contains(true));
	}
    
    @Test
    public void testAddItem() {
    	board.addItem(i);
    	assertEquals(i, board.getItem().get(board.getItem().size()-1));
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
		board.getCellBoard(1, 0).breakDoor(Direction.North);
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
		board.getCellBoard(0, 1).breakDoor(Direction.West);
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
		board.getCellBoard(1, 0).breakDoor(Direction.North);
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
		z1.getCurrentCell().breakDoor(Direction.East);
		board.getCellBoard(0, 1).breakDoor(Direction.West);
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
		board.BreakDoor(Direction.South,2,2);
		assertTrue(board.getCellBoard(2,2).getDoor(Direction.South).isBreak());
		board.BreakDoor(Direction.West,2,2);
		assertTrue(board.getCellBoard(2,2).getDoor(Direction.West).isBreak());
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
		assertEquals(Direction.South, board.getDirectionBetweenCells(board.getCellBoard(1, 1), board.getCellBoard(1, 4)));
		assertEquals(Direction.East, board.getDirectionBetweenCells(board.getCellBoard(1, 1), board.getCellBoard(4, 1)));
		assertEquals(Direction.West, board.getDirectionBetweenCells(board.getCellBoard(3, 1), board.getCellBoard(0, 1)));
		assertEquals(Direction.North, board.getDirectionBetweenCells(board.getCellBoard(3, 4), board.getCellBoard(3, 1)));
		assertNull(board.getDirectionBetweenCells(board.getCellBoard(3, 4), board.getCellBoard(2, 1)));
	}
    @Test
    public void testCheckOpenDoorsBetweenCells() {
        for(int i=4; i>=2; i--) {
            board.BreakDoor(d, 1, i);  
        }
        assertTrue(board.checkOpenDoorsBetweenCells(board.getCellBoard(1, 4), board.getCellBoard(1, 1)));
        assertTrue(board.checkOpenDoorsBetweenCells(board.getCellBoard(1, 4), board.getCellBoard(1, 2)));
		assertTrue(board.checkOpenDoorsBetweenCells(board.getCellBoard(1, 2), board.getCellBoard(1, 3)));
		assertFalse(board.checkOpenDoorsBetweenCells(board.getCellBoard(1, 3), board.getCellBoard(1, 0)));
        assertFalse(board.checkOpenDoorsBetweenCells(board.getCellBoard(0, 0), board.getCellBoard(0, 3)));
		assertNull(board.getDirectionBetweenCells(board.getCellBoard(3, 4), board.getCellBoard(2, 1)));

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> board.checkOpenDoorsBetweenCells(board.getCellBoard(3, 2), board.getCellBoard(1, 4)));
        assertEquals("Direction cannot be determined", exception.getMessage());
    }

	@Test
	public void testCheckOpenDoorsBetweenCells2() {
		for(int i=0; i<=3; i++) {
			board.BreakDoor(d2, i, 2);
		}
		assertTrue(board.checkOpenDoorsBetweenCells(board.getCellBoard(0, 2), board.getCellBoard(3, 2)));
		assertTrue(board.checkOpenDoorsBetweenCells(board.getCellBoard(0, 2), board.getCellBoard(2, 2)));
		assertTrue(board.checkOpenDoorsBetweenCells(board.getCellBoard(3, 2), board.getCellBoard(0, 2)));
		assertFalse(board.checkOpenDoorsBetweenCells(board.getCellBoard(0, 0), board.getCellBoard(4, 0)));
		assertFalse(board.checkOpenDoorsBetweenCells(board.getCellBoard(4, 0), board.getCellBoard(0, 0)));

	}

	@Test
	public void testDisplay(){
		this.board.Display();
		//add of a player and a zombie in each cell
		for(int i = 0;i < this.board.getBoard().length;i++){
			for(int j = 0 ; j < this.board.getBoard()[0].length;j++){
				this.board.getCellBoard(i,j).addPlayers(new Player(3,this.board.getCellBoard(i,j),i*j,5));
				this.board.getCellBoard(i,j).addZombies(new Walker(this.board.getCellBoard(i,j),i*j));
			}
		}
		this.board.Display();

	}

	@Test
	public void TestPlacementITem(){
		List<Item> g = new ArrayList<>();
		Item a = new Axe();
		Item b = new Carabine();
		Item c = new Gun();
		g.add(a);
		g.add(b);
		g.add(c);
		Board board = new Board(5,5,g);
		board.setItems(g);
		board.initBoard();

		List<Item> test = new ArrayList<>();
		for(int i = 0 ; i < board.getBoard().length ; i++){
			for(int j = 0 ; j < board.getBoard()[0].length ; j++){
				test.addAll(board.getCellBoard(i,j).getAllItems());
			}
		}
        for(Item i : test){
			assertTrue(i.equals(a) || i.equals(b) || i.equals(c));
		}
		if(test.isEmpty()){
			assertTrue(true);
		}
	}

	@Test
	public void testCleanNoise(){
		//set at 10 for each cell
		for(int i = 0 ; i < board.getBoard().length ; i++){
			for(int j = 0; j < board.getBoard()[0].length; j++){
				board.getBoard()[i][j].setNoise(10);
			}
		}
		for(int i = 0 ; i < board.getBoard().length ; i++){
			for(int j = 0; j < board.getBoard()[0].length; j++){
				assertEquals(board.getBoard()[i][j].getNoise(),10);
			}
		}
		board.cleanNoise();
		for(int i = 0 ; i < board.getBoard().length ; i++){
			for(int j = 0; j < board.getBoard()[0].length; j++){
				assertEquals(board.getBoard()[i][j].getNoise(),0);
			}
		}
	}

	@Test
	public void testAddGetSpecialCell(){
		Cell test = new Room(5,5);
		this.board.addSpecialCell(test);
		assertTrue(this.board.getSpecialCell().contains(test));
	}

	@Test void testSetGetItems(){
		List<Item> items = new ArrayList<>();
		Item Kit = new FirstAidKit(new RandomListChooser<>());
		Item glasses = new Glasses(this.board);
		Item heal = new HealingVial();
		items.add(Kit);
		items.add(glasses);
		items.add(heal);
		this.board.setItems(items);
		assertTrue(this.board.getItems().containsAll(items));
	}

	@Test
	public void testGetSpawnPlayer(){
		assertEquals(this.board.getSpawnPlayers(),this.board.getCellBoard(2,2));
	}


	@Test
	public void testAddGetZombie(){
		Zombie z1 = new Walker(null,5);
		Zombie z2 = new Walker(null,5);
		Zombie z3 = new Walker(null,5);
		List<Zombie> listZ = new ArrayList<>();
		listZ.add(z1);
		listZ.add(z2);
		listZ.add(z3);
		this.board.addZombieList(z1);
		this.board.addZombieList(z2);
		this.board.addZombieList(z3);
		List<Zombie> allZombieBoard = this.board.getAllZombies();
		assertTrue(allZombieBoard.containsAll(listZ));
	}

	@Test
	public void testUpdateListZombie(){

		Zombie z1 = new Walker(this.board.getCellBoard(2,2),5);
		Zombie z2 = new Walker(this.board.getCellBoard(2,2),5);
		Zombie z3 = new Walker(this.board.getCellBoard(2,2),5);
		List<Zombie> listZ = new ArrayList<>();
		listZ.add(z1);
		listZ.add(z2);
		listZ.add(z3);
		this.board.addZombieList(z1);
		this.board.addZombieList(z2);
		this.board.addZombieList(z3);

		//no zombie dead so
		assertTrue(this.board.updateListZombie().containsAll(listZ));

		// z1 and z2 are dead
		z1.takeDamage(10);
		z2.takeDamage(10);
		assertFalse(this.board.updateListZombie().containsAll(listZ));
		assertTrue(this.board.updateListZombie().contains(z3));
	}

	@Test
	public void ProducZombieTest(){
		List<Sewer> allSewers = this.board.getAllSewers();
		for(Cell cell : allSewers){
			assertTrue(cell.getAllZombies().isEmpty());
		}
		//each sewer have now 5 zombie
		this.board.ProductionZombie(5);

		for(Cell cell : allSewers){
            assertEquals(5, cell.getAllZombies().size());
		}
	}
	@Test
	public void TestCanBreakDoor(){
		//case in a street with all the Door open but there is two room near so you can break their door
		Player player = new Player(5,this.board.getCellBoard(2,3),5,5);
		this.board.getCellBoard(2,3).addPlayers(player);
		assertTrue(this.board.canBreakDoor(Direction.North,player));
		assertFalse(this.board.canBreakDoor(Direction.East,player));
		assertTrue(this.board.canBreakDoor(Direction.South,player));
		assertFalse(this.board.canBreakDoor(Direction.West,player));

		//Case in a Room with no door break

		Player player2 = new Player(5,this.board.getCellBoard(1,1),5,5);
		this.board.getCellBoard(1,1).addPlayers(player);
		assertTrue(this.board.canBreakDoor(Direction.North,player2));
		assertTrue(this.board.canBreakDoor(Direction.East,player2));
		assertTrue(this.board.canBreakDoor(Direction.South,player2));
		assertTrue(this.board.canBreakDoor(Direction.West,player2));

		//Case in border of the board in street

		Player player3 = new Player(5,this.board.getCellBoard(2,0),5,5);
		this.board.getCellBoard(2,0).addPlayers(player);
		assertTrue(this.board.canBreakDoor(Direction.North,player3));
		assertFalse(this.board.canBreakDoor(Direction.East,player3));
		assertTrue(this.board.canBreakDoor(Direction.South,player3));
		//Door unbreakble
		assertFalse(this.board.canBreakDoor(Direction.West,player3));

		//Case in corner border of the board in a room
		Player player4 = new Player(5,this.board.getCellBoard(0,0),5,5);
		this.board.getCellBoard(0,0).addPlayers(player);

		//Door unbreakble
		assertFalse(this.board.canBreakDoor(Direction.North,player4));

		assertTrue(this.board.canBreakDoor(Direction.East,player4));
		assertTrue(this.board.canBreakDoor(Direction.South,player4));

		//Door unbreakble
		assertFalse(this.board.canBreakDoor(Direction.West,player4));

	}
    

}
