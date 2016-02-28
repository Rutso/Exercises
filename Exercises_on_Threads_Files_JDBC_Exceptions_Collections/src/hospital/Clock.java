package hospital;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Clock extends Thread {

	
	//Fields:
	private Hospital hospital;
	public static LocalDate date;
		
	//Constructor:
	public Clock(Hospital hospital) {
		this.hospital = hospital;
		Clock.date = LocalDateTime.now().toLocalDate();
	}

	//Methods:
	@Override
	public void run() {
		
		
		
		while (true) {
					
			System.out.println("	NEW DAY BEGINS: " + Clock.date);
			Hospital.log.writeLog("	NEW DAY BEGINS: " + Clock.date);
				Clock.date = Clock.date.plusDays(1);
			for (Doctor dr : this.hospital.getDoctors()) {
				dr.startNewWorkDay();
			}
			
			for (Department dep : this.hospital.getDepartments()) {
				for (Nurse n : dep.getNurses()) {
					n.startNewWorkDay();
				}
			}
		
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			
			for(Doctor d : this.hospital.getDoctors()) {
				for(Case c : d.getCases()) {
					c.setTookMedicineToday(false);
				}
			}
			
			
		}
		
	}
		
	
}
