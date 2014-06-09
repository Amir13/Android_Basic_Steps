package course.android.graphics;

import com.course.android.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class GraphicsProgrammatically extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_graphics_progra);
		
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout_frame);
		
		ImageView view = new ImageView(getApplicationContext());
		view.setImageDrawable(getResources().getDrawable(R.drawable.image7));
		
		int width = (int) getResources().getDimension(R.dimen.image_width);
		int height = (int) getResources().getDimension(R.dimen.image_height);
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width,height);
		
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		
		view.setLayoutParams(params);
		
		layout.addView(view);
		
	}
}