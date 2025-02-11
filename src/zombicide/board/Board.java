package zombicide.board;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zombicide.actor.Actor;
import zombicide.actor.player.Player;
import zombicide.actor.zombie.*;
import zombicide.actor.zombie.Zombie;
import zombicide.cell.*;
import zombicide.util.Direction;
import zombicide.util.Door;
import zombicide.item.*;

/**
 * class of board
 */
public class Board {

	/** composition of board */
	protected Cell[][] board;
    private int xr;
    private List<Item> items;
	private final List<Zombie> listZombie;
	private final List<Sewer> listSewer;
	private SpawnPlayers spawnPlayer;
	private final List<Cell> SpecialCell;

	private int compteur =0;

	/**
	 * Builder of Board
	 * @param height of the board
	 * @param width of the board
	 */
	public Board(int height, int width) {
		this(height,width,new ArrayList<>() );
	}

	/**
	 * Builder of Board
	 * @param height of the board
	 * @param width of the board
	 * @param items list of all items in the board
	 */
	public Board(int height, int width,List<Item> items) {
		this.board = new Cell[width][height];
		this.listZombie = new ArrayList<>();
		this.listSewer= new ArrayList<>();
		this.spawnPlayer =null ;
        this.xr=((height-(height/4)) *(width-(width/4))-1) ;
		this.items = items;
		this.SpecialCell = new ArrayList<>();
		this.SpecialCell.add(new DrugStore(0,0));
		this.SpecialCell.add(new Continental(0,0));
	}
	
	/**
	 * Add a new specialCell in the board
	 * @param c the new cell
	 */
	public void addSpecialCell(Cell c) {
		this.SpecialCell.add(c);
	}

	/**
	 *Get the list of spécial cell
	 * @return  the list of special cell
	 */
	public List<Cell> getSpecialCell() {
		return this.SpecialCell;
	}


	/**
	 * set the list items of the board
	 * @param i the list of items
	 */
	public void setItems(List<Item> i) {
		this.items =i;
	}

	/**
	 * get the list of item
	 * @return list of item
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * Init the board
	 */
	public void initBoard(){
		initBoardBis(0,this.board.length,0,this.board[0].length,true);
	}

	/**
	 * init the Board
	 * @param xd start width
	 * @param xf final width
	 * @param yd start height
	 * @param yf final height
	 */
	private void initBoardBis(int xd, int xf, int yd, int yf, boolean sewer) {
		if(!isSplitable(xd, xf, yd, yf)){
			Building(xf, xd, yf, yd);
		}
		else{
			Random random = new Random();
			int Y = random.nextInt((yf-2) -(yd+2))+(yd+2);
			int X = random.nextInt((xf-2) -(xd+2))+(xd+2);
			VStreet(Y,xf,xd);
			HStreet(yd,yf, X);
			initBoardBis(xd, X, yd, Y,false);
			initBoardBis(X+1, xf, yd, Y,false);
			initBoardBis(xd, X, Y+1, yf,false);
			initBoardBis(X+1, xf, Y+1, yf,false);
			if (sewer){
				Sewer s1 = new Sewer(xd, Y);
				s1.getDoor(Direction.North).SetNotBreakble();
				this.board[xd][Y] = s1;
				this.listSewer.add(s1);

				Sewer s2 =  new Sewer(xf-1, Y);
				s2.getDoor(Direction.South).SetNotBreakble();
				this.board[xf-1][Y] = s2;
				this.listSewer.add(s2);
				Sewer s3 =  new Sewer(X, yd);
				s3.getDoor(Direction.West).SetNotBreakble();
				this.board[X][yd] = s3;
				this.listSewer.add(s3);


				Sewer s4= new Sewer(X, yf-1);
				s4.getDoor(Direction.East).SetNotBreakble();
				this.board[X][yf-1] = s4;
				this.listSewer.add(s4);

				SpawnPlayers SP = new SpawnPlayers(X,Y);
				this.board[X][Y] = SP;
				this.spawnPlayer = SP;
			}

		}
	}
	
	public SpawnPlayers getSpawnPlayers() {
		return this.spawnPlayer;
	}

	/**
	 *  Create Room
	 * @param xd start width
	 * @param xf final width
	 * @param yd start height
	 * @param yf final height
	 */
	private void Building(int xf,int xd , int yf,int yd) {
		for(int i =xd;i<(xf);i++){
			for(int j =yd;j<(yf);j++){
				Room room = new Room(i,j) ;
				this.board[i][j] = room  ;
				this.placeItemAlea(i,j);
				this.addDrugStoreAndContinentalInBuilding(i, j);
				if(i==0) {
					this.board[i][j].getDoor(Direction.North).SetNotBreakble();
				}
				if(j==0) {
					this.board[i][j].getDoor(Direction.West).SetNotBreakble();
				}
				if(i==this.board.length-1) {
					this.board[i][j].getDoor(Direction.South).SetNotBreakble();
				}
				if(j==this.board[0].length-1) {
					this.board[i][j].getDoor(Direction.East).SetNotBreakble();
				}

			}
		}
	}

	private void addDrugStoreAndContinentalInBuilding(int i , int j) {
        Random random = new Random() ;
		if(this.SpecialCell.size() >0) {
        	int X= random.nextInt(xr);
			if(X==0) {
				int Y= random.nextInt(this.SpecialCell.size());
				Cell c = this.SpecialCell.get(Y);
				c.setX(i);
				c.setY(j);
				this.board[i][j] = c;
				this.SpecialCell.remove(Y);
			}
			else {
				xr--;
			}
		}
	}

	/**
	 * Create vertical Street
	 * @param y1 height of the street
	 * @param xd  start index of the street
	 * @param xf  end index of the street
	 */
	private void VStreet(int y1,int xf, int xd){
		for(int i = xd;i<xf;i++){
			this.board[i][y1] = new Street(i,y1) ;
		}
	}
	/**
	 * Create horizontal street
	 * @param yd start index of the list
	 * @param yf end index  of the street
	 * @param x1 width of the street
	 */
	private void HStreet(int yd,int yf, int x1){
		for(int i = yd;i<yf;i++){
			this.board[x1][i] = new Street(x1,i) ;
		}
	}
	private boolean isSplitable(int xd, int xf, int yd, int yf){
		return (xf -xd)>=5 && (yf-yd)>=5 ;
	}

	/**
	 * get the Board
	 * @return the board
	 */
	public Cell[][] getBoard(){
		return this.board;
	}

	/**
	 * get the cell x y of the board
	 * @param x index of the cell
	 * @param y index of the cell
	 * @return the cell
	 */
	public Cell getCellBoard(int x , int y) {
		return  this.board[x][y];
	}

	/**
	 * Break the door in the cell
	 * @param d direction to break the door
	 * @param x indice of the cell
	 * @param y indice of the cell
	 */
	public void BreakDoor(Direction d,int x,int y) {
		this.board[x][y].breakDoor(d);
		if(d== Direction.West && y != 0) {
			this.board[x][y-1].breakDoor(Direction.oppose(d));
		}
		else if(d== Direction.East && y != this.board[x].length-1) {
			this.board[x][y+1].breakDoor(Direction.oppose(d));
		}
		else if(d== Direction.North && x != 0) {
			this.board[x-1][y].breakDoor(Direction.oppose(d));
		}
		else if(d== Direction.South && x != this.board.length-1) {
			this.board[x+1][y].breakDoor(Direction.oppose(d));
		}
	}

	/**
	 * return true if the actor can go in the direction he wants to go otherwise false
	 * @param actor the actor
	 * @param direction the direction
	 * @return true if the player can go in the direction he wants to go otherwise false
	 */
	public boolean canMove(Actor actor, Direction direction){
		int x = actor.getCurrentCell().getX();
		int y = actor.getCurrentCell().getY();
		boolean test;
		if ((direction == Direction.North && x == 0) ||
            (direction == Direction.South && x == board.length - 1) ||
            (direction == Direction.West && y == 0) ||
            (direction == Direction.East && y == board[0].length - 1)) {
            return false;
        }
		if(direction == Direction.North){
			test = this.getCellBoard(x-1, y).getDoor(Direction.oppose(direction)).isBreak()&& this.getCellBoard(x, y).getDoor(direction).isBreak();
		} else if (direction == Direction.South) {
			test = this.getCellBoard(x+1, y).getDoor(Direction.oppose(direction)).isBreak()&& this.getCellBoard(x, y).getDoor(direction).isBreak();
		} else if (direction == Direction.West) {
			test =this.getCellBoard(x, y-1).getDoor(Direction.oppose(direction)).isBreak()&&this.getCellBoard(x,y).getDoor(direction).isBreak();
		}
		else{
			test =this.getCellBoard(x, y+1).getDoor(Direction.oppose(direction)).isBreak()&&this.getCellBoard(x,y).getDoor(direction).isBreak();
		}

        return test;
	}

	/**
	 * method for determining whether a door can be broken in one direction
	 * @param direction the direction of the door
	 * @param actor the actor that want to break a door
	 * @return a boolean if he can break this door
	 */
	public boolean canBreakDoor(Direction direction,Actor actor){
		Cell cell =  actor.getCurrentCell();
		Door door = cell.getDoor(direction);
		//if the door is unBreakable it means that your are at a border of the board so there is no cell after
		if(door.isBreakable()){
			return !door.isBreak() || !this.getCellDirection(direction,actor).getDoor(Direction.oppose(direction)).isBreak();
		}
		return false;
	}



	/**
	 * move the actor in the direction
	 * @param player the player
	 * @param direction the direction
	 */
	public void movePlayer(Player player, Direction direction) {
        if (canMove(player, direction)) {
            int x = player.getCurrentCell().getX();
            int y = player.getCurrentCell().getY();

            if (direction == Direction.North) {
            	player.setCell(this.board[x - 1][y]);
            	this.board[x][y].remove(player);
            	this.board[x - 1][y].addPlayers(player);
            } else if (direction == Direction.South) {
            	player.setCell(this.board[x + 1][y]);
            	this.board[x][y].remove(player);
            	this.board[x+1][y].addPlayers(player);
            } else if (direction == Direction.West) {
            	player.setCell(this.board[x][y - 1]);
            	this.board[x][y].remove(player);
            	this.board[x][y - 1].addPlayers(player);
            } else if (direction == Direction.East) {
            	player.setCell(this.board[x][y + 1]);
            	this.board[x][y].remove(player);
            	this.board[x][y+1].addPlayers(player);
            }
        } else {
            System.out.println("Impossible de se déplacer dans cette direction.");
        }
    }


	/**
	 * move the actor in the direction
	 * @param zombie the zombie
	 * @param direction the direction
	 */
	public void moveZombie(Zombie zombie, Direction direction) {
        if (canMove(zombie, direction)) {
            int x = zombie.getCurrentCell().getX();
            int y = zombie.getCurrentCell().getY();

            if (direction == Direction.North) {
            	zombie.setCell(board[x - 1][y]);
            	this.board[x][y].remove(zombie);
            	this.board[x-1][y].addZombies(zombie);
            } else if (direction == Direction.South) {
            	zombie.setCell(board[x + 1][y]);
            	this.board[x][y].remove(zombie);
            	this.board[x+1][y].addZombies(zombie);
            } else if (direction == Direction.West) {
            	zombie.setCell(board[x][y - 1]);
            	this.board[x][y].remove(zombie);
            	this.board[x][y-1].addZombies(zombie);
            } else if (direction == Direction.East) {
            	zombie.setCell(board[x][y + 1]);
            	this.board[x][y].remove(zombie);
            	this.board[x][y+1].addZombies(zombie);
            }
        }
    }

	/**
	 * get the list item
	 * @return the list of items
	 */
	public List<Item> getItem(){
		return this.items;
	}

	/**
	 * add item in the list Items
	 * @param i item to add on the list
	 */
	public void addItem(Item i ) {
		this.items.add(i);
	}
	/**
	 * get the cell with the direction
	 * @param d direction of cell the next cell
	 * @param a  Actor who is on the cell
	 * @return the cell
	 */
	public Cell getCellDirection(Direction d,Actor a) {
		Cell c = a.getCurrentCell();
		if(d == Direction.North) {
			if(c.getX()-1>=0) {
				return this.board[c.getX()-1][c.getY()];
			}
		}
		else if(d == Direction.East) {
			if(c.getY()+1<this.board[0].length) {
				return this.board[c.getX()][c.getY()+1];
			}
		}
		else if(d == Direction.South) {
			if(c.getX()+1<this.board.length) {
				return this.board[c.getX()+1][c.getY()];
			}
		}
		else {
			if(c.getY()-1>=0) {
				return this.board[c.getX()][c.getY()-1];
			}
        }
		return null;
	}

	/**
	 * method which pseudo-randomly places an item on a cell of the board with a probability of 1/2 that an object will be placed.
	 * A minimum of 1 item will be placed in the cell, a maximum of 3.
	 * @param x indice of the cell
	 * @param y indice of the cell
	 */
	private void placeItemAlea(int x,int y){
        int nb;
		int[] aleaNonEquiprob = {1,1,1,1,1,2,2,2,3,3};

		if(items.isEmpty()){
			return;
		}
        try {
            Random nb_alea = new Random();
            nb = nb_alea.nextInt(2);
            if (nb == 1) {
				int nb_item_to_spawn = nb_alea.nextInt(aleaNonEquiprob.length);
				for(int i =0; i < aleaNonEquiprob[nb_item_to_spawn] ; i++){
					int index_item = nb_alea.nextInt(items.size());
					this.board[x][y].addItem(items.get(index_item).clone());
				}

            }
        }
        catch (CloneNotSupportedException exc){
            System.out.println(System.err);
        }
	}

	/**
	 * Print the Board
	 */
	public void Display() {
		for (int i = 0; i < this.board.length; i++) {
			for (int ligne = 0; ligne < 4; ligne++) {
				StringBuilder x = new StringBuilder();
				for (int j = 0; j < this.board[0].length; j++) {
					if (ligne == 0) {
						x.append(this.board[i][j].getDoor(Direction.North).toString());
					} else if (ligne == 1) {
						x.append(secondLineDisplay(i, j));
					} else if (ligne == 2) {
						x.append(ThirdLineDisplay(i, j));
					} else {
						x.append(this.board[i][j].getDoor(Direction.South).toString());
					}
				}
				System.out.println(x);
			}
		}
	}

	/**
	 * Display the third line of the cell contain the name of the cell and the number of Survivor in
	 * @param i horizontal index of the board
	 * @param j vertical index of the board
	 * @return a String of the display
	 */
	private String ThirdLineDisplay(int i, int j) {
		String x ="";
		if(this.board[i][j].getAllPlayers().isEmpty()) {
			x += this.board[i][j].getDoor(Direction.West).toString()+"   "+this.board[i][j].getDoor(Direction.East).toString();
		}
		else {
			int NbPlayer = this.board[i][j].getAllPlayers().size();
			x += this.board[i][j].getDoor(Direction.West).toString()+"s"+ (NbPlayer > 9 ? "+" : NbPlayer) +" "+this.board[i][j].getDoor(Direction.East).toString();
		}
		return x;
	}

	/**
	 * Display the second line of the cell contain the name of the cell and the number of zombie in
	 * @param i horizontal index of the board
	 * @param j vertical index of the board
	 * @return a String of the display
	 */
	private String secondLineDisplay(int i, int j) {
		String x="";
		if(this.board[i][j].getAllZombies().isEmpty()) {
			x += this.board[i][j].getDoor(Direction.West).toString()+this.board[i][j].toString()+"  "+this.board[i][j].getDoor(Direction.East).toString();
		}
		else {
			int NbZombie = this.board[i][j].getAllZombies().size();
			x += this.board[i][j].getDoor(Direction.West).toString()+this.board[i][j].toString()+"z"+ (NbZombie > 9 ? "+" : NbZombie)+this.board[i][j].getDoor(Direction.East).toString();
		}
		return x;
	}

	/**
	 * return true if the zombie is on the same line that the player
	 * @param cell1 the first cell
	 * @param cell2 the second cell
	 * @return true if the zombie is on the same line that the player
	 */
	public boolean cellIsOnSameLineOrColumn(Cell cell1, Cell cell2){
		int C1x = cell1.getX();
		int C1y = cell1.getY();
		int C2x = cell2.getX();
		int C2y = cell2.getY();

        return C1x == C2x || C1y == C2y;
    }

	/**
	 * return the direction between two cells
	 * @param cell1 cell1
	 * @param cell2 cell2
	 * @return the direction between two cells
	 * @throws IllegalArgumentException if the direction cannot be determined
	 */
	public Direction getDirectionBetweenCells(Cell cell1, Cell cell2) {
	    if(cellIsOnSameLineOrColumn(cell1, cell2)) {
	        int dx = cell2.getX() - cell1.getX();
	        int dy = cell2.getY() - cell1.getY();

	        if (dx > 0) {
	            return Direction.East;
	        } else if (dx < 0) {
	            return Direction.West;
	        } else if (dy > 0) {
	            return Direction.South;
	        } else if (dy < 0) {
	            return Direction.North;
	        }
	    }
		return null;
	}

	/**
	 * return true if the doors are open between two cell
	 * @param cell1 cell 1
	 * @param cell2 cell 2
	 * @return true if the doors are open between two cell
	 */
	public boolean checkOpenDoorsBetweenCells(Cell cell1, Cell cell2) {
	    int playerX = cell1.getX();
	    int playerY = cell1.getY();
	    int zombieX = cell2.getX();
	    int zombieY = cell2.getY();
		boolean res = false;

		Direction direction = getDirectionBetweenCells(cell1, cell2);
		if(direction != null){
			if (direction == Direction.North) {
				for (int y = Math.max(playerY, zombieY); y > Math.min(playerY, zombieY); y--) {
					Door door = this.board[playerX][y].getDoor(Direction.North);
					if (door != null && !door.isBreak()) {
						return res;
					}
				}
				res = true;
			} else if (direction == Direction.South) {
				for (int y = Math.min(playerY, zombieY); y < Math.max(playerY, zombieY); y++) {
					Door door = this.board[playerX][y].getDoor(Direction.South);
					if (door != null && !door.isBreak()) {
						return res;
					}
				}
				res = true;
			} else if (direction == Direction.East) {
				for (int x = Math.min(playerX, zombieX); x < Math.max(playerX, zombieX); x++) {
					Door door = this.board[x][playerY].getDoor(Direction.East);
					if (door != null && !door.isBreak()) {
						return res;
					}
				}
				res = true;
			} else if (direction == Direction.West) {
				for (int x = Math.max(playerX, zombieX); x > Math.min(playerX, zombieX); x--) {
					Door door = this.board[x][playerY].getDoor(Direction.West);
					if (door != null && !door.isBreak()) {
						return res;
					}
				}
				res = true;
			}
		} else {
			throw new IllegalArgumentException("Direction cannot be determined");
		}
		return res;
	}


	/**
	 * add a zombie to the list of the zombie
	 * @param z the zombie to add
	 */
	public void addZombieList(Zombie z){
		this.listZombie.add(z);
	}

	/**
	 * get the list of all zombie in the board
	 * @return all the zombies
	 */
	public List<Zombie> getAllZombies(){
		return this.listZombie;
	}

	/**
	 * update the list of the zombie if zombies are dead
	 * @return the list of the zombie alive 
	 */
	public List<Zombie> updateListZombie(){
		List<Zombie> zombie = new ArrayList<>();
		for(Zombie z : this.listZombie ) {
			if(!z.isDead()) {
				zombie.add(z);
			}
		}
		return zombie;
	}


	/**
	 * generate x zombies in each sewer
	 *
	 * @param nb the number of zombies to spawn
	 */
	public void ProductionZombie(int nb) {
		Random random = new Random();
		for(Sewer s : this.getAllSewers()) {
			for (int i = 0; i < nb; i++) {
				Zombie Z;
				int typeZ = random.nextInt(4);
				if(typeZ ==0) {
					Z = new Walker(s,compteur++);
				}
				else if(typeZ == 1 ) {
					Z = new Runner(s,compteur++);
				}
				else if(typeZ==2) {
					Z = new Broom(s,compteur++);
				}
				else{
					Z = new Abominations(s,compteur++);
				}
	            s.addZombies(Z);
	            this.addZombieList(Z);
			}
		}
	}
	
	/**
	 * put the noise of all the cell at 0
	 */
	public void cleanNoise(){
		for(int i = 0 ; i < this.getBoard().length ; i++){
			for(int j = 0; j < this.getBoard()[0].length; j++){
				this.board[i][j].setNoise(0);
			}
		}
	}

	/**
	 * get the list of Sewers in the board
	 * @return all sewers */
	public List<Sewer> getAllSewers(){
		return this.listSewer;
	}


}
