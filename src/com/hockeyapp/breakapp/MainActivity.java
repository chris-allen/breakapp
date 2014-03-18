package com.hockeyapp.breakapp;

import net.hockeyapp.android.Constants;
import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.NativeCrashManager;
import net.hockeyapp.android.UpdateManager;
import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	private static final String HOCKEYAPP_ID = "APP_ID_GOES_HERE"; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		// Setup native crash reports
		Constants.loadFromContext(this);
		NativeCrashManager.setUpBreakpad(Constants.FILES_PATH);
		NativeCrashManager.handleDumpFiles(this, HOCKEYAPP_ID);

		// Check for updates in HockeyApp
		UpdateManager.register(this, HOCKEYAPP_ID);
	}

	public void onResume() {
		super.onResume();

		// Setup java crash reports
		CrashManager.register(this, HOCKEYAPP_ID);
	}
}
