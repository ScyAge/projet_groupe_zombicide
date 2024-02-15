package zombicide.actor.player;

import java.util.*;

import zombicide.actor.Actor;
import zombicide.exeption.ItemDoesNotExistExeption;
import zombicide.item.*;




public class Player extends Actor{
	
	private Map<String,Item>  Bagpack;
	private  Item ItemInHand;
	private int expertiseLevel;
	
	public Player(int lifePoints) {
		super(lifePoints,3);
		this.Bagpack = new HashMap<>();
		this.ItemInHand = null ;
		this.expertiseLevel = 1;
		
	}
	
	/**
	 * Give life Point to the player
	 * @param heal numbers of lifePoints add 
	 */
	public void heal(int heal) {
		this.lifePoints += heal;
	}
	
	public Map<String,Item> openBagpack(){
		return this.Bagpack;
	}
	
	public void takeInHand(String name)throws ItemDoesNotExistExeption {
		if(!this.Bagpack.containsKey(name)) {
			throw new ItemDoesNotExistExeption("Item not fund in bagpack");
		}
		this.ItemInHand = this.Bagpack.get(name);
	}
	
	
	public int getExpertiseLevel() {
		return this.expertiseLevel;
	}


	public void setExpertiseLevel(int expertiseLevel) {
		this.expertiseLevel = expertiseLevel;
	}


	public Item getItemInHand() {
		return this.ItemInHand;
	}

}
