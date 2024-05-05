package zombicide.item.weapons;


/**
 * class Axe
 */
public class Axe extends Weapon{

    /**
     * constant range max of axe
     */
    private final static int Max_Range_Axe = 0;
    /**
     * constant range max of axe
     */
    private final static int Min_Range_Axe = 0;
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
     * constant nbDice of axe
     */
    private final static int nbDice_Axe = 1;
    /**
     * Builder of Axe
     */
    public Axe(){
        super(Max_Range_Axe,Min_Range_Axe, Damage_Axe, Threshold_Axe, BreakDoor_Axe, Noisy_Axe, nbDice_Axe);
    }
    
}
