package zombicide;
import java.util.ArrayList;
import java.util.List;

import zombicide.actor.actionPlayer.ActionsPlayer;
import zombicide.actor.actionPlayer.LookAround;
import zombicide.actor.actionPlayer.Move;
import zombicide.actor.actionPlayer.OpenDoor;
import zombicide.actor.actionPlayer.TakeInHandAction;
import zombicide.actor.actionPlayer.roles.*;
import zombicide.actor.actionZombie.AttackZombie;
import zombicide.actor.actionZombie.MoveZ;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.actor.player.*;
import zombicide.actor.zombie.*;
import zombicide.actor.actionPlayer.roles.*;
import zombicide.item.*;
import zombicide.item.equipment.*;
import zombicide.item.weapons.Axe;
import zombicide.util.Direction;

public class Livrable3 {
	
		public static void main(String[] arg ){
			Board b = new TrainingBoard();
			for(int x =0;x< b.getBoard().length;x++) {
				for(int y =0;y< b.getBoard()[0].length;y++) {
					b.getCellBoard(x, y).addZombies(new Gigantomachia(b.getCellBoard(x, y),1));
				}
				
			}
			
			Player p1 = new Player(5,b.getCellBoard(0, 2),1,6);
			Player p2 = new Player(5,b.getCellBoard(0, 2),2,6);
			Player p3 = new Player(5,b.getCellBoard(0, 2),3,6);
			Player p4 = new Player(5,b.getCellBoard(0, 2),4,6);
			ActionsPlayer Chanceux = new Chanceux(b);
			ActionsPlayer Combattant = new Combattant(b);
			ActionsPlayer Fouineur = new Fouineur();
			ActionsPlayer Soigneur = new Soigneur();
			p1.setRoles(Chanceux);
			p2.setRoles(Combattant);
			p3.setRoles(Fouineur);
			p4.setRoles(Soigneur);
			b.getCellBoard(0, 2).addPlayers(p1);
			b.getCellBoard(0, 2).addPlayers(p2);
			b.getCellBoard(0, 2).addPlayers(p3);
			b.getCellBoard(0, 2).addPlayers(p4);
			
			b.Display();
			
			Item heal = new HealingVial("heal", false);
			Axe axe = new Axe();
			
			p2.putItemInBackPack(axe);
			p3.putItemInBackPack(heal);
			
			TakeInHandAction take = new TakeInHandAction();
			LookAround LA =new LookAround();
			OpenDoor OD = new OpenDoor(b);
			Move move = new Move(b);
			
			take.action(p2);
			take.action(p3);
			
			LA.action(p1);
			OD.action(p2);
			move.action(p3);
			move.action(p4);
			
			List<Zombies> Zombies = new ArrayList<>();
			MoveZ movez = new MoveZ(b);
			AttackZombie AZ = new AttackZombie();
			
			for(int x =0;x< b.getBoard().length;x++) {
				for(int y =0;y< b.getBoard()[0].length;y++) {
					Zombies.addAll(b.getCellBoard(x, y).getAllZombies());

				}
			}
			
			for(Zombies z :Zombies) {
				movez.action(z);
				AZ.action(z);
			}
			
			b.Display();
			
			System.out.println(p1.toString());
			System.out.println(p2.toString());
			System.out.println(p3.toString());
			System.out.println(p4.toString());

		}
		
		

}
