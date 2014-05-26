package course.examples.viewGroups;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TextView;
import course.example.main.R;

public class ViewGroups extends Fragment{

	
	private static final String TAG = "ViewGroups";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");
		
		return inflater.inflate(R.layout.viewgroups, container, false);
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		final TextView tv = (TextView) getActivity().findViewById(R.id.textView);

		final OnClickListener radioListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				RadioButton rb = (RadioButton) v;
				tv.setText(rb.getText() + " chosen");
			}
		};
		final RadioButton choice1 = (RadioButton) getActivity().findViewById(R.id.choice1);
		choice1.setOnClickListener(radioListener);

		final RadioButton choice2 = (RadioButton) getActivity().findViewById(R.id.choice2);
		choice2.setOnClickListener(radioListener);

		final RadioButton choice3 = (RadioButton) getActivity().findViewById(R.id.choice3);
		choice3.setOnClickListener(radioListener);
	}
}
