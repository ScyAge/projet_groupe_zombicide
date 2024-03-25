package zombicide.item.weapons;

import zombicide.board.Board;

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
     * constant breakDoor of axe
     */
    private final static boolean BreakDoor_Chainsaw = true;
    /**
     * constant noisy of axe
     */
    private final static boolean Noisy_Chainsaw = true;
    /**
     * Builder of Chainsaw
     */
    public Chainsaw(Board board){
        super(Name_Chainsaw, Range_Chainsaw, Damage_Chainsaw, Threshold_Chainsaw, BreakDoor_Chainsaw, Noisy_Chainsaw, board);
    }
}
