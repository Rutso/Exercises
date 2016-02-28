package music;

public class Demo {

	
	static class Client extends Thread {
		
		private static final String[] INSTRUMENTS = {"flute", "guitar", "bassguitar", "piano", "tarambuka", "clarinet", "electricguitar", "drums", "harp", "tuba", "strings"};		
		private static final int MAX_QUANTITY = 3;
		private MusicShop favoriteShop;
		
		Client(MusicShop ms) {
			this.favoriteShop = ms;
		}
			
		@Override
		public void run() {

			while (true) {
				try {
					sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.favoriteShop.sellInstrument(Client.INSTRUMENTS[(int) (Math.random()*Client.INSTRUMENTS.length)], (int) (Math.random()*Client.MAX_QUANTITY + 1));
			}
			
		}
				
	}
		
	public static void main(String[] args) {

		//1. Create music shop:
		MusicShop lalala = new MusicShop("La-la-la Music Shop");
		
		//2. Create musical instruments:
		MusicalInstrument flute = new MusicalInstrument("flute", 200, "wind");
		MusicalInstrument guitar = new MusicalInstrument("guitar", 150, "string");
		MusicalInstrument bassGuitar = new MusicalInstrument("bassguitar", 250, "electric");
		MusicalInstrument piano = new MusicalInstrument("piano", 2500, "key");
		MusicalInstrument tarambuka = new MusicalInstrument("tarambuka", 65, "percusion");
		MusicalInstrument clarinet = new MusicalInstrument("clarinet", 300, "wind");
		MusicalInstrument electricGuitar = new MusicalInstrument("electricguitar", 500, "electric");
		MusicalInstrument drums = new MusicalInstrument("drums", 1500, "percusion");
		MusicalInstrument harp = new MusicalInstrument("harp", 1000, "string");
		MusicalInstrument tuba = new MusicalInstrument("tuba", 1700, "wind");
		MusicalInstrument strings = new MusicalInstrument("strings", 5, "accessories");
		
		//3. Add instruments to the shop:
		lalala.addNewInstrumentToCatalog(flute, 5);
		lalala.addNewInstrumentToCatalog(guitar, 10);
		lalala.addNewInstrumentToCatalog(bassGuitar, 8);
		lalala.addNewInstrumentToCatalog(piano, 2);
		lalala.addNewInstrumentToCatalog(tarambuka, 7);
		lalala.addNewInstrumentToCatalog(clarinet, 3);
		lalala.addNewInstrumentToCatalog(electricGuitar, 10);
		lalala.addNewInstrumentToCatalog(drums, 2);
		lalala.addNewInstrumentToCatalog(harp, 1);
		lalala.addNewInstrumentToCatalog(tuba, 0);
		lalala.addNewInstrumentToCatalog(strings, 50);
	
		//4. Create supplier and add some instruments with their delivery time in milliseconds:
		MusicalInstrumentSupplier mis = new MusicalInstrumentSupplier("Music Delivery", lalala);
		mis.addNewInstrument(flute, 3000);
		mis.addNewInstrument(guitar, 5000);
		mis.addNewInstrument(bassGuitar, 5500);
		mis.addNewInstrument(piano, 15000);
		mis.addNewInstrument(tarambuka, 6000);
		mis.addNewInstrument(clarinet, 4000);
		mis.addNewInstrument(electricGuitar, 6000);
		mis.addNewInstrument(drums, 10000);
		mis.addNewInstrument(harp, 13000);
		mis.addNewInstrument(tuba, 9000);
		mis.addNewInstrument(strings, 2000);
		System.out.println("Piano delivery time with " + mis.getName() + " is " + mis.returnDeliveryTime("piano")/1000 + " days.");
		mis.returnDeliveryTime("balalaika");
		//Add reference for the supplier in the music shop and enable clent orders:
		lalala.setSupplier(mis);
		//Get delivery time for some client's order:
		lalala.sellInstrument("piano", 7);
		System.out.println();
		
		//5. View the products:
		lalala.viewByType();
		System.out.println();
		lalala.viewByAlphabeticalOrder();
		System.out.println();
		lalala.viewByPriceAscending();
		System.out.println();
		lalala.viewByPriceDescending();
		System.out.println();
		lalala.viewTheAvailableProducts();
		System.out.println();
	
		//6. Sell more instruments:
		lalala.sellInstrument("guitar", 2);
		MusicalInstrument bestSeller = lalala.viewTheBestSeller();
		bestSeller.viewInstrumentInfo();
		System.out.println();
		lalala.sellInstrument("tambourine", 2);
		lalala.sellInstrument("strings", 7);
		lalala.sellInstrument("piano", 1);
		lalala.sellInstrument("tarambuka", 5);
		lalala.sellInstrument("drums", 2);
		lalala.sellInstrument("clarinet", 3);
		
		//7. Get reports:
		System.out.println();
		lalala.viewByBestSeller();
		System.out.println();
		lalala.viewTheLessSeller();
		System.out.println();
		lalala.viewTheBestSeller();
		System.out.println();
		lalala.viewBestSellingCategortByMoney();
		System.out.println();
		lalala.viewMoneyFromSales();
		System.out.println();
		
		//8. Some more use of the ordering system:
		lalala.sellInstrument("harp", 10);


		//9. Start of the monthly delivery service:
		mis.startMonthlyDelivery();
		
		//10. Create clients and start trade :)
		Client c1 = new Client(lalala);
		Client c2 = new Client(lalala);
		Client c3 = new Client(lalala);
		Client c4 = new Client(lalala);
		Client c5 = new Client(lalala);
		c1.start();
		c2.start();
		c3.start();
		c4.start();
		c5.start();

		
		
	}

}
