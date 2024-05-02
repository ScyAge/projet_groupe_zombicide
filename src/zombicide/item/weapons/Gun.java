package zombicide.item.weapons;


/**
 * class Gun
 */
public class Gun extends Weapon{

    /**
     * constant range min of Gun
     */
    private final static int Max_Range_Gun = 1;
    /**
     * constant range max of Gun
     */
    private final static int Min_Range_Gun = 0;
    /**
     * constant damage of Gun
     */
    private final static int Damage_Gun = 1;
    /**
     * constant threshold of Gun
     */
    private final static int Threshold_Gun = 0;
    /**
     * constant breakDoor of Gun
     */
    private final static boolean BreakDoor_Gun = true;
    /**
     * constant noisy of Gun
     */
    private final static boolean Noisy_Gun = true;
    /**
     * constant nbDice of Gun
     */
    private final static int nbDice_Gun = 1;
    
    /**
     * Builder of Gun
     */
    public Gun(){
        super(Max_Range_Gun,Min_Range_Gun, Damage_Gun, Threshold_Gun, BreakDoor_Gun, Noisy_Gun, nbDice_Gun);
    }
}
