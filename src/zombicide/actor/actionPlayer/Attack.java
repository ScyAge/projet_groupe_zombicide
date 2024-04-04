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
	private Board board;
	
	
	/**
	 * Builder of Attack with ListChooser in param to test
	 * @param chooser the listchooser of the action
	 */
	public Attack(ListChooser<Zombies> chooser,Board board) {
		this.chooser = chooser;
		this.board = board ;
	}
	
	
	/**
	 * Builder of Attack
	 */
	public Attack(Board board) {
		this.chooser = new InteractiveListChooser<>();
		this.board = board ;
	}
	
	/**
	 * take zombie who can attack
	 * @param player
	 * @param w
	 * @return list of zombie who can attack
	 */
	public List<Zombies> WhoCanAttack(Player player,Weapon w){
		List<Zombies> z = new ArrayList<>();
		Cell c;
		int i;
		z.addAll(player.getCurrentCell().getAllZombies());
		for(Direction D : Direction.values()) {
			c = player.getCurrentCell();
			i=0;
			if( D == Direction.West) {
				while(c.getDoor(D).isBreak() && c.getY() - i>0 && i < w.getRange()) {
					c = this.board.getCellBoard(c.getX(), c.getY()-1);
					z.addAll(c.getAllZombies());
					i+=1;
				}
			}
			if( D == Direction.East) {
				while(c.getDoor(D).isBreak() && c.getY()+i< this.board.getBoard()[0].length && i < w.getRange()) {
					c = this.board.getCellBoard(c.getX(), c.getY()+1);
					z.addAll(c.getAllZombies());
					i+=1;
				}
			}
			if( D == Direction.South) {
				while(c.getDoor(D).isBreak() && i+c.getX()< this.board.getBoard().length &&i < w.getRange()) {
					c = this.board.getCellBoard(c.getX()+1, c.getY());
					z.addAll(c.getAllZombies());
					i+=1;
				}
			}
			if( D == Direction.North) {
				while(c.getDoor(D).isBreak() && c.getX()-i> 0 && i < w.getRange()) {
					c = this.board.getCellBoard(c.getX()-1, c.getY());
					z.addAll(c.getAllZombies());
					i+=1;
				}
			}
		}
		return z;
	}
	
	/**
	 * player attack zombies
	 * @param p player who attack a zombie
	 */
	public void action(Player p) {
		if(p.getItemInHand() != null) {
			if(p.getItemInHand().cantAttack()) {
				Weapon w = (Weapon) p.getItemInHand();
				int nbDice = w.getNbDice();
				List<Zombies> zombies= WhoCanAttack(p,w);
				Zombies targetZ= this.chooser.choose("choose the zombie: ", zombies);
				Random random = new Random() ;
				int X= random.nextInt(6);
				while(X< w.getThreshold()&& nbDice-->1  ) {
					X= random.nextInt(6);
				}
				if(X>= w.getThreshold()) {
					if(w.isNoisy()) {
						ActionsPlayer a = new MakeNoise();
						a.action(p);
					}
					targetZ.takeDamage(w.getDamage());
					if(targetZ.isDead()) {
						p.UpOneExpertiseLevel();
					}
				}
				p.setAction_points(p.getAction_points()-1);
			}
		}
		Cell c= p.getCurrentCell();
		
	}

	
}
