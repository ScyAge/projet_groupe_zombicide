package zombicide;

import zombicide.actor.actionPlayer.ActionsPlayer;
import zombicide.actor.actionZombie.ActionZombie;
import zombicide.actor.actionZombie.AttackZombie;
import zombicide.actor.actionZombie.MoveZ;
import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;
import zombicide.item.Item;
import zombicide.util.listchooser.ListChooser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {
    private final List<Player> allPLayers;
    private final Board board;
    private final List<ActionsPlayer>AllActionPlayer;
    private final List<Item> allItem;

    private final ListChooser<ActionsPlayer> PlayerChooser;

    public Game(Board b, List<Player> players, List<ActionsPlayer> AllActions, List<Item> AllItem,ListChooser<ActionsPlayer> PlayerChooser) {
        this.board = b;
        this.allPLayers = players;
        this.AllActionPlayer = AllActions;
        this.allItem = AllItem;
        this.PlayerChooser = PlayerChooser;
    }

    /**
     * method that test if all the players in game are alive
     * @return the result of the predicate
     */
    public boolean AreTheyAllAlive(){
        List<Boolean> test = new ArrayList<>();
        for(Player p :  this.allPLayers){
            test.add(p.isDead());
        }
        Stream<Boolean> res = test.stream().filter(c -> !c);
        return  res.toList().contains(false);
    }

    /**
     * method that tests if all the zombies in game are alive
     * @return true if they're all dead, false if not
     *  */
    public boolean areZombiesAllALive() {
    	List<Boolean> test= new ArrayList<>();
    	for(Zombies z: this.board.getAllZombies()) {
    		test.add(z.isDead());
    	}
    	Stream<Boolean> res= test.stream().filter(c->!c);
    	return res.toList().contains(false);
    }


    /**
     * method that launch a game of zombicide
     */
    public void play(){
        while(this.AreTheyAllAlive()){
            //tour des joueurs
            this.roundPlayer();
            //Action des Zombies
            this.roundZombie();
        }
    }


    /**
     * method that play the round for all the player in the game
     */
    protected void roundPlayer() {
        for(Player p : this.allPLayers){
            while(p.getAction_points() > 0){
                List<ActionsPlayer> actionPossible = p.getActionOfThePlayer();
                ActionsPlayer actionHeChoose = this.PlayerChooser.choose("Qu'elle action souhaite tu faire",actionPossible);
                actionHeChoose.action(p);
            }
        }
        //update de la list des zombie
        this.board.updateListZombie();
    }

    /**
     * method that play the round for all the zombie on the board
     */
    protected void roundZombie(){
        ActionZombie attack = new AttackZombie();
        ActionZombie move = new MoveZ(this.board);
        for(Zombies z : this.board.getAllZombies()){
            while(z.getAction_points() > 0){
                if(attack.IsActionPlayable(z)){
                    attack.action(z);
                }
                else{
                    move.action(z);
                }
            }
        }
    }

    protected void roundUpdateBoard(){
        
    }

}
