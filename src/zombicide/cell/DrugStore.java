package zombicide.cell;

import java.util.*;

import zombicide.actor.player.Player;
import zombicide.cell.*;
import zombicide.item.HealingVial;
import zombicide.util.Direction;

/**
 * class of DrugStore
 */
public class DrugStore extends Room {
	private List<HealingVial> healingVials;
	
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
	
	@Override
	/**
	 * method that adds a player to the list
	 * @param  p the player
	 */
	public void addPlayers(Player p) {
		super.addPlayers(p);
		this.addHealingVial(null);
	}

	/**
	 * @param ligne ligne to display
	 * @return display of the DrugStore
	 */
	public String toString(int ligne) {
		if(ligne ==0) {
			return this.getDoor(Direction.North).toString();
		}
		else if(ligne ==1){
			if(this.getAllZombies().size()==0) {
				return this.getDoor(Direction.East).toString()+"\u001B[31m" +"D"+ "\u001B[0m"+"  "+this.getDoor(Direction.West).toString();
			}
			else {
				return this.getDoor(Direction.East).toString()+"\u001B[31m" +"D"+ "\u001B[0m"+"z"+this.getAllZombies().size()+this.getDoor(Direction.West).toString();
			}
		}
		else if(ligne ==2){
			if(this.getAllPlayers().size()==0) {
				return this.getDoor(Direction.East).toString()+"   "+this.getDoor(Direction.West).toString();
			}
			else {
				return this.getDoor(Direction.East).toString()+"s"+this.getAllPlayers().size()+" "+this.getDoor(Direction.West).toString();
			}
		}
		else if(ligne ==3){
			return this.getDoor(Direction.South).toString();
		}
		else {
			return "";
		}
	}
	
	
	
	
	
	

}
