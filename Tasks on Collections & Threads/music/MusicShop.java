package music;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


public class MusicShop {

	
	//Fields:
	private final static Integer MONTHLY_DELIVERY_QUANTITY = 5;
	private final static Integer MINIMUM_QUANTITY = 3;
	private String name;
	private TreeMap<String, TreeMap<MusicalInstrument, Integer>> catalog; //catalog with instruments by category
	private TreeMap<String, TreeMap<MusicalInstrument, Integer>> salesLog; //keeps count of the sold instruments from different categories
	private double cash; //here come the money from sales
	private MusicalInstrumentSupplier supplier;
	//Constructor:
	public MusicShop(String name) {
		this.name = name;
		this.catalog = new TreeMap<String, TreeMap<MusicalInstrument, Integer>>();
		this.salesLog = new TreeMap<String, TreeMap<MusicalInstrument, Integer>>();
		this.cash = 0;

	}
	
	//Setter:
	public void setSupplier(MusicalInstrumentSupplier supplier) {
		this.supplier = supplier;
	}
	
	
	
	//************************SELL*****************************//
	//sell instrument:
	public synchronized void sellInstrument(String name, int quantity) {
		for (Map.Entry<String, TreeMap<MusicalInstrument, Integer>> category : this.catalog.entrySet()) {
			for (Map.Entry<MusicalInstrument, Integer> instrument : category.getValue().entrySet()) {
				if (instrument.getKey().getName().equalsIgnoreCase(name)) {
					if (instrument.getValue() >= quantity) {
						System.out.println(this.name + " just sold " + quantity + " " + name + "s. Total cost: " + (instrument.getKey().getPrice()*quantity) + " lv.");
						logSale(instrument.getKey(), quantity);
						this.cash+=(instrument.getKey().getPrice()*quantity);
						category.getValue().put(instrument.getKey(), (instrument.getValue() - quantity));
						return;
					} else {
						System.out.println(this.name + " doesn't have " + quantity + " " + name + "s on stock. It has only " + instrument.getValue() + " left.");
						System.out.println("	Another " + (quantity - instrument.getValue()) + " " + name + "s has been ordered to our supplier. They will be in our store in " + makeOrder(name, (quantity - instrument.getValue()))/1000 + " days.");
						return;
					}
				}
			}
		}
		System.out.println(this.name + " doesn't offer " + name + "s.");
	}
	//add the sale to the sale log:
	private void logSale(MusicalInstrument i, int quantity) {
		if (this.salesLog.containsKey(i.getType())) { //if we have ever sold a product from this category
			TreeMap<MusicalInstrument, Integer> category = this.salesLog.get(i.getType());
			if (category.containsKey(i)) { //if we have ever sold this particular instrument
				category.put(i, (category.get(i) + quantity)); //add to quantity
			} else {
				category.put(i, quantity); //add a new entry for this instrument
			}
		
		} else {
			this.salesLog.put(i.getType(), new TreeMap<MusicalInstrument, Integer>()); //add a new category and create log entry for this instrument
			this.salesLog.get(i.getType()).put(i, quantity);
				
		}
	}
	private Integer makeOrder(String instrumentName, Integer quantity) {
		this.supplier.deliverOrder(instrumentName, quantity);
		return this.supplier.returnDeliveryTime(instrumentName);
		
	}
	
	//*************************ADD*****************************//
	//add new instrument to the catalog:
	public void addNewInstrumentToCatalog(MusicalInstrument i, int quantity) {
		if (this.catalog.containsKey(i.getType())) { //if we have such product category
			TreeMap<MusicalInstrument, Integer> category = this.catalog.get(i.getType());
			if (category.containsKey(i)) { //if we have such particular instrument already
				category.put(i, (category.get(i.getName()) + quantity)); //just add to quantity
			} else {
				category.put(i, quantity); //add a new entry for this instrument
				this.salesLog.get(i.getType()).put(i, 0);
			}
		
		} else {
			this.catalog.put(i.getType(), new TreeMap<MusicalInstrument, Integer>()); //add a new category and create entry for this instrument
			this.catalog.get(i.getType()).put(i, quantity);
			this.salesLog.put(i.getType(), new TreeMap<MusicalInstrument, Integer>()); //add log record with 0 value for sales
			this.salesLog.get(i.getType()).put(i, 0);	
		}
	}
	//supply new quantities of certain instrument:
	public synchronized void supplyInstrument(String name, int quantity) {
		for (Map.Entry<String, TreeMap<MusicalInstrument, Integer>> category : this.catalog.entrySet()) {
			for (Map.Entry<MusicalInstrument, Integer> instrument : category.getValue().entrySet()) {
				if (instrument.getKey().getName().equalsIgnoreCase(name)) {
					category.getValue().put(instrument.getKey(), (instrument.getValue() + quantity));
					System.out.println(quantity + " " + name + "s supplied to " + this.name + ".");
					return;
				}
			}
		}
		System.out.println("Can't take the stock. " + this.name + " doesn't sale " + name + "s.");
	}
	//get list of the depleted products:
	private ArrayList<String> getListOfDepletedProducts() {
		ArrayList<String> depletedProducts = new ArrayList<String>();
		for (Map.Entry<String, TreeMap<MusicalInstrument, Integer>> category : this.catalog.entrySet()) {
			for (Map.Entry<MusicalInstrument, Integer> instrument : category.getValue().entrySet()) {
				if (instrument.getValue() > MusicShop.MINIMUM_QUANTITY) {
					depletedProducts.add(instrument.getKey().getName());
				}
			}
		}
		return depletedProducts;
	}
	//supplier makes monthly delivery of the depleted products:
	public synchronized void makeMonthlySupplies() {
		ArrayList<String> depletedProducts = getListOfDepletedProducts();
		for (String product : depletedProducts) {
			supplyInstrument(product, MusicShop.MONTHLY_DELIVERY_QUANTITY);
			System.out.println("	Monthly Delivery:");
		}
	}
	
	
	//*************************VIEWS****************************//
	private ArrayList<MusicalInstrument> getListOfProducts() {
		ArrayList<MusicalInstrument> list = new ArrayList<MusicalInstrument>();
		for (Map.Entry<String, TreeMap<MusicalInstrument, Integer>> category : this.catalog.entrySet()) {
			for (Map.Entry<MusicalInstrument, Integer> instrument : category.getValue().entrySet()) {
				list.add(instrument.getKey());
			}
		}
		return list;
	}
	//view products in catalog by type:
	public ArrayList<MusicalInstrument> viewByType() { //has problems!
		ArrayList<MusicalInstrument> list = getListOfProducts();
		list.sort(new Comparator<MusicalInstrument>() {

			@Override
			public int compare(MusicalInstrument o1, MusicalInstrument o2) {
				return o1.getType().compareToIgnoreCase(o2.getType());
			}
	
		});
		
		for(MusicalInstrument i : list) {
			i.viewInstrumentInfo();
		}
		return list;
	}
	//view products in catalog in alphabetical order:
	public ArrayList<MusicalInstrument> viewByAlphabeticalOrder() {
		ArrayList<MusicalInstrument> list = getListOfProducts();
		list.sort(new Comparator<MusicalInstrument>() {

			@Override
			public int compare(MusicalInstrument o1, MusicalInstrument o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
	
		});
		
		for(MusicalInstrument i : list) {
			i.viewInstrumentInfo();
		}
		return list;
	}
	//view products in catalog by price ascending:
	public ArrayList<MusicalInstrument> viewByPriceAscending() {
			ArrayList<MusicalInstrument> list = getListOfProducts();
			list.sort(new Comparator<MusicalInstrument>() {

				@Override
				public int compare(MusicalInstrument o1, MusicalInstrument o2) {
					if (o1.getPrice() > o2.getPrice()) {
						return 1;
					} else {
						if (o1.getPrice() < o2.getPrice()) {
							return -1;
						}
					}
					
					return 0;
				}
		
			});
			
			for(MusicalInstrument i : list) {
				i.viewInstrumentInfo();
			}
			return list;
		}
	//view products in catalog by price descending:
	public ArrayList<MusicalInstrument> viewByPriceDescending() {
				ArrayList<MusicalInstrument> list = getListOfProducts();
				list.sort(new Comparator<MusicalInstrument>() {

					@Override
					public int compare(MusicalInstrument o1, MusicalInstrument o2) {
						if (o1.getPrice() > o2.getPrice()) {
							return -1;
						} else {
							if (o1.getPrice() < o2.getPrice()) {
								return 1;
							}
						}
						
						return 0;
					}
			
				});
				
				for(MusicalInstrument i : list) {
					i.viewInstrumentInfo();
				}
				return list;
			}
	//view only the available products:
	public ArrayList<MusicalInstrument> viewTheAvailableProducts() {
		ArrayList<MusicalInstrument> list = new ArrayList<MusicalInstrument>();
		for (Map.Entry<String , TreeMap<MusicalInstrument, Integer>> category : this.catalog.entrySet()) {
			for (Map.Entry<MusicalInstrument, Integer> instrument : category.getValue().entrySet()) {
				if (instrument.getValue() > 0) {
					list.add(instrument.getKey());
				}
				
			}
		}
		for(MusicalInstrument i : list) {
			i.viewInstrumentInfo();
		}
		return list;
	}
	
	
	//************************REPORTS****************************//
	private ArrayList<Entry<MusicalInstrument, Integer>> getListOfSales() {
		ArrayList<Entry<MusicalInstrument, Integer>> list = new ArrayList<Entry<MusicalInstrument, Integer>>();
		for (Map.Entry<String , TreeMap<MusicalInstrument, Integer>> category : this.salesLog.entrySet()) {
			for (Map.Entry<MusicalInstrument, Integer> instrument : category.getValue().entrySet()) {
				list.add(instrument);
			}
		}
		return list;
	}
	//view instruments by most sold:
	public ArrayList<Entry<MusicalInstrument, Integer>> viewByBestSeller() {
		ArrayList<Entry<MusicalInstrument, Integer>> list = getListOfSales();
		list.sort(new Comparator<Entry<MusicalInstrument, Integer>>(){

			@Override
			public int compare(Entry<MusicalInstrument, Integer> o1, Entry<MusicalInstrument, Integer> o2) {
				if (o1.getValue() > o2.getValue()) {
					return -1;
				} else {
					if (o1.getValue() < o2.getValue()) {
						return 1;
					}
				}
				return 0;
			}
			
		});
		
		for (Entry<MusicalInstrument, Integer> instrument : list) {
			instrument.getKey().viewInstrumentInfo();
			System.out.println("Sold items: " + instrument.getValue());
		}
		
		return list;
	}
	//view the money from sales:
	public double viewMoneyFromSales() {
			ArrayList<Entry<MusicalInstrument, Integer>> list = getListOfSales();
			double sum = 0;
			for(Entry<MusicalInstrument, Integer> instrument : list) {
				sum+= instrument.getKey().getPrice()*instrument.getValue();
			}
			
			
			System.out.println(this.name + "'s sales are " + sum + "lv. There are " + this.cash + "lv. in the safe.");
			
			return sum;
		}
	//view the money from sales:
	public MusicalInstrument viewTheBestSeller() {
			ArrayList<Entry<MusicalInstrument, Integer>> list = getListOfSales();
			Entry<MusicalInstrument, Integer> bestSeller = list.get(0);
			for (Entry<MusicalInstrument, Integer> instrument : list) {
				if (instrument.getValue() > bestSeller.getValue()) {
					bestSeller = instrument;
				}
			}
			MusicalInstrument theBestSelling = bestSeller.getKey();
			System.out.println("The best seller, with " + bestSeller.getValue() + " items sold is " + theBestSelling.getName() + " (of type: " + theBestSelling.getType() + ")"+ ", price - " + theBestSelling.getPrice() + " lv.");
			return theBestSelling;
	}
	//view the less selling instruments:
	public MusicalInstrument viewTheLessSeller() {
		ArrayList<Entry<MusicalInstrument, Integer>> list = getListOfSales();
		Entry<MusicalInstrument, Integer> lessSeller = list.get(0);
		for (Entry<MusicalInstrument, Integer> instrument : list) {
			if (instrument.getValue() < lessSeller.getValue()) {
				lessSeller = instrument;
			}
		}
		MusicalInstrument theLessSelling = lessSeller.getKey();
		System.out.println("The less seller, with " + lessSeller.getValue() + " items sold is " + theLessSelling.getName() + " (of type: " + theLessSelling.getType() + ")"+ ", price - " + theLessSelling.getPrice() + " lv.");
		return theLessSelling;
}
	//view the best selling category by items sold:
	public void viewBestSellingCategortByItems() {
		Entry<String, TreeMap<MusicalInstrument, Integer>> theBestSellingCategory = this.salesLog.firstEntry();
			Integer bestSales = 0;
		for (Map.Entry<MusicalInstrument, Integer> product : theBestSellingCategory.getValue().entrySet()) {
			bestSales+= product.getValue();
		}
		
		for (Map.Entry<String, TreeMap<MusicalInstrument, Integer>> category : this.salesLog.entrySet()) {
			Integer sales = 0;
			for (Map.Entry<MusicalInstrument, Integer> product : category.getValue().entrySet()) {
				sales+= product.getValue();
			}
			if (sales > bestSales) {
				theBestSellingCategory = category;
				bestSales = sales;
			}
		}
		
		System.out.println("The best selling category by items sold in " + this.name + " is " + theBestSellingCategory.getKey() + " with " + bestSales + " items sold.");
		for (Map.Entry<MusicalInstrument, Integer> product : theBestSellingCategory.getValue().entrySet()) {
			product.getKey().viewInstrumentInfo();
			System.out.println(product.getValue() + " items sold.");
		}
	
	}
	//view the best selling category by money from sales:
	public void viewBestSellingCategortByMoney() {
		Entry<String, TreeMap<MusicalInstrument, Integer>> theBestSellingCategory = this.salesLog.firstEntry();
			double bestSales = 0;
		for (Map.Entry<MusicalInstrument, Integer> product : theBestSellingCategory.getValue().entrySet()) {
			bestSales+= product.getValue()*product.getKey().getPrice();
		}
			
		for (Map.Entry<String, TreeMap<MusicalInstrument, Integer>> category : this.salesLog.entrySet()) {
			double sales = 0;
			for (Map.Entry<MusicalInstrument, Integer> product : category.getValue().entrySet()) {
				sales+= product.getValue()*product.getKey().getPrice();
			}
			if (sales > bestSales) {
				theBestSellingCategory = category;
				bestSales = sales;
			}
		}
		
		System.out.println("The best selling category by money in " + this.name + " is " + theBestSellingCategory.getKey() + " with sales for " + bestSales + " lv.");
		for (Map.Entry<MusicalInstrument, Integer> product : theBestSellingCategory.getValue().entrySet()) {
			product.getKey().viewInstrumentInfo();
			System.out.println("Generated sales for " + product.getValue()*product.getKey().getPrice() + " lv.");
		}
	
	}
	
	
}
