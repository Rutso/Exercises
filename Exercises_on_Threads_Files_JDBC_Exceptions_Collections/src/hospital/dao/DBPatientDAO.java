package hospital.dao;

import java.sql.*;
import java.util.Vector;

import hospital.Hospital;
import hospital.Patient;
import hospital.db.DBManager;

public class DBPatientDAO implements IPatientDAO {

		
	//Methods:
	@Override
	public void addPatient(Patient newPatient) {
			
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement("INSERT INTO Hospital.patients (first_name, last_name, phone_number, age, sex) VALUES (?,?,?,?,?)");
			pst.setString(1, newPatient.getFirstName());
			pst.setString(2, newPatient.getLastName());
			pst.setString(3, newPatient.getPhone());
			pst.setInt(4, newPatient.getAge());
			String sex = null;
			if (newPatient.isMale()) {
				sex = "male";
			} else {
				sex = "female";
			}
			pst.setString(5, sex);
			pst.execute();
			pst.close();
			System.out.println("	" + newPatient.getFirstName() + " " + newPatient.getLastName() + " inserted into database.");
			Hospital.log.writeLog("	" + newPatient.getFirstName() + " " + newPatient.getLastName() + " inserted into database.");
		} catch (SQLException e) {
			System.out.println("Error executing the statement in addPatient: " + e.getMessage());
		} //finally {
			//try {
			//	conn.close();
		//	} catch (SQLException e) {
		//		System.out.println(e.getMessage());
		//	}
		//}
	}

	@Override
	public Patient getPatient(String first_name, String last_name) {
		Patient patient = null;
		Connection conn = DBManager.getInstance().getConnection();
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT first_name, last_name, phone_number, age, sex FROM Hospital.patients " + "WHERE first_name='" + first_name + "' AND last_name='" + last_name + "';");
			rs.next();
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String phoneNumber = rs.getString("phone_number");
			int age = rs.getInt("age");
			String sex = rs.getString("sex");
			boolean isMale = false;
			if (sex.equalsIgnoreCase("male")) {
				isMale = true;
			}
			patient = new Patient(firstName, lastName, phoneNumber, age, isMale);
		} catch (SQLException e) {
			System.out.println("Error executing the statement in getPatient:" + e.getMessage());
		}
		
		
		return patient;
	}

	@Override
	public Vector<Patient> getAllPatients() {
		
		Vector<Patient> patients = new Vector<Patient>();
		try {
			Statement st = DBManager.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT first_name, last_name, phone_number, age, sex FROM Hospital.patients;");
			//Patient(String firstName, String lastName, String phoneNumber, int age, boolean isMale) 
			while(rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String phoneNumber = rs.getString("phone_number");
				int age = rs.getInt("age");
				boolean isMale = false;
				String sex = rs.getString("sex");
				if (sex.equalsIgnoreCase("male")) {
					isMale = true;
				}
				Patient patient = new Patient(firstName, lastName, phoneNumber, age, isMale);
				patients.addElement(patient);
			}
			st.close();
		} catch (SQLException e) {
			System.out.println("Error executing the statement in getAllPatients:" + e.getMessage());
			}
		
		return patients;
	}

	@Override
	public int getPatientId(Patient patient) {
		Connection conn = DBManager.getInstance().getConnection();
		int patientId = 0;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT patient_id FROM Hospital.patients WHERE first_name ='" + patient.getFirstName() + "' AND last_name ='" + patient.getLastName() + "';");
			rs.next();
			patientId = rs.getInt("patient_id");
		
		
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return patientId;
	}

		
	
}
