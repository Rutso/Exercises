package storage;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Storage {

	//Fields:
	private String name;
	public static final String[] PRODUCT_TYPES = {"Vegetables", "Fruits", "Dairy"};
	public static final String[] VEGETABLES = {"Cucumber", "Cabbage", "Potato"};
	public static final String[] FRUITS = {"Orange", "Cabage", "Potatoe"};
	public static final String[] DAIRY = {"Milk", "Yogurt", "Chees"};
	public static final Integer INITIAL_QUANTITIES = 0;
	public static final Integer MIN_QUANTITY_INSTOCK = 10;
	public static final Integer MAX_SELL_QUANTITY = 10;
	public static final Integer MAX_SUPPLIED_QUANTITY = 50;
	private TreeMap<String, TreeMap<String, Integer>> products;
	
	
	//Constructor:
	public Storage(String name) {
		this.name = name;
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
	public String getName() {
		return this.name;
	}
	
	
	//Methods:
	public synchronized void addStock() {
		ArrayList<String> depletedProducts = getDepletedProducts();
		
		if (depletedProducts.size() == 0) {
			try {
				wait(); //supplier waits until there is some product that needs to be supplied
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			for (String product : depletedProducts) {
				for (Map.Entry<String, TreeMap<String, Integer>> e : this.products.entrySet()) {
					TreeMap<String, Integer> productCategory = e.getValue();
					if (productCategory.containsKey(product)) {
						productCategory.put(product, (productCategory.get(product) + Storage.MAX_SUPPLIED_QUANTITY));
						 //supplier supplies product and notifies all (we are interested in the stores that are waiting to buy products from the storage)
					}
				}
			}
			notifyAll();
		}
		
		
		
	
	
	
	
	}
	
	
	public synchronized void sellStock(ArrayList<String> demandedProducts) {
		for (String demandedProduct : demandedProducts) {
			for (Map.Entry<String,TreeMap<String, Integer>> e : this.products.entrySet()) {
			TreeMap<String, Integer> productCategory = e.getValue();
				if (productCategory.containsKey(demandedProduct) && productCategory.get(demandedProduct) >= Storage.MAX_SELL_QUANTITY) {
					productCategory.put(demandedProduct, (productCategory.get(demandedProduct) - Storage.MAX_SELL_QUANTITY));
					notifyAll();
				} else {
				//	try {
						//wait();
				//	} catch (InterruptedException e1) {
					//	e1.printStackTrace();
				//	}
				}
			}
			
			
		}
		
		
	}
	
	
	private ArrayList<String> getDepletedProducts() {
		ArrayList<String> depletedProducts = new ArrayList<String>();
		
		for (Map.Entry<String, TreeMap<String, Integer>> e : this.products.entrySet()) {
			TreeMap<String, Integer> productCategory = e.getValue();
			
			for (Map.Entry<String, Integer> productType : productCategory.entrySet()) {
				if (productType.getValue() < Storage.MIN_QUANTITY_INSTOCK) {
					depletedProducts.add(productType.getKey());
				}
			}
			
		}
		
		return depletedProducts;
	}
	
	
	
}
