package course.example.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import course.example.main.R;

public class RelativeLayoutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_relative_layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.relative_layout, menu);
		return true;
	}

}
