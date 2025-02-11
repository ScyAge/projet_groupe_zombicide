package zombicide.actor.actionPlayer;

import java.util.List;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombie;
import zombicide.board.Board;
import zombicide.cell.Cell;
import zombicide.util.Direction;
import zombicide.util.Door;

/**
 * Class LookAround implementation  of ActionsPlayer
 */
public class LookAround implements ActionsPlayer {
	
	/**
	 * Param of LookAround
	 */
	private final Board b ;
	
	/** colour pink */
	public static final String colorPink= "\u001B[38;5;213m";
	
	/**color white*/
	public static final String colorWhite = "\u001B[0m" ;
	
	/**
	 * Builder of LookAround
	 * @param b The board
	 */
	public LookAround(Board b) {
		this.b = b;
	}
	

	@Override
	public void action(Player p) {
		String res= display(p);
		System.out.println(res);
	}
	/**
	 * Displays everything that's going on around the player:
	 * doors open or not, players and zombies present, etc.
	 * @param p Player who use the action
	 * @return the display
	 */
	public String display(Player p) {
		StringBuilder res = new StringBuilder();
		Cell c= p.getCurrentCell();
		res.append(String.format("- Description of the area : \n %s\n",c.description()));
		
		List<Player> players= c.getAllPlayers();
		List<Zombie> zombies= c.getAllZombies();
		
		if(!players.isEmpty()) {
			res.append("Players in this Area : \n");
			for(Player player: players) {
				res.append(String.format("Player %d\n",player.getId()));
			}
		}
		if (!zombies.isEmpty()) {
			res.append("Zombie in this Area : \n");
			for(Zombie z: zombies) {
				res.append(String.format("Zombie %d\n",z.getId()));
			}
		}
		
		res.append("The opened doors : \n");
		for(Direction d: Direction.values()) {
			Door door= c.getDoor(d);
			boolean test;
			if(b.getCellDirection(d,p) == null){
				test = false;
			}
			else{
				test = b.getCellDirection(d,p).getDoor(Direction.oppose(d)).isBreak();
			}
			if(door.isBreak() && test) {
				String direction = colorPink+d + colorWhite;
				res.append(String.format("the door at the direction %s is opened\n",direction));
			}
		}
		return res.toString();
	}

	@Override
	public boolean IsActionPlayable(Player p) {
		return p.getCurrentCell().canLook();
	}


	@Override
	public String toString() {
		return "LookAround action";
	}
}
