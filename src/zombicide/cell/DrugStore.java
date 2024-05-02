package zombicide.cell;

import java.util.*;

import zombicide.actor.player.Player;
import zombicide.item.equipment.HealingVial;

/**
 * class of DrugStore
 */
public class DrugStore extends Room {
	
	private final List<HealingVial> healingVials;
	
	/** Constructor of the class DrugStore
	 * @param x coordinates of the cell
	 * @param y coordinates of the cell
	 * 
	 */
	public DrugStore(int x, int y) {
		super(x, y);
		this.healingVials= new ArrayList<>();
	}
	
	/**
	 * returns the list of the healing Vials available in the drugstore
	 * @return List of healing vials
	 */
	public List<HealingVial> getHealingVials() {
		return this.healingVials;
	}
	
	/**
	 * adds a healing vial to the list 
	 * @param hv healing vial 
	 */
	public void addHealingVial(HealingVial hv) {
		this.healingVials.add(hv);
	}
	

	/**
	 * method that adds a player to the list
	 * @param  p the player
	 */
	public void addPlayers(Player p) {
		super.addPlayers(p);
		this.addHealingVial(new HealingVial());
	}

	/**
	 * @return display of the DrugStore
	 */
	public String toString() {
		return colorRed +"D"+ colorWhite;
	}
	
	
	
	
	
	

}
