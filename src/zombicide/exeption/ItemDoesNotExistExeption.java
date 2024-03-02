package zombicide.exeption;

/**
 * class of ItemDoesNotExistExeption
 */
public class ItemDoesNotExistExeption extends Exception{
	
	
	/**
	 * Builder of ItemDoesNotExistExeption
	 * @param msg msg report in the throw
	 */
	public ItemDoesNotExistExeption(String msg) {
		super(msg);
	}

}
