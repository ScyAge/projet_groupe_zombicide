package zombicide.actor.actionPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombie;
import zombicide.board.Board;
import zombicide.cell.Cell;
import zombicide.item.weapons.Weapon;
import zombicide.util.Direction;
import zombicide.util.listchooser.InteractiveListChooser;
import zombicide.util.listchooser.ListChooser;


/**
 * class Attack
 */
public class Attack implements ActionsPlayer{
	
	/**
	 * param 
	 */
	private final ListChooser<Zombie> chooser;
	private final Board board;
	
	
	/**
	 * Builder of Attack with ListChooser in param to test
	 * @param chooser the listchooser of the action
	 * @param board where attack
	 */
	public Attack(ListChooser<Zombie> chooser, Board board) {
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
	 * selects all the zombies around the player that he can attack with his weapon
	 * @param player the player who will attack
	 * @param w the weapon of the player
	 * @return the list of zombies that the player can attack
	 */
	public List<Zombie> WhoCanAttack(Player player, Weapon w){
		List<Zombie> z = new ArrayList<>();
		Cell c;
		int i;
		if(0>= w.getMinRange()) {
			if(player.getCurrentCell().canLook()){
				z.addAll(player.getCurrentCell().getAllZombies());
			}
		}
		for(Direction D : Direction.values()) {
			c = player.getCurrentCell();
			i=0;
			if( D == Direction.West) {
				while(c.getDoor(D).isBreak() && c.getY()-1 - i>=0 && i-1 < w.getRange() &&i>=w.getMinRange()&&this.board.getCellBoard(c.getX(), c.getY()-1).getDoor(Direction.East).isBreak()) {
					c = this.board.getCellBoard(c.getX(), c.getY()-1);
					if(c.canLook()){
						z.addAll(c.getAllZombies());
					}
					i+=1;
				}
			}
			if( D == Direction.East) {
				while(c.getDoor(D).isBreak() && c.getY()+i+1< this.board.getBoard()[0].length && i -1< w.getRange()&&i>=w.getMinRange()&&this.board.getCellBoard(c.getX(), c.getY()+1).getDoor(Direction.West).isBreak()) {
					c = this.board.getCellBoard(c.getX(), c.getY()+1);
					if(c.canLook()){
						z.addAll(c.getAllZombies());
					}
					i+=1;
				}
			}
			if( D == Direction.South) {
				while(c.getDoor(D).isBreak() && i+c.getX()+1< this.board.getBoard().length &&i-1 < w.getRange()&&i>=w.getMinRange()&&this.board.getCellBoard(c.getX()+1, c.getY()).getDoor(Direction.North).isBreak()) {
					c = this.board.getCellBoard(c.getX()+1, c.getY());
					if(c.canLook()){
						z.addAll(c.getAllZombies());
					}
					i+=1;
				}
			}
			if( D == Direction.North) {
				while(c.getDoor(D).isBreak() && c.getX()-i-1>= 0 && i-1 < w.getRange()&&i>=w.getMinRange()&&this.board.getCellBoard(c.getX()-1, c.getY()).getDoor(Direction.South).isBreak()) {
					c = this.board.getCellBoard(c.getX()-1, c.getY());
					if(c.canLook()){
						z.addAll(c.getAllZombies());
					}
					i+=1;
				}
			}
		}
		return z;
	}
	
	@Override
	public void action(Player p) {
		attack(p, 0);
		
	}

	/**
	 * The action attack of the Player
	 * @param p the player
	 * @param addDicePoint  the number of dice
	 */
	protected void attack(Player p,  int addDicePoint) {
		//verify if the player has an item in hand
		if(p.getItemInHand() != null) {
			//verify if the item can attack
			if(p.getItemInHand().canAttack()) {

				Weapon w = (Weapon) p.getItemInHand();
				int nbDice = w.getNbDice();
				List<Zombie> zombies= WhoCanAttack(p,w);
				Zombie targetZ= this.chooser.choose("choose the zombie to attack: ", zombies);

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

						System.out.println("-[ATTACK SUCCEED]-");
						System.out.printf("-[the %s have lost %d hp from the attack]-",targetZ.getClass().getSimpleName(),w.getDamage());

						if(targetZ.isDead()) {
							p.UpOneExpertiseLevel();
							System.out.printf("-[the %s id dead hp you gained a level your now lvl %d]-",targetZ.getClass().getSimpleName(),p.getExpertiseLevel());
						}
					}
					else{
						System.out.println("-[ATTACK FAIllED]-");
					}

				}
				p.setAction_points(p.getAction_points()-1);
				
			}
		}
	}

	@Override
	public boolean IsActionPlayable(Player p) {
		return (p.getItemInHand() != null && p.getItemInHand().canAttack())&&!this.WhoCanAttack(p,(Weapon) p.getItemInHand()).isEmpty() ;
	}

	@Override
	public String toString() {
		return "Attack action";
	}
}
