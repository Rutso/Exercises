package hospital;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

public class Administrator {

	
	//Fields:
	private PrintStream logWriter;
	private File log;
	
	
	//Constructor
	public Administrator(){
		this.log = new File("hospitalLog.txt");
		if(!(this.log.exists())) {
			try {
				this.log.createNewFile();
				System.out.println("				Hospital log created successfully.");
			} catch (IOException e) {
				System.out.println("				Something went wrong with creation of hospital log: " + e.getMessage());
			}
			
		}
		try {
			this.logWriter = new PrintStream(this.log);
			System.out.println("				Log writer created successfully.");

		} catch (FileNotFoundException e) {
			System.out.println("				Something went wrong with creation of log writer: " + e.getMessage());
		}
	}

	
	//Getters:
	public synchronized PrintStream getLogWriter(){
		return this.logWriter;
	}
		
	
	//Methods:
	public synchronized void writeLog(String info) {
		this.logWriter.println(info);
	}

	
}
