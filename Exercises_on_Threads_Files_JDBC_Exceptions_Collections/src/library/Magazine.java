package library;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Magazine extends Reading {

	
	//Fields:
	private int issue;
	private String publisher;
	private LocalDateTime editionDate;
	private String type;
	
	
	//Constructor:
	public Magazine(String name, int issue, String publisher, String type) {
		super.name = name;
		this.issue = issue;
		this.publisher = publisher;
		this.editionDate = LocalDateTime.now();
		this.type = type;
	}


	
	
	//Getters:
	protected String getName() {
		return name;
	}


	protected int getIssue() {
		return issue;
	}


	protected String getPublisher() {
		return publisher;
	}


	protected LocalDateTime getEditionDate() {
		return editionDate;
	}


	protected String getType() {
		return type;
	}
	
	
	//Methods:
	public void view() {
		System.out.println(this.name + "; " +  this.issue + "; " + this.editionDate + "; " + this.publisher + "; " + this.type);
	}




	@Override
	protected ArrayList<LogEntry> getHistory() {
		
		return null;
	}




	@Override
	protected boolean isLent() {
		
		return false;
	}




	@Override
	protected String info() {
		
		return this.name + "; " +  this.issue + "; " + this.editionDate + "; " + this.publisher + "; " + this.type;
	}
	
	
	
}
