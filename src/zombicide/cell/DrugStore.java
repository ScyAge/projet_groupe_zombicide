package zombicide.cell;

import java.util.*;

import zombicide.actor.player.Player;
import zombicide.item.equipment.HealingVial;

/**
 * class of DrugStore
 */
public class DrugStore extends Room {
	

	
	
	/** Constructor of the class DrugStore
	 * @param x coordinates of the cell
	 * @param y coordinates of the cell
	 * 
	 */
	public DrugStore(int x, int y) {
		super(x, y);
	}
	
	

	/**
	 * method that adds a player to the list
	 * @param  p the player
	 */
	public void addPlayers(Player p) {
		super.addPlayers(p);
		this.addItem(new HealingVial());
	}

	/**
	 * @return display of the DrugStore
	 */
	public String toString() {
		return colorRed +"D"+ colorWhite;
	}
	
	
	
	
	
	

}
