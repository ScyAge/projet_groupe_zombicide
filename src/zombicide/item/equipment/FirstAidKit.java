package zombicide.item.equipment;

import java.util.List;
import java.util.Scanner;
import zombicide.actor.*;
import zombicide.actor.player.*;
import zombicide.cell.*;



public class FirstAidKit extends Equipment {
	
	public FirstAidKit( String title) {
		super(title);
	}
	
	/**
	 * heals a player in the same area as the player  
	 * who has the FirstAidKit
	 * @param player who has the FirstAidkit
	 * */
	public void equipmentEffect(Player player ) {
		Cell cell= player.getCurrentCell();
		List<Player> players= player.getPlayersInArea(cell);
		if(!players.isEmpty()) {
			System.out.println("Enter the number of the player to heal");
			Scanner scanner= new Scanner(System.in);
			int index= scanner.nextInt();
			if(index>0 && index<=players.size()) {
				Player target= players.get(index-1);
				int targetLifePoints= target.getLifePoints();
				target.setLifePoints(targetLifePoints+1);
			}else {
				System.out.println("invalid number");
			}
			
		}else {
			System.out.println("No player in the same Area");
		}
	}
}
