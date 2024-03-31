package zombicide.item.weapons;

import javax.naming.Name;

import zombicide.board.Board;

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
    public Carabine(Board board){
        super(Name_Carabine, Range_Carabine, Damage_Carabine, Threshold_Carabine, BreakDoor_Carabine, Noisy_Carabine, board, nbDice_Carabine);
    }
}
