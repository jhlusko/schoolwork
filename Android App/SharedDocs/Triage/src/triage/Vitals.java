package triage;

import java.io.Serializable;
import java.util.Date;

public class Vitals implements Serializable {

	private static final long serialVersionUID = -4765678880601659109L;
	private Date timeTaken;
	private double temperature;
	private int systolicBP;
	private int diastolicBP;
	private int heartRate;
	
	public Vitals(double temperature, int systolicBP, int diastolicBP, int heartRate) {
		this.temperature = temperature;
		this.systolicBP = systolicBP;
		this.diastolicBP = diastolicBP;
		this.heartRate = heartRate;
		this.timeTaken = new Date();
	}

	// This constructor is called when loadData is called.
	public Vitals(Date timeTaken, double temperature, int systolicBP, int diastolicBP, int heartRate) {
		this.temperature = temperature;
		this.systolicBP = systolicBP;
		this.diastolicBP = diastolicBP;
		this.heartRate = heartRate;
		this.timeTaken = timeTaken;
	}
	
	public Date getTimeTaken() {
		return this.timeTaken;
	}

	public double getTemperature() {
		return temperature;
	}

	public int getSystolicBP() {
		return systolicBP;
	}

	public int getDiastolicBP() {
		return diastolicBP;
	}

	public int getHeartRate() {
		return heartRate;
	}
	
	public int computeParticalUrgencyScore() {
		int partical = 0;
		
		if (this.temperature >= 39.0) {
			partical += 1;
		}
		if (this.systolicBP >= 140 | this.diastolicBP >= 90) {
			partical += 1;
		}
		if (this.heartRate <= 50 | this.heartRate >= 100) {
			partical += 1;
		}
		
		return partical;
	}
	
	/** Returns a string representation for this Report, containing the
    *  string representations of all instance variables for this Report
    * @return The string representation for this Report
    */
    public String toString() {
           return "(" + this.timeTaken.toString() + "," + this.temperature +
                        "," + this.systolicBP + "," + this.diastolicBP + "," +
                        this.heartRate + ")";     
    }
    
}