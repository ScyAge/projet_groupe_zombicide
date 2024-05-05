package zombicide.actor.actionZombie;

import zombicide.actor.zombie.Zombie;

/**
 * interface ActionZombie
 */
public interface ActionZombie {

    /**
     * Execute an action of the zombie
     * @param z a zombie
     */
    void action(Zombie z);

    /**
     * Test if a zombie can execute the action
     * @param z a zombie
     * @return the result of the test
     */
    boolean IsActionPlayable(Zombie z);
}
