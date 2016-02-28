package library;

import java.time.LocalDateTime;

public class LogEntry {

	//Fields:
	private LocalDateTime dateTaken;
	private LocalDateTime dueDate;
	private LocalDateTime returnDate;
	private volatile boolean isReturned;
	private double tax;
	private double penalty;
	private int lentTime;
	private double moneyToPay;
	private User user;
	

	//Constructor:
	public LogEntry(int lendTime, double tax) {
		this.dateTaken = LocalDateTime.now();
		this.dueDate = this.dateTaken.plusSeconds(lendTime);
		this.tax = tax;
		this.moneyToPay = this.tax;
	}
	
	
	//Getters and setters:
	protected LocalDateTime getDateTaken() {
		return dateTaken;
	}
	protected void setDateTaken(LocalDateTime dateTaken) {
		this.dateTaken = dateTaken;
	}
	protected LocalDateTime getDueDate() {
		return dueDate;
	}
	protected void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}
	protected LocalDateTime getReturnDate() {
		return returnDate;
	}
	protected void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}
	protected synchronized double getTax() {
		return tax;
	}
	protected void setTax(double tax) {
		this.tax = tax;
	}
	protected synchronized double getPenalty() {
		return penalty;
	}
	protected void setPenalty(double penalty) {
		this.penalty = penalty;
	}
	protected synchronized boolean isReturned() {
		return isReturned;
	}
	protected synchronized void setReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}
	
	
	protected int getLentTime() {
		return lentTime;
	}


	protected void setLentTime(int lentTime) {
		this.lentTime = lentTime;
	}


	protected synchronized double getMoneyToPay() {
		return moneyToPay;
	}


	protected void setUser(User user) {
		this.user = user;
	}


	protected User getUser() {
		return user;
	}


	//Methods:
	public synchronized void addPenalty() {
		this.penalty+= this.tax;
	}
	
	public synchronized void setFinalTax() {
		this.moneyToPay = this.tax + this.penalty;
	}
	
	
	
}
