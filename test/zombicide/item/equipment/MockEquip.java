package zombicide.item.equipment;

import zombicide.actor.player.Player;

public class MockEquip extends Equipment{

    public int applyCalled = 0;
    public MockEquip(){
        super("MockEquip",false);
    }

    @Override
    protected void effectOfTheEquip(Player p) {
        this.applyCalled +=1;
    }
}
