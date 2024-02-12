package zombicide.board;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class BoardTest {
	private Board board;
    @BeforeEach
    void setUp() {
    	board = new Board(5,5);
    }
	
    @Test
	public void InitBoard() {
		for(int i = 0 ; i<board.getBoard().length;i++) {
			for(int j = 0 ; j<board.getBoard()[i].length;j++) {
				assertNotNull(board.getBoard()[i][j]);
			}
		}
	}
}
