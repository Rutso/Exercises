package hospital;

public class Bed {

	//Fields:
	private boolean isFree;
	
	//Constructor:
	public Bed() {
		this.isFree = true;
	}
	
	//Getters:
	public synchronized boolean isFree() {
		return isFree;
	}
	
	//Setters:
	public synchronized void setFree(boolean isFree) {
		this.isFree = isFree;
	}
	
	
}
