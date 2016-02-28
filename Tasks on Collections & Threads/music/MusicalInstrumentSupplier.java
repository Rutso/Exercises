package music;

import java.util.HashMap;
import java.util.Map;


public class MusicalInstrumentSupplier {

	class SingleDelivery extends Thread {
		
		private String instrument;
		private Integer quantity;

		@Override
		public void run() {
			
			try {
				sleep(returnDeliveryTime(instrument));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			client.supplyInstrument(instrument, quantity);
			
		}
		
		
		
	}
	
	class MonthlyDeliveryService extends Thread {
		
		@Override
		public void run() {
						
			while(true) {
				try {
					sleep(30000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				client.makeMonthlySupplies();
			}
		}
			
	}
	
	
	//Fields:
	private String name;
	private HashMap<MusicalInstrument, Integer> instrumentsAndDeliveryTime;
	private MusicShop client;

	
	//Constructor:
	public MusicalInstrumentSupplier(String name, MusicShop client) {
		this.name = name;
		this.client = client;
		instrumentsAndDeliveryTime = new HashMap<MusicalInstrument, Integer>();
	}
	
	//Getters:
	public String getName() {
		return this.name;
	}
	
	
	//Methods:
	public void addNewInstrument(MusicalInstrument newInstrument, Integer deliveryTime) {
		if (deliveryTime <= 0) {
			return;
		} else {
			instrumentsAndDeliveryTime.put(newInstrument, deliveryTime);
		}
	}
	
	public Integer returnDeliveryTime(String instrumentName) {
		for (Map.Entry<MusicalInstrument, Integer> instrument : this.instrumentsAndDeliveryTime.entrySet()) {
			if (instrument.getKey().getName().equalsIgnoreCase(instrumentName)) {
				return instrument.getValue();
			}
		}
		System.out.println("		" + this.name + " doesn't supply " + instrumentName + "s.");
		return -1;
		
	}
	
	public void deliverOrder(String instrumentName, Integer quantity) {
		
		SingleDelivery sd = new SingleDelivery();
		sd.instrument = instrumentName;
		sd.quantity = quantity;
		sd.start();
	}
	
	public void startMonthlyDelivery() {
		MonthlyDeliveryService mds = new MonthlyDeliveryService();
		mds.start();
	}
	
}
