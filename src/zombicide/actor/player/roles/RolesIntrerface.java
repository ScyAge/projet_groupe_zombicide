package zombicide.actor.player.roles;

import zombicide.actor.player.Player;

public interface RolesIntrerface {
    /**
     * method that allows a survivor to perform the special action related to its roles
     * @param p the survivor who use the action
     */
    public void action(Player p);
}


