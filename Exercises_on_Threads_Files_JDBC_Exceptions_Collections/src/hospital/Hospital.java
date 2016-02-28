package hospital;

import java.time.LocalDateTime;
import java.util.Vector;

import hospital.dao.DBPatientDAO;
import hospital.exceptions.DupilcateDoctorException;
import hospital.exceptions.DuplicateCaseException;
import hospital.exceptions.NoAvailableDoctorsException;
import hospital.exceptions.NoFreeRoomsException;

public class Hospital {

		
	//Fields:
	public static final String[] DEPARTMENT_NAMES = {"Cardiology", "Neurology", "Orthopaedics"};
	private static final int MAX_DAYS_IN_HOSPITAL = 5;
	private static final int MIN_DAYS_IN_HOSPITAL = 3;
	private String name;
	private Vector<Doctor> doctors;
	private Vector<Department> departments;
	private Vector<Case> closedCases;
	private Clock clock;
	public static Administrator log;
	
	
	//Constructor:
	public Hospital(String name){
		this.name = name;
		this.doctors = new Vector<Doctor>();
		this.departments = new Vector<Department>();
		for (int i = 0; i < Hospital.DEPARTMENT_NAMES.length; i++) {
			this.departments.addElement(new Department(Hospital.DEPARTMENT_NAMES[i]));
		}
		this.closedCases = new Vector<Case>();
		Hospital.log = new Administrator();
		this.clock = new Clock(this);
		this.clock.start();
		
	}
	
	//Getters:
	public String getName(){
		return this.name;
	}
	public Vector<Doctor> getDoctors() {
		return this.doctors;
	}
	public Vector<Department> getDepartments() {
		return this.departments;
	}
		
	
	//Methods:
	public void addNurse(Nurse newNurse) {
		int dep = (int) (Math.random()*this.departments.size());
		this.departments.get(dep).addNurse(newNurse);
	}

	public void addDoctor(Doctor newDoctor) {
		if (this.doctors.contains(newDoctor)) {
			try {
				throw new DupilcateDoctorException("Dr. " + newDoctor.getFirstName() + " " + newDoctor.getLastName() + " already works in " + this.name + ".");
			} catch (DupilcateDoctorException e) {
				System.out.println(e.getMessage());
			}
		} else {
			this.doctors.addElement(newDoctor);
			newDoctor.setHospital(this);
			System.out.println("Dr. " + newDoctor.getFirstName() + " " + newDoctor.getLastName() + " starts work in " + this.name + ".");
			Hospital.log.writeLog("Dr. " + newDoctor.getFirstName() + " " + newDoctor.getLastName() + " starts work in " + this.name + ".");
			Thread t = new Thread(newDoctor);
			t.start();
		}
	}
	
	public void serviceIncomingPatient(Patient incomingPatient) {
		for(Department dep : this.departments) {
			for (Case cs : dep.getCases()) {
				if (cs.getPatient().equals(incomingPatient)) {
					try {
						throw new DuplicateCaseException(incomingPatient.getFirstName() + " " + incomingPatient.getLastName() + " is already in " + this.name + ".");
					} catch (DuplicateCaseException e) {
						System.out.println(e.getMessage());
						return;
					}
				}
			}
		}
		
		int numberOfFreeRooms = 0;
		for (Department d : this.departments) {
			numberOfFreeRooms+= d.getNumberOfFreeRooms();
		}
		if (numberOfFreeRooms == 0) {
			try {
				throw new NoFreeRoomsException("No free rooms in " + this.name + ".");
			} catch (NoFreeRoomsException e) {
				System.out.println(e.getMessage());
				return;
			}
		}
		
		int numberOfDoctorsWithThePotentialToGetTheCase = 0;
		for (Doctor dr : this.doctors) {
			if (dr.getCases().size() < dr.getMaxNumberOfCases()) {
				numberOfDoctorsWithThePotentialToGetTheCase++;
			}
		}
		if (numberOfDoctorsWithThePotentialToGetTheCase == 0) {
			try {
				throw new NoAvailableDoctorsException("No available doctors in " + this.name + ".");
			} catch (NoAvailableDoctorsException e) {
				System.out.println(e.getMessage());
				return;
			}
		}
		
		
		
		for (Doctor d : this.doctors) {
			String diagnose = null;
			if (d.isFree() && d.getCases().size()<d.getMaxNumberOfCases()) {
				diagnose = d.diagnoseIncomingPatient(incomingPatient);
			}
			for (Department dep : this.departments) {
				if (dep.getName().equalsIgnoreCase(diagnose)) {
					if (dep.getNumberOfFreeRooms() == 0) {
						try {
							throw new NoFreeRoomsException("No free rooms in " + dep.getName() + ".");
						} catch (NoFreeRoomsException e) {
							System.out.println(e.getMessage());
							return;
						}
					} else {
						for (Room r : dep.getRooms()) {
							if (r.isMale() == incomingPatient.isMale() && r.getNumberOfFreeBeds() > 0 && r.getNumberOfFreeBeds() < Room.getMaximumBeds()) {
								Bed bed = null;
								for (Bed b : r.getBeds()) {
									if (b.isFree()) {
										bed = b;
									//	System.out.println("					Position 0" + bed + incomingPatient.getFirstName());
										bed.setFree(false);
										r.decreaseNumberOfFreeBeds();
										if (r.getNumberOfFreeBeds() == 0) {
											dep.decreaseNumberOfFreeRooms();
										}
										break;
									}
									
								}
							//	System.out.println("					Position 1" + bed + incomingPatient.getFirstName());
								Case newCase = new Case(incomingPatient, d, LocalDateTime.now(), dep, r, bed, dep.getMedicine(), daysInHospital());
								d.addCase(newCase);
								dep.addCase(newCase);
								DBPatientDAO patDao = new DBPatientDAO();
								patDao.addPatient(incomingPatient);
								int patientId = patDao.getPatientId(incomingPatient);
								System.out.println(incomingPatient.getFirstName() + " " +  incomingPatient.getLastName() + " - ID = " + patientId);
								Hospital.log.writeLog(incomingPatient.getFirstName() + " " +  incomingPatient.getLastName() + " - ID = " + patientId);
								return;
							} else {
								if (r.getNumberOfFreeBeds() == Room.getMaximumBeds()) {
									r.setMale(incomingPatient.isMale());
									Bed bed = null;
									for (Bed b : r.getBeds()) {
										if (b.isFree()) {
											bed = b;
											bed.setFree(false);
											r.decreaseNumberOfFreeBeds();
										}
										break;
									}
									//System.out.println("					Position 2" + bed );
									Case newCase = new Case(incomingPatient, d, LocalDateTime.now(), dep, r, bed, dep.getMedicine(), daysInHospital());
									d.addCase(newCase);
									dep.addCase(newCase);
									DBPatientDAO patDao = new DBPatientDAO();
									patDao.addPatient(incomingPatient);
									int patientId = patDao.getPatientId(incomingPatient);
									System.out.println(incomingPatient.getFirstName() + " " +  incomingPatient.getLastName() + " - ID = " + patientId);
									return;
								}
							}
						}
					}
				}
			}
			
			
		} 
		System.out.println("No free doctors at the moment.");
	}
	
	private static int daysInHospital(){
		return (int) (Math.random()*(Hospital.MAX_DAYS_IN_HOSPITAL-Hospital.MIN_DAYS_IN_HOSPITAL) + Hospital.MIN_DAYS_IN_HOSPITAL);
	}
	
	public synchronized void archiveCase(Case closedCase) {
		this.closedCases.addElement(closedCase);
	}
	
	
}
