package hospital.exceptions;

public class NoFreeRoomsException extends Exception {

	//Fields:
	private static final long serialVersionUID = -2200393227001141127L;

	//Constructor:
	public NoFreeRoomsException(String message) {
		super(message);
	}
	
}
