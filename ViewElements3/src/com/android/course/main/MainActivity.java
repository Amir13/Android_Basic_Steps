package com.android.course.main;

import com.android.course.notification.AlarmReceiver;
import com.example.viewelements3.R;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private AlarmManager alarmManager;
	private Intent alarmReceiver;
	private PendingIntent pendingIntent;

	private static final long INITIAL_ALARM_DELAY = 1 * 60 * 1000L;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Get the AlarmManager Service
		alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

		// Create PendingIntent to start the AlarmReceiver
		alarmReceiver = new Intent(getApplicationContext(), AlarmReceiver.class);
		pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0,
				alarmReceiver, 0);

		final Button btnSingle = (Button) findViewById(R.id.btnSingle);
		btnSingle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				alarmManager.set(AlarmManager.RTC_WAKEUP,
						SystemClock.elapsedRealtime() + INITIAL_ALARM_DELAY,
						pendingIntent);

				Toast.makeText(getApplicationContext(), "Single Alarm Set",
						Toast.LENGTH_LONG).show();
			}
		});

		final Button btnRepeat = (Button) findViewById(R.id.btnRepeat);
		btnRepeat.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
						SystemClock.elapsedRealtime() + INITIAL_ALARM_DELAY,
						AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);

				Toast.makeText(getApplicationContext(),
						"Repeating the alarm each 15 mins.", Toast.LENGTH_LONG)
						.show();
			}
		});

		final Button btnInexact = (Button) findViewById(R.id.btnInexact);
		btnInexact.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
						SystemClock.elapsedRealtime() + INITIAL_ALARM_DELAY,
						INITIAL_ALARM_DELAY * 2, pendingIntent);

				Toast.makeText(getApplicationContext(),
						"Inexact Repeating Alarm Set each 15 mins.",
						Toast.LENGTH_LONG).show();
			}

		});

		final Button btnCancel = (Button) findViewById(R.id.btnCancel);
		btnCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				alarmManager.cancel(pendingIntent);

				Toast.makeText(getApplicationContext(),
						"The alarm was canceled.", Toast.LENGTH_LONG)
						.show();
			}

		});
	}
}
