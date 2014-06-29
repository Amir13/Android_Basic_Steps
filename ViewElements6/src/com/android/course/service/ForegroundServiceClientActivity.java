package com.android.course.service;

import com.android.course.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ForegroundServiceClientActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_foreground_service);

		// Intent used for starting the MusicService
		final Intent musicService = new Intent(getApplicationContext(),
				ForegroundService.class);
		
		final Button startButton = (Button) findViewById(R.id.start_button);
		startButton.setOnClickListener(new OnClickListener() {
			public void onClick(View src) {
				
				// Start the MusicService using the Intent
				startService(musicService);

			}
		});

		final Button stopButton = (Button) findViewById(R.id.stop_button);
		stopButton.setOnClickListener(new OnClickListener() {
			public void onClick(View src) {

				// Stop the MusicService using the Intent
				stopService(musicService);

			}
		});
		
	}
}
