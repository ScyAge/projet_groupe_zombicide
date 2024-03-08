package zombicide.cell;


import java.util.*;
import zombicide.actor.player.*;
import zombicide.item.weapons.*;


/**
 * class of SpawnPlayers
 */
public class SpawnPlayers extends Street {
	
	/** Constructor of the class SpawnPlayers
	 * @param x coordinates of the cell
	 * @param y coordinates of the cell
	 */
	public SpawnPlayers(int x, int y) {
		super(x, y);
	}
	
	/** populates the spawn point with players 
	 * players are added to the list of players in the spawn point cell, and a
	 * gun is assigned to each player.
	 * @param players the list of players to populate on the spawn point
	 * */
	public void spawnPlayer(List<Player> players) {
		Weapon gun= new Weapon("Gun",1, 1,4, false);
		for(Player p : players) {
			this.addPlayers(p);
			p.setItemInHand(gun);
		}
	}

	@Override
	public String toString() {
		return colorBlue +"-"+ colorWhite;
	}
}
