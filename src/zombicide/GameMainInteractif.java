package zombicide;

import java.util.ArrayList;
import java.util.List;

import zombicide.actor.actionPlayer.ActionsPlayer;
import zombicide.actor.actionPlayer.Attack;
import zombicide.actor.actionPlayer.LookAround;
import zombicide.actor.actionPlayer.MakeNoise;
import zombicide.actor.actionPlayer.Move;
import zombicide.actor.actionPlayer.OpenDoor;
import zombicide.actor.actionPlayer.SearchInTRoomAction;
import zombicide.actor.actionPlayer.TakeInHandAction;
import zombicide.actor.actionPlayer.UseEquipmentAction;
import zombicide.actor.actionPlayer.roles.Chanceux;
import zombicide.actor.actionPlayer.roles.Combattant;
import zombicide.actor.actionPlayer.roles.Fouineur;
import zombicide.actor.actionPlayer.roles.Soigneur;
import zombicide.actor.player.Player;
import zombicide.board.Board;
import zombicide.item.Item;
import zombicide.item.equipment.FirstAidKit;
import zombicide.item.equipment.Glasses;
import zombicide.item.equipment.HealingVial;
import zombicide.item.equipment.MapCard;
import zombicide.item.equipment.MasterKey;
import zombicide.item.weapons.Axe;
import zombicide.item.weapons.Carabine;
import zombicide.item.weapons.Chainsaw;
import zombicide.item.weapons.Crowbar;
import zombicide.item.weapons.Gun;
import zombicide.util.listchooser.InteractiveListChooser;
import zombicide.util.listchooser.RandomListChooser;

public class GameMainInteractif {
	
	public static void main(String[] args) {
		int size = 5 ;
		if(args.length> 0) {
			size = Integer.parseInt(args[0]);
		}
		Board b = new Board(size,size);
		//init items
		List<Item> items = new ArrayList<>();
		Item Kit = new FirstAidKit(new InteractiveListChooser<>());
		Item glasses = new Glasses(b);
		Item heal = new HealingVial();
		Item Mapcard = new MapCard(b);
		Item MasterKey = new MasterKey(new InteractiveListChooser<>(),b);
		Item gun =new Gun();
		Item crowbar =new Crowbar();
		Item chainsaw =new Chainsaw();
		Item carabine =new Carabine();
		Item axe =new Axe();
		
		items.add(axe);
		items.add(Kit);
		items.add(glasses);
		items.add(heal);
		items.add(Mapcard);
		items.add(gun);
		items.add(crowbar);
		items.add(chainsaw);
		items.add(MasterKey);
		items.add(carabine);
		
		b.setItems(items);
		b.initBoard();
		
		//initalisation de toute les actions
		ActionsPlayer take = new TakeInHandAction(new InteractiveListChooser<>());
		ActionsPlayer LA =new LookAround(b);
		ActionsPlayer OD = new OpenDoor(new InteractiveListChooser<>(),b);
		ActionsPlayer move = new Move(b,new InteractiveListChooser<>());
		ActionsPlayer noise = new MakeNoise();
		ActionsPlayer useEquip = new UseEquipmentAction();
		ActionsPlayer attack = new Attack(new InteractiveListChooser<>(),b);
		ActionsPlayer search = new SearchInTRoomAction(new InteractiveListChooser<>());

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
		
		Item MasterKey2 = new MasterKey(new InteractiveListChooser<>(),b);
		
		
		List<Player> Players = new ArrayList<>();
		//creation des players
		Player p1 = new Player(5,b.getCellBoard(b.getSpawnPlayers().getX(), b.getSpawnPlayers().getY()),1,5,actions);
		Player p2 = new Player(5,b.getCellBoard(b.getSpawnPlayers().getX(), b.getSpawnPlayers().getY()),2,5,actions);
		Player p3 = new Player(5,b.getCellBoard(b.getSpawnPlayers().getX(), b.getSpawnPlayers().getY()),3,5,actions);
		Player p4 = new Player(5,b.getCellBoard(b.getSpawnPlayers().getX(), b.getSpawnPlayers().getY()),4,5,actions);

		Players.add(p1);
		Players.add(p2);
		Players.add(p3);
		Players.add(p4);

		//création des Roles plus ajout
		ActionsPlayer Chanceux = new Chanceux(new InteractiveListChooser<>(),b);
		ActionsPlayer Combattant = new Combattant(new InteractiveListChooser<>(),b);
		ActionsPlayer Fouineur = new Fouineur(new InteractiveListChooser<>());
		ActionsPlayer Soigneur = new Soigneur(new InteractiveListChooser<>());

		p1.setAction(Chanceux);
		p2.setAction(Combattant);
		p3.setAction(Fouineur);
		p4.setAction(Soigneur);
		
		ActionsPlayer take2 = new TakeInHandAction(new RandomListChooser<>());
		b.getSpawnPlayers().spawnPlayer(Players);
		p1.putItemInBackPack(MasterKey2);
		take2.action(p1);
		
		Game g = new Game(b,Players,actions,items,new InteractiveListChooser<>());	
		g.play();
	}
}
