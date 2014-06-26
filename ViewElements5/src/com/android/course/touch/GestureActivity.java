package com.android.course.touch;

import com.android.course.main.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class GestureActivity extends Activity {

	private int currentLayoutState, count;
	private ViewFlipper flipper;
	private TextView textView1, textView2;
	private GestureDetector gd;
	private final static int LEFT = 0;
	private final static int RIGHT = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesture);

		currentLayoutState = 0;

		flipper = (ViewFlipper) findViewById(R.id.view_flipper);
		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);

		textView1.setText(String.valueOf(count));
		gd = new GestureDetector(this,
				new GestureDetector.SimpleOnGestureListener() {
					@Override
					public boolean onFling(MotionEvent e1, MotionEvent e2,
							float velocityX, float velocityY) {

						if (velocityX < -10.0f) {
							currentLayoutState = currentLayoutState == 0 ? 1
									: 0;
							switchLayout(currentLayoutState, RIGHT);
						}else if(velocityX > 10.0f){
							currentLayoutState = currentLayoutState == 1 ? 0
									: 1;
							switchLayout(currentLayoutState, LEFT);
						}
						

						return true;
					}
				});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return gd.onTouchEvent(event);
	}

	public void switchLayout(int layout, int direction) {
		if (direction == RIGHT) {
			flipper.setInAnimation(inFromRightAnimation());
			flipper.setOutAnimation(inFromLeftAnimation());

			count++;
		}else{
			count--;
			flipper.setInAnimation(inFromRightAnimation2());
			flipper.setOutAnimation(inFromLeftAnimation2());
		}
		
		

		if (layout == 0) {
			textView1.setText(String.valueOf(count));
		} else {
			textView2.setText(String.valueOf(count));
		}

		flipper.showPrevious();
	}

	private Animation inFromRightAnimation() {
		Animation animation = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		animation.setDuration(500);
		animation.setInterpolator(new LinearInterpolator());
		return animation;
	}

	private Animation inFromLeftAnimation() {
		Animation animation = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		animation.setDuration(500);
		animation.setInterpolator(new LinearInterpolator());
		return animation;
	}
	
	private Animation inFromRightAnimation2() {
		Animation animation = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		animation.setDuration(500);
		animation.setInterpolator(new LinearInterpolator());
		return animation;
	}

	private Animation inFromLeftAnimation2() {
		Animation animation = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		animation.setDuration(500);
		animation.setInterpolator(new LinearInterpolator());
		return animation;
	}
}