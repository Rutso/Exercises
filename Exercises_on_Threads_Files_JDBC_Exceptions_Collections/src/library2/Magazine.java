package library2;

import java.time.LocalDateTime;

public class Magazine extends Reading {

	
	//Fields:
	private static final String TYPE = "Magazine";
	private int issue;
	private LocalDateTime publishingDate;
	
	
	//Constructor:
	public Magazine(String title, String publisher, String theme, int issue) {
		super(title, publisher, theme);
		this.type = Magazine.TYPE;
		this.issue = issue;
		this.publishingDate = LocalDateTime.now();
	}

	
	//Overriden methods:
	@Override
	protected int getTakeTime() {
		return 0;
	}
	@Override
	protected double getTax() {
		return 0;
	}
	@Override
	protected void info() {
		System.out.println(this.theme + " - " + this.title + " - " + this.issue + " - " + this.publishingDate + " - " + this.publisher);
		
	}
	@Override
	public int compareTo(Reading o) {
		Magazine mag = (Magazine) o;
		if (this.theme.compareToIgnoreCase(o.theme) > 0) {
			return 1;
		} else {
			if (this.theme.compareToIgnoreCase(o.theme) < 0) {
				return -1;
			} else {
				if (this.theme.compareToIgnoreCase(o.theme) == 0) {
					if (this.title.compareToIgnoreCase(mag.title) > 0) {
						return 1;
					} else {
						if (this.title.compareToIgnoreCase(mag.title) < 0) {
							return -1;
						} else {
							if (this.issue > mag.issue) {
								return 1;
							} else {
								if(this.issue < mag.issue) {
									return - 1;
								} else {
									return 0;
								}
							}
						}
					}
				}
			}
		}
		return 0;
	}
	
}
