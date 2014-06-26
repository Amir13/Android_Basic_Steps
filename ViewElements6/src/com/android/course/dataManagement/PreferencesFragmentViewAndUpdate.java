package com.android.course.dataManagement;

import com.android.course.main.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

public class PreferencesFragmentViewAndUpdate extends Activity {

	private static final String PREFERENCE_KEY = "user_name";
	private static final String ANOTHER_KEY = "pref_value";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preference_fragment_update);
	}

	// Fragment that displays the username preference
	public static class UserPreferenceFragment extends PreferenceFragment {

		private OnSharedPreferenceChangeListener listener;
		private Preference preference;
		private Preference preference2;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);

			// Load the preferences from an XML resource
			addPreferencesFromResource(R.xml.preference_user);

			// Get the username Preference
			preference = (Preference) getPreferenceManager().findPreference(
					PREFERENCE_KEY);
			preference2 = (Preference) getPreferenceManager().findPreference(
					ANOTHER_KEY);

			// Attach a listener to update summary when username changes
			listener = new OnSharedPreferenceChangeListener() {

				@Override
				public void onSharedPreferenceChanged(
						SharedPreferences sharedPreferences, String key) {

					preference.setSummary(sharedPreferences.getString(
							PREFERENCE_KEY, "None Set"));
					preference2.setSummary(sharedPreferences.getString(
							ANOTHER_KEY, "Set a value"));
				}
			};

			// Get SharedPreferences object managed by the PreferenceManager for
			// this Fragment
			SharedPreferences sp = getPreferenceManager().getSharedPreferences();
			
			// Register a listener on the SharedPreferences object
			sp.registerOnSharedPreferenceChangeListener(listener);
			
			// Invoke callback manually to display the current username
			listener.onSharedPreferenceChanged(sp, PREFERENCE_KEY);

			// Invoke callback manually to display the second preference
			listener.onSharedPreferenceChanged(sp, ANOTHER_KEY);

		}
	}

}
