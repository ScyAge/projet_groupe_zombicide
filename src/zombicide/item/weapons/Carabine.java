package zombicide.item.weapons;

import javax.naming.Name;

/**
 * class Gun
 */
public class Carabine extends Weapon{
    /**
     * constant name of Carabine
     */
    private final static String Name_Carabine = "carabine";
    /**
     * constant range of Carabine
     */
    private final static int Range_Carabine = 3;
    /**
     * constant damage of Carabine
     */
    private final static int Damage_Carabine = 1;
    /**
     * constant threshold of Carabine
     */
    private final static int Threshold_Carabine = 4;
    /**
     * Builder of Carabine
     */
    public Carabine(){
        super(Name_Carabine, Range_Carabine, Damage_Carabine, Threshold_Carabine, false);
    }
}
