package course.android.animation;

import android.app.Activity;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.course.android.R;

public class TransitionView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_animation_transition);

		TransitionDrawable transition = (TransitionDrawable) getResources()
				.getDrawable(R.drawable.transition);
		// Make visible the first transition and then fade out until second
		// transition arrives.	
		transition.setCrossFadeEnabled(true);

		((ImageView) findViewById(R.id.imageViewTrans))
				.setImageDrawable(transition);

		transition.startTransition(3000);
	}
}
