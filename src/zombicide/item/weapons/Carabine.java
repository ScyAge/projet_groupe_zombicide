package zombicide.item.weapons;



/**
 * class Gun
 */
public class Carabine extends Weapon{
	

    
    /**
     * constant range max of Carabine
     */
    private final static int Max_Range_Carabine = 3;
    
    /**
     * constant range min of Carabine
     */
    private final static int Min_Range_Carabine = 1;
    /**
     * constant damage of Carabine
     */
    private final static int Damage_Carabine = 1;
    
    /**
     * constant threshold of Carabine
     */
    private final static int Threshold_Carabine = 4;
    
    /**
     * constant breakDoor of Carabine
     */
    private final static boolean BreakDoor_Carabine = false;
    
    /**
     * constant noisy of Carabine
     */
    private final static boolean Noisy_Carabine = true;
    
    /**
     * constant nbDice of Carabine
     */
    private final static int nbDice_Carabine = 2;
    
    /**
     * Builder of Carabine
     */
    public Carabine(){
        super(Max_Range_Carabine,Min_Range_Carabine, Damage_Carabine, Threshold_Carabine, BreakDoor_Carabine, Noisy_Carabine, nbDice_Carabine);
    }
}
