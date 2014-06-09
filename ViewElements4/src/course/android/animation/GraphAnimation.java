package course.android.animation;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.course.android.R;

public class GraphAnimation extends Activity {
	private ImageView imageView;
	private Animation animation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation_frame);

		final Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.image7);
		
		imageView = (ImageView) findViewById(R.id.animation_frame);
		imageView.setImageBitmap(bitmap);
		
		
		
		
		animation = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.animation);

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			imageView.startAnimation(animation);
		}
	}
}
