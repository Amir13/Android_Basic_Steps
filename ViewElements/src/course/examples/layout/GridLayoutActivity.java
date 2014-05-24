package course.examples.layout;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import course.examples.Fragments.DynamicLayout.R;

public class GridLayoutActivity extends Activity {

	protected static final String EXTRA_RES_ID = "POS";
	private ArrayList<Integer> mThumbIdsFlowers = new ArrayList<Integer>(
			Arrays.asList(R.drawable.image1, R.drawable.image2,
					R.drawable.image3, R.drawable.image4, R.drawable.image5,
					R.drawable.image6, R.drawable.image7, R.drawable.image8,
					R.drawable.image9, R.drawable.image10, R.drawable.image11,
					R.drawable.image12));
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid_layout);
		
		GridView gridview = (GridView) findViewById(R.id.gridview);
		
		
		gridview.setAdapter(new ImageAdapter(this, mThumbIdsFlowers));
		
		gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View arg1, int position,
					long id) {
				Intent intent = new Intent(GridLayoutActivity.this,
						ImageViewActivity.class);
				intent.putExtra("EXTRA_RES_ID", (int) id);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.grid_layout, menu);
		return true;
	}

}
