package board;
import java.util.Random;
import cell.*;
public class Board {

	private Cell[][] board;
	
	public Board(int height, int width) {
		this.board = new Cell[width][height];
		initBoard(0,width,0,height);
	}
	private void initBoard(int xd,int xf,int yd,int yf) {
		if (isSplitable(xd, xf, yd, yf)) {
			Random random = new Random();
			int x1= random.nextInt(xf-xd-4)+2;
			HStreet(yf-yd, x1);
			int y1= random.nextInt(yf-yd-4)+2;
			VStreet(y1,xf-xd);
			initBoard(0,x1,0,y1);
			initBoard(x1+1,xf,0,y1);
			initBoard(0,x1,y1+1,yf);
			initBoard(x1+1,xf,y1+1,yf);
		}
		else {
			Building(xf,xd,yf,yd);
		}
	}
	private void Building(int xf,int xd , int yf,int yd) {
		for(int i =0;i<(xf-xd);i++) {
			for(int j =0;j<(yf-yd);j++) {
				this.board[xd+i][yd+j] = new Room(i,j,"tropico");
			}
		}
	}
	private void VStreet(int y1,int x) {
		for(int i = 0;i<x;i++) {
			this.board[i][y1] = new Street(i,y1,"ananas");
		}
	}
	private void HStreet(int y, int x1) {
		for(int i = 0;i<y;i++) {
			this.board[x1][i] = new Street(x1,i,"ananas");
		}
	}
	private boolean isSplitable(int xd, int xf, int yd, int yf) {
		return xf -xd+1>=5 && yf-yd+1>=5;
	}
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
