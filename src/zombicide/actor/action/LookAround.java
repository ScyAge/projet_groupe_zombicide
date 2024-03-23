package zombicide.actor.action;

import java.util.List;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;
import zombicide.cell.Cell;
import zombicide.util.Direction;
import zombicide.util.Door;

public class LookAround implements Actions {
	
	public LookAround() {
	}
	
	@Override
	public void action(Player p, Board b) {
		Cell c= p.getCurrentCell();
		System.out.println("Description of the area : "+ c.description());
		
		List<Player> players= c.getAllPlayers();
		List<Zombies> zombies= c.getAllZombies();
		
		if(!players.isEmpty()) {
			System.out.println("Players in this Area : ");
			for(Player player: players) {
				System.out.println(player.getId());
			}
		}
		if (!zombies.isEmpty()) {
			System.out.println("Zombies in this Area : ");
			for(Zombies z: zombies) {
				System.out.println(z.getId());
			}
		}
		
		System.out.println("The opened doors : ");
		for(Direction d: Direction.values()) {
			Door door= c.getDoor(d);
			if(door.isBreak()) {
				System.out.println("the door at the direction "+ d + "is opened");
			}
		}
	}

	@Override
	public void action(Zombies z, Board b) {
	}
}
