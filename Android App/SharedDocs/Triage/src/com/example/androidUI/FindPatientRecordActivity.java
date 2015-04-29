package com.example.androidUI;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class FindPatientRecordActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_patient_record);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.find_patient_record, menu);
		return true;
	}
	
	public void findPatient(View view) {
		
		Intent intent = new Intent(this, PatientFeaturesActivity.class);
		
		startActivity(intent);
	}

}
