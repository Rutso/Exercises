package hospital;

//import java.util.Collections;
//import java.util.Comparator;
import java.util.Vector;

public class Doctor extends Person implements Runnable, IHospitalWorker {

	
	//Fields:
	private static final int MAX_NUMBER_OF_CASES = 5;
	@SuppressWarnings("unused")
	private String speciality;
	private Vector<Case> cases; //he only reads the referent object
	private boolean isFree;
	private Hospital hospital;
	
	
	//Constructor:
	public Doctor(String firstName, String lastName, String phoneNumber, String speciality) {
		super(firstName, lastName, phoneNumber);
		this.speciality = speciality;
		this.cases = new Vector<Case>();
		this.isFree = true;
	}

	
	//Getters:
	protected Vector<Case> getCases() {
		return cases;
	}
	protected boolean isFree() {
		return isFree;
	}
	public String getFirstName() {
		return super.firstName;
	}
	public String getLastName() {
		return super.lastName;
	}
	public Hospital getHospital() {
		return this.hospital;
	}
	public int getMaxNumberOfCases() {
		return Doctor.MAX_NUMBER_OF_CASES;
	}
	
	
	//Setters:
	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}
	public void setHospital(Hospital hospital){
		this.hospital = hospital;
	}
	
	
	//Methods:
	public synchronized String diagnoseIncomingPatient(Patient newPatient) {
		return Hospital.DEPARTMENT_NAMES[(int) (Math.random()*Hospital.DEPARTMENT_NAMES.length)];
	}

	@Override
	public void run() {
		while (true) {
			this.isFree = false;
			Vector<Integer> casesToClose = new Vector<Integer>();
			for (int i = this.cases.size()-1 ; i>=0; i--) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				System.out.println(e.getMessage());
				}
				System.out.println("Dr. " + this.firstName + " " + this.lastName + " just visited " + this.cases.get(i).getPatient().getFirstName() + " " + this.cases.get(i).getPatient().getLastName() + ".");
				Hospital.log.writeLog("Dr. " + this.firstName + " " + this.lastName + " just visited " + this.cases.get(i).getPatient().getFirstName() + " " + this.cases.get(i).getPatient().getLastName() + ".");
				if(this.cases.get(i).getDaysInHospital() == 0) {
					casesToClose.addElement(i);
					//closeCase(this.cases.get(i));
				}
			}
			
			//Collections.sort(casesToClose, new Comparator<Integer>() {

				//@Override
			//	public int compare(Integer i1, Integer i2) {
					
			//		return -i1.compareTo(i2);
			//	}
				
		//	});
		//	for(int i = casesToClose.size()-1; i>=0; i--) {
		//		int index = casesToClose.get(i);
		//		System.out.println("				" + this.cases.get(index));
		//		closeCase(this.cases.get(index));
						
		//	}
			
			for(Integer i : casesToClose) {
				
				//System.out.println("				" + this.cases.get(i));
				closeCase(this.cases.get(i));
						
			}
			
			
			
			waitForTask();
			
		}
		
		
	}
	
	private synchronized void waitForTask(){
		this.isFree = true;
		try {
			wait();
		} catch (InterruptedException e) {
			//System.out.println("New working day for Dr. " + this.firstName + " " + this.lastName + ".");
		}
	}
		
	public synchronized void addCase(Case newCase) {
		this.cases.add(newCase);
	}
	
	public synchronized void closeCase(Case closedCase) {
		closedCase.setDateLeft(Clock.date);
		//System.out.println(" 				" + closedCase.getBed());
		closedCase.getBed().setFree(true);
		closedCase.getRoom().increaseNumberOfFreeBeds();
		if (closedCase.getRoom().getNumberOfFreeBeds()-1 == 0) {
			closedCase.getDepartment().increaseNumberOfFreeRooms();
		}
		closedCase.getDepartment().getCases().remove(closedCase);
		this.cases.remove(closedCase);
		System.out.println("Dr. " + this.firstName + " " + this.lastName + " closes the case of " + closedCase.getPatient().getFirstName() + " " + closedCase.getPatient().getLastName() + ", who leaves " + this.hospital.getName() + " in good health.");
		Hospital.log.writeLog("Dr. " + this.firstName + " " + this.lastName + " closes the case of " + closedCase.getPatient().getFirstName() + " " + closedCase.getPatient().getLastName() + ", who leaves " + this.hospital.getName() + " in good health.");

	}
	
	@Override
	public synchronized void startNewWorkDay() {
		notifyAll();
	}
	
}
