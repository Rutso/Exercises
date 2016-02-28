package warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Warehouse {

	
	//Fields:
	public static final String[] PRODUCT_TYPES = {"fruits", "vegetables", "meats"};
	public static final String[][] PRODUCTS= {{"banana", "orange", "apple"}, {"potato", "eggplant", "cucumber"}, {"pork", "beef", "chicken"}};
	public static final Integer INITIAL_QUANTITY = 15;
	public static final Integer MIN_QUANTITY = 5;
	public static final Integer SUPPLY_QUANTITY = 25;
	public static final Integer SELL_QUANTITY = 5;
	private HashMap<String, HashMap<String, Integer>> products;
	
	
	//Constructor:
	public Warehouse() {
		this.products = new HashMap<String, HashMap<String, Integer>>();
		for (int i = 0; i < Warehouse.PRODUCT_TYPES.length; i++){
			HashMap<String, Integer> temp = new HashMap<String, Integer>();
			for (int j = 0; j < Warehouse.PRODUCTS[i].length; j++) {
				temp.put(Warehouse.PRODUCTS[i][j], Warehouse.INITIAL_QUANTITY);
			}
			this.products.put(Warehouse.PRODUCT_TYPES[i], temp);
		}
		
	}
	
	
	//Methods:
	
	//SUPPLYER:
	//Returns a list with the names of products which quantity is under the minimum quantity, determinded by the warekouse:
	private ArrayList<String> getDepletedProducts() {
		ArrayList<String> depletedProducts = new ArrayList<String>();
		for (Map.Entry<String, HashMap<String, Integer>> category : this.products.entrySet()) {
			for (Map.Entry<String, Integer> product : category.getValue().entrySet()) {
				if (product.getValue() <= Warehouse.MIN_QUANTITY) {
					depletedProducts.add(product.getKey());
				}
			}
		}
		return depletedProducts;
	}
	//Adds a predetemined supply quantiy to the actual product quantity. Takes as argumen the products name:
	private void addToProductQuantity(String targetProduct) {
		for (Map.Entry<String, HashMap<String, Integer>> category : this.products.entrySet()) {
			for (Map.Entry<String, Integer> product : category.getValue().entrySet()) {
				if (product.getKey().equalsIgnoreCase(targetProduct)) {
					category.getValue().put(targetProduct, (product.getValue() + Warehouse.SUPPLY_QUANTITY));
					break;
				}
			}
		}
	}
	//Checks replenishes all products in the warehouse which quantity is under the minimum. This method is used by the supplier to bring new quantities to warehouse:
	public synchronized void supplyTheWarehouse() {
		ArrayList<String> depletedProducts = getDepletedProducts();
		if (depletedProducts.size() == 0) {
			try {
				wait();						//if there is no depleted product in the warehouse, the suplier thread goes in wait()-state (blocked)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			for (String depletedProduct : depletedProducts) {	//if there are products that need to be supplied, supplies them:
				addToProductQuantity(depletedProduct);
				System.out.println("		" + Warehouse.SUPPLY_QUANTITY + " " + depletedProduct + " supplied to warehouse.");
			}
			notifyAll();		//and notifies the stores that the warehouse has stock, so they can continue buying boods from it:
		}
	}
	
	
	//STORE:
	public synchronized void sellStockToStore(ArrayList<String> requestedProducts, Store store) {
		for (String requestedProduct : requestedProducts) {
			for (Map.Entry<String, HashMap<String, Integer>> category : this.products.entrySet()) {
				if (category.getValue().containsKey(requestedProduct)) {
					if (category.getValue().get(requestedProduct) >= Warehouse.MIN_QUANTITY && category.getValue().get(requestedProduct) >= Warehouse.SELL_QUANTITY) {
						category.getValue().put(requestedProduct, category.getValue().get(requestedProduct) - Warehouse.SELL_QUANTITY);
						System.out.println("	" + store.getStoreName() + " bought " + Warehouse.SELL_QUANTITY + " "+ requestedProduct + " from the warehouse.");
						notify(); //when a store buys some product it wakes-up the supplier in a ready mode
					} else {
						try {
							System.out.println("	" + store.getStoreName() + " waits for " + requestedProduct + " to be supplied in the warehouse.");
							wait(); //if there is no enough quantity from the requested product, the store waits until the supplier supplies new quantities to the warehouse.
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
			}
			
		}
		
	}
	
	
}
