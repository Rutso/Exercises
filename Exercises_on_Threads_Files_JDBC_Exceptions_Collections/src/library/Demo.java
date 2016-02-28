package library;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class Demo {

	
	public static void main(String[] args) {
		
	
	
	//1. Create library:
	Library lib = new Library("Gradskata Biblioteka");
	
	//2. Create users:
	User pesho = new User("Pesho");
	User gosho = new User("Gosho");
	User maria = new User("Maria");

	
	//3. Create readings:
	Reading island = new Book("Treasure Island", "Adventure", "R.L.S.");
	Reading stranger = new Book("Stranger In A Strange Land", "Sci-Fi", "R.H.");
	Reading friday = new Book("Friday", "Sci-Fi", "R.H.");
	Reading biology = new SchoolBook("Species", "prof A.B.", "Smart", "Biology");
	Reading math = new SchoolBook("Algebra", "prof M.C.", "Smart", "Mathematics");	
	Reading chem = new SchoolBook("Organic Chemistry", "prof S.D.", "Smart", "Chemistry");
	Reading burda1 = new Magazine("Burda", 1, "CoolKneatings", "Fashion");	
	Reading burda7 = new Magazine("Burda", 7, "CoolKneatings", "Fashion");
	Reading bravo = new Magazine("Bravo", 1, "LetsDance", "Youth");	


	//4. Add redings to the library:
	lib.addReading(island);
	lib.addReading(stranger);
	lib.addReading(friday);
	lib.addReading(biology);
	lib.addReading(math);
	lib.addReading(chem);
	lib.addReading(burda1);
	lib.addReading(burda7);
	lib.addReading(bravo);

	//5. Test the views:
	lib.viewBooks();
	lib.viewMagazines();
	lib.viewSchoolBooks();
	System.out.println("Number of readings in " + lib.getName() + ": " + lib.getNumberOfReadings());

	//6. Lets take some books:
	lib.lendReading(bravo, maria);
	lib.lendReading(island, gosho);
	lib.lendReading(biology, pesho);
	lib.lendReading(math, pesho);
	lib.lendReading(math, gosho);
	
	
	try {
		lib.getTakenReadings();
	} catch (FileNotFoundException | UnsupportedEncodingException e1) {
		e1.printStackTrace();
	}
	
	try {
		Thread.sleep(60000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	
	
	
	
	try {
		lib.getOverDueTaken();
	} catch (FileNotFoundException | UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	
	
	
	
	
	}
	

	
	
}
