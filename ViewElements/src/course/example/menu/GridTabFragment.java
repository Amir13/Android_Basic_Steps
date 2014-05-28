package course.example.menu;

import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import course.example.layout.ImageViewActivity;
import course.example.main.R;
import course.examples.layout.ImageAdapter;

public class GridTabFragment extends Fragment {
	
	protected static final String IMAGE_ID = "POS";
	
	private List<Integer> thumbnail;
	private GridView gridview;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		thumbnail = getArguments().getIntegerArrayList(MainActivityTabs.THUMBNAIL);
		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		gridview.setAdapter(new ImageAdapter(getActivity(), thumbnail));
		gridview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(getActivity(),
						ImageViewActivity.class);
				
				intent.putExtra(IMAGE_ID, (int)arg3);
				startActivity(intent);
				
			}
		});

	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.gridtab_fragment , container, false);
		gridview = (GridView) view.findViewById(R.id.gridview_tab);
		return view ; 
	}
	
}