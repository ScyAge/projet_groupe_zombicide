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
	/**
	 * the display around the player
	 * @param p Player who use the action
	 * @return the display
	 */
	public String display(Player p) {
		StringBuilder res = new StringBuilder();
		Cell c= p.getCurrentCell();
		res.append(String.format("Description of the area : %s\n",c.description()));
		
		List<Player> players= c.getAllPlayers();
		List<Zombies> zombies= c.getAllZombies();
		
		if(!players.isEmpty()) {
			res.append("Players in this Area : \n");
			for(Player player: players) {
				res.append(String.format("Player of id %d\n",player.getId()));
			}
		}
		if (!zombies.isEmpty()) {
			res.append("Zombies in this Area : \n");
			for(Zombies z: zombies) {
				res.append(String.format("Zombie of id %d\n",z.getId()));
			}
		}
		
		res.append("The opened doors : \n");
		for(Direction d: Direction.values()) {
			Door door= c.getDoor(d);
			if(door.isBreak()) {
				res.append(String.format("the door at the direction %s is opened\n",d));
			}
		}
		return res.toString();
	}

	@Override
	public boolean IsActionPlayable(Player p) {
		return p.getCurrentCell().canLook();
	}
}
