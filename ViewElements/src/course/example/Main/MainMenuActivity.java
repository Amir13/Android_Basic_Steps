package course.example.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import course.example.fragment.elements.MainActivity;
import course.example.layout.GridLayoutActivity;
import course.example.layout.LinealLayoutActivity;
import course.example.layout.RelativeLayoutActivity;
import course.example.layout.TableLayoutActivity;
import course.example.main.R;
import course.example.menu.ManuActivityTabs;
import course.example.menu.MenuActivity;
import course.example.viewGroups.ListView;
import course.example.viewGroups.SpinnerVG;

public class MainMenuActivity extends Activity {
	Intent intent = new Intent();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);

		final Button buttonElementsView = (Button) findViewById(R.id.buttonViewElements);
		final Button buttonListView = (Button) findViewById(R.id.buttonViewGroups);
		final Button buttonSpinnerView = (Button) findViewById(R.id.buttonSpinnerViewGroup);

		final Button buttonLinealLayout = (Button) findViewById(R.id.buttonLinealLayout);
		final Button buttonRealtiveLayout = (Button) findViewById(R.id.buttonRelativeLayout);
		final Button buttonTableLayout = (Button) findViewById(R.id.buttonTableLayout);
		final Button buttonGridLayout = (Button) findViewById(R.id.buttonGridLayout);
		
		final Button buttonMenu = (Button) findViewById(R.id.buttonMenu);
		final Button buttonMenuTabs = (Button) findViewById(R.id.buttonMenuTabs);

		final Button buttonDialog = (Button) findViewById(R.id.buttonDialog);
		
		buttonElementsView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), MainActivity.class);

				startActivity(intent);
			}
		});

		buttonListView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				intent = new Intent();
				intent.setClass(getApplicationContext(), ListView.class);

				startActivity(intent);
			}
		});

		buttonSpinnerView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				intent = new Intent();
				intent.setClass(getApplicationContext(), SpinnerVG.class);

				startActivity(intent);
			}
		});

		buttonLinealLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				intent.setClass(getApplicationContext(),
						LinealLayoutActivity.class);

				startActivity(intent);
			}
		});

		buttonRealtiveLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				intent = new Intent();
				intent.setClass(getApplicationContext(), RelativeLayoutActivity.class);

				startActivity(intent);
			}
		});
		
		buttonTableLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				intent = new Intent();
				intent.setClass(getApplicationContext(), TableLayoutActivity.class);

				startActivity(intent);
			}
		});
		
		buttonGridLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				intent = new Intent();
				intent.setClass(getApplicationContext(), GridLayoutActivity.class);

				startActivity(intent);
			}
		});
		
		buttonMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				intent = new Intent();
				intent.setClass(getApplicationContext(), MenuActivity.class);

				startActivity(intent);
			}
		});
		
		buttonMenuTabs.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				intent = new Intent();
				intent.setClass(getApplicationContext(), ManuActivityTabs.class);

				startActivity(intent);
			}
		});
		
		buttonDialog.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				intent = new Intent();
				intent.setClass(getApplicationContext(), ManuActivityTabs.class);

				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

}
