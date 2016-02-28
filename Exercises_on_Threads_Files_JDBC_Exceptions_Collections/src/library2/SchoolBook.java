package library2;

public class SchoolBook extends Reading {

	//Fields:
	private static final String TYPE = "School Book";
	private static final int TAKE_TIME = 150;
	private static final double TAKE_TAX = 3;
	private String author;

	
	//Constructor:
	public SchoolBook(String title, String publisher, String theme, String author) {
		super(title, publisher, theme);
		this.type = SchoolBook.TYPE;
		this.author = author;
	}

	
	//Overriden methods:
	@Override
	protected int getTakeTime() {
		return SchoolBook.TAKE_TIME;
	}
	@Override
	protected double getTax() {
		return SchoolBook.TAKE_TAX;
	}
	@Override
	protected void info() {
		System.out.println(this.theme + " - " + this.title + " - " + this.author + " - " + this.publisher);
		
	}
	@Override
	public int compareTo(Reading o) {
		SchoolBook sb = (SchoolBook) o;
		if (this.theme.compareToIgnoreCase(sb.theme) > 0) {
			return 1;
		} else {
			if (this.theme.compareToIgnoreCase(sb.theme) < 0) {
				return -1;
			} else {
				return this.title.compareToIgnoreCase(sb.title);
			}
		}
	}
	
	
}
