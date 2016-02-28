package hospital;

import java.util.ArrayList;

public class Room {

	//Fields:
	private int number;
	private ArrayList<Bed> beds;
	private static final int MAXIMUM_BEDS = 3;
	private volatile int numberOfFreeBeds;
	private volatile boolean isMale;
	
	//Constructor:
	public Room(int number) {
		this.number = number;
		this.beds = new ArrayList<Bed>();
		while (this.beds.size() < Room.MAXIMUM_BEDS) {
			this.beds.add(new Bed());
		}
		this.numberOfFreeBeds = Room.MAXIMUM_BEDS;
	}

	
	//Getters:
	public int getNumber() {
		return number;
	}

	public synchronized ArrayList<Bed> getBeds() {
		return beds;
	}

	public static int getMaximumBeds() {
		return MAXIMUM_BEDS;
	}

	public synchronized int getNumberOfFreeBeds() {
		return numberOfFreeBeds;
	}

	public synchronized boolean isMale() {
		return isMale;
	}


	
	//Setters:
	public synchronized void setMale(boolean isMale) {
		this.isMale = isMale;
	}
	
		
	//Methods:
	public synchronized void increaseNumberOfFreeBeds(){
		this.numberOfFreeBeds++;
	}
	public synchronized void decreaseNumberOfFreeBeds(){
		this.numberOfFreeBeds--;
	}
	
	
	
}
