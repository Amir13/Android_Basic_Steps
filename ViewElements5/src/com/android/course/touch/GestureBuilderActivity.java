package com.android.course.touch;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.course.main.R;

public class GestureBuilderActivity extends Activity implements
		OnGesturePerformedListener {

	private FrameLayout frame;
	private int bgColor = 0;
	private int firstColor, startColor = Color.GRAY;
	private GestureLibrary library;
	private RelativeLayout relativeLayout;

	private static final String PAL = "Pal";
	private static final String ROD = "Rod";
	private static final String SYMBOL = "Symbol";
	private static final String SYMBOLX = "SymbolX";
	private static final String INFINITE = "Infinite";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesture_builder);

		frame = (FrameLayout) findViewById(R.id.frame);
		firstColor = bgColor = new Random().nextInt(0xFFFFFF) | 0xFF000000;
		frame.setBackgroundColor(startColor);

		relativeLayout = (RelativeLayout) findViewById(R.id.main_layout);
		relativeLayout.setBackgroundColor(startColor);
		
		frame.setBackground(getResources().getDrawable(R.drawable.symbols));
		
		library = GestureLibraries.fromRawResource(this, R.raw.gestures);
		if (!library.load()) {
			finish();
		}

		// Make this the target of gesture detection callbacks
		GestureOverlayView gestureView = (GestureOverlayView) findViewById(R.id.overlay);
		gestureView.addOnGesturePerformedListener(this);

	}

	@Override
	public void onGesturePerformed(GestureOverlayView arg0, Gesture gesture) {

		// Get gesture predictions
		ArrayList<Prediction> predictionList = library.recognize(gesture);

		// Get highest-ranked prediction
		if (predictionList.size() > 0) {
			Prediction prediction = predictionList.get(0);

			// Ignore weak predictions
			if (prediction.score > 2.0) {

				if (prediction.name.equals(PAL)) {
					bgColor -= 100;
					frame.setBackgroundColor(bgColor);
					Toast.makeText(this, PAL, Toast.LENGTH_SHORT).show();
				} else if (prediction.name.equals(ROD)) {
					bgColor += 100;
					frame.setBackgroundColor(bgColor);
					Toast.makeText(this, ROD, Toast.LENGTH_SHORT).show();
				} else if (prediction.name.equals(SYMBOL)) {
					relativeLayout.setBackgroundColor(bgColor);
					Toast.makeText(this, SYMBOL, Toast.LENGTH_SHORT).show();
				} else if (prediction.name.equals(SYMBOLX)) {
					relativeLayout.setBackgroundColor(startColor);
					frame.setBackgroundColor(firstColor);
					Toast.makeText(this, SYMBOLX, Toast.LENGTH_SHORT).show();
				} else if (prediction.name.equals(INFINITE)) {
					
					frame.setBackground(getResources().getDrawable(R.drawable.symbols));
					Toast.makeText(this, "Infinite", Toast.LENGTH_SHORT).show();
				}

			}else{
				Toast.makeText(this, "No Prediction", Toast.LENGTH_SHORT).show();
			}
		}

	}

}
