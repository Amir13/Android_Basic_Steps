package course.android.graphics;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.course.android.R;

public class ShapeProgramatically extends Activity {
	int alpha = 127;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shape_progra);

		int width = (int) getResources().getDimension(R.dimen.shape_width);
		int height = (int) getResources().getDimension(R.dimen.shape_height);
		int padding = (int) getResources().getDimension(R.dimen.shape_padding);

		// Get container View
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout_shape_pro);

		// Create Left Shape
		ShapeDrawable left = new ShapeDrawable(new OvalShape());
		left.getPaint().setColor(Color.CYAN);
		left.setIntrinsicWidth(width);
		left.setIntrinsicHeight(height);
		left.setAlpha(alpha);

		// Create Right Shape
		ShapeDrawable right = new ShapeDrawable(new OvalShape());
		right.getPaint().setColor(Color.RED);
		right.setIntrinsicWidth(width);
		right.setIntrinsicHeight(height);
		right.setAlpha(alpha);

		// Put shapes into ImageView
		ImageView leftView = new ImageView(getApplicationContext());
		leftView.setImageDrawable(left);
		leftView.setPadding(padding, padding, padding, padding);
		
		// Specify placement of ImageView within RelativeLayout
		RelativeLayout.LayoutParams leftParams = new RelativeLayout.LayoutParams(height, width);
		leftParams.addRule(RelativeLayout.CENTER_VERTICAL);
		leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		leftView.setLayoutParams(leftParams);
		layout.addView(leftView);
		
		// Put Magenta Shape into an ImageView
		ImageView rightView = new ImageView(getApplicationContext());
		rightView.setImageDrawable(right);
		rightView.setPadding(padding, padding, padding, padding);
		
		// Specify placement of ImageView within RelativeLayout	
		RelativeLayout.LayoutParams rightParams = new RelativeLayout.LayoutParams(height, width);
		rightParams.addRule(RelativeLayout.CENTER_VERTICAL);
		rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		
		rightView.setLayoutParams(rightParams);
		layout.addView(rightView);
		
	}

}
