package zombicide.cell;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.player.Player;
import zombicide.board.Board;

class SpawnPlayersTest {

	private SpawnPlayers SpawnPlayers;
	private Player player;
	
    @BeforeEach
    void setUp() {
    	Board board = new Board(5,5);
    	SpawnPlayers = new SpawnPlayers(1, 1);
        player = new Player(8,SpawnPlayers,1,6);

    }
    
    @Test
    void testSpawnPlayer() {
    	List<Player> players =new ArrayList<Player>();
    	players.add(player);
    	SpawnPlayers.spawnPlayer(players);
    	assertEquals(players,SpawnPlayers.getAllPlayers());
    	
    }

	@Test
	public void testToString(){
		assertEquals(this.SpawnPlayers.toString(),String.format("%s-%s",Cell.colorBlue,Cell.colorWhite));
	}
    
    

}
