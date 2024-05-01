package zombicide.item;

import zombicide.actor.player.Player;

public class MockItem extends Item{

    public static boolean breakDoor =true;
    public static boolean canAttack =true;
    public static boolean noisy =true;
    public MockItem(){
        super(breakDoor,canAttack,noisy);
    }

    @Override
    public void ItemEffect(Player player) {
        return;
    }
}
