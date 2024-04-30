package zombicide.item;

import zombicide.actor.player.Player;

public class MockItem extends Item{
    public static String title ="test";
    public static boolean breakDoor =true;
    public static boolean canAttack =true;
    public static boolean noisy =true;
    public MockItem(){
        super(title,breakDoor,canAttack,noisy);
    }

    @Override
    public void ItemEffect(Player player) {
        return;
    }
}
