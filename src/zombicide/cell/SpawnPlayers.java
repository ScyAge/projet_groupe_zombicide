package zombicide.cell;


import java.util.*;
import zombicide.actor.player.*;



public class SpawnPlayers extends Street {

	public SpawnPlayers(int x, int y) {
		super(x, y);
	}
	
	public void spawnPlayer(List<Player> players) {
		for(Player p : players) {
			this.addPlayers(p);
		}
	}

	@Override
	public String toString() {
		return colorBlue +"-"+ colorWhite;
	}
}
