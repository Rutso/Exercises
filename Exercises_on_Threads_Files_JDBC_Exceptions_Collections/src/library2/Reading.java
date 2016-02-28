package library2;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Reading implements Comparable<Reading> {

	
	//Inner class:
	class LogEntry {
		
		//Fields:
		private Reading reading;
		private LocalDateTime takeDate;
		private LocalDateTime dueDate;
		private LocalDateTime returnDate;
		private int takeTime;
		private double tax;
		private double compensation;
		private double finalTax;
		private Client client;
		
		//Constructor:
		private LogEntry(Client client, int takeTime, double tax, Reading reading) {
			this.reading = reading;
			this.client = client;
			this.takeTime = takeTime;
			this.tax = tax;
			this.compensation = 0;
			this.finalTax = this.tax;
			this.takeDate = LocalDateTime.now();
			this.dueDate = this.takeDate.plusSeconds(this.takeTime);
		}
		
		//Getters:
		protected LocalDateTime getTakeDate() {
			return takeDate;
		}
		protected LocalDateTime getDueDate() {
			return dueDate;
		}
		protected LocalDateTime getReturnDate() {
			return returnDate;
		}
		protected double getTax() {
			return tax;
		}
		protected double getCompensation() {
			return compensation;
		}
		protected double getFinalTax() {
			return finalTax;
		}
		protected Client getClient() {
			return client;
		}
		protected int getTakeTime() {
			return takeTime;
		}
		protected Reading getReading() {
			return reading;
		}

		//Setters:
		protected void setReturnDate(LocalDateTime returnDate) {
			this.returnDate = returnDate;
		}
		protected void increaseCompensation() {
			this.compensation+= this.tax*0.01;
		}
		protected void setFinalTax() {
			this.finalTax = this.tax + this.compensation;
		}
				
	}
	
	
	//Fields:
	protected String type;
	protected String title;
	protected String publisher;
	protected String theme;
	protected ArrayList<LogEntry> history;
	protected volatile boolean isTaken;
		
	
	//Constructor:
	public Reading(String title, String publisher, String theme) {
		this.title = title;
		this.publisher = publisher;
		this.theme = theme;
		this.history = new ArrayList<LogEntry>();
		this.isTaken = false;
				
	}

	
	//Getters:
	protected String getType() {
		return type;
	}
	protected String getTitle() {
		return title;
	}
	protected String getPublisher() {
		return publisher;
	}
	protected String getTheme() {
		return theme;
	}
	protected ArrayList<LogEntry> getHistory() {
		return history;
	}
	protected LogEntry getLastLogEntry() {
		return this.history.get(this.history.size()-1);
	}
	protected boolean isTaken() {
		return isTaken;
	}
	protected void setStatus(boolean isTaken) {
		this.isTaken = isTaken;
	}

	
	//Methods:
	protected synchronized void take(Client client) throws CantTakeHomeException {
		int takeTime = getTakeTime();
		if (takeTime == 0) {
			
				throw new CantTakeHomeException("Dear " + client.getClientName() + ", you can read " + this.type + "s only inside the library.");
	
		}
		double tax = getTax();
		setStatus(true);
		addLogEntry(client, takeTime, tax);
	}
	private void addLogEntry(Client client, int takeTime, double tax) {
		LogEntry le = new LogEntry(client, takeTime, tax, this);
		this.history.add(le);
	};
	
		
	//Abstract methods:
	protected abstract int getTakeTime();
	protected abstract double getTax();
	protected abstract void info();
	
	
}
