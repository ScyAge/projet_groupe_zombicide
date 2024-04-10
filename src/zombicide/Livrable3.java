package zombicide;
import java.util.ArrayList;
import java.util.List;

import zombicide.actor.actionPlayer.*;
import zombicide.actor.actionPlayer.roles.*;
import zombicide.actor.actionZombie.AttackZombie;
import zombicide.actor.actionZombie.MoveZ;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.actor.player.*;
import zombicide.actor.zombie.*;
import zombicide.item.*;
import zombicide.item.equipment.*;
import zombicide.item.weapons.Axe;
import zombicide.util.listchooser.ListChooser;
import zombicide.util.listchooser.RandomListChooser;

public class Livrable3 {
	
		public static void main(String[] arg ){
			Board b = new TrainingBoard();

			//initalisation de toute les actions
			ActionsPlayer take = new TakeInHandAction(new RandomListChooser<>());
			ActionsPlayer LA =new LookAround(b);
			ActionsPlayer OD = new OpenDoor(new RandomListChooser<>(),b);
			ActionsPlayer move = new Move(b,new RandomListChooser<>());
			ActionsPlayer noise = new MakeNoise();
			ActionsPlayer useEquip = new UseEquipmentAction();
			ActionsPlayer attack = new Attack(new RandomListChooser<>(),b);
			ActionsPlayer search = new SearchInTRoomAction(new RandomListChooser<>());

			//ajout dans une ArrayList
			List<ActionsPlayer> actions = new ArrayList<>();
			actions.add(take);
			actions.add(LA);
			actions.add(OD);
			actions.add(move);
			actions.add(noise);
			actions.add(useEquip);
			actions.add(attack);
			actions.add(search);
			// creation d'une liste de player et de zombie
			List<Player> Players = new ArrayList<>();

			//ajout des zombies dans chaque cell
			for(int x =0;x< b.getBoard().length;x++) {
				for(int y =0;y< b.getBoard()[0].length;y++) {
					Zombies z = new Runner(b.getCellBoard(x, y),x+y);
					b.getCellBoard(x, y).addZombies(z);
				}
				
			}

			//creation des players
			Player p1 = new Player(5,b.getCellBoard(0, 2),1,5,actions);
			Player p2 = new Player(5,b.getCellBoard(0, 2),2,5,actions);
			Player p3 = new Player(5,b.getCellBoard(0, 2),3,5,actions);
			Player p4 = new Player(5,b.getCellBoard(0, 2),4,5,actions);

			Players.add(p1);
			Players.add(p2);
			Players.add(p3);
			Players.add(p4);

			//création des Roles plus ajout
			ActionsPlayer Chanceux = new Chanceux(new RandomListChooser<>(),b);
			ActionsPlayer Combattant = new Combattant(new RandomListChooser<>(),b);
			ActionsPlayer Fouineur = new Fouineur(new RandomListChooser<>());
			ActionsPlayer Soigneur = new Soigneur(new RandomListChooser<>());

			p1.setAction(Chanceux);
			p2.setAction(Combattant);
			p3.setAction(Fouineur);
			p4.setAction(Soigneur);

			//ajout des players dans le board
			b.getCellBoard(0, 2).addPlayers(p1);
			b.getCellBoard(0, 2).addPlayers(p2);
			b.getCellBoard(0, 2).addPlayers(p3);
			b.getCellBoard(0, 2).addPlayers(p4);
			
			b.Display();

			//création et ajout des deux objet dans les sac des joueurs
			Item heal = new HealingVial("heal", false);
			Axe axe = new Axe();

			p2.putItemInBackPack(axe);
			p3.putItemInBackPack(heal);

			//puis ajout dans leurs main
			take.action(p2);
			take.action(p3);


			ListChooser<ActionsPlayer> Lchoose = new RandomListChooser<>();
			//execution d'une action pour chaque joueurs
			for(Player p : Players){
				List<ActionsPlayer> ActionOfPlayer = p.getActionOfThePlayer();
				ActionsPlayer a =Lchoose.choose("Choose a action to execute",ActionOfPlayer);
				a.action(p);

			}

			

			
			// creation des deux actions des zombies
			MoveZ movez = new MoveZ(b);
			AttackZombie AZ = new AttackZombie();
			
			// add des zombie dans une list
			List<Zombies> Zombies = new ArrayList<>();
			for(int x =0;x< b.getBoard().length;x++) {
				for(int y =0;y< b.getBoard()[0].length;y++) {
					Zombies.addAll(b.getCellBoard(x, y).getAllZombies());
				}
			}

			//execution des action attaque et move pour chacun des zombie
			for(Zombies z :Zombies) {
				AZ.action(z);
				movez.action(z);
			}
			
			b.Display();
			
			System.out.println(p1);
			System.out.println(p2);
			System.out.println(p3);
			System.out.println(p4);

		}
		
		

}
