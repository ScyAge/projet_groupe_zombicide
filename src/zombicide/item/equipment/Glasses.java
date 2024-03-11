package zombicide.item.equipment;

import java.util.ArrayList;
import java.util.List;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.cell.Cell;
import zombicide.cell.Room;
import zombicide.cell.Street;

/**
 * class Glasses
 */
public class Glasses extends Equipment {
	
	/**
	 * Builder of Glasses
	 * @param title of the item
	 */
	public Glasses(String title) {
		super(title);
		
	}

	@Override
	/**
	 * shows the actors that are in adjacent cells to the player's cell
	 * @param Player 
	 * */
	public void equipmentEffect(Player player) {
		super.equipmentEffect(player);
		Cell cell = player.getCurrentCell();
		int x= cell.getX();
		int y = cell.getY();
		
		List<Cell> adjCell= new ArrayList<>();
		
		adjCell.addAll(adjacentCells(x+1,y));
		adjCell.addAll(adjacentCells(x-1,y));
		adjCell.addAll(adjacentCells(x,y+1));
		adjCell.addAll(adjacentCells(x,y-1));
		
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
		
	


	/** gives the list of adjacent cells to the cell with the given coordinates
	 * @param x the horizontal coordinate of the cell
	 * @param the vertical coordinate of the cell
	 * @return a list of the adjacent cells
	 *  */
	private List<Cell> adjacentCells(int x, int y) {
		Room r= new Room(x,y);
		Street s= new Street(x,y);
		
		List<Cell> cells= new ArrayList<Cell>();
		cells.add(r);
		cells.add(s);
		return cells;
		
	}
}
