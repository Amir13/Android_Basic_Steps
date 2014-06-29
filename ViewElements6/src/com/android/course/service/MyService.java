package com.android.course.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyService extends IntentService {
	
	public static final String MSG = "message";
	private String message;
	
	public MyService() {
		super("com.android.course.service.myservice");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		
		message = intent.getStringExtra(MSG);
		Log.i("MyService", message);
		Toast.makeText(getApplicationContext(), message,
				Toast.LENGTH_LONG).show();
		
		Log.i("MyService", "Started my Service");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(getApplicationContext(), intent.getStringExtra(MSG),
				Toast.LENGTH_LONG).show();
		return super.onStartCommand(intent, flags, startId);
	}
}
