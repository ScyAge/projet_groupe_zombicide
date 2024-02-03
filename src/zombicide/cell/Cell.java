package zombicide.cell;
import java.util.*;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.util.*;


/**
 *Class that describe how a cell work 
 */
public abstract class Cell {
	private List<Player> players;
	private List<Zombies> zombies;
	private int x;
	private int y;
	
	/**
	 * Constructor of the class Cell 
	 * @param x coordinates of the cell
	 * @param y coordinates of the cell
	 * @param type the type of the cell 
	 */
	public Cell(int x , int y) {
		this.x = x;
		this.y = y;
		this.players = new ArrayList<>();
		this.zombies = new ArrayList<>();
		
	}
	
	
	/**
	 * Return the list of all the players in the cell
	 * @return List of players
	 */
	public List<Player> getAllPlayers() {
		return this.players;
	}
	

//	public Player getPlayers(Player p) throw ActorDoesNotExistExeption {
//		if(this.players.indexOf(p) ==-1) {
//			throws new ActorDoesNotExistExeption();
//		}
//		return players.get(players.indexOf(p));
//		
//	}
	
	/**
	 * method that adds a player to the list
	 * @param  p the player
	 */
	public void addPlayers(Player p) {
		this.players.add(p) ;
	}
	
	/**
	 * Return the list of all the zombies in the cell
	 * @return List of zombies
	 */
	public List<Zombies> getAllZombies() {
		return this.zombies;
	}
	
	/**
	 * method that adds a zombie to the list
	 * @param  p the player
	 */
	public void addZombies(Zombies z) {
		this.zombies.add(z);
	}
	
	/**
	 * remove the specified player from the list of player
	 * @param p the player
	 */
	public void RemovePlayer(Player p ) {
		this.players.remove(p);
	}
	
	/**
	 * remove the specified zombie from the list of zombie
	 * @param z the zombie
	 */
	public void RemoveZombie(Zombies z ) {
		this.zombies.remove(z);
	}
	
	

	/**
	 * @return the x coordinates of the cell
	 */
	public int getX() {
		return x;
	}
	/**
	 * @return the y coordinates of the cell
	 */
	public int getY() {
		return y;
	}
	
	
	/**
	 * method that say if a player can look in the room
	 * @return
	 */
	public boolean canLook() {
		return true;
	}
	 
	
	/**
	 * gives a recap of the number of actors in the cell
	 * @return a description of the cell 
	 */
	public  String description() {
		String cellName= this.getClass().getSimpleName();
		int nbPlayers= players.size();
		int nbZombies= zombies.size();
		return "("+ this.getX() +","+ this.getY() + ") " + cellName + " [ Players : " + nbPlayers+ " ; Zombies : " + nbZombies + "]";
	}
	
	@Override
	public abstract String toString();
		
	
	
	
}
