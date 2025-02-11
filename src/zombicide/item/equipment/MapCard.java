package zombicide.item.equipment;

import zombicide.actor.player.Player;
import zombicide.board.Board;
import zombicide.cell.Cell;
import zombicide.util.Direction;

/**
 *  class MapCard
*/
public class MapCard extends Equipment{
	private final Board board;

	/**
	 * Create MapCard
	 *
	 * @param board of the card
	 */
	public MapCard(Board board) {
		super(true);
		this.board=board;
	}

	
	/**
	 * displays the board 
	 * @param player player who use the effect
	 */
	public void effectOfTheEquip(Player player) {
		this.display();
	}


	/**
	 * displays the board without showing what's inside the continental
	 * */
	public void display() {
		Cell[][] b= this.board.getBoard();
		for(int i =0;i<b.length;i++) {
			for(int ligne = 0;ligne <4;ligne++) {
				StringBuilder x = new StringBuilder();
				for(int j =0;j<b[0].length;j++) {
					if(ligne ==0) {
						x.append(b[i][j].getDoor(Direction.North).toString());
					}
					else if(ligne ==1) {
						x.append(this.secondLineDisplay(i, j,b[i][j].canLook()));
					}
					else if(ligne ==2){
						x.append(this.ThirdLineDisplay(i, j,b[i][j].canLook()));
					}
					else{
						x.append(b[i][j].getDoor(Direction.South).toString());
					}
				}
				System.out.println(x);
			}
		}
			
	}
		
		
	/**
	 * Display the third line of the cell contain the name of the cell and the number of Survivor in
	 * @param i horizontal index of the board
	 * @param j vertical index of the board
	 * @param canLook boolean that tells whether we can see the actors inside a cell or not
	 * @return a String of the display
	 */
	private String ThirdLineDisplay(int i, int j,boolean canLook) {
		String x ="";
		if(this.board.getBoard()[i][j].getAllPlayers().isEmpty() || !canLook) {
			x += this.board.getBoard()[i][j].getDoor(Direction.West).toString()+"   "+this.board.getBoard()[i][j].getDoor(Direction.East).toString();
		}
		else {
			int NbPlayer = this.board.getBoard()[i][j].getAllPlayers().size();
			x += this.board.getBoard()[i][j].getDoor(Direction.West).toString()+"s"+ (NbPlayer > 9 ? "+" : NbPlayer) +" "+this.board.getBoard()[i][j].getDoor(Direction.East).toString();
		}
		return x;
	}

	/**
	 * Display the second line of the cell contain the name of the cell and the number of zombie in
	 * @param i horizontal index of the board
	 * @param j vertical index of the board
	 * @param canLook boolean that tells whether we can see the actors inside a cell or not
	 * @return a String of the display
	 */
	private String secondLineDisplay(int i, int j,boolean canLook) {
		String x="";
		if(this.board.getBoard()[i][j].getAllZombies().isEmpty()|| !canLook) {
			x += this.board.getBoard()[i][j].getDoor(Direction.West).toString()+this.board.getBoard()[i][j].toString()+"  "+this.board.getBoard()[i][j].getDoor(Direction.East).toString();
		}
		else {
			int NbZombie = this.board.getBoard()[i][j].getAllZombies().size();
			x += this.board.getBoard()[i][j].getDoor(Direction.West).toString()+this.board.getBoard()[i][j].toString()+"z"+ (NbZombie > 9 ? "+" : NbZombie)+this.board.getBoard()[i][j].getDoor(Direction.East).toString();}
		return x;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MapCard){
			MapCard i = (MapCard) obj;
			return super.equals(i) && (this.board == i.board);
		}
		return false;
	}
	
	@Override
	public String itemDescription() {
		String res=("allows you to visualize the entire city and the actors");
		return res;
	}
}
