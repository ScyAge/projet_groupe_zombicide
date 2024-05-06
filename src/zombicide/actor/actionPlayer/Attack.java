package zombicide.actor.actionPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zombicide.actor.Actor;
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
		List<Zombie> ListZ = new ArrayList<>();

		if(0>= w.getMinRange()) {
			if(player.getCurrentCell().canLook()){
				ListZ.addAll(player.getCurrentCell().getAllZombies());
			}
		}

		for(Direction D : Direction.values()) {
			int range = 1 ;
			//a pseudo player used to execute canMove of the Board
			Player bisPlayer = new Player(0,player.getCurrentCell(),0,0);
			while(board.canMove(bisPlayer,D) && range <= w.getMaxRange()){
				Cell nextCell = board.getCellDirection(D,bisPlayer);
				if(nextCell.canLook()){
					ListZ.addAll(nextCell.getAllZombies());
				}
				bisPlayer.setCell(nextCell);
				range+=1;
			}
		}
		return ListZ;
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

						System.out.println("-[ATTACK SUCCEED]-\n");
						System.out.printf("-[the %s have lost %d hp from the attack]-\n",targetZ.getClass().getSimpleName(),w.getDamage());

						if(targetZ.isDead()) {
							p.UpOneExpertiseLevel();
							System.out.printf("-[the %s is dead hp you gained a level your now lvl %d]-\n",targetZ.getClass().getSimpleName(),p.getExpertiseLevel());
						}
					}
					else{
						System.out.println("-[ATTACK FAIllED]-\n");
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
