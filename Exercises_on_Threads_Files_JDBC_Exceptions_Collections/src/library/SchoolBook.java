package library;

import java.util.ArrayList;

public class SchoolBook extends Reading {

	//Fields:
	private String author;
	private String publisher;
	private String type;

	
	//Constructor:
	public SchoolBook(String name, String author, String publisher, String type) {
		super.name = name;
		this.author = author;
		this.publisher = publisher;
		this.type = type;
		super.history = new ArrayList<LogEntry>();
		super.isLent = false;
	}

	
	//Getters:
	protected String getName() {
		return name;
	}

	protected String getAuthor() {
		return author;
	}

	protected String getPublisher() {
		return publisher;
	}

	protected String getType() {
		return type;
	}
	
	protected boolean isLent() {
		return isLent;
	}
	protected void setLent(boolean isLent) {
		this.isLent = isLent;
	}


	protected ArrayList<LogEntry> getHistory() {
		return history;
	}


	//Methods:
	public void addLog(LogEntry newEntry) {
		this.history.add(newEntry);
	}
	
	
	public void view() {
		System.out.println(this.type + "; " + this.name + "; " + this.author + "; " + this.publisher);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SchoolBook) {
			SchoolBook temp = (SchoolBook) obj;
			if(temp.name.equalsIgnoreCase(this.name)) {
				return true;
			}
		}

		return false;
	}


	@Override
	protected String info() {
		
		return this.type + "; " + this.name + "; " + this.author + "; " + this.publisher;
	}
	
}
