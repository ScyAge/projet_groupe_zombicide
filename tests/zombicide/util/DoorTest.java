package zombicide.util;

import java.beans.Transient;

import zombicide.util.*;
import zombicide.util.door.East_west_door;
import zombicide.util.door.North_South_door;;

public class DoorTest {


    @BeforeEach
    void setUp() {
        Door dew = new East_west_door(true);
        Door dew2 = new East_west_door(false);
        Door dns = new North_South_door(true);
        Door dns2 = new North_South_door(false);
    }

    @Test
    public void TestBreakDoor(){
        assertTrue
    }
}
