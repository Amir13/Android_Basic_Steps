package com.android.course.sensor;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.android.course.main.R;

public class AccelomenterActivity extends Activity implements
		SensorEventListener {

	private TextView txtViewX, txtViewY, txtViewZ;
	private SensorManager sensorManager;
	private Sensor accelerometer;
	private long lastUpdate;
	private static final int UPDATE_THRESHOLD = 500;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor_accelerometer);

		txtViewX = (TextView) findViewById(R.id.txtX);
		txtViewY = (TextView) findViewById(R.id.txtY);
		txtViewZ = (TextView) findViewById(R.id.txtZ);

		// Get reference to SensorManager
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		// Get reference to Accelerometer
		accelerometer = sensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		if (accelerometer == null) {
			finish();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();

		sensorManager.registerListener(this, accelerometer,
				SensorManager.SENSOR_DELAY_UI);
		
		lastUpdate = System.currentTimeMillis();
	}

	@Override
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this);
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			
			long actualTime = System.currentTimeMillis();
			
			if (actualTime - lastUpdate > UPDATE_THRESHOLD) {
				lastUpdate = actualTime;
				
				float x = event.values[0];
				float y = event.values[1];
				float z = event.values[2];
				
				txtViewX.setText(String.valueOf(x));
				txtViewY.setText(String.valueOf(y));
				txtViewZ.setText(String.valueOf(z));
			}
		}
	}

}
