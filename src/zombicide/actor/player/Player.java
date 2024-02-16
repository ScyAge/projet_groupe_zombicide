package zombicide.actor.player;

import java.util.*;

import zombicide.actor.Actor;
import zombicide.exeption.ItemDoesNotExistExeption;
import zombicide.item.*;
import zombicide.cell.*;


public class Player extends Actor{
	
	private Map<String,Item>  backpack;
	private  Item itemInHand;
	private int expertiseLevel;
	private Cell cell;
	
	public Player(int lifePoints, Cell cell) {
		super(lifePoints,3, cell);
		this.backpack = new HashMap<>();
		this.itemInHand = null ;
		this.expertiseLevel = 1;
		
		
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
	
}
