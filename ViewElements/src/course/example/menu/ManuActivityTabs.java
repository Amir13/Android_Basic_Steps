package course.example.menu;

import course.example.main.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ManuActivityTabs extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manu_activity_tabs);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manu_activity_tabs, menu);
		return true;
	}

}