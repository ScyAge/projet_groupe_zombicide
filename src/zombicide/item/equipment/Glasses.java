package zombicide.item.equipment;

import java.util.ArrayList;
import java.util.List;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;
import zombicide.cell.Cell;
import zombicide.cell.Room;
import zombicide.cell.Street;

/**
 * class Glasses
 */
public class Glasses extends Equipment {
	private Board board;
	
	/**
	 * Builder of Glasses
	 * @param title of the item
	 */
	public Glasses(String title, Board board) {
		super(title);
		this.board=board;
	}

	@Override
	/**
	 * shows the actors that are in adjacent cells to the player's cell
	 * @param Player 
	 * */
	public void ItemEffect(Player player) {
		super.ItemEffect(player);
		Cell cell = player.getCurrentCell();
		int x= cell.getX();
		int y = cell.getY();
		
		List<Cell> adjCell= getAdjacentCells(x,y);
		
		
		for(Cell c : adjCell) {
			if (c!=null && c.canLook()) {
				System.out.println("Players in cell (" + c.getX() + ", " + c.getY() + "):");
				for (Player p : c.getAllPlayers()) {
				    System.out.println("Player: " + p);
				}
				System.out.println("Zombies in cell (" + c.getX() + ", " + c.getY() + "):");
				for(Zombies z: c.getAllZombies()) {
					System.out.println("Zombie: "+ z);
				}
			}
		}
	}

	/**
	 * return true if the equipment is noisy else false
	 * @return true if the equipment is noisy else false
	 */
	public boolean isNoisy() {
		return false;
	}


	/** gives the list of adjacent cells to the cell with the given coordinates
	 * @param x the horizontal coordinate of the cell
	 * @param the vertical coordinate of the cell
	 * @return a list of the adjacent cells
	 *  */
	private List<Cell> getAdjacentCells(int x, int y) {
		List<Cell> cells= new ArrayList<Cell>();
		
		if(isValidCell(x-1, y)) {
			cells.add(this.board.getBoard()[x-1][y]);
		}
		if(isValidCell(x+1,y)) {
			cells.add(this.board.getBoard()[x+1][y]);
		}
		if(isValidCell(x,y-1)) {
			cells.add(this.board.getBoard()[x][y-1]);
		}
		if(isValidCell(x,y+1)) {
			cells.add(this.board.getBoard()[x][y+1]);
		}
		
		return cells;
	}
	
	/**
	 * tells whether the cell with the given coordinates is valid or not
	 * @param x the horitontal coordinate of the cell
	 * @param y the vertical coordinate of the cell
	 * @return true if the coordinates are valid, and false if not*/
	private boolean isValidCell(int x, int y) {
		return x>=0 && x<this.board.getBoard().length && y>=0 && y< this.board.getBoard()[0].length;
	}
}
