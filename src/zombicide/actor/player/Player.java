package zombicide.actor.player;

import java.util.*;

import zombicide.actor.Actor;
import zombicide.actor.player.roles.RolesIntrerface;
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
	 * Give life Point to the player
	 * @param heal numbers of lifePoints add 
	 */
	public void heal(int heal) {
		this.lifePoints += heal;
	}
	
	/**
	 * gives the Items in the player's backpack
	 * @return Map of the items in the backpack
	 * */
	public Map<String,Item> openBackpack(){
		return this.backpack;
	}
	
	/**
	 * */
	public void takeInHand(String name)throws ItemDoesNotExistExeption {
		if(!this.backpack.containsKey(name)) {
			throw new ItemDoesNotExistExeption("Item not fund in bagpack");
		}
		this.itemInHand = this.backpack.get(name);
		this.backpack.remove(this.backpack.get(name));
	}
	
	/**
	 * gives the player's expertise level
	 * @return expertiseLevel
	 * */
	public int getExpertiseLevel() {
		return this.expertiseLevel;
	}

	/**
	 * sets the expertise level for the player
	 * @param expertiselevel's value 
	 *  */
	public void setExpertiseLevel(int expertiseLevel) {
		this.expertiseLevel = expertiseLevel;
	}

	
	/**
	 * gives the item that the player has in hand
	 * @return the item in hand
	 * */
	public Item getItemInHand() {
		return this.itemInHand;
	}

	
	/** gives the list of players located in the same cell
	 * @param the cell where the players are
	 * @return players in the same cell
	 * */
	public List<Player> getPlayersInArea(Cell cell){
		List<Player> players= new ArrayList<>();
		
		if(this.getCurrentCell()==cell) {
			players.add(this);
		}
		return players;
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
