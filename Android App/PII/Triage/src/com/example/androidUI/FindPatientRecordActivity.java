package com.example.androidUI;

import triage.Nurse;
import triage.Patient;
import triage.PatientNotFoundException;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class FindPatientRecordActivity extends Activity {
	
	/** Nurse that is logged in*/
	private Nurse nurse;
	
	/** Patient that needs to be accessed*/
	private Patient patient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_patient_record);

		Intent intentF = getIntent();
		this.nurse = (Nurse) intentF.getSerializableExtra("nurseKey");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.find_patient_record, menu);
		return true;
	}
	
	@Override
	public void onBackPressed(){
		Intent data = new Intent();
	    data.putExtra("resultKey", this.nurse);
	    setResult(Activity.RESULT_OK, data);
	    super.onBackPressed();
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);

	    if(requestCode == 1 && resultCode == Activity.RESULT_OK){
	        this.patient = (Patient) data.getSerializableExtra("resultPKey");
	        this.nurse.recordPatient(this.patient);
	    }
	    
	}
	
	public void findPatient(View view) {
		EditText hcnText = (EditText) findViewById(R.id.findHealthCardText);
		TextView message = (TextView) findViewById(R.id.findMessage);
		Intent intent = new Intent(this, PatientFeaturesActivity.class);
		
		try {
			this.patient = nurse.lookUpPatient(hcnText.getText().toString());
			intent.putExtra("patientKey", this.patient);
			intent.putExtra("nurseKey", this.nurse);
			message.setText("");
			startActivityForResult(intent,1);
			
		} catch (PatientNotFoundException e) {
			
			message.setText("Patient could not be Found");
		}
		
	}

}
