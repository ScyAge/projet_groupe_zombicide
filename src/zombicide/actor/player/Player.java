package zombicide.actor.player;

import java.util.*;

import zombicide.actor.Actor;
import zombicide.exeption.ItemDoesNotExistExeption;
import zombicide.item.*;



public class Player extends Actor{
	
	private Map<String,Item>  Bagpack;
	private  Item ItemInHand;
	private int expertiseLevel;
	private List<Roles> roles;
	
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


	/**
	 * method that allows survivor to perform a SpecialAction related to its roles. If he has no role,
	 * he does nothing; if he has only one, he executes the action; if he has more than one,
	 * the role_number parameter lets him choose which action to use.
	 * @param role_number number represent which of is roles the survivor want to use
	 * @throws IndexOutOfBoundsException if role_number is in the range 0,size of the list roles
	 */
	public void actionSpecial(int role_number) throws IndexOutOfBoundsException{

		int size = this.roles.size();
		if(size ==1){
			this.roles.get(0).action(this);
		} else if (size >1) {
			if((role_number > size)||(role_number < 0)){
				throw new IndexOutOfBoundsException("choose a role number between 0 and"+(size-1));
			}
			this.roles.get(role_number).action(this);
		}
	}

}
