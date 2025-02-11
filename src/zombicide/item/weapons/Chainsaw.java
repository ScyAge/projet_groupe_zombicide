package zombicide.item.weapons;


/**
 * class Chainsaw
 */
public class Chainsaw extends Weapon{

    /**
     * constant range max of  Chainsaw
     */
    private final static int Max_Range_Chainsaw = 0;
    /**
     * constant range Min of  Chainsaw
     */
    private final static int Min_Range_Chainsaw = 0;
    /**
     * constant damage of Chainsaw
     */
    private final static int Damage_Chainsaw = 3;
    /**
     * constant threshold of Chainsaw
     */
    private final static int Threshold_Chainsaw = 5;
    /**
     * constant breakDoor of chainsaw
     */
    private final static boolean BreakDoor_Chainsaw = true;
    /**
     * constant noisy of chainsaw
     */
    private final static boolean Noisy_Chainsaw = true;
    /**
     * constant nbDice of chainsaw
     */
    private final static int nbDice_Chainsaw = 2;
    /**
     * Builder of Chainsaw
     */
    public Chainsaw(){
        super(Max_Range_Chainsaw,Min_Range_Chainsaw, Damage_Chainsaw, Threshold_Chainsaw, BreakDoor_Chainsaw, Noisy_Chainsaw, nbDice_Chainsaw);
    }
}
