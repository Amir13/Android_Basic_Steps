package course.examples.Fragments.DynamicLayout;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class ButtonFragment extends Fragment {
	int count = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.button, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		final Button button = (Button) getActivity().findViewById(R.id.button);

		if (button != null)
			button.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					button.setText("Got pressed: " + ++count);
					 
					Intent invokerIntent = new Intent();
					invokerIntent.setClassName("course.examples.Fragments.DynamicLayout", "course.examples.Fragments.DynamicLayout.InvokingActivity");
					
					startActivity(invokerIntent);

				}

			});
		
		
		final ToggleButton toggleButton = (ToggleButton) getActivity().findViewById(R.id.toggleButton);
		final LinearLayout llayout = (LinearLayout) getActivity().findViewById(R.id.buttonLyout);
		
		toggleButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (toggleButton.isChecked()) {
					llayout.setBackgroundColor(0xFFF3F3F3);
				} else {
					llayout.setBackgroundColor(0xFF000000);
				}
				
			}
		});
		
	}

	
}
