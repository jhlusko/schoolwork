package triage;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;


public class Report implements Serializable {

	private static final long serialVersionUID = 4761472535279835583L;
	private List<Vitals> vitals;
	private SortedMap<Date, String> symptoms;
	private Integer patientAge;
	private Date arrivalTime;
	@SuppressWarnings("deprecation")
	private static Date NOTSEENDOCTOR = new Date(-100,0,01,0, 0);
	//private String comments;
	private Date doctorDate;
	
	public Report(Date arrivalTime, int patientAge) {
		this.arrivalTime = new Date();
		this.vitals = new ArrayList<Vitals>();
		//this.vitals.add(vitals);
		this.symptoms = new TreeMap<Date, String>();
		//this.symptoms.put(this.arrivalTime, symptoms);
		this.patientAge = patientAge;
		this.doctorDate = NOTSEENDOCTOR;
	}

	// This constructor is called with loadData is called
	public Report(Date arrivalTime, Date doctorDate, int patientAge) {
		this.arrivalTime = new Date();
		this.vitals = new ArrayList<Vitals>();
		this.symptoms = new TreeMap<Date, String>();
		this.patientAge = patientAge;
		this.doctorDate = doctorDate;
	}
	
	
	public List<Vitals> getVitals() {
		return this.vitals;
	}
	
	public Vitals getLastVitals() {
		return this.vitals.get(-1);
	}
	
	public SortedMap<Date, String> getSymptoms() {
		return this.symptoms;
	}
	
	public String getLastSymptoms() {
		return this.symptoms.get(this.symptoms.lastKey());
	}
	
	public int computeUrgencyScore() {
		int urgencyScore = getLastVitals().computeParticalUrgencyScore();
		if (patientAge < 2) {
			urgencyScore += 1;
		}
		return urgencyScore;
	}
	
	public void setDoctorDate() {
		this.doctorDate = new Date();
	}
	
	public Date getDoctorDate() {
		return this.doctorDate;
	}
	
	public void addVitals(Vitals vitals) {
		this.vitals.add(vitals);
	}
	
	public void addSymptoms(String symptoms) {
		this.symptoms.put(new Date(), symptoms);
	}

	// This method is called when loadData is called.
	public void addSymptoms(Date timeTaken, String symptoms) {
		this.symptoms.put(timeTaken, symptoms);
	}
	
    /** Returns a string representation for this Report, containing the
    *  string representations of all instance variables for this Report
    * @return The string representation for this Report
    */
    public String toString() {
           String vitalsString = "";
           for (Vitals v : this.vitals) {
                  vitalsString += "###" + v.toString();
           }
           String symptomsString = symptoms.toString();
          
           return this.arrivalTime.toString() + "$$$" + this.doctorDate.toString()
                        + "$$$" + this.patientAge + "$$$" +  vitalsString + "$$$" +
                        symptomsString;
    }

}
