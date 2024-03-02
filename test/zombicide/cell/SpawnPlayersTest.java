package zombicide.cell;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.player.Player;

class SpawnPlayersTest {

	private SpawnPlayers SpawnPlayers;
	private Player player;
	
    @BeforeEach
    void setUp() {
    	SpawnPlayers = new SpawnPlayers(1, 1);
        player = new Player(8,SpawnPlayers,1);

    }
    
    @Test
    void testSpawnPlayer() {
    	List<Player> players =new ArrayList<Player>();
    	players.add(player);
    	SpawnPlayers.spawnPlayer(players);
    	assertEquals(players,SpawnPlayers.getAllPlayers());
    	
    }
    
    

}
