package zombicide.cell;


import java.util.*;
import zombicide.actor.player.*;
import zombicide.board.Board;
import zombicide.item.weapons.*;


/**
 * class of SpawnPlayers
 */
public class SpawnPlayers extends Street {
	
	private final Board board;
	
	/** Constructor of the class SpawnPlayers
	 * @param x coordinates of the cell
	 * @param y coordinates of the cell
	 */
	public SpawnPlayers(int x, int y, Board board) {
		super(x, y);
		this.board =board;
	}
	
	/** populates the spawn point with players 
	 * players are added to the list of players in the spawn point cell, and a
	 * gun is assigned to each player.
	 * @param players the list of players to populate on the spawn point
	 * */
	public void spawnPlayer(List<Player> players) {
		Weapon gun= new Gun();
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
