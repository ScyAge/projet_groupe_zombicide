package zombicide.board;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zombicide.actor.Actor;
import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
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
	private boolean drugStoreExist;
	private boolean continentalExist;
    private int xr;
    private List<Item> items;
    
    
	/**
	 * Builder of Board
	 * @param height of the board
	 * @param width of the board
	 */
	public Board(int height, int width) {
		this(height,width,new ArrayList<Item>() );
	}
	
	/**
	 * Builder of Board
	 * @param height of the board
	 * @param width of the board
	 * @param items list of all items in the board
	 */
	public Board(int height, int width,List<Item> items) {
		this.board = new Cell[width][height];
		this.drugStoreExist=false;
		this.continentalExist=false;
        this.xr=((height-(height/4)) *(width-(width/4))-1) ;
		this.items = items;
		initBoard(0,width,0,height,true);
	}
	
	/**
	 * init the Board
	 * @param xd start width
	 * @param xf final width
	 * @param yd start height
	 * @param yf final height
	 */
	private void initBoard(int xd,int xf,int yd,int yf,boolean sewer) {
		if(!isSplitable(xd, xf, yd, yf)){
			Building(xf, xd, yf, yd);
		}
		else{
			Random random = new Random();
			int Y = random.nextInt((yf-2) -(yd+2))+(yd+2);
			int X = random.nextInt((xf-2) -(xd+2))+(xd+2);
			VStreet(Y,xf,xd);
			HStreet(yd,yf, X);
			initBoard(xd, X, yd, Y,false);
			initBoard(X+1, xf, yd, Y,false);
			initBoard(xd, X, Y+1, yf,false);
			initBoard(X+1, xf, Y+1, yf,false);
			if (sewer){
				this.board[xd][Y] = new Sewer(xd, Y);
				this.board[xf-1][Y] = new Sewer(xd, Y);
				this.board[X][yd] = new Sewer(xd, Y);
				this.board[X][yf-1] = new Sewer(xd, Y);
				this.board[X][Y] = new SpawnPlayers(X,Y);
			}
			
		}
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
				this.addDrugStoreAndContinentalInBuilding(i, j);
				
			}
		}
	}
	
	private void addDrugStoreAndContinentalInBuilding(int i , int j) {
        Random random = new Random() ;
		if(!continentalExist || !drugStoreExist ) {
        	int X= random.nextInt(xr);
			if(X==0) {
				int Y= random.nextInt(2);
				if((!continentalExist && Y == 1) || (!continentalExist && drugStoreExist)  ) {
					Continental continental = new Continental(i,j);
					this.board[i][j]=continental;
					continentalExist=true;
				}
				else if((!drugStoreExist)){
					DrugStore drugstore = new DrugStore(i,j);
					this.board[i][j]=drugstore ;
					drugStoreExist=true ;
				}
				
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
	private boolean canMove(Actor actor, Direction direction){
		int x = actor.getCurrentCell().getX();
		int y = actor.getCurrentCell().getY();

		if ((direction == Direction.North && x == 0) ||
            (direction == Direction.South && x == board.length - 1) ||
            (direction == Direction.West && y == 0) ||
            (direction == Direction.East && y == board[0].length - 1)) {
            return false;
        }
		
		Door door = board[x][y].getDoor(direction);
        return door != null && door.isBreak();
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
	public void moveZombie(Zombies zombie, Direction direction) {
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
        } else {
            System.out.println("Impossible de se déplacer dans cette direction.");
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
		if(canMove(a,d))
			if(d == Direction.North) {
				return this.board[c.getX()-1][c.getY()];
			}
			else if(d == Direction.East) {
				return this.board[c.getX()][c.getY()+1];
			}
			else if(d == Direction.South) {
				return this.board[c.getX()+1][c.getY()];
			}
			else {
				return this.board[c.getX()][c.getY()-1];
			}
		return null;
	}
	
	/**
	 * Place item on the board if and only if the random number drawn is 5.
	 * @param x indice of the cell
	 * @param y indice of the cell
	 */
	private void placeItemAlea(int x,int y){
		int nb;
		Random nb_alea = new Random();
		for( Item item : this.getItem()) {
			nb = nb_alea.nextInt((this.getItem().size()*5)+1);
			if(nb == 5){
				this.board[x][y].addItem(item);
			}
		}
	}
	
	/**
	 * Print the Board
	 */
	public void Display() {
		for(int i =0;i<this.board.length;i++) {
			for(int ligne = 0;ligne <4;ligne++) {
				String x = "";
				for(int j =0;j<this.board[0].length;j++) {
					if(ligne ==0) {
						x += this.board[i][j].getDoor(Direction.North).toString();
					}
					else if(ligne ==1){
						x += secondLineDisplay(i, j);
					}
					else if(ligne ==2){
						x += ThirdLineDisplay(i, j);
					}
					else{
						x += this.board[i][j].getDoor(Direction.South).toString();
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
}
