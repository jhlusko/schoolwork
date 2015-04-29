package com.example.androidUI;

import java.util.List;
import java.util.Map;

import triage.NoLastVitalsException;
import triage.Patient;
import triage.PatientNotFoundException;
import triage.Staff;
import triage.Vitals;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class PatientDataActivity extends Activity {

	/** Patient that is being viewed*/
	private Patient patient;
	
	/** staff member that is logged in*/
	private Staff user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_data);
		
		TextView infoText = (TextView) findViewById(R.id.patientInfo);
		Intent intent = getIntent();
		
		Patient p = (Patient) intent.getSerializableExtra("patientKey");
		this.user = (Staff) intent.getSerializableExtra("staffKey");
		
		try {
			this.patient = user.lookUpPatient(p.getHealthCardNumber());
			
		} catch (PatientNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		// Displays Text of current patient Data
		if (this.patient.getReports().size() >= 1){
			String vitals = "";
			String medicines = "";
			List<Vitals> vitalsList = 
					this.patient.getCurrentReport().getVitals();
			Map<String,String> perscriptions = 
					this.patient.getCurrentReport().getPrescriptions();
			
			for(String medicine : perscriptions.keySet()){
				medicines += "Medicine: " + medicine + 
						"\n\n\t Instructions: " +
						perscriptions.get(medicine) + "\n\n";
						
			}
			
			for(int i = vitalsList.size() - 1 ; i >= 0 ; i--){
				vitals += "Time taken: " + 
						vitalsList.get(i).getTimeTaken().toString() + 
						"\n\n\tTempature: "  + 
						String.valueOf(vitalsList.get(i).getTemperature()) + 
						" Degree Celsius\n\tDiastolic BP: " + 
						String.valueOf(vitalsList.get(i).getDiastolicBP()) + 
						"\n\tSystolic BP: " + 
						String.valueOf(vitalsList.get(i).getSystolicBP()) +
						"\n\tHeart Rate: " + 
						String.valueOf(vitalsList.get(i).getHeartRate()) + " bpm\n\n";
				
			}
			
			infoText.setText(this.patient.getName() + "\n" +
					this.patient.getHealthCardNumber() + "\n" +
					this.patient.getDateofBirth().toString() + "\n\n" +
					vitals + "\n\n" + medicines);

		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_data, menu);
		return true;
	}

}
