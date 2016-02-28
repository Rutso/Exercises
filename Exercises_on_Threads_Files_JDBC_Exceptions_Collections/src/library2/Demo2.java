package library2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class Demo2 {

	public static void main(String[] args) {
		Library narodna = new Library("Narodna biblioteka");
		
		Reading r1 = new Book("Treasure Island", "Colibri", "Adventure", "R.L.S");
		Reading r2 = new Book("Vinetu", "Colibri", "Adventure", "K.M.");
		Reading r3 = new Book("Vinetu2", "Colibri", "Adventure", "K.M.");
		Reading r4 = new Book("Solaris", "Galactica", "Science Fiction", "S.L.");
		Reading r5 = new Book("Friday", "Galactica", "Science Fiction", "R.H.");
		Reading r6 = new Book("Again and Again", "Galactica", "Science Fiction", "A.A.");
		Reading r7 = new SchoolBook("Organic Chemistry", "Nauka", "Chemistry", "prof Ivanov");
		Reading r8 = new SchoolBook("Chemistry of Metals", "Nauka", "Chemistry", "prof Milev");
		Reading r9 = new SchoolBook("Geometry", "Nauka", "Mathematics", "prof Bonev");
		Reading r10 = new SchoolBook("Algebra", "Nauka", "Mathematics", "prof Takeva");
		Reading r11= new SchoolBook("Movement of bodies", "Tehnika", "Mechanics", "prof Milchev");
		Reading r12 = new SchoolBook("Kinetic Energy", "Tehnika", "Mechanics", "prof Gerov");
		Reading r13 = new Magazine("Burda", "Krasota", "Fashion", 8);
		Reading r14 = new Magazine("Burda", "Krasota", "Fashion", 1);
		Reading r15 = new Magazine("Burda", "Krasota", "Fashion", 34);
		Reading r16 = new Magazine("Bravo", "Tralala", "Music", 2);
		Reading r17= new Magazine("Playboy", "Playboy", "Erotic", 12);
		Reading r18 = new Magazine("Karate", "Kiai", "Sport", 2);
		
		narodna.addReading(r1);
		narodna.addReading(r2);	
		narodna.addReading(r3);
		narodna.addReading(r4);
		narodna.addReading(r5);
		narodna.addReading(r6);
		narodna.addReading(r7);
		narodna.addReading(r8);
		narodna.addReading(r9);
		narodna.addReading(r10);
		narodna.addReading(r11);
		narodna.addReading(r12);
		narodna.addReading(r13);
		narodna.addReading(r14);
		narodna.addReading(r15);
		narodna.addReading(r16);
		narodna.addReading(r17);
		narodna.addReading(r18);

		
		Gson g = new Gson();
		
		
		File f = new File("test.json");
		if (!(f.exists())) {
			try {
				f.createNewFile();
				System.out.println("File " + f.getName() + " creates successfully.");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		
		
		System.out.println(g.toJson(r18));
		//try (FileWriter fw = new FileWriter(f)){
		//	fw.write(r18.toJSONString());
		//	System.out.println("JSON written successfully.");			
		//} catch (IOException e) {
		//	System.out.println(e.getMessage());
		//}
		
		
	}

}
