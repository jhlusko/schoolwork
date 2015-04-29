package com.example.androidUI;

import triage.NoLastVitalsException;
import triage.Patient;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class PatientDataActivity extends Activity {

	/** Patient that is being viewed*/
	private Patient patient;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_data);
		
		TextView infoText = (TextView) findViewById(R.id.patientInfo);
		Intent intent = getIntent();
		
		this.patient = (Patient) intent.getSerializableExtra("patientKey");
		
		// Displays Text of current patient Data
		if (this.patient.getReports().size() >= 1){
			try {
				infoText.setText(this.patient.getName() + "\n" +
						this.patient.getHealthCardNumber() + "\n" +
						this.patient.getDateofBirth().toString() + "\n\n" +
						this.patient.getCurrentReport().getLastVitals().toString());
				
			} catch (NoLastVitalsException e) {
				infoText.setText(this.patient.getName() + "\n" +
						this.patient.getHealthCardNumber() + "\n" +
						this.patient.getDateofBirth().toString());
			}

		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_data, menu);
		return true;
	}

}
