package storage;

import java.util.ArrayList;

public class Demo {

	public static void main(String[] args) {

		//1. Create storage:
		Storage storage = new Storage("Sklada na Mecho");
		
		
		//2. Create supplier:
		Supplier supplier = new Supplier("Ezhko Bezhko OOD");
		supplier.addStorage(storage);
		supplier.start();
		
		//3. Create store:
		Store store = new Store("Magazina na Baba Metsa");
		store.addStorage(storage);
		store.start();
		ArrayList<String> productsToBuyFromStorage = new ArrayList<String>();
		productsToBuyFromStorage.add("Potato");
		
		Thread t = new Thread(new Runnable() {

		
		
			@Override
			public void run() {
				while (true) {
					storage.sellStock(productsToBuyFromStorage);
				}
				
				
			}
			
		});
		
		t.start();
		
		
	}

}
