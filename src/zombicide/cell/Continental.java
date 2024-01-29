package zombicide.cell;



public class Continental extends Room {

	public Continental(int x, int y) {
		super(x, y);
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
