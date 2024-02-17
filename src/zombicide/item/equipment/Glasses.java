package zombicide.item.equipment;

import java.util.ArrayList;
import java.util.List;

import zombicide.actor.player.Player;
import zombicide.actor.zombie.Zombies;
import zombicide.cell.Cell;
import zombicide.cell.Room;
import zombicide.cell.Street;

public class Glasses extends Equipment {

	public Glasses(String title) {
		super(title);
		
	}

	@Override
	public void equipmentEffect(Player player) {
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
		
	



	private List<Cell> adjacentCells(int x, int y) {
		Room r= new Room(x,y);
		Street s= new Street(x,y);
		
		List<Cell> cells= new ArrayList<Cell>();
		cells.add(r);
		cells.add(s);
		return cells;
		
	}
}
