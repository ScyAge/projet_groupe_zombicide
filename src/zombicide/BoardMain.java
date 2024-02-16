package zombicide;
import zombicide.board.*;
import zombicide.util.Direction;

public class BoardMain {

	public static void main(String[] arg ) {
		Board b = new Board(5,5);
		b.Display();
		System.out.println("\n");
		b.BreakDoor(Direction.South, 1, 1);
		b.Display();
		System.out.println(b.getBoard()[2][0].getDoor(Direction.North).isBreak());
	}
}
