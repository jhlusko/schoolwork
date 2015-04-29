package com.example.androidUI;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class RecordVitalsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record_vitals);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.record_vitals, menu);
		return true;
	}

}
