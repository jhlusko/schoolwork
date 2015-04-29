package com.example.androidUI;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class PatientFeaturesActivity extends Activity {

	private ListView features;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_features);
		this.features = (ListView) findViewById(R.id.featuresListView);
		this.recordData(getCurrentFocus());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.features, menu);
		return true;
	}
	
	public void recordData(View view){
		
		features.setOnItemClickListener(new OnItemClickListener() {  
			
			@Override
	        public void onItemClick(AdapterView<?> adView, View view, 
	        		int position,long id)                               
			{                                                                                                                                                                           
	            if(features.getItemAtPosition(position).equals("View Patient Data")) {                                                                                                                                                        
	                Intent i = new Intent(PatientFeaturesActivity.this, 
	                		PatientDataActivity.class);                                                                                        
	                startActivity(i);     
	                
	            } 
	            
	            else if (features.getItemAtPosition(position).equals("Record Vitals")){
	            	Intent i = new Intent(PatientFeaturesActivity.this, 
	                		RecordVitalsActivity.class);                                                                                        
	                startActivity(i); 
	                
	            }
	        }                                                                                                       
		});
	}

}
