package zombicide.actor.player;

import java.util.*;

import zombicide.actor.Actor;
import zombicide.item.*;




public class Player extends Actor{
	
	private Map<String,Item>  Bagpack;
	
	public Player(int hp,int dgt) {
		super(hp,dgt,3);
		this.Bagpack = new HashMap<>();
		
	}
	
	public void heal(int heal) {
		this.hp += heal;
	}

}
