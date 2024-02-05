package zombicide;

import zombicide.board.Board;
import zombicide.board.TrainingBoard;

public class Livrable1 {
	
	public static void main(String[] arg ) {
		System.out.println("Plateau principal :");
		System.out.println('\n');
		if(arg.length <= 0) {
			System.out.println("Pas d'argument donc génération automatique (10x10)");
			Board c = new Board(10,10);
			c.Display();
		}
		else if (arg.length >= 2){
			Board b = new Board(Integer.parseInt(arg[0]),Integer.parseInt(arg[1]));
			b.Display();
			
		}
		System.out.println('\n');
		System.out.println("Plateau d'entrainement :");
		Board test = new TrainingBoard();
		test.Display();
	}
}
