package com.android.course.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.android.course.main.R;

public class MessengerServiceClientActivity extends Activity {

	// Intent for binding to the service
	private Intent intentService ;

	private Messenger messenger;
	private boolean isBound;

	private String MESSAGE_KEY = "Key";

	// Object implementing Service Connection callbacks
	private ServiceConnection serviceConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName arg0, IBinder service) {
			messenger = new Messenger(service);
			isBound = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			messenger = null;
			isBound = false;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_messenger);

		final Button btnService = (Button) findViewById(R.id.btnService);
		btnService.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				if (isBound) {

					// Create a message and send it to the Service
					// through messenger object
					Message msg = Message
							.obtain(null, MessengerService.LOG_MSG);

					Bundle bundle = new Bundle();
					bundle.putString(MESSAGE_KEY, "Sending a message");
					msg.setData(bundle);
					
					Toast.makeText(getApplicationContext(), "Your messages will be printed in the log by the server.", Toast.LENGTH_SHORT).show();
					

					try {
						messenger.send(msg);
					} catch (Exception e) {
						Log.e("", e.getMessage());
					}

				}
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		intentService = new Intent(getApplicationContext(),MessengerService.class);
		bindService(intentService, serviceConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onPause() {
		if (isBound) {
			unbindService(serviceConnection);
		}
		super.onPause();
	}
}