package course.example.menu;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import course.example.main.R;

public class MenuActivity extends Activity {

	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		TextView tv = (TextView) findViewById(R.id.text_view);

		registerForContextMenu(tv);

		// Add the left fragment
		fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction
				.add(R.id.menu_left_fragment, new MenuLeftFragment());
		fragmentTransaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menu_agenda:
			Toast.makeText(getApplicationContext(),
					"you've been selected: " + item.toString(),
					Toast.LENGTH_LONG).show();
			return true;

		case R.id.actionbar_item:
			Toast.makeText(getApplicationContext(),
					"This option was added by MenuLeftFragment",
					Toast.LENGTH_SHORT).show();
			return true;
		default:
			Toast.makeText(getApplicationContext(),
					"Your selection was: " + item.toString(), Toast.LENGTH_LONG)
					.show();
			return true;
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.menu_context, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		Toast.makeText(getApplicationContext(),
				"Menu Selection: " + item.toString(), Toast.LENGTH_SHORT)
				.show();
		return true;
	}
}
