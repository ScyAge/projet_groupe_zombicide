package zombicide.actor.actionPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;
import zombicide.cell.Cell;
import zombicide.item.Item;
import zombicide.item.weapons.Weapon;
import zombicide.util.Direction;
import zombicide.util.listchooser.InteractiveListChooser;
import zombicide.util.listchooser.ListChooser;

public class Attack implements ActionsPlayer{
	
	/**
	 * param 
	 */
	private ListChooser<Zombies> chooser;
	
	/**
	 * Builder of Attack with ListChooser in param to test
	 * @param chooser the listchooser of the action
	 */
	public Attack(ListChooser<Zombies> chooser) {
		this.chooser = chooser;
	}
	
	
	/**
	 * Builder of Attack
	 */
	public Attack() {
		this.chooser = new InteractiveListChooser<>();
	}
	
	
	/**
	 * player attack zombies
	 * @param p player who attack a zombie
	 */
	public void action(Player p) {
		Cell c= p.getCurrentCell();
		Zombies z= chooser.choose("pick a zombie to attack : ", c.getAllZombies());
		attackZombie(p,z);
	}
	
	/*private List<Zombies> zombiesInTheCell(Cell c) {
		List<Zombies> zombiesId= new ArrayList<>();
 		if( c.getAllZombies()!=null) {
			for(Zombies z: c.getAllZombies()) {
				zombiesId.add(z.getId());
			}
		}
		return zombiesId;
	}
	*/
	
	/**
	 * attacks the zombie by the given player
	 * @param p player that's going to attack
	 * @param z the zombie that's being attacked
	 * */
	private void attackZombie(Player p, Zombies z) {
		Weapon w= (Weapon) p.getItemInHand();
		
		Random random= new Random();
		for(int i=0; i<w.getNbDice(); i++) {
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
