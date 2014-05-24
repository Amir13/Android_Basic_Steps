package course.example.layout;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private Context context;
	private List<Integer> thumbIds;
	private static final int PADDING = 8;
	private static final int WIDTH = 250;
	private static final int HEIGHT = 250;
	
	
	public ImageAdapter(Context c, List<Integer> ids) {
		context = c;
		this.thumbIds = ids;
	}

	// Return the name of item handle by the Adapter
	@Override
	public int getCount() {
		return thumbIds.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	// Will get called to provide the ID that
	// is passed to OnItemClickListener.onItemClick()
	@Override
	public long getItemId(int arg0) {
		return thumbIds.get(arg0);
	}

	// create a new ImageView for each item referenced by the Adapter
	//
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = (ImageView) convertView;
		
		// if convertView's not recycled, initialize some attributes
		if (imageView == null) {
			imageView = new ImageView(context);
			imageView.setLayoutParams(new GridView.LayoutParams(WIDTH, HEIGHT));
			imageView.setPadding(PADDING, PADDING, PADDING, PADDING);
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		}

		imageView.setImageResource(thumbIds.get(position));
		return imageView;
	}

}
