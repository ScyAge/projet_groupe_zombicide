package board;
import java.util.Random;
import java.lang.Math;
import cell.*;
public class Board {

	private Cell[][] board;
	/**
	 * Builder of Board
	 * @param height of the board
	 * @param width of the board
	 */
	public Board(int height, int width) {
		this.board = new Cell[width][height];
		initBoard(0,width,0,height);
	}
	/**
	 * init the Board
	 * @param xd start width
	 * @param xf final width
	 * @param yd start height
	 * @param yf final height
	 */
	private void initBoard(int xd,int xf,int yd,int yf) {
		if (isSplitable(xd, xf, yd, yf)) {
			Random random = new Random();
			// placement random de la route horizontal (erreur)
			int x1= random.nextInt((xf-2)-(xd+2))+(xd+2);
			HStreet(yf-yd, x1);
			// placement random de la route vertical (erreur)
			int y1= random.nextInt((yf-2)-(yd+2))+(yd+2);
			VStreet(y1,xf-xd);
			// on fait la meme chose dans les sous board
			initBoard(0,x1-1,0,y1-1);
			initBoard(x1+1,xf,0,y1-1);
			initBoard(0,x1-1,y1+1,yf);
			initBoard(x1+1,xf,y1+1,yf);
		}
		else {
			// si on peut pas mettre de route on met des room
			Building(xf,xd,yf,yd);
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
		for(int i =0;i<(xf-xd);i++) {
			for(int j =0;j<(yf-yd);j++) {
				this.board[xd+i][yd+j] = new Room(i,j,"tropico");
			}
		}
	}
	/**
	 * Creat vertical Street
	 * @param y1 height of the street
	 * @param x  the size of the street
	 */
	private void VStreet(int y1,int x) {
		for(int i = 0;i<x;i++) {
			this.board[i][y1] = new Street(i,y1,"ananas");
		}
	}
	/**
	 * Create horizontal street
	 * @param y the size of the street
	 * @param x1 width of the street
	 */
	private void HStreet(int y, int x1) {
		for(int i = 0;i<y;i++) {
			this.board[x1][i] = new Street(x1,i,"ananas");
		}
	}
	private boolean isSplitable(int xd, int xf, int yd, int yf) {
		return xf -xd+1>=5 && yf-yd+1>=5;
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
