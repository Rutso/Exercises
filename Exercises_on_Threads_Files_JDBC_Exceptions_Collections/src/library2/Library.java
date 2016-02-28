package library2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import library2.Reading.LogEntry;

public class Library {

	
	//Inner class:
	class Taken extends Thread {
		
		//Fields:
		private LogEntry currentLog;

		//Constructor:
		private Taken(LogEntry currentLog) {
			this.currentLog = currentLog;
		}
		
		//Overriden methods:
		@Override
		public void run() {

			int sleepTime = this.currentLog.getTakeTime()*100; //for the purpose of the demo the time periods are shortened
			Reading r = this.currentLog.getReading();
			Client c = this.currentLog.getClient();
			System.out.println(c.getClientName() + " took " + r.getTitle() + " from " + name + ".");

			try {
				sleep(sleepTime);
			} catch (InterruptedException e) {
				System.out.println(r.getTitle() + " was returned to library before the due date.");
				return; //when the book is returned, the log entry is modified and interrupt() is called to stop the thread!
			}
			
			while(true) {
				
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					System.out.println(r.getTitle() + " was returned in the last moment before the next increase of the compensation.");
					return; //When the book is returned, the log entry is modified and interrupt() is called to stop the thread!
				}
				
				this.currentLog.increaseCompensation();
				this.currentLog.setFinalTax();
				System.out.println(c.getClientName() + " is delaying the return of " + r.getTitle() + " and must pay " + this.currentLog.getFinalTax() + "lv.");
				
			}
			
			
			
		}
				
	}
	
	
	//Fields:
	private int revisionNumber;
	public static final String[] READING_TYPES = {"Book", "School Book", "Magazine"};
	private String name;
	private HashMap<String, HashMap<String, ArrayList<Reading>>> catalog;
	private LinkedList<Taken> takenList;

	
	
	//Constructor:
	public Library(String name) {
		this.name = name;
		this.catalog = new HashMap<String, HashMap<String, ArrayList<Reading>>>();
		this.takenList = new LinkedList<Taken>();
		this.revisionNumber = 0;
	}
		
	
	//Library methods:
	public synchronized void addReading(Reading newReading) {
		String type = newReading.getType();
		String theme = newReading.getTheme();
		if(this.catalog.containsKey(type)) {
			
			if (this.catalog.get(type).containsKey(theme)) {
				this.catalog.get(type).get(theme).add(newReading);
			} else {
				this.catalog.get(type).put(theme, new ArrayList<Reading>());
				this.catalog.get(type).get(theme).add(newReading);
			}
			
		} else {
			this.catalog.put(type, new HashMap<String, ArrayList<Reading>>());
			this.catalog.get(type).put(theme, new ArrayList<Reading>());
			this.catalog.get(type).get(theme).add(newReading);
		}
	}
	
	
	//Client methods:
	public synchronized void takeReading(String readingTitle, Client client) throws CantTakeHomeException {
		for (Map.Entry<String, HashMap<String, ArrayList<Reading>>> type : this.catalog.entrySet()) {
			for (Map.Entry<String, ArrayList<Reading>> theme : type.getValue().entrySet()) {
				for (Reading r : theme.getValue()) {
					if (r.getTitle().equalsIgnoreCase(readingTitle)) { //If the library has a reading with the same name
						if (r.isTaken()) {
							try {
								throw new TakenException("Dear " + client.getClientName() + ", " + readingTitle + " is taken at the moment.");
							} catch (TakenException e) {
								System.out.println(e.getMessage());
								return;
							}
						} else {
							r.take(client);		//client takes the book
							Taken t = new Taken(r.getLastLogEntry()); 
							takenList.add(t);	//the library adds another element to it's takenList
							t.start();		//The count starts
							return;
						}
					}
				}
			}
		}
		
		try {
			throw new NoSuchReadingException("Dear " + client.getClientName() + ", " + this.name + " doesn't have " + readingTitle + ".");
		} catch (NoSuchReadingException e) {
			System.out.println(e.getMessage());
		}
		
		
		
	}
	
	public synchronized void returnReading(String readingName, Client client) {
		for (Taken t : this.takenList) {
			if (t.currentLog.getClient().equals(client) && t.currentLog.getReading().getTitle().equalsIgnoreCase(readingName)) {
				t.currentLog.setReturnDate(LocalDateTime.now());
				Reading r = t.currentLog.getReading();
				r.setStatus(false);
				LogEntry last = r.getLastLogEntry();
				t.interrupt(); //stop calculating compensations and kill the thread
				this.takenList.remove(t);
				System.out.println(client.getClientName() + " returned " + readingName + " to " + this.name + " and must pay " + last.getFinalTax() + "lv. Compensation : " + last.getCompensation() + "lv.");
				return;
			} else {
				try {
					throw new WrongLibraryException("Dear " + client.getClientName() + ", this copy of " + readingName + " is not from " + this.name + ".");
				} catch (WrongLibraryException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
	
	public void viewCatalog(String readingType) {
		if (hasSuchReadingType(readingType)) {
			ArrayList<Reading> list = getListOfReadings(readingType);
			Collections.sort(list);
			System.out.println(readingType.toUpperCase() + "S:");
			viewList(list);
			System.out.println();
		} else {
			return;
		}
			
	}
		
		private ArrayList<Reading> getListOfReadings(String readingType) {
		ArrayList<Reading> list = new ArrayList<Reading>();
		for (Map.Entry<String, ArrayList<Reading>> theme : this.catalog.get(readingType).entrySet()) {
			list.addAll(theme.getValue());
		}
		return list;
	}
		private void viewList(ArrayList<Reading> listOfReadings) {
		for (Reading r : listOfReadings) {
			r.info();
		}
	}
		private boolean hasSuchReadingType(String readingType) {
		if (!(this.catalog.containsKey(readingType))) {
			try {
				throw new NoSuchReadingTypeException("Dear client, " + this.name + " doesn't have " + readingType + "s.");
			} catch (NoSuchReadingTypeException e) {
				System.out.println(e.getMessage());
				return false;
			}
		} else {
			return true;
		}
	}
	
		
	//Revision methods:
	public synchronized int countAvailableReadings() {
		int available = 0;
		for (Map.Entry<String, HashMap<String, ArrayList<Reading>>> type : this.catalog.entrySet()) {
			for (Map.Entry<String, ArrayList<Reading>> theme : type.getValue().entrySet()) {
				for (Reading r : theme.getValue()) {
					if (!(r.isTaken())) {
						available++;
					}
				}
			}
		}
		
		System.out.println("At this moment there are " + available + " available readings in " + this.name + ".");
		return available;
	}
	
	public synchronized void logTakenReadings() {
		Collections.sort(this.takenList, new Comparator<Taken>(){
			
			
			@Override
			public int compare(Taken t1, Taken t2) {
			
				return t1.currentLog.getTakeDate().compareTo(t2.currentLog.getTakeDate());
			}
			
		});
		
		
		File f = new File("revision" + this.revisionNumber + ".txt");
			this.revisionNumber++; 
			if (!(f.exists())) {
				try {
					f.createNewFile();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		
			
		try (PrintStream ps = new PrintStream(f)){
			ps.println("Total number of taken Readings from " + this.name + " as for " + LocalDateTime.now() + " is " + this.takenList.size() + ".");
			ps.println("Follows the list by date taken:");
			for (Taken t : this.takenList) {
				ps.println(t.currentLog.getTakeDate() + " - " + t.currentLog.getReading().getTitle());
			}
					
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
		
		
	}
	
	public synchronized int logDueReadings() {
		return 0;
	}
	
	
	
	
}
