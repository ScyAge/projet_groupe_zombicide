package zombicide.item.weapons;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;
import zombicide.cell.Cell;
import zombicide.item.*;
import zombicide.util.Direction;
import zombicide.util.listchooser.InteractiveListChooser;
import zombicide.util.listchooser.ListChooser;

/**
 * class weapons
 */
public class Weapon extends Item {
	

	/**
	 * Param of weapons
	 */
	private int threshold;
	private int rangeMax;
	private int rangeMin;
	private int damage;
	private boolean noisy;
	private int nbDice;
	
	

	
	/**
	 * Builder of the weapons
	 * @param title of the item
	 * @param range of the weapons
	 * @param damage of the weapons
	 * @param threshold of the weapons
	 * @param breakDoor  can break door or not
	 * @param noisy if the weapon is noisy
	 * @param board of the weapons
	 * @param nbDice of the weapon
	 */
	public Weapon(String title,int rangeMax,int rangeMin,int damage, int threshold, boolean breakDoor, boolean noisy, int nbDice) {
		super(title, breakDoor);
		this.damage =damage;
		this.rangeMax =rangeMax;
		this.rangeMin =rangeMin;
		this.threshold = threshold;
		this.noisy = noisy;
		this.nbDice= nbDice;
	}
	
	/**
	 * Item can attack or not
	 * @return true if it can attack
	 */
	public boolean cantAttack(){
		return true;
	}
	
	/**returns the threshold of the Weapon 
	 * @return threshold of the given weapon
	 * */
	public int getThreshold() {
		return this.threshold;
	}

	
	/**gives the range of the given weapon
	 * @return range of the weapon 
	 * */
	public int getRange() {
		return this.rangeMax;
	}
	/**gives the min range of the given weapon
	 * @return min range of the weapon 
	 * */
	public int getMinRange() {
		return this.rangeMin;
	}

	
	/**
	 * gives the damage caused by the given weapon 
	 * @return damage 
	 * */
	public int getDamage() {
		return this.damage;
	}

	/**
	 * gives the number of dices 
	 * @return nbDice number of dices that a weapon allows to do */
	public int getNbDice() {
		return this.nbDice;
	}
	
	
	/**
	 * the weapon is used 
	 * */
	public void Used() {
		this.Use=false;
	}
	
	

	/**
	 * return true if the weapon is noisy else false (for attack)
	 * @return true if the weapon is noisy else false (for attack)
	 */
	public boolean isNoisy(){
		return this.noisy;
	}
	
	
	
	
}
