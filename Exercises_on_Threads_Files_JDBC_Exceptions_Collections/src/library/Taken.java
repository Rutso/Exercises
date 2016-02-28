package library;

public class Taken extends Thread {

	
	//Fields:
	private Reading reading;
	
	
	//Constructor:
	public Taken(Reading reading) {
		this.reading = reading;
		
	}

	//Getter:
	protected Reading getReading() {
		return reading;
	}
	
	
	
	//Methods:

	@Override
	public void run() {

		if (this.reading instanceof Book) {
			
			
			Book book = (Book) this.reading;
			try {
				sleep(book.getHistory().get(book.getHistory().size() - 1).getLentTime()*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			
			while (book.isLent()) {
			
					
					try {
						sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					
					book.getHistory().get(book.getHistory().size() - 1).addPenalty();
					book.getHistory().get(book.getHistory().size() - 1).setFinalTax();
				}
			}
			
			System.out.println(book.getHistory().get(book.getHistory().size() - 1).getUser().getName() + " must pay " + book.getHistory().get(book.getHistory().size() - 1).getMoneyToPay() + " including " + book.getHistory().get(book.getHistory().size() - 1).getPenalty() + " lv. penalty.");
			
			
		} else {
			if (this.reading instanceof SchoolBook) {
				SchoolBook schoolBook = (SchoolBook) this.reading;
				try {
					sleep(schoolBook.getHistory().get(schoolBook.getHistory().size() - 1).getLentTime()*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				while (schoolBook.isLent()) {
					
						
						try {
							sleep(1000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						
						schoolBook.getHistory().get(schoolBook.getHistory().size() - 1).addPenalty();
						schoolBook.getHistory().get(schoolBook.getHistory().size() - 1).setFinalTax();
					}
				}
				System.out.println(schoolBook.getHistory().get(schoolBook.getHistory().size() - 1).getUser().getName() + " must pay " + schoolBook.getHistory().get(schoolBook.getHistory().size() - 1).getMoneyToPay() + " including " + schoolBook.getHistory().get(schoolBook.getHistory().size() - 1).getPenalty() + " lv. penalty.");

			}
		}

		
	}

	
	
	
	
	
}
