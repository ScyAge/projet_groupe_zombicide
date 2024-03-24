package zombicide.item.weapons;

/**
 * class Axe
 */
public class Axe extends Weapon{
    /**
     * constant name of axe
     */
    private final static String Name_Axe = "axe";
    /**
     * constant range of axe
     */
    private final static int Range_Axe = 0;
    /**
     * constant damage of axe
     */
    private final static int Damage_Axe = 2;
    /**
     * constant threshold of axe
     */
    private final static int Threshold_Axe = 4;
    /**
     * constant breakDoor of axe
     */
    private final static boolean BreakDoor_Axe = true;
    /**
     * constant noisy of axe
     */
    private final static boolean Noisy_Axe = false;
    /**
     * Builder of Axe
     */
    public Axe(){
        super(Name_Axe, Range_Axe, Damage_Axe, Threshold_Axe, BreakDoor_Axe, Noisy_Axe);
    }
}
