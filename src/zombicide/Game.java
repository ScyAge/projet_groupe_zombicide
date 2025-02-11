package zombicide;

import zombicide.actor.actionPlayer.ActionsPlayer;
import zombicide.actor.actionZombie.ActionZombie;
import zombicide.actor.actionZombie.AttackZombie;
import zombicide.actor.actionZombie.MoveZ;
import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombie;
import zombicide.board.Board;
import zombicide.item.Item;
import zombicide.util.listchooser.ListChooser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Game {
    private List<Player> allPlayers;
    private final Board board;

    /** colour pink */
	public static final String colorPink= "\u001B[38;5;213m";
	
	/**color white*/
	public static final String colorWhite = "\u001B[0m" ;
	
	/**color green*/
	public static final String colorGreen = "\u001B[32m" ;
	
    private final ListChooser<ActionsPlayer> PlayerChooser;

    public Game(Board b,List<Player> player, List<ActionsPlayer> AllActions, List<Item> AllItem,ListChooser<ActionsPlayer> PlayerChooser) {
        this.allPlayers = player;
        this.board =b;
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
        return  test.contains(false);
    }

    /**
     * method that tests if all the zombies in game are alive
     * @return true if they're all dead, false if not
     *  */
    public boolean areZombiesAllALive() {
    	List<Boolean> test= new ArrayList<>();
    	for(Zombie z: this.board.getAllZombies()) {
    		test.add(z.isDead());
    	}
    	return test.contains(false);
    }
    
    
    /**
     * method that gives the total of expertiseLevel of all players 
     * @return totalExpertiseLevel*/
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
    	
    	boolean firstRound = true;
    	
    	while((this.AreTheyAllAlive()&& this.areZombiesAllALive()&&this.totalXP()<30)|| firstRound){
            this.board.Display();
            //Status des joueurs
            System.out.println(colorGreen+ "** Status of players "+ colorWhite);
            for(Player p: this.allPlayers ) {
            	p.playerStatus();
            }
    
            System.out.println("\n");
          
            //tour des joueurs
    		System.out.println(colorGreen+"** Survivor tour"+ colorWhite);
            this.roundPlayer();
            System.out.println("\n");
            //Action des Zombie
            System.out.println(colorGreen+"** Zombie tour"+ colorWhite);
            if(this.areZombiesAllALive()) {
            	this.roundZombie();
            }
            System.out.println("\n");
    		//update board
            System.out.println(colorGreen+"** Update Board"+ colorWhite);
        	if(this.AreTheyAllAlive()&& (this.areZombiesAllALive()|| firstRound)) {
        		this.roundUpdateBoard();
        	}
        	firstRound = false;
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
    private void roundPlayer() {
        for(Player p : this.allPlayers){
        	System.out.println("I am player "+p.getId());
            while(p.getAction_points() > 0){
                List<ActionsPlayer> actionPossible = p.getActionOfThePlayer();
                ActionsPlayer actionHeChoose = this.PlayerChooser.choose("\n*** Which action do you want to do?",actionPossible);
                if(actionHeChoose != null)
                	System.out.println("* "+ colorPink+ actionHeChoose+ colorWhite);
                	actionHeChoose.action(p);
                this.board.updateListZombie();
            }
            this.board.updateListZombie();
            System.out.println("\n");
        }
        for(Player p : this.allPlayers){
        	p.setAction_points(p.getMaxActionPoints());
    		if (p.getExpertiseLevel() ==3){
    			p.addNMaxActionPoints(1);
    		}
    		else if(p.getExpertiseLevel() == 7) {
    			p.addNMaxActionPoints(1);
    		}
    		else if(p.getExpertiseLevel() == 11) {
    			p.addNMaxActionPoints(1);
    		}
        }
    }
    
    private void UpdatePlayer(){
		List<Player> player = new ArrayList<>();
		for(Player p : this.allPlayers ) {
			if(!p.isDead()) {
				player.add(p);
			}
		}
		this.allPlayers = player;
    }
    
    /**
     * method that play the round for all the zombie on the board
     */
    private void roundZombie(){
        ActionZombie attack = new AttackZombie();
        ActionZombie move = new MoveZ(this.board);
        List<Zombie> zombies = this.board.updateListZombie();
        for(Zombie z : zombies){
            while(z.getAction_points() > 0){
                if(attack.IsActionPlayable(z)){
                    attack.action(z);
                    this.UpdatePlayer();
                }
                else{
                    move.action(z);
                }
            }
        }
        for(Zombie z : zombies){
        	z.setAction_points(z.getMaxActionPoints());
        }
    }

    /**
     * method to achieve the end of the game turn,
     * set the noise levels to 0 and make the zombies appear (the actors were already deleted by themselves)
     */
    protected void roundUpdateBoard(){
        board.cleanNoise();
        this.multiplyZombies();

    }
    
    
    /**
     * Multiplies zombies across all sewer cells on the board based on the average expertise level of all players.
     */
    private void multiplyZombies() {
    	int totalExpertiseLevel = this.totalXP();
    	int averageExpertiseLevel = totalExpertiseLevel/this.allPlayers.size();
    	
    	int nbZombiesToAdd = (int) Math.ceil(averageExpertiseLevel/3.0);   
    	this.board.ProductionZombie(nbZombiesToAdd);
    	
    }

}
