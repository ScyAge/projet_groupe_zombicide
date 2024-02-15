package zombicide.actor.zombie;

import zombicide.actor.*;

public  class Zombies extends Actor {
	
	protected int dommagePoints;
	
	
	public Zombies(int lifePoints,int dommagePoints,int ActionPoints) {
		super(lifePoints, ActionPoints);
		this.dommagePoints = dommagePoints ;
	}

	
	public int getDommagePoints() {
		return dommagePoints;
	}

	public void setDommagePoints(int dommagePoints) {
		this.dommagePoints = dommagePoints;
	}
	
	public String toString() {
		return "Zombie have " + this.dommagePoints+"dommage Points";
	}
	
	
}
