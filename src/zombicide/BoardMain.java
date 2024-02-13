package zombicide;
import zombicide.board.*;
import zombicide.util.Direction;

public class BoardMain {

	public static void main(String[] arg ) {
		Board b = new Board(5,5);
		b.Display();
		System.out.println("\n");
		b.BreakDoor(Direction.East, 1, 1);
		b.Display();
	}
}
