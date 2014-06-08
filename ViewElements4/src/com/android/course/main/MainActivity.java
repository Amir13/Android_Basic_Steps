package com.android.course.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.course.android.R;

import course.android.course.network.NetworkAndroidHttpClient;
import course.android.course.network.NetworkHttpUrlConnection;
import course.android.course.network.NetworkJson;
import course.android.course.network.NetworkSocket;
import course.android.course.network.NetworkXml;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Button btnSocket = (Button) findViewById(R.id.btnSocket);
		btnSocket.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent intent = new Intent(getApplicationContext(), NetworkSocket.class);
				startActivity(intent);
				
			}
		});
		
		final Button btnHttp = (Button) findViewById(R.id.btnHttp);
		btnHttp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent intent = new Intent(getApplicationContext(), NetworkHttpUrlConnection.class);
				startActivity(intent);
				
			}
		});
		
		final Button btnAndroid = (Button) findViewById(R.id.btnAndroid);
		btnAndroid.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent intent = new Intent(getApplicationContext(), NetworkAndroidHttpClient.class);
				startActivity(intent);
				
			}
		});
		
		final Button btnJson = (Button) findViewById(R.id.btnJson);
		btnJson.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent intent = new Intent(getApplicationContext(), NetworkJson.class);
				startActivity(intent);
				
			}
		});
		
		final Button btnXml = (Button) findViewById(R.id.btnXml);
		btnXml.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent intent = new Intent(getApplicationContext(), NetworkXml.class);
				startActivity(intent);
				
			}
		});
		
		
		
		
		
		
		
	}


}
