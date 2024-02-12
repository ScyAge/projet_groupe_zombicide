package zombicide.cell;
import java.util.*;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.util.*;
import zombicide.util.door.*;


/**
 *Class that describe how a cell work 
 */
public abstract class Cell {
	protected Map<Direction, Door> doors;
	private List<Player> players;
	private List<Zombies> zombies;
	private int x;
	private int y;
	
	/**
	 * Constructor of the class Cell 
	 * @param x coordinates of the cell
	 * @param y coordinates of the cell
	 */
	public Cell(int x , int y) {
		this.x = x;
		this.y = y;
		this.players = new ArrayList<>();
		this.zombies = new ArrayList<>();
		this.doors = new HashMap<>();
		for (Direction direction : Direction.values()) {
            if(direction == Direction.East || direction == Direction.West){
				this.doors.put(direction, new East_west_door(true,false));
			}
			else{
				this.doors.put(direction, new North_South_door(true,false));
			}
        }
		
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
	 * @param  z the zombie
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
	 * return the x coordinates of the cell
	 * @return the x coordinates of the cell
	 */
	public int getX() {
		return x;
	}
	/**
	 * return the y coordinates of the cell
	 * @return the y coordinates of the cell
	 */
	public int getY() {
		return y;
	}
	
	
	/**
	 * method that say if a player can look in the room
	 * @return true if a player can look in the room
	 */
	public boolean canLook() {
		return true;
	}
	 
	/**
	 * return the door
	 * @param direction direction of the door
	 * @return the door
	 */
	public Door getDoor(Direction direction) {
		return this.doors.get(direction);
	}
	
	/**
	 * return the door
	 * @return the door
	 */
	public Map<Direction, Door> getDoors() {
	    return this.doors;
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
	/**
	 * display a line of the cell 
	 * @param ligne the line of the cell
	 * @return display string
	 */
	public abstract String toString();
		
	
	
	
}
