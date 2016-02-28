package hospital;

public class Patient extends Person {

	
	//Fields:
	private int age;
	private boolean isMale;
	private Case personalCase;
			
	
	//Constructor:
	public Patient(String firstName, String lastName, String phoneNumber, int age, boolean isMale) {
		super(firstName, lastName, phoneNumber);
		this.age = age;
		this.isMale = isMale;
	}
	
	
	//Getters:
	public int getAge() {
		return age;
	}
	public boolean isMale() {
		return isMale;
	}
	public Case getPersonalCase() {
		return personalCase;
	}
	public String getFirstName() {
		return super.firstName;
	}
	public String getLastName() {
		return super.lastName;
	}
	public String getPhone() {
		return super.phoneNumber;
	}
	
	//Setters:
	protected void setPersonalCase(Case personalCase) {
		this.personalCase = personalCase;
	}

		
}
