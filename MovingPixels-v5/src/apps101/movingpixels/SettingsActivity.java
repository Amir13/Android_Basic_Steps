/*
Copyright (c) 2014 Lawrence Angrave
Dual licensed under Apache2.0 and MIT Open Source License (included below): 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */
package apps101.movingpixels;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingsActivity extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			showPreferencesPreHoneycomb();
		} else {
			showPreferencesFragmentStyle(savedInstanceState);
		}
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void showPreferencesFragmentStyle(Bundle savedInstanceState) {
		if (savedInstanceState == null) {
			FragmentTransaction transaction = getFragmentManager()
					.beginTransaction();
			Fragment fragment = new MyPreferencesFragment();
			transaction.replace(android.R.id.content, fragment);
			transaction.commit();
		}

	}

	@SuppressWarnings("deprecation")
	private void showPreferencesPreHoneycomb() {
		Log.d("Hurrah","Pre-Honeycomb!");
		addPreferencesFromResource(R.xml.penguin_prefs);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static class MyPreferencesFragment extends PreferenceFragment {
		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			Log.d("F", "I'm attached to an activity - I have a context!");
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			this.addPreferencesFromResource(R.xml.penguin_prefs);
			return super.onCreateView(inflater, container, savedInstanceState);
		}
	};
}
