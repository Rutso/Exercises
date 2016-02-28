package hospital;



public class Nurse extends Person implements Runnable, IHospitalWorker {

	
	//Fields:
	@SuppressWarnings("unused")
	private int yearsOfExperience;
	private Department department;
	
	
	//Constructor:
	public Nurse(String firstName, String lastName, String phoneNumber, int yearsOfExperience) {
		super(firstName, lastName, phoneNumber);
		this.yearsOfExperience = yearsOfExperience;
	}

	
	//Getters:
	public String getFirstName() {
		return super.firstName;
	}
	public String getLastName() {
		return super.lastName;
	}
	public Department getDepartment() {
		return this.department;
	}
	
	//Setters:
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
	//Methods:
	public synchronized void waitForNewDay() {
		try {
			wait();
		} catch (InterruptedException e) {
			System.out.println("New working day for nurse " + this.firstName + " " + this.lastName + " from " + this.department.getName() + ".");
		}
	}

	@Override
	public void run() {

		while (true) {
			
			for (int i = this.department.getCases().size()-1; i>=0 ; i--){
				this.department.getCases().get(i).giveMedicine(this);
			}
			waitForNewDay();
			
		}
				
	}

	@Override
	public synchronized void startNewWorkDay() {
		notifyAll();
	}
	
	
	
}
