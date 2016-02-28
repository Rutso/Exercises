package library;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Book extends Reading {

	//Fields:
	
	private String type;
	private String author;
	private LocalDateTime editionDate;
	
	
	//Constructor:
	public Book(String name, String type, String author) {
		super.name = name;
		this.type = type;
		this.author = author;
		this.editionDate = LocalDateTime.now();
		super.history = new ArrayList<LogEntry>();
		super.isLent = false;
	}

	
	
	//Getters:
	protected String getName() {
		return name;
	}

	protected String getType() {
		return type;
	}

	protected String getAuthor() {
		return author;
	}

	protected LocalDateTime getEditionDate() {
		return editionDate;
	}

	protected ArrayList<LogEntry> getHistory() {
		return history;
	}
	
	protected boolean isLent() {
		return isLent;
	}
	protected void setLent(boolean isLent) {
		this.isLent = isLent;
	}



	//Methods:
	public void addLog(LogEntry newEntry) {
		this.history.add(newEntry);
	}
	
	public void view() {
		System.out.println(this.type + "; " +  this.editionDate + "; " + this.name + "; " + this.author );
	}



	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Book) {
			Book temp = (Book) obj;
			if(temp.name.equalsIgnoreCase(this.name)) {
				return true;
			}
		}

		return false;
	}



	@Override
	protected String info() {
		
		return this.type + "; " +  this.editionDate + "; " + this.name + "; " + this.author ;
	}
	
	
	
	
}
