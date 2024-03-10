package zombicide.item.equipment;

import zombicide.actor.player.Player;
import zombicide.board.Board;
import zombicide.cell.Cell;
import zombicide.util.Direction;

/**
 *  class MapCard
*/
public class MapCard extends Equipment{
	private Board board;

	/**
	 * Create MapCard
	 * @param title of the MapCard
	 * @param board of the card
	 */
	public MapCard(String title,Board board) {
		super(title);
		this.board=board;
	}

	
	/**
	 * displays the board 
	 * @param player player who use the effect
	 */
	public void equipmentEffect(Player player) {
		this.display();
		
	}
	
	
	/**
	 * displays the board without showing what's inside the continental
	 * */
	public void display() {
		Cell[][] b= this.board.getBoard();
		for(int i =0;i<b.length;i++) {
			for(int ligne = 0;ligne <4;ligne++) {
				String x = "";
				for(int j =0;j<b[0].length;j++) {
					if(b[i][j].canLook()){
						if(ligne ==0) {
							x += b[i][j].getDoor(Direction.North).toString();
						}
						else if(ligne ==1) {
							x += this.board.secondLineDisplay(i, j);
						}
						else if(ligne ==2){
							x += this.board.ThirdLineDisplay(i, j);
						}
						else{
							x +=b[i][j].getDoor(Direction.South).toString();
						}
					}else {
						if(ligne==0|| ligne==3) {
							x+= b[i][j].getDoor(Direction.West).toString() + b[i][j].getDoor(Direction.East).toString();
						}
					}
				}
				System.out.println(x);
			}
			
		}
	}
}
