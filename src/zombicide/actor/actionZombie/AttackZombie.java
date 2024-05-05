package zombicide.actor.actionZombie;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombie;
import zombicide.cell.Cell;
import zombicide.util.listchooser.ListChooser;
import zombicide.util.listchooser.RandomListChooser;

import java.util.List;


/**
 * class AttackZombie
 */
public class AttackZombie implements ActionZombie{

	
	/**
	 * Builder of Attack Zombie
	 */
	public AttackZombie() {
		
	}
	
	
    @Override
    public void action(Zombie z) {
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
	        z.setAction_points(z.getAction_points()-1);
        }


    }
    
    /**
     * the zombie can attack
     * @param z is the the zombie who use the action
     */
    public boolean IsActionPlayable(Zombie z){
        return !z.getCurrentCell().getAllPlayers().isEmpty() && z.getCurrentCell().canLook();
    }
}
