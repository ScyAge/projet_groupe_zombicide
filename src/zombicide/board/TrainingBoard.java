package zombicide.board;
import zombicide.cell.*;

public class TrainingBoard extends Board {

	public TrainingBoard() {
		super(5,5);
		this.changeTraining();
	}
	private void changeTraining() {
		this.board[2][0] = new Street(2,0);
		this.board[0][2] = new Street(0,2);
		this.board[2][4] = new Street(2,4);
		this.board[4][2] = new Street(4,2);
		this.board[2][2] = new Sewer(2,2);
	}
}
