package zombicide.actor.zombie;

import zombicide.actor.Actor;
import zombicide.cell.Cell;

/**
 * Class Zombie
 */
public class Zombie extends Actor {

    /**
     * domage of the zombies
     */
    protected int damagePoints;

    /**
     * Builder of Zombie
     *
     * @param lifePoints    number of life point of the Zombie
     * @param action_points number of action point of the Zombie
     * @param cell          the cell where the Zombie is
     * @param id            the id of the Zombie
     * @param damagePoints  the damage that the Zombie deal
     */
    public Zombie(int lifePoints, int action_points, Cell cell, int id, int damagePoints) {
        super(lifePoints, action_points, cell, id);
        this.damagePoints = damagePoints;
    }


    @Override
    protected void consequenceDeath() {
        this.Dead();
    }

    /**
     * When this method is used, the zombie is removed from the cell it was in.
     */
	public void Dead() {
		this.cell.remove(this);
		this.setCell(null);
	}

    /**
     * gives the damagePoints of the Zombie
     *
     * @return damagePoints
     */
    public int getDamagePoints() {
        return damagePoints;
    }

    /**
     * set the damage points of the zombie
     *
     * @param damagePoints of the zombies
     */
    public void setDamagePoints(int damagePoints) {
        this.damagePoints = damagePoints;
    }


    /**
     * gives an information about how much life points the zombie has
     *
     * @return Affichage of Zombie
     */
    public String toString() {
    	Cell c= this.getCurrentCell();
        return String.format("%s has %d life Points and is at the cell (%d,%d)",this.getClass().getSimpleName(),this.lifePoints, c.getX(), c.getY());
    }
    
    /**
     * prints the status of the zombie*/
    public void zombieStatus() {
    	
    	System.out.println("Zombie"+ this.getId()+ " lifePoints="+ this.getLifePoints());
    }


}
