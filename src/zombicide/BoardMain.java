package zombicide;
import zombicide.board.*;
import zombicide.util.Direction;

public class BoardMain {

	public static void main(String[] arg ) {
		Board b = new Board(5,5);
		b.Display();
		System.out.println("\n");
		b.getBoard()[1][1].getDoor(Direction.South).Break();
		b.Display();
	}
}
