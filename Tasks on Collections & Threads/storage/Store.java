package storage;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Store extends Thread {

	//Fields:
	private String name;
	private ArrayList<Storage> storagesToSupplyFrom;
	public static final String[] PRODUCT_TYPES = {"Vegetables", "Fruits", "Dairy"};
	public static final String[] VEGETABLES = {"Cucumber", "Cabbage", "Potato"};
	public static final String[] FRUITS = {"Orange", "Cabage", "Potatoe"};
	public static final String[] DAIRY = {"Milk", "Yogurt", "Chees"};
	public static final Integer INITIAL_QUANTITIES = 0;
	public static final Integer MIN_QUANTITY_INSTOCK = 10;
	public static final Integer MAX_SELL_QUANTITY = 2;
	public static final Integer MAX_SUPPLIED_QUANTITY = 5;
	private TreeMap<String, TreeMap<String, Integer>> products;
	
	
	//Constructor:
	public Store(String name) {
		this.name = name;
		this.storagesToSupplyFrom = new ArrayList<Storage>();
		this.products = new TreeMap<String, TreeMap<String, Integer>>();
		for (int i = 0; i < Storage.PRODUCT_TYPES.length; i++) {
			this.products.put(Storage.PRODUCT_TYPES[i], new TreeMap<String, Integer>());
				switch (i) {
				case 0:
					for (int j = 0; j < Storage.VEGETABLES.length; j++) {
						this.products.get(Storage.PRODUCT_TYPES[i]).put(Storage.VEGETABLES[i], Storage.INITIAL_QUANTITIES);
					}
					break;
				case 1:
					for (int j = 0; j < Storage.FRUITS.length; j++) {
						this.products.get(Storage.PRODUCT_TYPES[i]).put(Storage.FRUITS[i], Storage.INITIAL_QUANTITIES);
					}
					break;
				case 2:
					for (int j = 0; j < Storage.DAIRY.length; j++) {
						this.products.get(Storage.PRODUCT_TYPES[i]).put(Storage.DAIRY[i], Storage.INITIAL_QUANTITIES);
					}
					break;
				}
				
		}
	}
	
	//Getters:
	public String getStoreName() {
		return this.name;
	}
	
	@Override
	public void run() {
		while (true) {
			ArrayList<String> depletedProducts = getDepletedProducts();
			if (depletedProducts.size() == 0) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				for (Storage s : this.storagesToSupplyFrom) {
					s.sellStock(depletedProducts);
				System.out.println(this.name + " was supplied from the " + s.getName());
					
				}
		notify();
			
		}
			
			
			
		}
	}
	
	public void addStorage(Storage newStorage) {
		this.storagesToSupplyFrom.add(newStorage);
	}
	
	
	private ArrayList<String> getDepletedProducts() {
		ArrayList<String> depletedProducts = new ArrayList<String>();
		
		for (Map.Entry<String, TreeMap<String, Integer>> e : this.products.entrySet()) {
			TreeMap<String, Integer> productCategory = e.getValue();
			
			for (Map.Entry<String, Integer> productType : productCategory.entrySet()) {
				if (productType.getValue() < Store.MIN_QUANTITY_INSTOCK) {
					depletedProducts.add(productType.getKey());
				}
			}
			
		}
		
		return depletedProducts;
	}
	
	
	
}
