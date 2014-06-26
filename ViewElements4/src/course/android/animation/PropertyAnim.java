package course.android.animation;

import com.course.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

public class PropertyAnim extends Activity {

	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_graphics_xml);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

		imageView = (ImageView) findViewById(R.id.imageView1);

		if (hasFocus) {
			fadeIn.run();
		}
	}

	Runnable fadeIn = new Runnable() {

		@Override
		public void run() {
			imageView.animate().setDuration(3000)
					.setInterpolator(new LinearInterpolator()).alpha(1.0f)
					.withEndAction(rotate);
		}

	};

	Runnable rotate = new Runnable() {

		@Override
		public void run() {
			imageView.animate().setDuration(3000)
					.setInterpolator(new AccelerateInterpolator())
					.rotationBy(720.0f).withEndAction(translate);
		}

	};

	Runnable translate = new Runnable() {

		@Override
		public void run() {

			float value = 100f;
			imageView.animate().setDuration(3000)
					.setInterpolator(new OvershootInterpolator())
					.translationXBy(value).translationYBy(value)
					.withEndAction(scale);
			;
		}

	};
	
	Runnable scale = new Runnable() {
		public void run() {
			imageView.animate().setDuration(3000)
					.setInterpolator(new AnticipateInterpolator())
					.scaleXBy(1.0f).scaleYBy(1.0f).withEndAction(fadeOut);
		}
	};
	
	Runnable fadeOut = new Runnable() {
		public void run() {
			imageView.animate().setDuration(2000)
					.setInterpolator(new DecelerateInterpolator()).alpha(0.0f).withEndAction(lastFadeIn);
		}
	};
	
	Runnable lastFadeIn = new Runnable() {
		public void run() {
			imageView.animate().setDuration(2000)
					.setInterpolator(new LinearInterpolator()).alpha(1.0f);
		}
	};
}