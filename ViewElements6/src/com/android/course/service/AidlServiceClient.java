package com.android.course.service;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.android.course.interfaces.aidl.service.KeyGeneratorAidl;
import com.android.course.main.R;

public class AidlServiceClient extends Activity {

	private KeyGeneratorAidl keyGen;
	private boolean isBound;

	private final ServiceConnection connectionService = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			keyGen = KeyGeneratorAidl.Stub.asInterface(service);
			isBound = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			keyGen = null;
			isBound = false;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aidl);

		final TextView output = (TextView) findViewById(R.id.output);

		final Button goButton = (Button) findViewById(R.id.go_button);
		goButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				try {

					if (isBound)
						output.setText(keyGen.getKey());

				} catch (RemoteException e) {
					Log.e("", e.getMessage());
				}
			}
		});

	}

	// Unbind from KeyGenerator Service
	@Override
	protected void onResume() {
		super.onResume();
		boolean bounded= false;
		
		if (!isBound) {
			Intent intent = new Intent(KeyGeneratorAidl.class.getName());
//			Creating the intent passing the context does not work
//			Intent intent = new Intent(getApplicationContext(), KeyGeneratorAidl.class);
			bounded = bindService(intent, connectionService, Context.BIND_AUTO_CREATE);
		}
		
		Log.i("AidlService", "Bounded: " + bounded + " isBound: " + isBound);
	}
	
	@Override
	protected void onPause() {
		if (isBound) {
			unbindService(connectionService);
		}
		super.onPause();
	}
}
