package zombicide.item.weapons;

import java.util.ArrayList;
import java.util.List;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.cell.Cell;
import zombicide.item.*;
import zombicide.util.Direction;

/**
 * class weapons
 */
public class Weapon extends Item {
	

	/**
	 * Param of weapons
	 */
	private int threshold;
	private int range;
	private int damage;
	private boolean breakDoor;
	private boolean noisy;
	
	/**
	 * Builder of the weapons
	 * @param title of the item
	 * @param range of the weapons
	 * @param damage of the weapons
	 * @param threshold of the weapons
	 * @param breakDoor  can break door or not
	 */
	public Weapon(String title,int range,int damage, int threshold, boolean breakDoor, boolean noisy) {
		super(title);
		this.damage =damage;
		this.range =range;
		this.threshold = threshold;
		this.breakDoor= breakDoor;
		this.noisy = noisy;
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
		return this.range;
	}

	
	/**
	 * gives the damage caused by the given weapon 
	 * @return damage 
	 * */
	public int getDamage() {
		return this.damage;
	}

	/**
	 * says whether the weapon breaks a door or not
	 * @return boolean
	 *   */
	public boolean getBreakDoor() {
		return this.breakDoor;
	}
	
	/**
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
	
	
	private List<Zombies> WhoCanAttack(Player player){
		List<Zombies> z = new ArrayList<>();
		Cell c;
		int i;
		for(Direction D : Direction.values()) {
			c = player.getCurrentCell();
			i=0;
			while(c.getDoor(D).isBreak() && i < this.range) {
				z.addAll(c.getAllZombies());
			}
		}
		return z;
	}
	
	/**
	 * realizes the equipment effect 
	 * @param player who uses the equipment 
	 * */
	public void ItemEffect(Player player) {
		
	}
	
}
