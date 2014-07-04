package com.android.course.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.course.service.AidlServiceClient;
import com.android.course.service.ForegroundServiceClientActivity;
import com.android.course.service.KeyServiceClient;
import com.android.course.service.MessengerServiceClientActivity;
import com.android.course.service.MyService;

public class ThridFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View root = inflater.inflate(R.layout.fragment_thrid, container, false);
		return root;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		final Button btnService = (Button) getActivity().findViewById(
				R.id.btnService);
		btnService.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getActivity()
						.getApplicationContext(), MyService.class);

				// put the message for the service
				intent.putExtra(MyService.MSG, "Invoking the service");

				// start the service
				getActivity().getApplicationContext().startService(intent);

			}
		});

		final Button btnRemoteService = (Button) getActivity().findViewById(
				R.id.btnRemoteService);
		btnRemoteService.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getActivity()
						.getApplicationContext(),
						ForegroundServiceClientActivity.class);
				startActivity(intent);

			}
		});

		final Button btnMessenger = (Button) getActivity().findViewById(
				R.id.btnMessenger);
		btnMessenger.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getActivity()
						.getApplicationContext(),
						MessengerServiceClientActivity.class);
				startActivity(intent);

			}
		});

		final Button btnAidl = (Button) getActivity()
				.findViewById(R.id.btnAidl);
		btnAidl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getActivity()
						.getApplicationContext(), AidlServiceClient.class);
				startActivity(intent);

			}
		});
		
		final Button btnExternal = (Button) getActivity()
				.findViewById(R.id.btnExternal);
		btnExternal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getActivity()
						.getApplicationContext(), KeyServiceClient.class);
				startActivity(intent);

			}
		});

	}

}