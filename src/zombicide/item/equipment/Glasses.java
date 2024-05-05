package zombicide.item.equipment;

import java.util.ArrayList;
import java.util.List;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombie;
import zombicide.board.Board;
import zombicide.cell.Cell;


/**
 * class Glasses
 */
public class Glasses extends Equipment {
	private final Board board;
	
	/**
	 * Builder of Glasses
	 *
	 * @param board of the glasses
	 */
	public Glasses(Board board) {
		super(false);
		this.board=board;
	}

	/**
	 * shows the actors that are in adjacent cells to the player's cell
	 * @param player the player who use the item
	 * */
	public void effectOfTheEquip(Player player) {
		this.actorsAround(player);
	}
	
	
	/**
	 * return the actors that are in adjacent cells to the player's cell
	 * @param p the player who use the item
	 * @return players and zombies id in the adjacent cells
	 * */
	
	public String actorsAround(Player p) {
		Cell cell = p.getCurrentCell();
		int x= cell.getX();
		int y = cell.getY();
		StringBuilder res = new StringBuilder();
		
		List<Cell> adjCell= getAdjacentCells(x,y);

		
		for(Cell c : adjCell) {

			if (c!=null && c.canLook()) {
				res.append(String.format("Players in cell (%d, %d): \n",c.getX(),c.getY()));
				for (Player player : c.getAllPlayers()) {
				    res.append(String.format("P%d\n",player.getId()));
				}
				res.append(String.format("Zombie in cell (%d, %d): \n",c.getX(),c.getY()));
				for(Zombie z: c.getAllZombies()) {
					res.append(String.format("Z%d\n",z.getId()));
				}
			} else if (c!=null && !c.canLook()) {
				res.append(String.format("You can't look in the room (%d,%d)\n",c.getX(),c.getY()));
			}
		}
		return res.toString();
	}


    /** 
     * gives the list of adjacent cells to the cell with the given coordinates
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
	 * @return true if the coordinates are valid, and false if not
	 * */
	private boolean isValidCell(int x, int y) {
		return x>=0 && x<this.board.getBoard().length && y>=0 && y< this.board.getBoard()[0].length;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Glasses){
			Glasses i = (Glasses) obj;
			return super.equals(i) && (this.board == i.board);
		}
		return false;
	}
	
	@Override
	public String itemDescription() {
		String res="see through walls";
		return res;
	}
}
