package Cell;

import Cell.Room;

public class Continental extends Room {

	public Continental(int x, int y, String type) {
		super(x, y, type);
	}
	
	@Override
	public boolean canLook() {
		return false;
	}

	@Override
	public String toString() {
		return "players are in a rest, there are no attacks";
	}
	
	
}
