package zombicide.util;

/**
 * class of Direction
 */
public enum Direction {
    /** the north */
    North,
    /** the south */
    South, 
    /** the east */
    East, 
    /** the west */
    West;
	
	
    /**
     * Give the oppose direction 
     * @param d the direction 
     * @return the oppose direction 
     */
    public static Direction oppose(Direction d) {
    	if(d ==Direction.East) {
    		return Direction.West;
    	}
    	else if(d ==Direction.West) {
    		return Direction.East;
    	}
    	else if(d ==Direction.North) {
    		return Direction.South;
    	}
    	else {
    		return Direction.North;
    	}
    }
}
