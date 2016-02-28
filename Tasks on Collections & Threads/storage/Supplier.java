package storage;

import java.util.ArrayList;

public class Supplier extends Thread {

	
	//Fields:
	private String name;
	private ArrayList<Storage> storagesToSupply;
	
	
	//Constructor:
	public Supplier(String name) {
		this.name = name;
		this.storagesToSupply = new ArrayList<Storage>();
	}
	
	
	//Method:
	public void addStorage(Storage newStorage) {
		this.storagesToSupply.add(newStorage);
	}
	
	@Override
	public void run() {
		while (true) {
			for (Storage s : this.storagesToSupply) {
				s.addStock();
				System.out.println(this.name + " delivered stock to " + s.getName());
			}
		}
	}
	
	
}
