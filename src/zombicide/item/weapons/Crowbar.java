package zombicide.item.weapons;

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
     * constant breakDoor of axe
     */
    private final static boolean BreakDoor_Crowbar = true;
    /**
     * constant noisy of axe
     */
    private final static boolean Noisy_Crowbar = false;
    /**
     * Builder of Crowbar
     */
    public Crowbar(){
        super(Name_Crowbar, Range_Crowbar, Damage_Crowbar, Threshold_Crowbar, BreakDoor_Crowbar, Noisy_Crowbar);
    }
}
