package zombicide.actor.actionPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.*;
import zombicide.board.Board;
import zombicide.cell.Cell;
import zombicide.item.Item;
import zombicide.util.Direction;
import zombicide.util.Door;
import zombicide.util.listchooser.*;

/**
 * Class OpenDoor implementation  of ActionsPlayer
 */
public class OpenDoor implements ActionsPlayer {
	
	/**
	 * List chooser
	 */
    private final ListChooser<Direction> chooser;
    
    /**
     * the Board 
     */
    private final Board board;
    
    /**
     * Builder of SearchIntRoomAction with ListChooser to test
     * @param chooser ListChooser of the Action
     * @param b the board 
     */
    public OpenDoor(ListChooser<Direction> chooser,Board b){
        this.chooser = chooser;
		this.board = b;
    }
    /**
     * Builder of SearchInTRoomAction 
     * @param b the board 
     */
    public OpenDoor(Board b){
		this.chooser = new InteractiveListChooser<>();
		this.board = b;
    }
	
	private List<Direction> DirectionToOpen(Player player) {
		List<Direction> d = new ArrayList<>();
		Cell c= player.getCurrentCell();
		for(Direction D : Direction.values()) {
			if( D == Direction.West) {
				if(c.getY()>0 && (! this.board.getCellBoard(c.getX(), c.getY()-1).getDoor(Direction.East).isBreak()||!this.board.getCellBoard(c.getX(), c.getY()).getDoor(D).isBreak())){
					d.add(D);
				}
			}
			if( D == Direction.East) {
				if(c.getY()<this.board.getBoard()[0].length-1&& (! this.board.getCellBoard(c.getX(), c.getY()+1).getDoor(Direction.West).isBreak()||!this.board.getCellBoard(c.getX(), c.getY()).getDoor(D).isBreak())){
					d.add(D);
				}
			}
			if( D == Direction.South) {
				if(c.getX()<this.board.getBoard().length-1 &&(!  this.board.getCellBoard(c.getX()+1, c.getY()).getDoor(Direction.North).isBreak()||!this.board.getCellBoard(c.getX(), c.getY()).getDoor(D).isBreak())){
					d.add(D);
				}

			}
			if( D == Direction.North ) {
				if(c.getX()>0 && (! this.board.getCellBoard(c.getX()-1, c.getY()).getDoor(Direction.South).isBreak()||!this.board.getCellBoard(c.getX(), c.getY()).getDoor(D).isBreak()) ){
					d.add(D);
				}
			}
		}
		return d;
	}
    
	@Override 
	public void action(Player p) {
		Item item= p.getItemInHand();
		Cell c= p.getCurrentCell();
		if(item != null && item.getBreakDoor()) {
			System.out.println("enter the Direction of the door you want to open");
			
			List<Direction> directions= DirectionToOpen(p);
			Direction targetD= this.chooser.choose("choose a direction: ", directions);
			
			if(targetD != null) {
				this.board.BreakDoor(targetD, c.getX(), c.getY());
				if (item.cantAttack()) {
					c.setNoise(c.getNoise() + 1);
				}
				generateZombies(p, targetD);
				
			}else {
				System.out.println("No direction chosen");
			}
		}
		else {
			System.out.println("The item in hand can't break the door");
		}
		p.setAction_points(p.getAction_points()-1);
		
	}
	
	
	
	/**
	 * generates randomly zombies and special zombies 
	 * @param p player who's opening the door
	 * @param d the direction of the opened door*/
	private void generateZombies(Player p, Direction d) {
		Cell c= getAdjacentCell(p,d);
		if(c!=null) {
			Random random= new Random();
			int numZ= random.nextInt(3)+1;
			for (int i=0; i<numZ; i++) {
				Walker z=new Walker(c,random.nextInt(100));
				c.addZombies(z);
				this.board.addZombieList(z);
			}
			ListChooser<String> zombieChooser = new RandomListChooser<>();
			List<String> specialZ= List.of("Abomination", "Broom");
			String zombieType= zombieChooser.choose("choose the special type of zombie", specialZ);
			switch(zombieType) {
				case "Abomination":
					Abominations a= new Abominations(c, random.nextInt(100));
					c.addZombies(a);
					this.board.addZombieList(a);
					break;
				case "Broom":
					Broom b= new Broom(c, random.nextInt(100));
					c.addZombies(b);
					this.board.addZombieList(b);
					break;
			}
		}
	}
	
	
	/**
	 * returns the adjacent cell in the direction of the opened door where we generate zombies 
	 * @param p the player who's doing the action
	 * @param openDirection the direction of the opened door
	 * @return the cell where we generate zombies */
	private Cell getAdjacentCell(Player p, Direction openDirection) {
		Cell c= p.getCurrentCell();
		
		int adjX= c.getX();
		int adjY= c.getY();
		
		switch(openDirection) {
		case North:
			adjX++;
			break;
		case South:
			adjX--;
			break;
		case East:
			adjY++;
			break;
		case West:
			adjY--;
			break;
			
		}
		if(adjX>=0 && adjX< this.board.getBoard().length && adjY>=0 && adjY< this.board.getBoard()[0].length) {
			return this.board.getCellBoard(adjX, adjY);
		}else {
			return null;
		}
		
	}

	@Override
	public boolean IsActionPlayable(Player p) {
		Cell c = p.getCurrentCell();
		List<Boolean> res = new ArrayList<>();
		for(Direction d: Direction.values()) {
			Door door= c.getDoor(d);
			boolean test;
			if(board.getCellDirection(d,p) == null){
				test = false;
			}
			else{
				test = board.getCellDirection(d,p).getDoor(Direction.oppose(d)).isBreak();
			}
			res.add(!test || !door.isBreak());
		}
		for(boolean r : res){
			if(r){
				return p.getItemInHand() != null && p.getItemInHand().getBreakDoor();
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "OpenDoor action";
	}
}
