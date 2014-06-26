package com.android.course.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.course.touch.GestureActivity;
import com.android.course.touch.GestureBuilderActivity;
import com.android.course.touch.TouchActivity;

/**
 * A dummy fragment representing a section of the app, but that simply displays
 * dummy text.
 */
public class FirstFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
		case 1:

			View rootView = inflater.inflate(R.layout.touch_gestures,
					container, false);

			return rootView;

		default:
			rootView = inflater.inflate(R.layout.fragment_main_dummy,
					container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView.setText("a");
			return rootView;
		}

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		final Button btnTouch = (Button) getActivity().findViewById(
				R.id.btnTouch);
		if (btnTouch != null)
			btnTouch.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {

					Intent intent = new Intent(getActivity()
							.getApplicationContext(), TouchActivity.class);

					startActivity(intent);

				}

			});
		
		final Button btnGesture = (Button) getActivity().findViewById(
				R.id.btnGesture);
		if (btnGesture != null)
			btnGesture.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {

					Intent intent = new Intent(getActivity()
							.getApplicationContext(), GestureActivity.class);

					startActivity(intent);

				}

			});

		final Button btnGestureDetecture = (Button) getActivity().findViewById(
				R.id.btnGesture_detector);
		if (btnGestureDetecture != null)
			btnGestureDetecture.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {

					Intent intent = new Intent(getActivity()
							.getApplicationContext(),
							GestureBuilderActivity.class);

					startActivity(intent);

				}

			});
	}

}
