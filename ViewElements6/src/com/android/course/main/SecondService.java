package com.android.course.main;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class SecondService extends IntentService {

	public static final String MSG = "message";
	
	public SecondService() {
		super("SecondService");

	}
	public SecondService(String name) {
		super("SecondService");

	}

	@Override
	protected void onHandleIntent(Intent intent) {
		
		String message = intent.getStringExtra(MSG);
		
		Log.i("SecondService", message);

	}

	
}
