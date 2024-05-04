package zombicide.actor;

import zombicide.cell.Cell;

public class MockActor extends Actor{
    public int CallDead;
    public int CallCons;

    public static final int LifePoint = 3;
    public static final int ActionPoint = 3;

    public static final int Id = 9999;

    public MockActor(Cell cell){
        super(LifePoint,ActionPoint,cell,Id);
        this.CallDead = 0;
        this.CallCons = 0;
    }

    @Override
    public void Dead() {
        CallDead +=1;
    }

    @Override
    protected void consequenceDeath() {
        this.Dead();
        CallCons+=1;
    }
}
