package triage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Queue;

public class Nurse implements Serializable{

	/** Unique serial ID */
	private static final long serialVersionUID = -1280031338409276508L;

	/** Map of usernames (key) to passwords */
	private static Map<String, String> logInMap;

	/** The emergency room that Nurse modifies */
	private EmergencyRoom emergencyRoom;

	/**Constructs a nurse */
	public Nurse(){}

	/**Creates an emergencyRoom loaded from file inputStream if available 
	 * @param inputStream The file which is loaded
	 */
	public void loadEmergencyRoom(FileInputStream inputStream){
		this.emergencyRoom = new EmergencyRoom(inputStream);
	}

	/**Saves emergencyRoom to file
	 * @param outputStream The file you are saving to
	 * @throws IOException In case the file is corrupted
	 */
	public void saveEmergencyRoom(FileOutputStream outputStream) throws IOException{
		this.emergencyRoom.saveData(outputStream);
	}

	/** Adds a patient to emergencyRoom 
	 * @param patient
	 */
	public void recordPatient(Patient patient){
		this.emergencyRoom.addPatient(patient);
	}

	/**
	 * Finds patient by health card number
	 * @param hcn The patient's health card number
	 * @return The patient with that health card number.
	 * @throws PatientNotFoundException
	 */
	public Patient lookUpPatient(String hcn) throws PatientNotFoundException{
		return this.emergencyRoom.lookupPatient(hcn);
	}


	/**Returns the Queue of Patients that need to see a doctor
	 *  ordered by Urgency Score, based on the most up to date Urgency Scores 
	 *  @return doctorQueueByUrgency The Queue of Patients that need to see
	 *  		a doctor, ordered by Urgency Score. For patients with the same
	 *  		Urgency Score, patients are organized by arrival time
	 */
	public Queue<Patient> accessDoctorQueueByUrgency(){
		return this.emergencyRoom.generateDoctorQueueByUrgency();
	}

	/**
	 * Returns the Queue of Patients that need to see a doctor ordered by 
	 *  arrival time to the Emergency Room 
	 *  @return doctorQueueByArrival The Queue of Patients that need to see
	 *  		a doctor, ordered by arrival time
	 */
	public Queue<Patient> accessDoctorQueueByArrival(){
		return this.emergencyRoom.getDoctorQueueByArrival();
	}


	/**
	 * Adds a new vitals object to the end of that patients list of vitals
	 * @param patient The patient whose vitals need to be updated
	 * @param vitals The new vitals for that patient
	 */
	public void recordVitals(Patient patient, Vitals vitals){
		Report report = patient.getCurrentReport();
		report.addVitals(vitals);
	}

	/**
	 * Adds symptoms to the end of that patients map of symptoms.
	 * @param patient The patient whose symptoms need to be updated
	 * @param symptoms The new symptoms for that patient
	 */
	public void recordSymptoms(Patient patient, String symptoms){
		Report report = patient.getCurrentReport();
		report.addSymptoms(symptoms);
	}

	/**
	 * Updates the patient's doctorDate to the current time.
	 * @param patient The patient who was visited by a doctor
	 */
	public void recordDoctorVisit(Patient patient){
		Report report = patient.getCurrentReport();
		report.setDoctorDate();
	}


}
