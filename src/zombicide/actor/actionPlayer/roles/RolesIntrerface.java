package zombicide.actor.actionPlayer.roles;

import zombicide.actor.player.Player;
/**
 * interface RolesIntrerface
 */
public interface RolesIntrerface {
    /**
     * method that allows a survivor to perform the special action related to its roles
     * @param p the survivor who use the action
     */
    public void action(Player p);
}


