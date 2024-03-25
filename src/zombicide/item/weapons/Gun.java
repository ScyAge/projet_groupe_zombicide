package zombicide.item.weapons;

import zombicide.board.Board;

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
     * constant breakDoor of axe
     */
    private final static boolean BreakDoor_Gun = false;
    /**
     * constant noisy of axe
     */
    private final static boolean Noisy_Gun = true;
    /**
     * Builder of Gun
     */
    public Gun(Board board){
        super(Name_Gun, Range_Gun, Damage_Gun, Threshold_Gun, BreakDoor_Gun, Noisy_Gun, board);
    }
}
