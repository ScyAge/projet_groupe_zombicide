package zombicide.actor.actionZombie;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.cell.Cell;
import zombicide.util.listchooser.ListChooser;
import zombicide.util.listchooser.RandomListChooser;

import java.util.ArrayList;
import java.util.List;

public class AttackZombie implements ActionZombie{

	
	/**
	 * Builder of Attack Zombies
	 */
	public AttackZombie() {
		
	}
	
	
    @Override
    public void action(Zombies z) {
        Cell curent = z.getCurrentCell();
        if(this.IsActionPlayable(z)){
	        List<Player> AllPlayer = curent.getAllPlayers();
	        if(AllPlayer.size() == 1){
	            AllPlayer.get(0).takeDamage(z.getDamagePoints());
	        }
	        else{
	            ListChooser<Player> choosePlayer = new RandomListChooser<>();
	            Player pToAttack = choosePlayer.choose("The zombie choose a random player",AllPlayer);
	            pToAttack.takeDamage(z.getDamagePoints());
	        }
        }


    }
    
    /**
     * the zombie can attack
     * @param z is the the zombie who use the action
     */
    public boolean IsActionPlayable(Zombies z){
        return !z.getCurrentCell().getAllPlayers().isEmpty();
    }
}
