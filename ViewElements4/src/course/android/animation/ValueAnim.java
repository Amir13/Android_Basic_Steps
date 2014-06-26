package course.android.animation;

import com.course.android.R;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

public class ValueAnim extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_animation_frame);
		
		final ImageView imageView = (ImageView) findViewById(R.id.animation_frame);
		
		ValueAnimator animator = ValueAnimator.ofObject(new ArgbEvaluator(), Color.RED, Color.BLUE);
		
		animator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator value) {

				imageView.setBackgroundColor((Integer)value.getAnimatedValue());
			}
		});
		
		animator.setDuration(5000);
		animator.start();
	}
	
}
