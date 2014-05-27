package course.examples.fragment.dynamicLayout;

import course.examples.Fragments.DynamicLayout.R;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class CheckboxFragment extends Fragment {

	private static final String TAG = "CheckboxFragment";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
		
		setRetainInstance(true);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.checkbox, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		final CheckBox chkbox = (CheckBox) getActivity().findViewById(R.id.checkBox);
		
		chkbox.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

				if (chkbox.isChecked()) {
					chkbox.setText("I'm checked");
				} else {
					chkbox.setText("I'm not checked");
				}
			}
		});
		
		final RatingBar ratingBar = (RatingBar) getActivity().findViewById(R.id.ratingBar);
		final TextView tv = (TextView) getActivity().findViewById(R.id.textView);
		
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar arg0, float arg1, boolean arg2) {
				tv.setText("Rating: " + arg1);
			}
		});
		
	}

}