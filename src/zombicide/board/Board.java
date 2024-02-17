package zombicide.board;
import java.util.Random;

import zombicide.actor.Actor;
import zombicide.cell.*;
import zombicide.util.Direction;
import zombicide.util.Door;

/**
 * class of board
 */
public class Board {
	
	/** composition of board */
	protected Cell[][] board;
	private boolean drugStoreExist;
	private boolean continentalExist;
    private int xr;
    
    
	/**
	 * Builder of Board
	 * @param height of the board
	 * @param width of the board
	 */
	public Board(int height, int width) {
		this.board = new Cell[width][height];
		this.drugStoreExist=false;
		this.continentalExist=false;
        this.xr=((height-1) *(width-1))/2 ;
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
						else if((!drugStoreExist&& Y == 0) || (continentalExist && !drugStoreExist)){
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
		}
	}
	
	
	
	/**
	 * Create vertical Street
	 * @param y1 height of the street
	 * @param x  the size of the street
	 */
	private void VStreet(int y1,int xf, int xd){
		for(int i = xd;i<xf;i++){
			this.board[i][y1] = new Street(i,y1) ;
		}
	}
	/**
	 * Create horizontal street
	 * @param y the size of the street
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
	 * 
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
			this.board[x][y+1].breakDoor(Direction.oppose(d));;
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
	 * @param actor the actor
	 * @param direction the direction
	 */
	public void moveActor(Actor actor, Direction direction) {
        if (canMove(actor, direction)) {
            int x = actor.getCurrentCell().getX();
            int y = actor.getCurrentCell().getY();

            if (direction == Direction.North) {
                actor.setCell(board[x - 1][y]);
            } else if (direction == Direction.South) {
                actor.setCell(board[x + 1][y]);
            } else if (direction == Direction.West) {
                actor.setCell(board[x][y - 1]);
            } else if (direction == Direction.East) {
                actor.setCell(board[x][y + 1]);
            }
        } else {
            System.out.println("Impossible de se déplacer dans cette direction.");
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
						if(this.board[i][j].getAllZombies().size()==0) {
							x += this.board[i][j].getDoor(Direction.West).toString()+this.board[i][j].toString()+"  "+this.board[i][j].getDoor(Direction.East).toString();
						}
						else {
							int NbZombie = this.board[i][j].getAllZombies().size();
							x += this.board[i][j].getDoor(Direction.West).toString()+this.board[i][j].toString()+"z"+ (NbZombie > 9 ? "+" : NbZombie) + this.board[i][j].getAllZombies().size()+this.board[i][j].getDoor(Direction.East).toString();
						}
					}
					else if(ligne ==2){
						if(this.board[i][j].getAllPlayers().size()==0) {
							x += this.board[i][j].getDoor(Direction.West).toString()+"   "+this.board[i][j].getDoor(Direction.East).toString();
						}
						else {
							int NbPlayer = this.board[i][j].getAllPlayers().size();
							x += this.board[i][j].getDoor(Direction.West).toString()+"s"+ (NbPlayer > 9 ? "+" : NbPlayer) + this.board[i][j].getAllPlayers().size()+" "+this.board[i][j].getDoor(Direction.East).toString();
						}
					}
					else if(ligne ==3){
						x +=this.board[i][j].getDoor(Direction.South).toString();
					}
				}
				System.out.println(x);
			}
			
		}
	}
}
