package zombicide.item.equipment;

import zombicide.actor.player.Player;

public class MockEquip extends Equipment{

    public int applyCalled = 0;
    public MockEquip(){
        super(false,true);
    }

    @Override
    protected void effectOfTheEquip(Player p) {
        this.applyCalled +=1;
    }
}
