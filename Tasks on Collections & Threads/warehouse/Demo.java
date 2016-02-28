package warehouse;

public class Demo {

	
	public static void main(String[] args) {
		
		//1. Create a warehouse:
		Warehouse w = new Warehouse();
		
		//2. Create a supplier:
		Supplier s = new Supplier(w);
		
		//3. Start the supplier:
		s.start();
		
		//4. Create 3 stores:
		Store st1 = new Store("Baba Metsa's Store", w);
		Store st2 = new Store("Kuma Lisa's Store", w);
		Store st3 = new Store("Zaio Baio's Store", w);
		st1.start();
		st2.start();
		st3.start();
			
		
		//5. Create 9 clients:
		Client cl1 = new Client("Ejko", st1);
		Client cl2 = new Client("Valcho", st1);
		Client cl3 = new Client("Mecho", st1);
		Client cl4 = new Client("Orlio", st2);
		Client cl5 = new Client("Garjo", st2);
		Client cl6 = new Client("Petlio", st2);
		Client cl7 = new Client("Mravcho", st3);
		Client cl8 = new Client("Buhala", st3);
		Client cl9 = new Client("Elena", st3);
		cl1.start();
		cl2.start();
		cl3.start();
		cl4.start();
		cl5.start();
		cl6.start();
		cl7.start();
		cl8.start();
		cl9.start();
		
		
		
		
		
		
	}
	
}
