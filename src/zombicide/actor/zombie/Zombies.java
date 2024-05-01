package zombicide.actor.zombie;

import zombicide.actor.Actor;
import zombicide.cell.Cell;

/**
 * Class Zombies
 */
public class Zombies extends Actor {

    /**
     * domage of the zombies
     */
    protected int damagePoints;
    /**
     * basic action point
     */
    protected int basic_action_point;
    /**
     * Builder of Zombies
     *
     * @param lifePoints    of the Zombie
     * @param action_points of the Zombie
     * @param cell          where the Zombie is
     * @param id            of the Zombie
     * @param damagePoints  of the Zombie
     */
    public Zombies(int lifePoints, int action_points, Cell cell, int id, int damagePoints) {
        super(lifePoints, action_points, cell, id);
        this.damagePoints = damagePoints;
        basic_action_point = this.action_points;
    }

    /**
     * actor take damage
     *
     * @param damage taken by the actor
     */
    public void takeDamage(int damage) {

        this.lifePoints -= damage;
        if (this.lifePoints <= 0) {
            this.isDead = true;
            this.Dead();
        }
    }
    
    public int getBasicActionPoint() {
    	return this.basic_action_point;
    }
    /**
     * remove the zombie to the cell
     */
	public void Dead() {
		this.cell.remove(this);
		this.setCell(null);
	}

    /**
     * gives the damagePoints of the Zombies
     *
     * @return damagePoints
     */
    public int getDamagePoints() {
        return damagePoints;
    }

    /**
     * set the damage points
     *
     * @param damagePoints of the zombies
     */
    public void setDamagePoints(int damagePoints) {
        this.damagePoints = damagePoints;
    }


    /**
     * gives an information about how many damage points the zombie has
     *
     * @return Affichage of Zombies
     */
    public String toString() {
        return "Zombie have " + this.damagePoints + " life Points";
    }


}
