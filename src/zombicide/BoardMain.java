package zombicide;
import zombicide.actor.player.Player;
import zombicide.board.*;
import zombicide.util.Direction;

public class BoardMain {

	public static void main(String[] arg ) {
		Board b = new Board(5,5);
		b.Display();
		System.out.println("\n");
		Player p = new Player(5,b.getBoard()[1][1]);
		Player p2 = new Player(5,b.getBoard()[1][1]);
		b.BreakDoor(Direction.South, 1, 1);
		b.getBoard()[1][1].addPlayers(p);
		b.getBoard()[1][1].addPlayers(p2);

		System.out.println(b.getBoard()[2][0].getDoor(Direction.North).isBreak());
	}
}
