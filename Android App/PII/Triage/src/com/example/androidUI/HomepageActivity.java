package com.example.androidUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import triage.Nurse;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class HomepageActivity extends Activity {
	
	/** Nurse that is logged in*/
	private Nurse nurse;
	
	/** Output Stream for File*/
	private FileOutputStream out;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_homepage);
		
		Intent intent = getIntent();
		String user = (String) intent.getStringExtra("usernameKey");
		
		TextView header = (TextView) findViewById(R.id.headerView);
		header.setText("Welcome\n\n" + user);
		
		//EmergencyRoom and Nurse are collected from previous activity
		this.nurse = new Nurse();
		

		try {
			out = openFileOutput("PatientData.txt",Context.MODE_APPEND);
			out.close();
			FileInputStream in = openFileInput("PatientData.txt");
			Scanner scanner = new Scanner(in);
			String record = "";
			while (scanner.hasNextLine()){
				record += scanner.nextLine();
			}

			//EmergencyRoom loadData method called here
			nurse.loadEmergencyRoom(record);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);

	    if(requestCode == 1 && resultCode == Activity.RESULT_OK){
	        this.nurse = (Nurse) data.getSerializableExtra("resultKey");
	    }
	    
	    if(requestCode == 2 && resultCode == Activity.RESULT_OK){
	    	this.nurse = (Nurse) data.getSerializableExtra("resultKey");
	    }
	}
	
	// Goes to the record activity when Record Patient is clicked
	public void recordPatient(View view){
		TextView message = (TextView) findViewById(R.id.saveMessage);
		
		Intent intentR = new Intent(this, RecordPatientActivity.class);
		intentR.putExtra("nurseKey", this.nurse);
		message.setText("");
		startActivityForResult(intentR,1);
	}

	// Accesses the database of recorded patients 
	public void accessPatient(View view){
		TextView message = (TextView) findViewById(R.id.saveMessage);
		
		Intent intentP = new Intent(this, FindPatientRecordActivity.class);
		intentP.putExtra("nurseKey", this.nurse);
		message.setText("");
		startActivityForResult(intentP,2);
	}
	
	// Save all Data recorded and updated within the login session of the Nurse
	public void saveAllData(View view) {
		TextView message = (TextView) findViewById(R.id.saveMessage);
		
		//EmergencyRoom saveData method called here
		try {
			out = openFileOutput("PatientData.txt",Context.MODE_PRIVATE);
			String data = nurse.saveEmergencyRoom();
			out.write(data.getBytes());
			out.close();
			message.setText("Save Successful");
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
	}
}
