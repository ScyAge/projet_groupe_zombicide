package zombicide.exeption;

/**
 * class of ActorDoesNotExistExeption
 */
public class ActorDoesNotExistExeption extends Exception{
	
	/**
	 * Builder of ActorDoesNotExistExeption
	 * @param msg report in the throw
	 */
	public ActorDoesNotExistExeption(String msg) {
		super(msg);
	}
}
