package zombicide.actor.player;

import java.util.*;

import zombicide.actor.Actor;
import zombicide.actor.actionPlayer.ActionsPlayer;
import zombicide.exeption.ItemDoesNotExistExeption;
import zombicide.item.*;
import zombicide.cell.*;

/**
 * Class Player 
 */
public class Player extends Actor{
	
	
	/** Param of Player */
	private final List<Item> backpack;
	private final int backPackSize;
	private  Item itemInHand;
	private int expertiseLevel;
	private final List<ActionsPlayer> AllAction;

	/**
	 * Test Builder of Player class
	 * @param lifePoints life of the player
	 * @param cell cell where the player is
	 * @param id of the player
	 * @param taille_sac the number max of item that can be hold in the backpack
	 */
	public Player(int lifePoints,Cell cell, int id,int taille_sac) {
		super(lifePoints,3, cell, id);
		this.backpack = new ArrayList<>(taille_sac);
		this.itemInHand = null ;
		this.expertiseLevel = 1;
		this.backPackSize = taille_sac;
		this.AllAction = new ArrayList<>();
	}

	/**
	 * Builder of Player class
	 * @param lifePoints life of the player
	 * @param cell cell where the player is
	 * @param id of the player
	 * @param taille_sac the number max of item that can be hold in the backpack
	 * @param actions the list of all the actions
	 */
	public Player(int lifePoints,Cell cell, int id,int taille_sac,List<ActionsPlayer> actions) {
		super(lifePoints,3, cell, id);
		this.backpack = new ArrayList<>(taille_sac);
		this.itemInHand = null ;
		this.expertiseLevel = 1;
		this.backPackSize = taille_sac;
		this.AllAction = new ArrayList<>();
		this.AllAction.addAll(actions);
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
	 * remove the Player to the cell
	 */
	public void Dead() {
		this.cell.remove(this);
	}
	
	/**
	 * method that return the backPack of the player
	 * @return backpack
	 */
	public List<Item> getBackPack(){return this.backpack;}

	/**
	 * Test if the backpack is full
	 * @return the results of the test
	 */
	public boolean IsBackPackFull(){return this.backpack.size()>= this.backPackSize;}

	/**
	 * take a item from the backpack and place it in the hand of the player
	 * @param index_of_item the position of the wanted item
	 * @throws ItemDoesNotExistExeption if there is no Item at the specified position
	 */
	public void takeInHandFromBackPack(int index_of_item) throws ItemDoesNotExistExeption{
		if(this.itemInHand != null){
			Item temp_main = this.itemInHand;
			this.setItemInHand(this.takeItemInTheBackPack(index_of_item));
			this.putItemInBackPack(temp_main);
		}
		else{
			this.setItemInHand(this.takeItemInTheBackPack(index_of_item));
		}
	}

	/**
	 * put the item in your hand in the backpack if the backpack is full do nothing and keep the item in your hand
	 */
	public void PutItemInHandInBackPack(){
			if(!this.IsBackPackFull()){
				this.putItemInBackPack(this.itemInHand);
				this.itemInHand = null;
			}
	}
	/**
	 * put the item in your hand in the cell where you are
	 */
	public void PutItemInHandInCell(){
		this.cell.addItem(this.itemInHand);
		this.itemInHand = null;
	}

	/**
	 * method that adds an object to the bag at the first free location and returns
	 * a message confirming the operation if the bag is full returns that the bag is full
	 * @param item the item you want to add
	 */
	public void putItemInBackPack(Item item) {
		if (!this.IsBackPackFull()){
			this.backpack.add(item);
		}
	}

	/**
	 *method that returns the Item to the specified position
	 * @param index the position of the wanted item
	 * @return the item
	 * @throws ArrayIndexOutOfBoundsException if the index give in parameter is out of bound
	 */
	public Item takeItemInTheBackPack(int index)throws ArrayIndexOutOfBoundsException{
		if(index < 0 || index > this.backpack.size()){
			throw new ArrayIndexOutOfBoundsException();
		}
		Item i = this.backpack.get(index);
		this.backpack.remove(index);
		return i;
	}

	/**
	 * method for adding a role to a player
	 * @param role the role you want to add
	 */
	public void setAction(ActionsPlayer role) {
		this.AllAction.add(role);
	}

	/**
	 * player take damage if is hp are below 0 so if is dead all is item in the backpack and in is hand drop in the cell
	 * @param damage taken by the actor
	 */
	public void takeDamage(int damage) {
		super.takeDamage(damage);
		if(this.lifePoints <=0){
			for(Item i :this.backpack){
				this.cell.addItem(i);
				if(this.itemInHand != null){
					this.PutItemInHandInCell();
				}
			}
			this.Dead();
		}
		
	}

	@Override
	public String toString() {
		String status = (this.getLifePoints() > 0) ? "Alive" : "Dead";
	    return String.format("Player %d: HP : %d, Status : %s", this.id, this.getLifePoints(), status);
	}

	/**
	 * method that return the list of all the actions
	 * @return all actions 
	 */
	public List<ActionsPlayer> getAllAction(){return this.AllAction;}

	/**
	 * Return the list of all the actions a player can do at T moment
	 * @return  actions of the player
	 */
	public List<ActionsPlayer> getActionOfThePlayer(){
		List<ActionsPlayer> res = new ArrayList<>();
		for(ActionsPlayer a:this.AllAction){
			if(a.IsActionPlayable(this)){
				res.add(a);
			}
		}
		return res;
	}
}
