package warehouse;

public class Client extends Thread {

	
	//Fields:
	private static final String[][] PRODUCTS = {{"banana", "orange", "apple"}, {"potato", "eggplant", "cucumber"}, {"pork", "beef", "chicken"}};
	private static final Integer MAX_QUANTITY_TO_BUY = 4;
	private String name;
	private Store favoriteStore;
	
	//Constructor:
	public Client(String name, Store favoriteStore) {
		this.name = name;
		this.favoriteStore = favoriteStore;
	}
	
	//Getters:
	public String getClientName() {
		return this.name;
	}

	@Override
	public void run() {
		
		while (true) {
			try {
				sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			int quantity = (int) (Math.random()*Client.MAX_QUANTITY_TO_BUY + 1);
			int i = (int) (Math.random()*Client.PRODUCTS.length);
			int j = (int) (Math.random()*Client.PRODUCTS[i].length);
			this.favoriteStore.sellProductToClient(Client.PRODUCTS[i][j], quantity, this);
					
		}
	}
	
	
	
}
