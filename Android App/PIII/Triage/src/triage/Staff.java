package triage;

import java.io.Serializable;
import java.util.Map;

public class Staff implements Serializable{

	/** Unique serial ID */
	private static final long serialVersionUID = -1280031338409276508L;

	/** The emergency room that Nurse modifies */
	protected EmergencyRoom emergencyRoom;

	/**Constructs a nurse */
	public Staff(){}

	/**Creates an emergencyRoom loaded from file inputStream if available 
	 * @param inputString The string with the date to be loaded
	 */
	public void loadEmergencyRoom(String inputString){
		this.emergencyRoom = new EmergencyRoom(inputString);
	}

	/**Saves emergencyRoom to file
	 * @return The string representation of the entire EmergencyRoom
	 */
	public String saveEmergencyRoom() {
		return this.emergencyRoom.saveData();
	}
	
	/** Adds a patient to emergencyRoom 
	 * @param patient
	 */
	public Patient lookUpPatient(String hcn) throws PatientNotFoundException{
		return this.emergencyRoom.lookupPatient(hcn);
	}
	
	/**
	 * Updates a patient that is waiting or has already seen a doctor 
	 * @param patient
	 */
	public void UpdatePatient(Patient patient) {
		Map<String, Patient> waitingPatients = 
				emergencyRoom.getWatingPatientsByHCN();
		Map<String, Patient> seenPatients = 
				emergencyRoom.getpatientsSeenByDoctor();
		
		if(waitingPatients.containsKey(patient.getHealthCardNumber())){
			waitingPatients.put(patient.getHealthCardNumber(), patient);
		}
		else if(seenPatients.containsKey(patient.getHealthCardNumber())){
			seenPatients.put(patient.getHealthCardNumber(), patient);
		}

	}

}
