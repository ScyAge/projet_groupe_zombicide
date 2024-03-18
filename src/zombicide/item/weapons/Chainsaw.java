package zombicide.item.weapons;

/**
 * class Chainsaw
 */
public class Chainsaw extends Weapon{
    /**
     * constant name of Chainsaw
     */
    private final static String Name_Chainsaw = "chainsaw";
    /**
     * constant range of Chainsaw
     */
    private final static int Range_Chainsaw = 0;
    /**
     * constant damage of Chainsaw
     */
    private final static int Damage_Chainsaw = 3;
    /**
     * constant threshold of Chainsaw
     */
    private final static int Threshold_Chainsaw = 5;
    /**
     * Builder of Chainsaw
     */
    public Chainsaw(){
        super(Name_Chainsaw, Range_Chainsaw, Damage_Chainsaw, Threshold_Chainsaw, true);
    }
}
