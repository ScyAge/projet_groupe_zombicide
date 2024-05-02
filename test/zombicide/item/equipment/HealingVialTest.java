package zombicide.item.equipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import zombicide.actor.player.Player;
import zombicide.cell.Cell;
import zombicide.cell.Room;

class HealingVialTest extends EquipmentTest{
    private Cell testCell;
    private HealingVial testHV;
    private Player testP;

    @Override
    protected Equipment createEquip() {
        return new HealingVial();
    }

    @BeforeEach
    public void initH(){
        this.testCell = new Room(5,5);
        this.testP = new Player(5,this.testCell,1,6);
        this.testCell.addPlayers(this.testP);
        this.testHV = new HealingVial();
    }

    @Test
    public void TestEffectOfTheEquipHealingVialMaxPv(){
        testHV.ItemEffect(this.testP);
        assertEquals(5, this.testP.getLifePoints()); // ne peut pas dépassé ses pv max
    }

    @Test
    public void TestEffectOfTheEquipHealingVial(){
        this.testP.takeDamage(2);
        testHV.ItemEffect(this.testP);
        assertEquals(4, this.testP.getLifePoints()); // ne peut pas dépassé ses pv max
    }


}