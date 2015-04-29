package triage;

import android.annotation.SuppressLint;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.io.*;

@SuppressLint("SimpleDateFormat")
public class EmergencyRoom implements Serializable {
	
	/** Unique serial ID */
	private static final long serialVersionUID = 6084360396938805851L;

	/** This EmergencyRoom's map of waiting patients with HCN as keys */
	private Map<String, Patient> watingPatientsByHCN;
	
	/** This EmergencyRooms's queue of patients waiting for doctors 
	 * by arrival.
	 */
	private Queue<Patient> doctorQueueByArrival;
	
	/** This EmergencyRoom's map of patients already seen by doctors with HCN
	 * as key.
	 */
	private Map<String, Patient> patientsSeenByDoctor;
		
	/** Constructs an EmergencyRoom object and initializes instance variables
	 *  to empty Queues and Maps. Populates those instance variables if saved
	 *  data file exists.
	 *  @param inputStream The FileInputStream object corresponding to the
	 *  file where the data to be loaded is found
	 */
	public EmergencyRoom(FileInputStream inputStream) {
		this.watingPatientsByHCN = new HashMap<String, Patient>();
		this.doctorQueueByArrival = new LinkedBlockingQueue<Patient>();
		this.patientsSeenByDoctor = new HashMap<String, Patient>();
		
		Scanner scanner = new Scanner(inputStream);
        String loadingData = "";
		while (scanner.hasNextLine()) {
			loadingData += scanner.nextLine();
		}
		scanner.close();
		
		try {
			this.loadData(loadingData);
		} catch (ParseException e) {
			// TODO Note: this would only happen if the data file is corrupt
			//      at this point we are not handling this, as it is beyond
			//      the scope of this project.
			e.printStackTrace();
		}
	}


	/** Constructs an EmergencyRoom object and initializes instance variables
	 *  to empty Queues and Maps. Populates those instance variables if saved
	 *  data file exists. This is the testing version of the constructor
	 *  @param stringData The String object corresponding to the
	 *  the data to be loaded is found
	 */
	public EmergencyRoom(String stringData) {
		this.watingPatientsByHCN = new HashMap<String, Patient>();
		this.doctorQueueByArrival = new LinkedBlockingQueue<Patient>();
		this.patientsSeenByDoctor = new HashMap<String, Patient>();
		if (stringData.length() > 0) {
		try {
			this.loadData(stringData);
		} catch (ParseException e) {
			// TODO Note: this would only happen if the data file is corrupt
			//      at this point we are not handling this, as it is beyond
			//      the scope of this project.
			e.printStackTrace();
		}
		}
	}

	/** Loads saved data and populates those instance variables if saved
	 *  data file exists.
	 *  @param inputStream The FileInputStream object corresponding to the
	 *  @throws ParseException 
	 */
	 
	public void loadData(String stringData) throws ParseException {
		       
		String[] stringDataArray = stringData.split("&&&");
		String[] patientsSeenByDoctorString;
		String[] doctorQueueByArrivalString;
		
		// An array of string representations of patients to be added to
		// doctorQueueByArrival and watingPatientsByHCN
		if (stringDataArray.length > 0){
			doctorQueueByArrivalString = stringDataArray[0].
					split("\\|\\|NP\\|\\|");
		} else {
			doctorQueueByArrivalString = new String[0];
		}
		// An array of string representations of patients to be added to
		// patientsSeenByDoctor
		if (stringDataArray.length > 1){
			patientsSeenByDoctorString = stringDataArray[1].
					split("\\|\\|NP\\|\\|");
		} else {
			patientsSeenByDoctorString = new String[0];
		}
        
        
        // Populate doctorQueueByArrival and watingPatientsByHCN:
        for (String pString : doctorQueueByArrivalString) {
        	Patient patient = EmergencyRoom.toPatient(pString);
        	this.doctorQueueByArrival.add(patient);
        	this.watingPatientsByHCN.put(patient.getHealthCardNumber(), 
        			patient);
        	}
        
     // Populate doctorQueueByArrival and patientsSeenByDoctor:
        for (String pString : patientsSeenByDoctorString) {
        	Patient patient = EmergencyRoom.toPatient(pString);
        	this.patientsSeenByDoctor.put(patient.getHealthCardNumber(), 
        			patient);
        	}
        } 
	
	/** Saves all patient data to file. 
	 * @returns The string representation of the whole EmergencyRoom
	 */
	public String saveData() {
		String savedDataString = "";
		// The first line of the file contains all the patients in 
		// doctorQueueByArrival separated by "||NP||"
        for (Patient p : this.watingPatientsByHCN.values()) {
        	savedDataString += p.toString() + "||NP||";
        }
		// The second line of the file contains all the patients in 
		// doctorQueueByArrival separated by "||NP||"
        savedDataString += "&&&";
        for (Patient p : this.patientsSeenByDoctor.values()) {
        	savedDataString += p.toString() + "||NP||";
        }
        savedDataString += "&&&";
        return savedDataString;
	}
	
	/** Loads saved data and populates those instance variables if saved
	 *  data file exists.
	 *  @param inputStream The FileInputStream object corresponding to the
	 *  @throws ParseException 
	 */
	 
	public void loadData(FileInputStream inputStream) throws ParseException {
		Scanner scanner = new Scanner(inputStream);
        String loadingData = "";
		while (scanner.hasNextLine()) {
			loadingData += scanner.nextLine();
		}
		scanner.close();
		this.loadData(loadingData);
	
//		// An array of string representations of patients to be added to
//		// doctorQueueByArrival and watingPatientsByHCN
//		String[] doctorQueueByArrivalString;
//		
//		// An array of string representations of patients to be added to
//		// patientsSeenByDoctor
//		String[] patientsSeenByDoctorString;
//        
//		// Read string representations of patients to be added to
//		// doctorQueueByArrival and watingPatientsByHCN:
//        if(scanner.hasNextLine()) {
//        	doctorQueueByArrivalString = scanner.nextLine().
//        			split("\\|\\|NP\\|\\|");
//        } else {
//        	doctorQueueByArrivalString = new String[0];
//        }
//		
//        // Read string representations of patients to be added to
//		// patientsSeenByDoctor:
//        if(scanner.hasNextLine()) {
//        	patientsSeenByDoctorString = scanner.nextLine().
//        			split("\\|\\|NP\\|\\|");
//        } else {
//        	patientsSeenByDoctorString = new String[0];
//        }
//        scanner.close();
//        
//        // Populate doctorQueueByArrival and watingPatientsByHCN:
//        for (String pString : doctorQueueByArrivalString) {
//        	Patient patient = EmergencyRoom.toPatient(pString);
//        	this.doctorQueueByArrival.add(patient);
//        	this.watingPatientsByHCN.put(patient.getHealthCardNumber(), 
//        			patient);
//        	}
//        
//     // Populate doctorQueueByArrival and patientsSeenByDoctor:
//        for (String pString : patientsSeenByDoctorString) {
//        	System.out.println(pString);//===============================================================
//        	Patient patient = EmergencyRoom.toPatient(pString);
//        	this.patientsSeenByDoctor.put(patient.getHealthCardNumber(), 
//        			patient);
//        	}
        } 

	/** Returns a Patient object given a string representation of that 
	 *  Patient object. 
	 *  This is a helper method for loadData()
	 *  @param pString The string representation of the Patient
	 *  @return The Patient object corresponding to pString
	 *  @throws ParseException 
	 */
	private static Patient toPatient(String pString) throws ParseException {
		String[] pStringArray = pString.split("\\|\\|\\|");
		// Re-construct Date object from string representations:
		Date patientDOB = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").
						  parse(pStringArray[1]);
		// Create new patient object:
		Patient patient = new Patient(pStringArray[0], patientDOB, 
						  pStringArray[2]);
		
		// Split the reports part and call the toReport helper method:
		if(pStringArray.length > 3){
			String[] reportsString = pStringArray[3].split("@@@");
		
			for (String reportString : reportsString) {
				if (reportString.length()>0){
					Report report = EmergencyRoom.toReport(reportString);
					patient.addReport(report);
				}
			}
		}
		// Finally return the populated Patient object:
		return patient;
		
	}
	
	/** Returns a Report object given a string representation of that 
	 *  Report object. 
	 *  This is a helper method for toPatient()
	 *  @param reportString The string representation of the Report
	 *  @return The Report object corresponding to reportString
	 * @throws ParseException 
	 */
	private static Report toReport(String reportString) throws ParseException {
		String [] reportStringArray = reportString.split("\\$\\$\\$");
		// Re-construct Date objects from string representations:
		Date arrivalTime = 
				new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").
				parse(reportStringArray[0]);
		Date doctorDate = 
				new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").
				parse(reportStringArray[1]);
		int patientAge = Integer.parseInt(reportStringArray[2]);
		// Create new Report object based on the above data:
		Report report = new Report(arrivalTime, doctorDate, patientAge);
		
		// Split the vitals part and call the toVitals helper method, add
		// all the Vitals objects created to the current report:
		String[] vitalsStringArray =  reportStringArray[3].split("###");
		for (String vitalsString : vitalsStringArray) {
			if(vitalsString.length() > 0) {
				Vitals vitals = EmergencyRoom.toVitals(vitalsString);
				report.addVitals(vitals);
			}
		}
		// Split the Symptoms part and re-construct the Symptoms 
		// Map<Date, String>
		
		String symptomsString = reportStringArray[4].substring(1, 
				reportStringArray[4].length()-1);
		String[] symptomsStringArray = symptomsString.split(",");
		
		for (String symptom : symptomsStringArray){
			if (symptom.length()>0) {
				String[] symptomArray = symptom.split("=");
			
				Date timeTaken = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").
						parse(symptomArray[0]);
				report.addSymptoms(timeTaken, symptomArray[1]);
			}
		}	
		// Finally, return the populated Report object:
		return report;
	}

	/** Returns a Vitals object given a string representation of that 
	 *  Vitals object. 
	 *  This is a helper method for toReport()
	 *  @param vitalsString The string representation of the Report
	 *  @return The Vitals object corresponding to vitalsString
	 *  @throws ParseException 
	 */
	private static Vitals toVitals(String vitalsString) throws ParseException {
		vitalsString = vitalsString.substring(1, vitalsString.length()-1);
		String[] vitalsStringArray = vitalsString.split(",");
		// Re-construct Date object from string representations:
		Date timeTaken = 
				new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").
				parse(vitalsStringArray[0]);
		// Convert Strings to double and ints as applicable:
		double temp = Double.parseDouble(vitalsStringArray[1]);
		int systolicBP = Integer.parseInt(vitalsStringArray[2]);
		int diastolicBP = Integer.parseInt(vitalsStringArray[3]);
		int hearRate = Integer.parseInt(vitalsStringArray[4]);
		// Create new Report object based on the above data:
		Vitals vitals = new Vitals(timeTaken, temp, systolicBP, 
				diastolicBP, hearRate);
		// Finally, return the Vitals Object
		return vitals;
	}
	
	/** Saves all patient data to file. 
	 * @throws IOException 
	 */
	public void saveData(FileOutputStream outputStream) throws IOException {
//		outputStream.write(this.saveData().getBytes());
//		outputStream.close();
		
		// The first line of the file contains all the patients in 
		// doctorQueueByArrival separated by "||NP||"
        for (Patient p : this.watingPatientsByHCN.values()) {
        	outputStream.write((p.toString() + "||NP||").getBytes());
        }
		// The second line of the file contains all the patients in 
		// doctorQueueByArrival separated by "||NP||"
        outputStream.write(("\n").getBytes());
        for (Patient p : this.patientsSeenByDoctor.values()) {
        	outputStream.write((p.toString() + "||NP||").getBytes());
        }
        outputStream.write(("\n").getBytes());
        outputStream.close();
	}
	
	/** Generates and returns the Queue of Patients that need to see a doctor
	 *  ordered by Urgency Score, based on the most up to date Urgency Scores 
	 *  @return doctorQueueByUrgency The Queue of Patients that need to see
	 *  		a doctor, ordered by Urgency Score. For patients with the same
	 *  		Urgency Score, patients are organized by arrival time
	 */
	public Queue<Patient> generateDoctorQueueByUrgency() {
		Queue<Patient> doctorQueueByUrgency = new PriorityQueue<Patient>();
		// TODO To be implemented in Phase III
		return doctorQueueByUrgency;
	}
	
	/** Returns the Queue of Patients that need to see a doctor ordered by 
	 *  arrival time to the Emergency Room 
	 *  @return doctorQueueByArrival The Queue of Patients that need to see
	 *  		a doctor, ordered by arrival time
	 */
	public Queue<Patient> getDoctorQueueByArrival() {
		return this.doctorQueueByArrival;
	} 
	
	/** Returns the Map of Patients that need to see a doctor with Health 
	 *  Card Number as keys
	 *  @return watingPatientsByHCN The Map of Patients that need to see
	 *  		a doctor, with Health Card Number as keys
	 */
	public Map<String, Patient> getWatingPatientsByHCN() {
		return this.watingPatientsByHCN;
	} 
	
	/** Returns the Map of Patients that already saw a doctor with Health 
	 *  Card Number as keys
	 *  @return watingPatientsByHCN The Map of Patients that already saw
	 *  		a doctor, with Health Card Number as keys
	 */
	public Map<String, Patient> getpatientsSeenByDoctor() {
		return this.patientsSeenByDoctor;
	} 
	
	/** Adds patient to this EmergencyRoom.  patient is added to 
	 * doctorQueueByArrival and to watingPatientsByHCN.
	 * @param patient The patient to be added to this EmergencyRoom
	 */
	public void addPatient(Patient patient) {
		this.doctorQueueByArrival.add(patient);
		this.watingPatientsByHCN.put(patient.getHealthCardNumber(), patient);
	}
	
	/** Returns the Patient with healthCardNum hcn. Throws 
	 * PatientNotFoundException if the patient is not in the EmergencyRoom
	 * @param hcn The patient's Health Card Number
	 * @return The patient whose Health Card Number is hcn
	 * @throws PatientNotFoundException
	 */
	public Patient lookupPatient(String hcn) throws PatientNotFoundException {
		if (this.watingPatientsByHCN.containsKey(hcn)){
			return this.watingPatientsByHCN.get(hcn);
		} else if (this.patientsSeenByDoctor.containsKey(hcn)){
			return this.patientsSeenByDoctor.get(hcn);
		} else {
			throw new PatientNotFoundException();
		}
	}
	
	/** Moves patient to patientsSeenByDoctor. patient is added to 
	 * patientsSeenByDoctor and removed from doctorQueueByArrival and from
	 * watingPatientsByHCN
	 * @param hcn The patient's Health Card Number
	 * @throws PatientNotFoundException
	 */
	public void movePatientToSeenByDoctor(String hcn) 
			throws PatientNotFoundException {
		Patient movePatient = this.lookupPatient(hcn);
		this.watingPatientsByHCN.remove(hcn);
		this.patientsSeenByDoctor.put(hcn, movePatient);
	}
}
