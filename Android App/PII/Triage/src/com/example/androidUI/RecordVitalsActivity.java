package com.example.androidUI;

import triage.Nurse;
import triage.Patient;
import triage.Vitals;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RecordVitalsActivity extends Activity {
	
	/** Patient being updated*/
	private Patient patient;
	
	/** Nurse that is logged in*/
	private Nurse nurse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record_vitals);
		
		Intent intentV = getIntent();
		this.patient = (Patient) intentV.getSerializableExtra("patientKey");
		this.nurse = (Nurse) intentV.getSerializableExtra("nurseKey");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.record_vitals, menu);
		return true;
	}
	
	// When back button is pressed is passes back the changed object
	@Override
	public void onBackPressed(){
		Intent data = new Intent();
	    data.putExtra("resultPKey", this.patient);
	    setResult(Activity.RESULT_OK, data);
	    super.onBackPressed();
	}

	// Record Vitals of a patient when it record button is pressed
	public void recordVitals(View view){
		EditText tempText = (EditText) findViewById(R.id.tempText);
		EditText bpdText = (EditText) findViewById(R.id.bpDiastolicText);
		EditText bpsText = (EditText) findViewById(R.id.bpSystolicText);
		EditText heartText = (EditText) findViewById(R.id.heartRateText);
		TextView message = (TextView) findViewById(R.id.confirmMessage);
		
		// Takes the input from nurse and changes them to their correct values
		double temp = Double.parseDouble(tempText.getText().toString());
		int bpd = Integer.parseInt(bpdText.getText().toString());
		int bps = Integer.parseInt(bpsText.getText().toString());
		int heart = Integer.parseInt(heartText.getText().toString());


		if (tempText.length() == 0){
			tempText.setError("Must fill out required input");
			
		}
		
		if (tempText.length() == 0){
			tempText.setError("Must fill out required input");
			
		}
		
		if (tempText.length() == 0){
			tempText.setError("Must fill out required input");
			
		}
		
		if (tempText.length() == 0){
			tempText.setError("Must fill out required input");
			
		}
		
		else if (tempText.length() > 0 &&
				bpdText.length() > 0 &&
				bpsText.length() > 0){
			
			// creates vitals object and records it to the specified patient
			Vitals vitals = new Vitals(temp, bps, bpd, heart);
			
			this.nurse.recordVitals(this.patient, vitals);
			
			message.setText("Vitals was successfully recorded");
		}
		
	}
}
