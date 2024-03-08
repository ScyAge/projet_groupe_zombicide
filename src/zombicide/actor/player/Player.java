package zombicide.actor.player;

import java.util.*;

import zombicide.actor.Actor;
import zombicide.actor.player.roles.RolesIntrerface;
import zombicide.actor.zombie.Zombies;
import zombicide.exeption.ItemDoesNotExistExeption;
import zombicide.item.*;
import zombicide.cell.*;

/**
 * Class Player 
 */
public class Player extends Actor{
	
	
	/** Param of Player */
	private Item[] backpack;
	private  Item itemInHand;
	private int expertiseLevel;
	private List<RolesIntrerface> roles;
	
	/**
	 * Builder of Player class
	 * @param lifePoints life of the player
	 * @param cell cell where the player is
	 * @param id of the player
	 */
	public Player(int lifePoints,Cell cell, int id) {
		super(lifePoints,3, cell, id);
		this.backpack = new Item[6];
		this.itemInHand = null ;
		this.expertiseLevel = 1;
		this.roles = new ArrayList<>();
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
	 * method for adding a weapon to a player in is hand
	 * @param itemInHand the item you want to add
	 */
	public void setItemInHand(Item itemInHand) {
		this.itemInHand = itemInHand;
	}

	/**
	 * method that return the backPack of the player
	 * @return backpack
	 */
	public Item[] getBackPack(){return this.backpack;}

	/**
	 * take a item from the backpack and place it in the hand of the player
	 * @param index_of_item the position of the wanted item
	 * @throws ItemDoesNotExistExeption if there is no Item at the specified position
	 */
	public void takeInHandFromBackPack(int index_of_item) throws ItemDoesNotExistExeption{
		this.setItemInHand(this.takeItemInTheBackPack(index_of_item));
	}

	/**
	 * method that adds an object to the bag at the first free location and returns
	 * a message confirming the operation if the bag is full returns that the bag is full
	 * @param item the item you want to add
	 * @return a message that confirm to you the operation
	 */
	public String putItemInBackPack(Item item){
		for(int i = 0;i <this.backpack.length;i++){
			if(this.backpack[i] == null) {
				this.backpack[i] = item;
				return "success";
			}
		}
		return "full";
		}

	/**
	 *method that returns the Item to the specified position
	 * @param index the position of the wanted item
	 * @return the item
	 * @throws ArrayIndexOutOfBoundsException if the index give in parameter is out of bound
	 * @throws ItemDoesNotExistExeption if there is no Item at the specified position
	 */
	public Item takeItemInTheBackPack(int index) throws ArrayIndexOutOfBoundsException,ItemDoesNotExistExeption{
		if(index < 0 || index > this.backpack.length){
			throw new ArrayIndexOutOfBoundsException();
		}
		Item i = this.backpack[index];
		if(i == null){
			throw new ItemDoesNotExistExeption();
		}
		this.backpack[index] = null;
		return i;
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
	 * destroy item
	 * @param i item to destroy
	 */
	public void destroyItem(Item i) {
		/*this.backpack.remove(i.getTitle());*/
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
