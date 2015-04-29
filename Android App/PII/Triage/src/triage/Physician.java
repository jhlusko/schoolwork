package triage;

public class Physician extends Staff{

	/** Unique serial ID */
	private static final long serialVersionUID = -1280031338409276508L;

	/**Constructs a nurse */
	public Physician(){}
	
	public void recordPrescription(Patient patient, String medication, String instructions){
		Report report = patient.getCurrentReport();
		report.addPrescription(medication, instructions);
	}

}
