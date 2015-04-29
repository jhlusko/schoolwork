package com.example.androidUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class HomepageActivity extends Activity {
	
	//nurse that logged in (instance variable)
	//private Nurse nurse;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_homepage);
		
		Intent intent = getIntent();
		String user = (String) intent.getStringExtra("usernameKey");
		
		TextView header = (TextView) findViewById(R.id.headerView);
		header.setText("Welcome\n\n" + user);
		
		//EmergencyRoom and Nurse are collected from previous activity
		//this.nurse = intent.getSerializableExtra("nurseKey")
		
		try {
			FileInputStream in = openFileInput("PatientData.txt");
			//EmergencyRoom loadData method called here
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.homepage, menu);
		return true;
	}
	
	public void recordPatient(View view){
		Intent intent = new Intent(this, RecordPatientActivity.class);
		
		startActivity(intent);
	}

	public void accessPatient(View view){
		Intent intent = new Intent(this, FindPatientRecordActivity.class);
		//intent.putExtra("nurseKey", Nurse());
		startActivity(intent);
	}
	
	public void saveAllData(View view) throws FileNotFoundException{
		FileOutputStream out = openFileOutput("PatientData.txt", 
				Context.MODE_PRIVATE);
		
		//EmergencyRoom saveData method called here
		
	}
}
