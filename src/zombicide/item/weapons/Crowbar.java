package zombicide.item.weapons;

import zombicide.board.Board;

/**
 * class Crowbar
 */
public class Crowbar extends Weapon{
    /**
     * constant name of Crowbar
     */
    private final static String Name_Crowbar = "crowbar";
    /**
     * constant range of Chainsaw
     */
    private final static int Range_Crowbar = 0;
    /**
     * constant damage of Crowbar
     */
    private final static int Damage_Crowbar = 1;
    /**
     * constant threshold of Crowbar
     */
    private final static int Threshold_Crowbar = 4;
    /**
     * constant breakDoor of Crowbar
     */
    private final static boolean BreakDoor_Crowbar = true;
    /**
     * constant noisy of Crowbar
     */
    private final static boolean Noisy_Crowbar = false;
    /**
     * constant nbDice of Crowbar
     */
    private final static int nbDice_Crowbar = 1;
    /**
     * Builder of Crowbar
     */
    public Crowbar(Board board){
        super(Name_Crowbar, Range_Crowbar, Damage_Crowbar, Threshold_Crowbar, BreakDoor_Crowbar, Noisy_Crowbar, nbDice_Crowbar);
    }
}
