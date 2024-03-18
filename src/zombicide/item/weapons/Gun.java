package zombicide.item.weapons;

/**
 * class Gun
 */
public class Gun extends Weapon{
    /**
     * constant name of Gun
     */
    private final static String Name_Gun = "gun";
    /**
     * constant range of Gun
     */
    private final static int Range_Gun = 1;
    /**
     * constant damage of Gun
     */
    private final static int Damage_Gun = 1;
    /**
     * constant threshold of Gun
     */
    private final static int Threshold_Gun = 4;
    /**
     * Builder of Gun
     */
    public Gun(){
        super(Name_Gun, Range_Gun, Damage_Gun, Threshold_Gun, false);
    }
}
