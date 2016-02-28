package hospital;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Case {

	//Fields:
	private Patient patient;
	private Doctor doctor;
	private LocalDateTime dateAccepted;
	private LocalDate dateLeft;
	private Department department;
	private Room room;
	private Bed bed;
	private String medicine;
	private volatile int daysInHospital;
	private volatile boolean tookMedicineToday;
	
	//Constructor:
	public Case(Patient patient, Doctor doctor, LocalDateTime dateAccepted, Department department, Room room, Bed bed, String medicine, int daysInHospital) {
		this.patient = patient;
		this.doctor = doctor;
		this.dateAccepted = dateAccepted;
		this.department = department;
		this.room = room;
		this.bed = bed;
		this.medicine = medicine;
		this.daysInHospital = daysInHospital;
		this.tookMedicineToday = false;
	}

	//Getters:
	public Patient getPatient() {
		return patient;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public LocalDateTime getDateAccepted() {
		return dateAccepted;
	}
	public LocalDate getDateLeft() {
		return dateLeft;
	}
	public Department getDepartment() {
		return this.department;
	}
	public Room getRoom() {
		return this.room;
	}
	public Bed getBed() {
		return this.bed;
	}
	public String getMedicine() {
		return this.medicine;
	}
	public synchronized int getDaysInHospital() {
		return this.daysInHospital;
	}
	public boolean tookMedicineToday() {
		return this.tookMedicineToday;
	}

	
	
	//Setters:
	public void setDateLeft(LocalDate dateLeft) {
		this.dateLeft = dateLeft;
	}

	public void setDaysInHospital() {
		this.daysInHospital--;
	}

	public void setTookMedicineToday(boolean tookMedicineToday) {
		this.tookMedicineToday = tookMedicineToday;
	}

	public synchronized void giveMedicine(Nurse nurse) {
		if (!(tookMedicineToday) && daysInHospital > 0) {
			setTookMedicineToday(true);
			setDaysInHospital();
			System.out.println("Nurse " + nurse.firstName + " " + nurse.lastName + " from " + this.department.getName() + " gave the medicine to " + patient.getFirstName() + " " + patient.getLastName() + ".");
			Hospital.log.writeLog("Nurse " + nurse.firstName + " " + nurse.lastName + " from " + this.department.getName() + " gave the medicine to " + patient.getFirstName() + " " + patient.getLastName() + ".");

		}
	}
	
	//Methods:
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Case other = (Case) obj;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		return true;
	}
	
		
}
