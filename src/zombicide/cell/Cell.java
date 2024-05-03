package zombicide.cell;
import java.util.*;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.util.*;
import zombicide.util.door.*;
import zombicide.item.*;

/**
 *Class that describe how a cell work 
 */
public abstract class Cell {
	
	
	
	private final List<Player> players;
	private final List<Zombies> zombies;
	private  int x;
	private  int y;
	private int noise;
	
	/**
	 * List of Items put in the cell
	 */
	protected List<Item> items;
	
	/**
	 * Builder Constant
	 */
	protected Map<Direction, Door> doors;
	

		/**color white*/
	public static final String colorWhite = "\u001B[0m" ;
		/**color red*/
	public static final String colorRed = "\u001B[31m" ;
		/**color green*/
	public static final String colorGreen = "\u001B[32m" ;
		/**color yellow*/
	public static final String colorYellow = "\u001B[33m" ;
		/**color blue*/
	public static final String colorBlue = "\u001B[34m" ;
	
	
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
		this.items = new ArrayList<>();
		for (Direction direction : Direction.values()) {
            if(direction == Direction.East || direction == Direction.West){
				this.doors.put(direction, new East_west_door(true,true));
			}
			else{
				this.doors.put(direction, new North_South_door(true,true));
			}
        }
		this.noise = 0;
		
	}
	
	/**
	 * Sets the value of the X-coordinate.
	 * @param x the new value of the X-coordinate
	 */
	public void setX(int x) {
	    this.x = x;
	}

	/**
	 * Sets the value of the Y-coordinate.
	 * @param y the new value of the Y-coordinate
	 */
	public void setY(int y) {
	    this.y = y;
	}
	
	/**
	 * return the level of noise
	 * @return the level of noise
	 */
	public int getNoise() {
		return this.noise;
	}
	
	/**
	 * set the level of noise
	 * @param noise the level of noise
	 */
	
	public void setNoise(int noise) {
		this.noise = noise;
	}

	/**
	 * reset the noise of the cell to 0
	 */
	public void resetNoise(){
		this.noise = 0;
	}
	/**
	 * Return the list of all the players in the cell
	 * @return List of players
	 */
	public List<Player> getAllPlayers() {
		return this.players.stream().filter(p -> !p.isDead()).toList();
	}

	/**
	 * method that adds a player to the list
	 * @param  p the player
	 */
	public void addPlayers(Player p) {
		this.players.add(p) ;
	}
	
	/**
	 * Break the door in the cell
	 * @param d direction to break the door
	 */
	public void breakDoor(Direction d) {
		this.getDoor(d).Break();
	}
	
	/**
	 * Return the list of all the zombies in the cell
	 * @return List of zombies
	 */
	public List<Zombies> getAllZombies() {
		return this.zombies.stream().filter(p -> !p.isDead()).toList();
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
	 * @param player the player
	 */
	public void remove(Player player) {
		this.players.remove(player);
	}
	
	/**
	 * remove the specified zombie from the list of zombie
	 * @param z the zombie
	 */
	public void remove(Zombies z ) {
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
	 * method for adding Item in the cell
	 * @param i an Item
	 */
	public abstract void addItem(Item i);

	/**
	 * removes the given item from the list of items
	 * @param i Item
	 * @return the item remove
	 */
	public abstract Item removeItem(Item i);

	/**
	 * Return the list of all the items in the room
	 * @return List of items
	 */
	public List<Item> getAllItems() {
		return this.items;
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
	 * @return display string
	 */
	public abstract String toString();
		
	
	
	
}
