package zombicide;

import zombicide.actor.actionPlayer.ActionsPlayer;
import zombicide.actor.player.Player;
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
     * method that test if all the player in game are alive
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
     * method that launch a game of zombicide
     */
    public void play(){
        while(this.AreTheyAllAlive()){
            //Phase 1 tour des joueur
            for(Player p : this.allPLayers){
                while(p.getAction_points() > 0){
                    List<ActionsPlayer> actionPossible = p.getActionOfThePlayer();
                    ActionsPlayer actionHeChoose = this.PlayerChooser.choose("Qu'elle action souhaite tu faire",actionPossible);
                    actionHeChoose.action(p);
                }
            }
            //Action des Zombie

        }
    }


}
