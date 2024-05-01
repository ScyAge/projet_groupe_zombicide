package zombicide;

import zombicide.actor.actionPlayer.ActionsPlayer;
import zombicide.actor.actionZombie.ActionZombie;
import zombicide.actor.actionZombie.AttackZombie;
import zombicide.actor.actionZombie.MoveZ;
import zombicide.actor.player.Player;
import zombicide.actor.zombie.Walker;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;
import zombicide.cell.Sewer;
import zombicide.item.Item;
import zombicide.util.listchooser.ListChooser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {
    private List<Player> allPlayers;
    private final Board board;
    private final List<ActionsPlayer>AllActionPlayer;
    private final List<Item> allItem;

    private final ListChooser<ActionsPlayer> PlayerChooser;

    public Game(Board b,List<Player> player, List<ActionsPlayer> AllActions, List<Item> AllItem,ListChooser<ActionsPlayer> PlayerChooser) {
        this.allPlayers = player;
        this.board =b;
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
        for(Player p :  this.allPlayers){
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
    	return res.toList().contains(false)||test.size() ==0;
    }
    
    public int totalXP() {
    	int totalExpertiseLevel = 0;
    	for(Player p: this.allPlayers) {
    		totalExpertiseLevel += p.getExpertiseLevel(); 
    	}
    	return totalExpertiseLevel;
    }

    /**
     * method that launch a game of zombicide
     */
    public void play(){
    	this.board.Display();
    	int compteur;
    	while(this.AreTheyAllAlive()&& this.areZombiesAllALive()&&this.totalXP()<30){
            //tour des joueurs
            this.roundPlayer();
            //Action des Zombies
            this.roundZombie();
            //update board
        	if(this.allPlayers.size() > 0) {
        		this.roundUpdateBoard();
        	}
            this.board.Display();
        }
        this.board.Display();
        if(this.AreTheyAllAlive()) {
        	System.out.println(this.totalXP());
        	System.out.println("player win");
        }
        else {
        	System.out.println("player lose");
        }
    }


	/**
     * method that play the round for all the player in the game
     */
    protected void roundPlayer() {
        for(Player p : this.allPlayers){
        	this.board.Display();
        	System.out.println("je suis le player "+p.getId());
            while(p.getAction_points() > 0){
                List<ActionsPlayer> actionPossible = p.getActionOfThePlayer();
                ActionsPlayer actionHeChoose = this.PlayerChooser.choose("Qu'elle action souhaite tu faire",actionPossible);
                actionHeChoose.action(p);
                this.board.updateListZombie();
            }
            this.board.updateListZombie();
        }
        for(Player p : this.allPlayers){
        	p.setAction_points(3);
        }
    }

    /**
     * method that play the round for all the zombie on the board
     */
    protected void roundZombie(){
        ActionZombie attack = new AttackZombie();
        ActionZombie move = new MoveZ(this.board);
        List<Zombies> zombies = this.board.updateListZombie();
        for(Zombies z : zombies){
            while(z.getAction_points() > 0){
                if(attack.IsActionPlayable(z)){
                    attack.action(z);
                    this.allPlayers = this.allPlayers.stream().filter(p -> !p.isDead()).toList();
                }
                else{
                    move.action(z);
                }
            }
        }
    }

    /**
     * method to achieve the end of the game turn,
     * set the noise levels to 0 and make the zombies appear (the actors were already suprimmed by themselves)
     */
    protected void roundUpdateBoard(){
        board.cleanNoise();
        this.multiplyZombies();

    }
    
    
    /**
     * Multiplies zombies across all sewer cells on the board based on the average expertise level of all players.
     */
    protected void multiplyZombies() {
    	int totalExpertiseLevel = this.totalXP();
    	int averageExpertiseLevel = totalExpertiseLevel/this.allPlayers.size();
    	
    	int nbZombiesToAdd = (int) Math.ceil(averageExpertiseLevel/3.0);
    	this.board.ProductionZombie(nbZombiesToAdd,totalExpertiseLevel);
    	
    }

}
