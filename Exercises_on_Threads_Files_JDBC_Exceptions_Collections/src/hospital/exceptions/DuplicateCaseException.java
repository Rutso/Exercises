package hospital.exceptions;

public class DuplicateCaseException extends Exception {

	//Fields:
	private static final long serialVersionUID = -6432051422139058386L;

	
	//Constructor:
	public DuplicateCaseException(String message) {
		super(message);
	}
	
}
