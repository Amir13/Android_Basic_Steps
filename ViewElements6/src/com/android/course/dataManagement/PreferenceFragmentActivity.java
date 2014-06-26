package com.android.course.dataManagement;

import com.android.course.main.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PreferenceFragmentActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preference_fragment);

		// Open a User Preferences Entry Activity
		final Button button = (Button) findViewById(R.id.check_pref_button);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(PreferenceFragmentActivity.this,
						PreferencesFragmentViewAndUpdate.class));
			}
		});
	}
}
