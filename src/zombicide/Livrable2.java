package zombicide;

import zombicide.actor.actionPlayer.ActionsPlayer;
import zombicide.actor.actionPlayer.roles.*;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.actor.player.*;
import zombicide.actor.zombie.*;
import zombicide.item.*;
import zombicide.item.equipment.*;
import zombicide.util.Direction;

public class Livrable2 {
	
	public static void main(String[] arg ){
		
		Board test = new TrainingBoard();
		
		Zombie z  = new Gigantomachia(test.getCellBoard(0, 0),1);
		Zombie z1  = new Gigantomachia(test.getCellBoard(4, 0),2);
		Zombie z2  = new Gigantomachia(test.getCellBoard(0, 4),3);
		Zombie z3  = new Gigantomachia(test.getCellBoard(4, 4),4);
		test.getCellBoard(0, 0).addZombies(z);
		test.getCellBoard(4, 0).addZombies(z1);
		test.getCellBoard(0, 4).addZombies(z2);
		test.getCellBoard(4, 4).addZombies(z3);
		
		Player p1 = new Player(5,test.getCellBoard(2, 2),1,6);
		Player p2 = new Player(5,test.getCellBoard(2, 2),2,6);
		Player p3 = new Player(5,test.getCellBoard(2, 2),3,6);
		Player p4 = new Player(5,test.getCellBoard(2, 2),4,6);
		ActionsPlayer Chanceux = new Chanceux(test);
		ActionsPlayer Combattant = new Combattant(test);
		ActionsPlayer Fouineur = new Fouineur();
		ActionsPlayer Soigneur = new Soigneur();
		p1.setAction(Chanceux);
		p2.setAction(Combattant);
		p3.setAction(Fouineur);
		p4.setAction(Soigneur);
		test.getCellBoard(2, 2).addPlayers(p1);
		test.getCellBoard(2, 2).addPlayers(p2);
		test.getCellBoard(2, 2).addPlayers(p3);
		test.getCellBoard(2, 2).addPlayers(p4);
		
		Item MapCard1 = new MapCard(test);
		Item MapCard2 = new MapCard(test);
		Item MapCard3 = new MapCard(test);
		Item MapCard4 = new MapCard(test);
		p1.putItemInBackPack(MapCard1);
		p2.putItemInBackPack(MapCard2);
		p3.putItemInBackPack(MapCard3);
		p4.putItemInBackPack(MapCard4);
		
		Item HealingVial1 = new HealingVial();
		Item HealingVial2 = new HealingVial();
		Item HealingVial3 = new HealingVial();
		Item HealingVial4 = new HealingVial();
		p1.putItemInBackPack(HealingVial1);
		p2.putItemInBackPack(HealingVial2);
		p3.putItemInBackPack(HealingVial3);
		p4.putItemInBackPack(HealingVial4);
		try {
			p1.takeInHandFromBackPack(1);
			p2.takeInHandFromBackPack(1);
			p3.takeInHandFromBackPack(1);
			p4.takeInHandFromBackPack(1);
		}catch(Exception e) {
			System.out .println(e.getMessage());
		}
		System.out .println("First display ");
		test.Display();
		System.out .println("\n Second display ");
		
		test.movePlayer(p1, Direction.North);
		test.movePlayer(p2, Direction.North);
		test.movePlayer(p3, Direction.North);
		test.movePlayer(p4, Direction.North);
		
		test.Display();
		
		
		
		
	}
}

