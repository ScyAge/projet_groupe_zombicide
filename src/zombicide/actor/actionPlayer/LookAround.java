package zombicide.actor.actionPlayer;

import java.util.List;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.cell.Cell;
import zombicide.util.Direction;
import zombicide.util.Door;

/**
 * Class LookAround implementation  of ActionsPlayer
 */
public class LookAround implements ActionsPlayer {
	
	/**
	 * Builder of LookAround
	 */
	public LookAround() {
	}
	

	@Override
	public void action(Player p) {
		String res= display(p);
		System.out.println(res);
	}
	
	public String display(Player p) {
		Cell c= p.getCurrentCell();
		System.out.println("Description of the area : "+ c.description());
		
		List<Player> players= c.getAllPlayers();
		List<Zombies> zombies= c.getAllZombies();
		String res="";
		
		if(!players.isEmpty()) {
			res+="Players in this Area : ";
			for(Player player: players) {
				res+=player.getId();
			}
		}
		if (!zombies.isEmpty()) {
			res+="Zombies in this Area : ";
			for(Zombies z: zombies) {
				res+=z.getId();
			}
		}
		
		res+="The opened doors : ";
		for(Direction d: Direction.values()) {
			Door door= c.getDoor(d);
			if(door.isBreak()) {
				res+="the door at the direction "+ d + "is opened";
			}
		}
		return res;
	}

	@Override
	public boolean IsActionPlayable(Player p) {
		return true;
	}
}
