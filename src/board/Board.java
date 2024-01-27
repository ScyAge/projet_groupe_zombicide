package board;
import java.util.Random;
import cell.*;
import util.Direction;
import util.door;
public class Board {

	private Cell[][] board;
	/**
	 * Builder of Board
	 * @param height of the board
	 * @param width of the board
	 */
	public Board(int height, int width) {
		this.board = new Cell[width][height];
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
			}
		}
	}
	
	/**
	 *  Creat Room
	 * @param xd start width
	 * @param xf final width
	 * @param yd start height
	 * @param yf final height
	 */
	private void Building(int xf,int xd , int yf,int yd) {
		for(int i =xd;i<(xf);i++) {
			for(int j =yd;j<(yf);j++) {
				Room room = new Room(i,j);
				this.board[i][j] = room;
				
				if (i > xd) {
	                room.setDirection(Direction.West, new door(true));
	            } else {
	                room.setDirection(Direction.West, new door(false)); 
	            }
				if (i < xf - 1) {
	                room.setDirection(Direction.East, new door(true));
	            } else {
	                room.setDirection(Direction.East, new door(false)); 
	            }
				if (j > yd) {
	                room.setDirection(Direction.North, new door(true));
	            } else {
	                room.setDirection(Direction.North, new door(false)); 
	            }

	            if (j < yf - 1) {
	                room.setDirection(Direction.South, new door(true));
	            } else {
	                room.setDirection(Direction.South, new door(false)); 
	            }
			}
		}
	}
	/**
	 * Creat vertical Street
	 * @param y1 height of the street
	 * @param x  the size of the street
	 */
	private void VStreet(int y1,int xf, int xd) {
		for(int i = xd;i<xf;i++) {
			this.board[i][y1] = new Street(i,y1);
		}
	}
	/**
	 * Create horizontal street
	 * @param y the size of the street
	 * @param x1 width of the street
	 */
	private void HStreet(int yd,int yf, int x1) {
		for(int i = yd;i<yf;i++) {
			this.board[x1][i] = new Street(x1,i);
		}
	}
	private boolean isSplitable(int xd, int xf, int yd, int yf) {
		return (xf -xd)>=5 && (yf-yd)>=5;
	}

	/**
	 * Print the Board
	 */
	public void Display() {
		for(int i =0;i<this.board[0].length;i++) {
			for(int j =0;j<this.board.length;j++) {
				System.out.print("|"+this.board[j][i].toString());
			}
			System.out.println("|");
			for(int j =0;j<this.board.length;j++) {
				System.out.print("――");
			}
			System.out.println("");
		}
	}
}
