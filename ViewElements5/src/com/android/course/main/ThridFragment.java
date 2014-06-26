package com.android.course.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.course.location.LocationActivity;
import com.android.course.location.MapActivity;
import com.android.course.location.MapActivity2;
import com.android.course.sensor.AccelomenterActivity;
import com.android.course.sensor.MagnetometerActivity;

public class ThridFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_thrid, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		Button btnSensorAcc = (Button) getActivity().findViewById(R.id.btnSensorAcc);
		btnSensorAcc .setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity().getApplicationContext(), AccelomenterActivity.class);
				startActivity(intent);
				
			}
		});
		
		Button btnMagnetometer = (Button) getActivity().findViewById(R.id.btnMagnetometer);
		btnMagnetometer .setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity().getApplicationContext(), MagnetometerActivity.class);
				startActivity(intent);
				
			}
		});
		
		Button btnLocation = (Button) getActivity().findViewById(R.id.btnLocation);
		btnLocation .setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity().getApplicationContext(), LocationActivity.class);
				startActivity(intent);
				
			}
		});
		
		Button btnMap = (Button) getActivity().findViewById(R.id.btnMap);
		btnMap .setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity().getApplicationContext(), MapActivity.class);
				startActivity(intent);
				
			}
		});
		
		Button btnMapJson = (Button) getActivity().findViewById(R.id.btnMapJson);
		btnMapJson .setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity().getApplicationContext(), MapActivity2.class);
				startActivity(intent);
				
			}
		});

	}
}