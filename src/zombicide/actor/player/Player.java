package zombicide.actor.player;

import java.util.*;

import zombicide.actor.Actor;
import zombicide.actor.player.roles.RolesIntrerface;
import zombicide.actor.zombie.Zombies;
import zombicide.exeption.ItemDoesNotExistExeption;
import zombicide.item.*;
import zombicide.cell.*;

public class Player extends Actor{
	
	private Map<String,Item>  backpack;
	private  Item itemInHand;
	private int expertiseLevel;
	private List<RolesIntrerface> roles;
	
	public Player(int lifePoints,Cell cell) {
		super(lifePoints,3, cell);
		this.backpack = new HashMap<>();
		this.itemInHand = null ;
		this.expertiseLevel = 1;
		this.roles = new ArrayList<>();
	}
	

	/**
	 * gives the Items in the player's backpack
	 * @return Map of the items in the backpack
	 * */
	public Map<String,Item> openBackpack(){
		return this.backpack;
	}

	/**
	 * method that takes an action as parameter and executes the specified action (action not implement for the moment so nothing in parametter
	 */
	public void action(){

	}

	/**
	 * gives the player's expertise level
	 * @return expertiseLevel
	 * */
	public int getExpertiseLevel() {
		return this.expertiseLevel;
	}

	/**
	 * increases the survivor's level by 1 if he reaches level 3 7 or 11 his number of action points increases by 1
	 *  */
	public void UpOneExpertiseLevel() {
		this.expertiseLevel += 1;
		if(this.expertiseLevel == 3 ||this.expertiseLevel ==7 || this.expertiseLevel ==11){
			this.setAction_points(this.getAction_points()+1);
		}
	}

	
	/**
	 * gives the item that the player has in hand
	 * @return the item in hand
	 * */
	public Item getItemInHand() {
		return this.itemInHand;
	}

	/**
	 * method for adding a weapon to a player
	 * @param itemInHand the item you want to add
	 */
	public void setItemInHand(Item itemInHand) {
		this.itemInHand = itemInHand;
	}


	/** gives the list of players located in the same cell
	 * @param  cell where the players are
	 * @return players in the same cell
	 * */
	public List<Player> getPlayersInArea(Cell cell){
		List<Player> players= new ArrayList<>();
		
		for(Player p: cell.getAllPlayers()) {
			players.add(p);
		}
		return players;
	}
	
	/** gives the list of zombies located in the same cell
	 * @param  cell where the players are
	 * @return zombies in the same cell
	 * */
	public List<Zombies> getZombiesInArea(Cell cell){
		List<Zombies> zombies= new ArrayList<>();
		
		for(Zombies z: cell.getAllZombies()) {
			zombies.add(z);
		}
		return zombies;
	}
	
	


	/**
	 * method for adding a role to a player
	 * @param role the role you want to add
	 */
	public void setRoles(RolesIntrerface role) {
		this.roles.add(role);
	}

	/**
	 * method that return a role in the list roles
	 * @param index the index of the role you want
	 * @return Roles the role returned
	 */
	public RolesIntrerface getRoles(int index){
		return this.roles.get(index);
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
