package zombicide.item.weapons;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;
import zombicide.cell.Cell;
import zombicide.item.*;
import zombicide.util.Direction;
import zombicide.util.listchooser.InteractiveListChooser;
import zombicide.util.listchooser.ListChooser;

/**
 * class weapons
 */
public class Weapon extends Item {
	

	/**
	 * Param of weapons
	 */
	private int threshold;
	private int range;
	private int damage;
	private boolean noisy;
	private Board board;
	private int nbDice;
	private ListChooser<Zombies> chooser;
	
	
	
	/**
	 * Builder of the weapons
	 * @param title of the item
	 * @param range of the weapons
	 * @param damage of the weapons
	 * @param threshold of the weapons
	 * @param breakDoor  can break door or not
	 */
	public Weapon(String title,int range,int damage, int threshold, boolean breakDoor, boolean noisy,Board board, int nbDice) {
		super(title, breakDoor);
		this.damage =damage;
		this.range =range;
		this.threshold = threshold;
		this.noisy = noisy;
		this.board =board ;
		this.nbDice= nbDice;
		this.chooser = new InteractiveListChooser<>();
	}
	
	/**
	 * Builder of the weapons
	 * @param title of the item
	 * @param range of the weapons
	 * @param damage of the weapons
	 * @param threshold of the weapons
	 * @param breakDoor  can break door or not
	 * @param chooser the listchooser to choose the zombies who you want
	 */
	public Weapon(String title,int range,int damage, int threshold, boolean breakDoor, boolean noisy,Board board, int nbDice,ListChooser<Zombies> chooser) {
		super(title, breakDoor);
		this.damage =damage;
		this.range =range;
		this.threshold = threshold;
		this.noisy = noisy;
		this.board =board ;
		this.nbDice= nbDice;
		this.chooser = chooser;
	}
	
	
	/**returns the threshold of the Weapon 
	 * @return threshold of the given weapon
	 * */
	public int getThreshold() {
		return this.threshold;
	}

	
	/**gives the range of the given weapon
	 * @return range of the weapon 
	 * */
	public int getRange() {
		return this.range;
	}

	
	/**
	 * gives the damage caused by the given weapon 
	 * @return damage 
	 * */
	public int getDamage() {
		return this.damage;
	}

	/**
	 * gives the number of dices 
	 * @return nbDice number of dices that a weapon allows to do */
	public int getNbDice() {
		return this.nbDice;
	}
	/**
	 * */
	public void Used() {
		this.Use=false;
	}

	/**
	 * return true if the weapon is noisy else false (for attack)
	 * @return true if the weapon is noisy else false (for attack)
	 */
	public boolean isNoisy(){
		return this.noisy;
	}
	
	
	public List<Zombies> WhoCanAttack(Player player){
		List<Zombies> z = new ArrayList<>();
		Cell c;
		int i;
		z.addAll(player.getCurrentCell().getAllZombies());
		for(Direction D : Direction.values()) {
			c = player.getCurrentCell();
			i=0;
			if( D == Direction.West) {
				while(c.getDoor(D).isBreak() && c.getY() - i>0 && i < this.range) {
					c = this.board.getCellBoard(c.getX(), c.getY()-1);
					z.addAll(c.getAllZombies());
					i+=1;
				}
			}
			if( D == Direction.East) {
				while(c.getDoor(D).isBreak() && c.getY()+i< this.board.getBoard()[0].length && i < this.range) {
					c = this.board.getCellBoard(c.getX(), c.getY()+1);
					z.addAll(c.getAllZombies());
					i+=1;
				}
			}
			if( D == Direction.South) {
				while(c.getDoor(D).isBreak() && i+c.getX()< this.board.getBoard().length&&i < this.range) {
					c = this.board.getCellBoard(c.getX()+1, c.getY());
					z.addAll(c.getAllZombies());
					i+=1;
				}
			}
			if( D == Direction.North) {
				while(c.getDoor(D).isBreak() && c.getX()-i> 0 && i < this.range) {
					c = this.board.getCellBoard(c.getX()-1, c.getY());
					z.addAll(c.getAllZombies());
					i+=1;
				}
			}
		}
		return z;
	}
	
	/**
	 * realizes the equipment effect 
	 * @param player who uses the equipment 
	 * */
	public void ItemEffect(Player player) {
		List<Zombies> zombies= WhoCanAttack(player);
		Zombies targetZ= this.chooser.choose("choose the zombie: ", zombies);
		Random random = new Random() ;
		int X= random.nextInt(6);
		if(X>= this.threshold) {
			if(this.noisy) {
				player.getCurrentCell().setNoise(player.getCurrentCell().getNoise()+1);
			}
			targetZ.takeDamage(this.getDamage());
			if(targetZ.isDead()) {
				player.UpOneExpertiseLevel();
			}
		}
	}
	
}
