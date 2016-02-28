package warehouse;

public class Supplier extends Thread {

	//Fields:
	private Warehouse clientWarehouse;
	
	//Constructor:
	public Supplier(Warehouse clientWarehouse) {
		this.clientWarehouse = clientWarehouse;
	}

	@Override
	public void run() {
		
		while (true) {
			try {
				sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.clientWarehouse.supplyTheWarehouse();
			
		}

	}
	
	
	
	
}
