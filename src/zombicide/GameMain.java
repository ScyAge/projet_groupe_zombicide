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
import zombicide.util.listchooser.RandomListChooser;

public class GameMain {
	
	public static void main(String[] args) {
		int size = 5 ;
		if(args.length> 0) {
			size = Integer.parseInt(args[0]);
		}
		Board b = new Board(size,size);
		
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
		
		List<Player> Players = new ArrayList<>();
		
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
		
		List<Item> items = new ArrayList<>();
		Item Kit = new FirstAidKit("kit");
		Item glasses = new Glasses("glasse",b);
		Item heal = new HealingVial("heal");
		Item Mapcard = new MapCard("heal",b);
		Item MasterKey = new MasterKey("key");
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
		
		Game g = new Game(b,Players,actions,items,new RandomListChooser<>());	
		g.play();
	}
}
