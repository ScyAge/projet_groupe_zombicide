package zombicide.actor.actionPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;
import zombicide.cell.Cell;
import zombicide.item.weapons.Weapon;
import zombicide.util.Direction;
import zombicide.util.listchooser.InteractiveListChooser;
import zombicide.util.listchooser.ListChooser;

public class Attack implements ActionsPlayer{
	
	/**
	 * param 
	 */
	private final ListChooser<Zombies> chooser;
	private final Board board;
	
	
	/**
	 * Builder of Attack with ListChooser in param to test
	 * @param chooser the listchooser of the action
	 * @param board where attack
	 */
	public Attack(ListChooser<Zombies> chooser,Board board) {
		this.chooser = chooser;
		this.board = board ;
	}
	
	
	/**
	 * Builder of Attack
	 * @param board where attack
	 */
	public Attack(Board board) {
		this.chooser = new InteractiveListChooser<>();
		this.board = board ;
	}
	
	/**
	 * take zombie who can attack
	 * @param player the player who will attack
	 * @param w the weapon of the player
	 * @return list of zombie who can attack
	 */
	public List<Zombies> WhoCanAttack(Player player,Weapon w){
		List<Zombies> z = new ArrayList<>();
		Cell c;
		int i;
		if(0>= w.getMinRange()) {
			z.addAll(player.getCurrentCell().getAllZombies());
		}
		for(Direction D : Direction.values()) {
			c = player.getCurrentCell();
			i=0;
			if( D == Direction.West) {
				while(c.getDoor(D).isBreak() && c.getY()-1 - i>0 && i < w.getRange() &&i>=w.getMinRange()) {
					c = this.board.getCellBoard(c.getX(), c.getY()-1);
					z.addAll(c.getAllZombies());
					i+=1;
				}
			}
			if( D == Direction.East) {
				while(c.getDoor(D).isBreak() && c.getY()+i+1< this.board.getBoard()[0].length && i < w.getRange()&&i>=w.getMinRange()) {
					c = this.board.getCellBoard(c.getX(), c.getY()+1);
					z.addAll(c.getAllZombies());
					i+=1;
				}
			}
			if( D == Direction.South) {
				while(c.getDoor(D).isBreak() && i+c.getX()+1< this.board.getBoard().length &&i < w.getRange()&&i>=w.getMinRange()) {
					c = this.board.getCellBoard(c.getX()+1, c.getY());
					z.addAll(c.getAllZombies());
					i+=1;
				}
			}
			if( D == Direction.North) {
				while(c.getDoor(D).isBreak() && c.getX()-i-1> 0 && i < w.getRange()&&i>=w.getMinRange()) {
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
		attack(p, 0, 0);
		
	}


	protected void attack(Player p, int nbRollDice, int addDicePoint) {
		if(p.getItemInHand() != null) {
			if(p.getItemInHand().cantAttack()) {
				Weapon w = (Weapon) p.getItemInHand();
				int nbDice = w.getNbDice()+ nbRollDice;
				List<Zombies> zombies= WhoCanAttack(p,w);

				Zombies targetZ= this.chooser.choose("choose the zombie: ", zombies);
				if(targetZ != null) {
					Random random = new Random() ;
					int X= random.nextInt(6) + addDicePoint;
					while(X< w.getThreshold()&& nbDice-->1  ) {
						X= random.nextInt(6) + addDicePoint;
					}
					if(X>= w.getThreshold()) {
						if(w.isNoisy()) {
							p.getCurrentCell().setNoise(p.getCurrentCell().getNoise()+1);
						}
						targetZ.takeDamage(w.getDamage());
						if(targetZ.isDead()) {
							p.UpOneExpertiseLevel();
						}
					}
					p.setAction_points(p.getAction_points()-1);
				}
				
			}
		}
	}

	@Override
	public boolean IsActionPlayable(Player p) {
		return !p.getCurrentCell().getAllZombies().isEmpty();
	}
}
