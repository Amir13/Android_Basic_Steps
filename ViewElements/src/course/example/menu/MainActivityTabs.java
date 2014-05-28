package course.example.menu;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import course.example.main.R;

public class MainActivityTabs extends Activity {

	protected static final String THUMBNAIL = "thumbnail";

	private static final String ORIGINAL_ORDER = "Original Order";
	private static final String REVERSE_ORDER = "Reverse Order";
	
	private ArrayList<Integer> thumbIds = new ArrayList<Integer>(
			Arrays.asList(R.drawable.image1, R.drawable.image2,
					R.drawable.image3, R.drawable.image4, R.drawable.image5,
					R.drawable.image6, R.drawable.image7, R.drawable.image8,
					R.drawable.image9, R.drawable.image10, R.drawable.image11,
					R.drawable.image12));
	
	private ArrayList<Integer> thumbIdsReverse = new ArrayList<Integer>(
			Arrays.asList(R.drawable.image12, R.drawable.image11,
					R.drawable.image10, R.drawable.image9, R.drawable.image8,
					R.drawable.image7, R.drawable.image6, R.drawable.image5,
					R.drawable.image4, R.drawable.image3, R.drawable.image2,
					R.drawable.image1));

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_tabs);

		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		GridTabFragment tab1 = new GridTabFragment();
		Bundle args = new Bundle();
		args.putIntegerArrayList(THUMBNAIL, thumbIds);
		tab1.setArguments(args);
		actionBar.addTab(actionBar.newTab().setText(ORIGINAL_ORDER)
				.setTabListener(new TabListener(tab1)));

		GridTabFragment tab2 = new GridTabFragment();
		args = new Bundle();
		args.putIntegerArrayList(THUMBNAIL, thumbIdsReverse);
		tab2.setArguments(args);
		actionBar.addTab(actionBar.newTab().setText(REVERSE_ORDER)
				.setTabListener(new TabListener(tab2)));

	}

	public static class TabListener implements ActionBar.TabListener {
		private final Fragment fragment;

		public TabListener(Fragment f) {
			fragment = f;
		}

		@Override
		public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		}

		@Override
		public void onTabSelected(Tab arg0, FragmentTransaction ft) {
			if (null != fragment ) {
				ft.replace(R.id.fragment_layout, fragment);
			}
		}

		@Override
		public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
			if (null != fragment)
				arg1.remove(fragment);
		}

	}

}
