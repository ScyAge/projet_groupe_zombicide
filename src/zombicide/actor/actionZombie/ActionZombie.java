package zombicide.actor.actionZombie;

import zombicide.actor.zombie.Zombies;

/**
 * interface ActionZombie
 */
public interface ActionZombie {

    /**
     * Execute an action of the zombie
     * @param z a zombie
     */
    public void action(Zombies z);

    /**
     * Test if a zombie can execute the action
     * @param z a zombie
     * @return the result of the test
     */
    public boolean IsActionPlayable(Zombies z);
}
