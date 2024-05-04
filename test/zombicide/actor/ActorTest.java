package zombicide.actor;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.cell.Cell;
import zombicide.cell.Room;

import static org.junit.jupiter.api.Assertions.*;
public class ActorTest {

    private MockActor mock;
    private Cell cell;
    @BeforeEach
    public void init(){
        cell = new Room(2,2);
        mock = new MockActor(cell);

    }
    @Test
    public void TestGetLifePoint(){
        assertEquals(mock.getLifePoints(),MockActor.LifePoint);
    }

    @Test
    public void TestGetMaxLifePoint(){
        assertEquals(mock.getMaxLifePoint(),MockActor.LifePoint);
    }

    @Test
    public void TestTakeDamage(){
        assertEquals(mock.getLifePoints(),MockActor.LifePoint);
        mock.takeDamage(1);
        assertEquals(mock.getLifePoints(),MockActor.LifePoint-1);
    }

    @Test
    public void TestTakeDamageHPGoesBelow0(){
        assertEquals(mock.getLifePoints(),MockActor.LifePoint);
        mock.takeDamage(3);
        assertEquals(mock.getLifePoints(),0);
        assertTrue(mock.isDead);
        assertEquals(mock.CallDead,1);
        assertEquals(mock.CallCons,1);
    }

    @Test
    public void TestGetActionPoint(){
        assertEquals(mock.getAction_points(),MockActor.ActionPoint);
    }

    @Test
    public void TestSetActionPoint(){
        assertEquals(mock.getAction_points(),MockActor.ActionPoint);
        mock.setAction_points(5);
        assertEquals(mock.getAction_points(),5);
    }


    @Test
    public void TestGetCell(){
        assertEquals(mock.getCurrentCell(),this.cell);
    }

    @Test
    public void TestSetCellt(){
        Cell cell = new Room(4,4);
        assertEquals(mock.getCurrentCell(),this.cell);
        mock.setCell(cell);
        assertEquals(mock.getCurrentCell(),cell);
    }

    @Test
    public void TestGetID(){
        assertEquals(mock.getId(),MockActor.Id);
    }

    @Test
    public void TestSetLifePoint(){
        mock.takeDamage(2);
        assertEquals(mock.getLifePoints(),1);
        mock.setLifePoints(2);
        assertEquals(mock.getLifePoints(),3);
    }


    @Test
    public void TestSetLifePointWhenHPMax(){

        assertEquals(mock.getLifePoints(),MockActor.LifePoint);
        mock.setLifePoints(2);
        assertEquals(mock.getLifePoints(),MockActor.LifePoint);
    }

    @Test
    public void TestGetMAxactionPoint(){
        assertEquals(mock.getMaxActionPoints(),MockActor.ActionPoint);
    }

    @Test
    public void TestSetMaxActionPoint(){
        assertEquals(mock.getMaxActionPoints(),MockActor.ActionPoint);
        mock.addNMaxActionPoints(5);
        assertEquals(mock.getMaxActionPoints(),MockActor.ActionPoint+5);
    }


}
