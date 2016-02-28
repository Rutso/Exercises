package warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Store extends Thread {

	//Fields:
	private String name;
	private Warehouse warehouseToGetStockFrom;
	private HashMap<String, HashMap<String, Integer>> products;
	public static final Integer INITIAL_QUANTITY = 15;
	public static final Integer MIN_QUANTITY = 5;
	public static final Integer SUPPLY_QUANTITY = 5;
	
	
	//Constructor:
	public Store(String name, Warehouse warehouseToGetStockFrom) {
		this.name = name;
		this.warehouseToGetStockFrom = warehouseToGetStockFrom;
		this.products = new HashMap<String, HashMap<String, Integer>>();
		for (int i = 0; i < Warehouse.PRODUCT_TYPES.length; i++){
			HashMap<String, Integer> temp = new HashMap<String, Integer>();
			for (int j = 0; j < Warehouse.PRODUCTS[i].length; j++) {
				temp.put(Warehouse.PRODUCTS[i][j], Store.INITIAL_QUANTITY);
			}
			this.products.put(Warehouse.PRODUCT_TYPES[i], temp);
		}
		
		
		
		
	}
	
	
	
	//Getters:
	public String getStoreName() {
		return this.name;
	}
	
	//Methods:
	private ArrayList<String> getDepletedProducts() {
		ArrayList<String> depletedProducts = new ArrayList<String>();
		for (Map.Entry<String, HashMap<String, Integer>> category : this.products.entrySet()) {
			for (Map.Entry<String, Integer> product : category.getValue().entrySet()) {
				if (product.getValue() <= Store.MIN_QUANTITY) {
					depletedProducts.add(product.getKey());
				}
			}
		}
		return depletedProducts;
	}



	
	private void addToProductQuantity(String targetProduct) {
		for (Map.Entry<String, HashMap<String, Integer>> category : this.products.entrySet()) {
			for (Map.Entry<String, Integer> product : category.getValue().entrySet()) {
				if (product.getKey().equalsIgnoreCase(targetProduct)) {
					category.getValue().put(targetProduct, (product.getValue() + Store.SUPPLY_QUANTITY));
					break;
				}
			}
		}
	}
	@Override
	public void run() {
		
		while (true) {
				synchronized (this) {
				ArrayList<String> depletedProducts = getDepletedProducts();
				if (depletedProducts.size() == 0) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					this.warehouseToGetStockFrom.sellStockToStore(depletedProducts, this);
					for (String product : getDepletedProducts()) {
						addToProductQuantity(product);
				
					}
					notifyAll();
					}
				}
				
				
				
				
		}	
			
	
		
	}
	
	
	public synchronized void sellProductToClient(String requestedProduct, Integer quantity, Client client) {
		
			for (Map.Entry<String, HashMap<String, Integer>> category : this.products.entrySet()) {
				if (category.getValue().containsKey(requestedProduct)) {
					if (category.getValue().get(requestedProduct) > Store.MIN_QUANTITY && category.getValue().get(requestedProduct) >= quantity) {
						category.getValue().put(requestedProduct, category.getValue().get(requestedProduct) - quantity);
						System.out.println(client.getClientName() + " bought " + quantity + " " + requestedProduct + " from " + this.name + ".");
						notifyAll(); //when a client buys some product it wakes-up the store in a ready mode
					} else {
						try {
							System.out.println(client.getClientName() + " waits for " + requestedProduct + " to be supplied in " + this.name + ".");
							wait(); //if there is no enough quantity from the requested product, the client waits until the store dets new quantities from the warehouse.
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
			}
			
		
		
	}
	
	
	
}
