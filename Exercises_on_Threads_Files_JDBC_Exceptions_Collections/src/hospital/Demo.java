package hospital;

import hospital.db.DBManager;

public class Demo {

	
	public static void main(String[] args) {
		
		//1. Create a Hospital:
		Hospital tokuda = new Hospital("Tokuda Hospital");
		DBManager.getInstance().deployDatabase();
		
		//2. Create several doctors and add them to the hospital:
		Doctor dr1 = new Doctor("Mihail", "Stanchev", "0896543341", "Endocrinology");
		Doctor dr2 = new Doctor("Asen", "Vasilev", "0894588341", "Cardiology");
		Doctor dr3 = new Doctor("Boriana", "Mladenova", "0887003341", "Orthopedy");
		tokuda.addDoctor(dr1);
		tokuda.addDoctor(dr2);
		tokuda.addDoctor(dr1);
		tokuda.addDoctor(dr3);
		
		//2. Create several doctors and add them to the hospital:
		Nurse nr1 = new Nurse("Magdalena", "Toneva", "0896522341", 5);
		Nurse nr2 = new Nurse("Veronika", "Borisova", "0888543321", 1);
		Nurse nr3 = new Nurse("Ana", "Kirilova", "0896763321", 3);
		Nurse nr4 = new Nurse("Galina", "Petkova", "0896502301", 5);
		Nurse nr5 = new Nurse("Teodora", "Ivanova", "0885903344", 1);
		Nurse nr6 = new Nurse("Albena", "Karageorgieva", "0877463231", 3);
		tokuda.addNurse(nr1);
		tokuda.addNurse(nr2);
		tokuda.addNurse(nr3);
		tokuda.addNurse(nr4);
		tokuda.addNurse(nr5);
		tokuda.addNurse(nr6);	
		
		//3. Create several patients and add them to the hospital:
		Patient p1 = new Patient("Kaloian", "Mihailov", "0987567444", 23, true);
		Patient p2 = new Patient("Sergei", "Koev", "0887543434", 43, true);
		Patient p3 = new Patient("Monika", "Veleva", "0887006244", 58, false);
		tokuda.serviceIncomingPatient(p1);
		tokuda.serviceIncomingPatient(p2);
		tokuda.serviceIncomingPatient(p3);

		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		Patient p4 = new Patient("Yordan", "Genov", "0987545444", 48, true);
		Patient p5 = new Patient("Anelia", "Pavlova", "0877543474", 57, false);
		Patient p6 = new Patient("Veronika", "Manolova", "0887006244", 24, false);
		tokuda.serviceIncomingPatient(p4);
		tokuda.serviceIncomingPatient(p5);
		tokuda.serviceIncomingPatient(p5);
		tokuda.serviceIncomingPatient(p6);
		
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		Patient p7 = new Patient("Manol", "Boev", "0987345444", 68, true);
		Patient p8 = new Patient("Gergana", "Kalinova", "0878143474", 17, false);
		Patient p9 = new Patient("Iliana", "Valentinova", "0887002144", 34, false);
		Patient p10 = new Patient("Vasil", "Donkov", "0887005454", 44, true);
		tokuda.serviceIncomingPatient(p7);
		tokuda.serviceIncomingPatient(p8);
		tokuda.serviceIncomingPatient(p9);
		tokuda.serviceIncomingPatient(p10);
		
	}
	
	
}
