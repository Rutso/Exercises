package hospital.exceptions;

public class NoAvailableDoctorsException extends Exception {

	//Fields:
	private static final long serialVersionUID = 7058953976943480059L;

	//Constructor:
	public NoAvailableDoctorsException(String message) {
		super(message);
	}
		

}
