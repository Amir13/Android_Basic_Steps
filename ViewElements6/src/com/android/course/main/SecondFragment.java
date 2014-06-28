package com.android.course.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.course.contentProvider.ContentInsertActivity;
import com.android.course.contentProvider.ContentInsertMyAdapterActivity;
import com.android.course.contentProvider.ContentLoaderActivity;
import com.android.course.contentProvider.ContentProviderActivity;

public class SecondFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View root = inflater.inflate(R.layout.fragment_second, container,false);
		
		return root;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		final Button btnContent = (Button) getActivity().findViewById(R.id.btnContent);
		btnContent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getActivity().getApplicationContext(),
						ContentProviderActivity.class);
				startActivity(intent);

			}
		});
		
		final Button btnContentLoader = (Button) getActivity().findViewById(R.id.btnContentLoader);
		btnContentLoader.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent intent = new Intent(getActivity().getApplicationContext(),
						ContentLoaderActivity.class);
				startActivity(intent);
				
			}
		});
		
		final Button btnInsert = (Button) getActivity().findViewById(R.id.btnInsert);
		btnInsert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent intent = new Intent(getActivity().getApplicationContext(),
						ContentInsertActivity.class);
				startActivity(intent);
				
			}
		});
		
		final Button btnInsert2 = (Button) getActivity().findViewById(R.id.btnInsert2);
		btnInsert2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent intent = new Intent(getActivity().getApplicationContext(),
						ContentInsertMyAdapterActivity.class);
				startActivity(intent);
				
			}
		});
		
	}
}
