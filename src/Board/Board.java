package Board;
import Cell.*;
public class Board {

	private Cell[][] board;
	
	public Board(int height, int width) {
		this.board = new Cell[width][height];
		initBoard();
	}
	private void initBoard() {}
	
	public void Display() {}
}
