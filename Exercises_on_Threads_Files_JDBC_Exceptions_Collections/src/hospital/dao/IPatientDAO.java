package hospital.dao;

import java.util.Vector;

import hospital.Patient;

public interface IPatientDAO {

	
	public void addPatient(Patient newPatient);
	
	public Patient getPatient(String first_name, String last_name);
	
	public int getPatientId(Patient patient);
	
	public Vector<Patient> getAllPatients();
	
}
