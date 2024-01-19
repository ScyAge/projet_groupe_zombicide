package Cell;

import Cell.*;
import Item.HealingVial;
import java.util.*;
import Player.Player;

public class DrugStore extends Room {
	private List<HealingVial> healingVials;
	
	/** Constructor of the class DrugStore
	 * @param x coordinates of the cell
	 * @param y coordinates of the cell
	 * @param type the type of the cell */
	public DrugStore(int x, int y, String type) {
		super(x, y, type);
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
	
	@Override
	/** adds a healing vial to the drugstore whenever a player comes in
	 *@param p the player 
	 */
	public void addPlayers(Player p) {
		super.addPlayers(p);
		this.addHealingVial(null);
	}
	
	
	
	

}
