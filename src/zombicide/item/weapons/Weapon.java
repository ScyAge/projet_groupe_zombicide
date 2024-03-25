package zombicide.item.weapons;

import java.util.ArrayList;
import java.util.List;

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
	
	/**
	 * Builder of the weapons
	 * @param title of the item
	 * @param range of the weapons
	 * @param damage of the weapons
	 * @param threshold of the weapons
	 * @param breakDoor  can break door or not
	 */
	public Weapon(String title,int range,int damage, int threshold, boolean breakDoor, boolean noisy,Board board) {
		super(title, breakDoor);
		this.damage =damage;
		this.range =range;
		this.threshold = threshold;
		this.noisy = noisy;
		this.board =board ;
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
	
	
	private List<Zombies> WhoCanAttack(Player player){
		List<Zombies> z = new ArrayList<>();
		Cell c;
		int i;
		for(Direction D : Direction.values()) {
			c = player.getCurrentCell();
			i=0;
			if( D == Direction.West) {
				while(c.getDoor(D).isBreak() && c.getY() - i>0&&i < this.range) {
					z.addAll(c.getAllZombies());
					c = this.board.getCellBoard(c.getX(), c.getY()-1);
				}
			}
			if( D == Direction.East) {
				while(c.getDoor(D).isBreak() && c.getY()+i< this.board.getBoard()[0].length &&i < this.range) {
					z.addAll(c.getAllZombies());
					c = this.board.getCellBoard(c.getX(), c.getY()+1);
				}
			}
			if( D == Direction.South) {
				while(c.getDoor(D).isBreak() && i+c.getX()< this.board.getBoard().length&&i < this.range) {
					z.addAll(c.getAllZombies());
					c = this.board.getCellBoard(c.getX()+1, c.getY());
				}
			}
			if( D == Direction.North) {
				while(c.getDoor(D).isBreak() && c.getX()-i> 0 && i < this.range) {
					z.addAll(c.getAllZombies());
					c = this.board.getCellBoard(c.getX()-1, c.getY());
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
		ListChooser<Zombies> listChooser = new InteractiveListChooser<>();
		Zombies targetZ= listChooser.choose("choose the zombie: ", zombies);
		targetZ.getCurrentCell().setNoise(2);
		targetZ.setLifePoints(targetZ.getLifePoints()- this.damage);
	}
	
}
