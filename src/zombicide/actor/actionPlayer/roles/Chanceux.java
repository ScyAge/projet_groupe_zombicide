package zombicide.actor.actionPlayer.roles;

import java.util.Random;

import zombicide.actor.actionPlayer.ActionsPlayer;
import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.cell.Cell;
import zombicide.item.weapons.Weapon;
import zombicide.util.listchooser.InteractiveListChooser;
import zombicide.util.listchooser.ListChooser;

/**
 * class for Chanceux
 */
public class Chanceux implements ActionsPlayer {
	
	/**
	 * param 
	 */
	private ListChooser<Zombies> chooser;
	
	/**
	 * Builder of Chanceux with ListChooser in param to test
	 * @param chooser the listchooser of the action
	 */
	public Chanceux(ListChooser<Zombies> chooser) {
		this.chooser = chooser;
	}
	
	
	/**
	 * Builder of Chanceux
	 */
	public Chanceux() {
		this.chooser = new InteractiveListChooser<>();
	}
	
    /**
     * method that allows a survivor to perform the special action related to its roles
     * @param p the survivor who use the action
     */
    @Override
    public void action(Player p) {
    	Cell c= p.getCurrentCell();
		Zombies z= chooser.choose("pick a zombie to attack : ", c.getAllZombies());
		attackZombie(p,z);
    }
    
 
    /**
	 * attacks the zombie by the given player and adds a dice 
	 * @param p player that's going to attack
	 * @param z the zombie that's being attacked
	 * */
	private void attackZombie(Player p, Zombies z) {
		Weapon w= (Weapon) p.getItemInHand();
		
		Random random= new Random();
		for(int i=0; i<w.getNbDice()+1; i++) {
			int dice= random.nextInt(6)+1;
			
			if(dice>=w.getThreshold()) {
				z.takeDamage(w.getDamage());
				
				if(z.isDead()) {
					p.UpOneExpertiseLevel();
				}
			}else {
				System.out.println("attack failed");
			}
		}
	}
}
