package hospital;


import java.util.Vector;

import hospital.exceptions.DuplicateCaseException;
import hospital.exceptions.DuplicateNurseException;

public class Department {

	//Fields:
	private String medicine;
	private String name;
	private Vector<Room> rooms;
	private static final int MAX_NUMBER_OF_ROOMS = 10;
	private int numberOfFreeRooms;
	private Vector<Case> cases;
	private Vector<Nurse> nurses;
	
	
	//Constructor:
	public Department(String name) {
		this.name = name;
		this.medicine = "Medicines specific for the " + this.name + "department.";
		this.rooms = new Vector<Room>();
		int i = 1;
		while(this.rooms.size() < Department.MAX_NUMBER_OF_ROOMS) {
			this.rooms.add(new Room(i));
			i++;
		}
		this.numberOfFreeRooms = Department.MAX_NUMBER_OF_ROOMS;
		this.cases = new Vector<Case>();
		this.nurses = new Vector<Nurse>();
	}


	
	//Getters:
	public String getName() {
		return name;
	}


	public Vector<Room> getRooms() {
		return rooms;
	}


	public static int getMaxNumberOfRooms() {
		return MAX_NUMBER_OF_ROOMS;
	}


	public synchronized int getNumberOfFreeRooms() {
		return numberOfFreeRooms;
	}


	public Vector<Case> getCases() {
		return cases;
	}


	public Vector<Nurse> getNurses() {
		return nurses;
	}
	
	public String getMedicine() {
		return this.medicine;
	}
	
	//Methods:
	public void addCase(Case newCase) {
		if (this.cases.contains(newCase)) {
			try {
				throw new DuplicateCaseException("Such case already exists in " + this.name + " department.");
			} catch (DuplicateCaseException e) {
				System.out.println(e.getMessage());
			}
		} else {
			this.cases.addElement(newCase);
			Patient p = newCase.getPatient();
			System.out.println("New case added to " + this.name + " department: " + p.getFirstName() + " " + p.getLastName() + " in Room N" + newCase.getRoom().getNumber());
			Hospital.log.writeLog("New case added to " + this.name + " department: " + p.getFirstName() + " " + p.getLastName() + " in Room N" + newCase.getRoom().getNumber());

		}
	}
	
	public void removeCase(Case closedCase){
		this.cases.remove(closedCase);
	}
	
	public void addNurse(Nurse newNurse) {
		if (this.nurses.contains(newNurse)) {
			try {
				throw new DuplicateNurseException("Such nurse already works in " + this.name + " department.");
			} catch (DuplicateNurseException e) {
				System.out.println(e.getMessage());
			}
		} else {
			this.nurses.addElement(newNurse);
			System.out.println("New nurse starts work in " + this.name + " department.");
			Hospital.log.writeLog("New nurse starts work in " + this.name + " department.");
			newNurse.setDepartment(this);
			Thread t = new Thread(newNurse);
			t.start();
		}
	}
	
	public synchronized void increaseNumberOfFreeRooms(){
		this.numberOfFreeRooms++;
	}
	
	public synchronized void decreaseNumberOfFreeRooms(){
		this.numberOfFreeRooms--;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
