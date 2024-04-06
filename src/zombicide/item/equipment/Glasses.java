package zombicide.item.equipment;

import java.util.ArrayList;
import java.util.List;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.board.Board;
import zombicide.cell.Cell;


/**
 * class Glasses
 */
public class Glasses extends Equipment {
	private final Board board;
	
	/**
	 * Builder of Glasses
	 * @param title of the item
	 */
	public Glasses(String title,boolean breakDoor, Board board) {
		super(title, breakDoor);
		this.board=board;
	}

	/**
	 * shows the actors that are in adjacent cells to the player's cell
	 * @param player the player who use the item
	 * */
	public void ItemEffect(Player player) {
		super.ItemEffect(player);
		this.actorsAround(player);
	}
	
	
	/**
	 *return the actors that are in adjacent cells to the player's cell
	 * @param p the player who use the item
	 * @return players and zombies id in the adjacent cells
	 * */
	
	public String actorsAround(Player p) {
		Cell cell = p.getCurrentCell();
		int x= cell.getX();
		int y = cell.getY();
		String res="";
		
		List<Cell> adjCell= getAdjacentCells(x,y);
		
		
		for(Cell c : adjCell) {
			if (c!=null && c.canLook()) {
				res+=("Players in cell (" + c.getX() + ", " + c.getY() + "): \n" );
				for (Player player : c.getAllPlayers()) {
				    res+=("P" + player.getId()+ "\n");
				}
				res+=("Zombies in cell (" + c.getX() + ", " + c.getY() + "): \n");
				for(Zombies z: c.getAllZombies()) {
					res+=("Z"+ z.getId()+"\n");
				}
			}
		}
		return res;
	}


    /** gives the list of adjacent cells to the cell with the given coordinates
	 * @param x the horizontal coordinate of the cell
	 * @param y the vertical coordinate of the cell
	 * @return a list of the adjacent cells
	 *  */
	private List<Cell> getAdjacentCells(int x, int y) {
		List<Cell> cells= new ArrayList<>();
		
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
