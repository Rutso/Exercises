package library2;

import java.time.LocalDateTime;

public class Book extends Reading {

	
	//Fields:
	private static final String TYPE = "Book";
	private static final int TAKE_TIME = 300;
	private static final double TAKE_TAX = 2;
	private String author;
	private LocalDateTime publishingDate;
	
	
	//Constructor:
	public Book(String title, String publisher, String theme, String author) {
		super(title, publisher, theme);
		this.type = Book.TYPE;
		this.author = author;
		this.publishingDate = LocalDateTime.now();
		
	}

	
	//Overriden methods:
	@Override
	protected int getTakeTime() {
		return Book.TAKE_TIME;
	}
	@Override
	protected double getTax() {
		return Book.TAKE_TAX;
	}
	@Override
	protected void info() {
		System.out.println(this.theme + " - " + this.publishingDate + " - " + this.title + " - " + this.author + " - " + this.publisher);
		
	}
	@Override
	public int compareTo(Reading o) {
		Book book = (Book) o;
		if (this.theme.compareToIgnoreCase(book.theme) > 0) {
			return 1;
		} else {
			if (this.theme.compareToIgnoreCase(book.theme) < 0) {
				return 1;
			} else {
				if (this.theme.compareToIgnoreCase(book.theme) == 0) {
					return -this.publishingDate.compareTo(book.publishingDate);
				}
			}
		}
		return 0;
	}

	
}
