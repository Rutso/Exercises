package library;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Library {

	
	//Fields:
	public static final int BOOK_LENDING_TIME = 30;
	public static final int SCHOOLBOOK_LENDING_TIME = 15;
	public static final double BOOK_TAX = 2;
	public static final double SCHOOLBOOK_TAX = 3;
	private String name;
	private HashMap<String, ArrayList<Reading>> books;
	private HashMap<String, ArrayList<Reading>> magazines;
	private HashMap<String, ArrayList<Reading>> schoolBooks;
	private LinkedList<Taken> taken;
		
	
	//Constructor:
	public Library(String name) {
		this.name = name;
		this.books = new HashMap<String, ArrayList<Reading>>();
		this.magazines = new HashMap<String, ArrayList<Reading>>();
		this.schoolBooks = new HashMap<String, ArrayList<Reading>>();
		this.taken = new LinkedList<Taken>();
	}
	
	//Getters:
	protected String getName() {
		return name;
	}



	//Methods:
	//**************Management******************
	public void addReading(Reading newReading) {
		if (newReading instanceof Book) {
			Book temp = (Book) newReading;
			if (this.books.containsKey(temp.getType())) {
				this.books.get(temp.getType()).add(temp);
			} else {
				this.books.put(temp.getType(), new ArrayList<Reading>());
				this.books.get(temp.getType()).add(temp);
			}
			
		}
		if (newReading instanceof Magazine) {
			Magazine temp = (Magazine) newReading;
			if (this.magazines.containsKey(temp.getType())) {
				this.magazines.get(temp.getType()).add(temp);
			} else {
				this.magazines.put(temp.getType(), new ArrayList<Reading>());
				this.magazines.get(temp.getType()).add(temp);
			}
			
		}
		if (newReading instanceof SchoolBook) {
			SchoolBook temp = (SchoolBook) newReading;
			if (this.schoolBooks.containsKey(temp.getType())) {
				this.schoolBooks.get(temp.getType()).add(temp);
			} else {
				this.schoolBooks.put(temp.getType(), new ArrayList<Reading>());
				this.schoolBooks.get(temp.getType()).add(temp);
			}
			
		}
		
		
	}
	
	public void lendReading(Reading reading, User user) {
		if (reading instanceof Magazine) {
			try {
				throw new NoLendException("Please excuse us, but magazines can't be taken home.");
			} catch (NoLendException e) {
				System.out.println(e.getMessage());
				return;
			}
		}
		if (reading instanceof Book) {
			Book temp = (Book) reading;
			if (this.books.containsKey(temp.getType())) {
				if (this.books.get(temp.getType()).contains(reading)) {
					if (temp.isLent()) {
						System.out.println(temp.getName() + " is lent at the moment.");
					} else {
						for (Reading book : this.books.get(temp.getType())) {
							Book theBook = (Book) book;
							if (theBook.getName().equalsIgnoreCase(temp.getName())) {
								theBook.setLent(true);
								LogEntry le = new LogEntry(Library.BOOK_LENDING_TIME, Library.BOOK_TAX);
								le.setLentTime(Library.BOOK_LENDING_TIME);
								le.setUser(user);
								theBook.addLog(le);
								Taken takenBook = new Taken(theBook);
								this.taken.add(takenBook);
								takenBook.start();
							}
						}
					}
				} else {
					System.out.println(this.name + " doesn't have the book " + temp.getName() + ".");

				}
			}
		}
		if (reading instanceof SchoolBook) {
			SchoolBook temp = (SchoolBook) reading;
			if (this.schoolBooks.containsKey(temp.getType())) {
				if (this.schoolBooks.get(temp.getType()).contains(reading)) {
					if (temp.isLent()) {
						System.out.println(temp.getName() + " is lent at the moment.");
					} else {
						for (Reading schoolBook : this.schoolBooks.get(temp.getType())) {
							SchoolBook theSchoolBook = (SchoolBook) schoolBook;
							if (theSchoolBook.getName().equalsIgnoreCase(temp.getName())) {
								theSchoolBook.setLent(true);
								LogEntry le = new LogEntry(Library.SCHOOLBOOK_LENDING_TIME, Library.SCHOOLBOOK_TAX);
								le.setLentTime(Library.SCHOOLBOOK_LENDING_TIME);
								le.setUser(user);
								theSchoolBook.addLog(le);
								Taken takenSchoolBook = new Taken(theSchoolBook);
								this.taken.add(takenSchoolBook);
								takenSchoolBook.start();
							}
						}
					}
				} else {
					System.out.println(this.name + " doesn't have the schoolbook " + temp.getName() + ".");

				}
			}
		}
	}
	
	public void returnReading(Reading reading) {
		if (reading instanceof Book) {
			Book temp = (Book) reading;
			if(this.books.containsKey(temp.getType())) {
				if (this.books.get(temp.getType()).contains(reading)) {
					temp.setLent(false);
				}
			}
		}
	}
	
	
	
	//****************Catalogs*****************
	public void viewBooks() {
		ArrayList<Book> allBooks = new ArrayList<Book>();
		for (Map.Entry<String, ArrayList<Reading>> type : this.books.entrySet()) {
			for(Reading book : type.getValue()) {
				Book temp = (Book) book;
				allBooks.add(temp);
			}
		}
		
		allBooks.sort(new Comparator<Book>() {

			@Override
			public int compare(Book b1, Book b2) {
				
				if (b1.getType().compareToIgnoreCase(b2.getType()) > 0) {
					return 1;
				} else {
					if (b1.getType().compareToIgnoreCase(b2.getType()) < 0) {
						return -1;
					} else {
						return -b1.getEditionDate().compareTo(b2.getEditionDate());
					}
				}

			
			}
			
		});
		
		for (Book book : allBooks) {
			book.view();
		}
		System.out.println();
	}
	
	public void viewMagazines() {
		ArrayList<Magazine> allMagazines = new ArrayList<Magazine>();
		for (Map.Entry<String, ArrayList<Reading>> type : this.magazines.entrySet()) {
			for(Reading magazine : type.getValue()) {
				Magazine temp = (Magazine) magazine;
				allMagazines.add(temp);
			}
		}
		
		allMagazines.sort(new Comparator<Magazine>() {

			@Override
			public int compare(Magazine m1, Magazine m2) {

				if(m1.getName().compareToIgnoreCase(m2.getName()) > 0) {
					return 1;
				} else {
					if (m1.getName().compareToIgnoreCase(m2.getName()) < 0) {
						return -1;
					} else {
						if (m1.getIssue() > m2.getIssue()) {
							return 1;
						} else {
							if (m1.getIssue() < m2.getIssue()) {
								return -1;
							}
						}
					}
				}
				
				
				return 0;
			}

			
			
		});
		
		for (Magazine magazine : allMagazines) {
			magazine.view();
		}
		System.out.println();
		
	}
	
	public void viewSchoolBooks() {
		ArrayList<SchoolBook> allSchoolBooks = new ArrayList<SchoolBook>();
		for (Map.Entry<String, ArrayList<Reading>> type : this.schoolBooks.entrySet()) {
			for(Reading schoolBook : type.getValue()) {
				SchoolBook temp = (SchoolBook) schoolBook;
				allSchoolBooks.add(temp);
			}
		}
		
		allSchoolBooks.sort(new Comparator<SchoolBook>() {

			@Override
			public int compare(SchoolBook sb1, SchoolBook sb2) {

				if (sb1.getType().compareToIgnoreCase(sb2.getType()) > 0) {
					return 1;
				} else {
					if (sb1.getType().compareToIgnoreCase(sb2.getType()) < 0) {
						return -1;
					} else {
						return sb1.getName().compareToIgnoreCase(sb2.getName());
					}
				}
				
			}

			
		});
		
		for (SchoolBook schoolBook : allSchoolBooks) {
			schoolBook.view();
		}
		System.out.println();
		
	}
	
	
		
	
	
	
	//**************Revision*******************
	public int getNumberOfReadings() {
		int numberOfReadings = 0;
		for (Map.Entry<String, ArrayList<Reading>> type : this.books.entrySet()) {
			for (Reading book : type.getValue()) {
				Book temp = (Book) book;
				if (!(temp.isLent())) {
					numberOfReadings++;
				}
			}
		}
		for (Map.Entry<String, ArrayList<Reading>> type : this.schoolBooks.entrySet()) {
			for (Reading schoolBook : type.getValue()) {
				SchoolBook temp = (SchoolBook) schoolBook;
				if (!(temp.isLent())) {
					numberOfReadings++;
				}
			}
		}
		for (Map.Entry<String, ArrayList<Reading>> type : this.magazines.entrySet()) {
				numberOfReadings+=type.getValue().size();
		}
			
		return numberOfReadings;
	}
	
	public void getTakenReadings() throws FileNotFoundException, UnsupportedEncodingException {
		ArrayList<Taken> notReturned = new ArrayList<Taken>();
		for (Taken taken : this.taken) {
			if (taken.getReading().isLent) {
				notReturned.add(taken);
			}
		}
		
		notReturned.sort(new Comparator<Taken>() {

			@Override
			public int compare(Taken t1, Taken t2) {

				LocalDateTime time1 = t1.getReading().getHistory().get(t1.getReading().getHistory().size()-1).getDateTaken();
				LocalDateTime time2 = t2.getReading().getHistory().get(t2.getReading().getHistory().size()-1).getDateTaken();
				
				
				
				return time1.compareTo(time2);
			}
			
		});
		
		PrintWriter writer = new PrintWriter("TakenReadings.txt", "UTF-8");
		for (Taken taken : notReturned) {
			writer.println(taken.getReading().info());
			writer.println("	Date taken: " + taken.getReading().getHistory().get(taken.getReading().getHistory().size()-1).getDateTaken());
			
		}
		
		writer.println("Number of not returned books: " + notReturned.size());
				
		writer.close();
		
		
	}
	
	public void getOverDueTaken() throws FileNotFoundException, UnsupportedEncodingException {
		ArrayList<Taken> overDues = new ArrayList<Taken>();
		for (Taken taken : this.taken) {
			LocalDateTime due = taken.getReading().getHistory().get(taken.getReading().getHistory().size()-1).getDueDate();
			LocalDateTime now = LocalDateTime.now();
			if (now.compareTo(due) > 0) {
				overDues.add(taken);
			}
			
			
		}
		
		overDues.sort(new Comparator<Taken>() {

			@Override
			public int compare(Taken t1, Taken t2) {

				double penalty1 = t1.getReading().getHistory().get(t1.getReading().getHistory().size()-1).getPenalty();
				double penalty2 = t2.getReading().getHistory().get(t2.getReading().getHistory().size()-1).getPenalty();
				
				if (penalty1 > penalty2) {
					return -1;
				} else {
					if (penalty1 < penalty2) {
						return 1;
					}
				}
				
				
				return 0;
			}
			
		});
		
		PrintWriter writer = new PrintWriter("OverDues.txt", "UTF-8");
		double penalty = 0;
		for (Taken taken : overDues) {
			writer.println(taken.getReading().info());
			writer.println("	User: " + taken.getReading().getHistory().get(taken.getReading().getHistory().size()-1).getUser().getName());
			writer.println("	Penalty: " + taken.getReading().getHistory().get(taken.getReading().getHistory().size()-1).getPenalty());
			penalty+=taken.getReading().getHistory().get(taken.getReading().getHistory().size()-1).getPenalty();
		}
		
		writer.println("Sum of penalties: " + penalty + " lv.");
				
		writer.close();
	}
	
	
}
