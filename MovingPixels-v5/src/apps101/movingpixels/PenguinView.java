/*
Copyright (c) 2014 Lawrence Angrave
Dual licensed under Apache2.0 and MIT Open Source License (included below): 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */
package apps101.movingpixels;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PenguinView extends View implements OnSharedPreferenceChangeListener {
	private Bitmap mBitmap;
	private Bitmap mPenguin;
	private int mPHwidth; // Penguin half height
	private int mPHheight; // Penguin half height
	private boolean mTouching;
	private Paint mPaint;
	private float x;
	private float y;
	private float vx = 1;
	private float vy = 1;
	private Canvas mCanvas;
	private TextPaint mTextPaint;
	private String mPenguinName;
	private boolean mEnableGravity;
	private SharedPreferences mPrefs;

	public PenguinView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public PenguinView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public PenguinView(Context context) {
		super(context);
		init();

	}
	
	@Override
	public void onSharedPreferenceChanged(SharedPreferences p, String key) {
		// Simple but inefficient: 
		// For each preference item that changes we will read all of the preferences again
		mPenguinName = mPrefs.getString("name", "no name");
		mEnableGravity = mPrefs.getBoolean("gravity", true);
	}
	
	private void init() {
		mPrefs = PreferenceManager
				.getDefaultSharedPreferences(getContext());
		// If we are going to cache the name and gravity
		// Then we better find out when they are changed
		mPrefs.registerOnSharedPreferenceChangeListener(this);
		
		onSharedPreferenceChanged(null, null);// Use the code above to read the preferences

		Bitmap original = BitmapFactory.decodeResource(getResources(),
				R.drawable.rain_penguin_180);
		int desired = getResources().getDimensionPixelSize(R.dimen.penguin);
		mPenguin = Bitmap.createScaledBitmap(original, desired, desired, true);
		// Calculate the half width and height
		mPHwidth = mPenguin.getWidth() / 2;
		mPHheight = mPenguin.getHeight() / 2;

		mBitmap = Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
		mCanvas.drawColor(0xff808080); // Opaque Gray or 'grey'
		mTextPaint = new TextPaint();
		mTextPaint.setTextSize(32);
		mTextPaint.setColor(0x800000ff);

		mPaint = new Paint();
		// mPaint.setColor(0xff0000ff);
		mPaint.setStrokeWidth(0);
		mPaint.setFilterBitmap(false);
		// paint.setAntiAlias(false);
		// paint.setStyle(Style.STROKE);
		// Surprise!
		// The end point (3,3) is NOT drawn by drawLine.
		// Nor by drawRect with the default fill style.
		// mCanvas.drawLine(1, 1, 3, 3, mPaint);
		// mCanvas.drawRect(1, 1, 3, 3, mPaint);
		OnTouchListener onTouch = new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// Log.d(TAG, "onTouch!" + event.getAction());
				// This app is not multi-touch aware:
				// When the user performs a multi-touch event the app will get
				// some large action values (because the 'action' parameter
				// encodes additional multi-touch information)
				// So they are ignored by the app
				int action = event.getAction();
				if (action == MotionEvent.ACTION_UP
						|| action == MotionEvent.ACTION_CANCEL) {
					mTouching = false;
				}
				if (action == MotionEvent.ACTION_DOWN) {
					mTouching = true;
				}

				if (action == MotionEvent.ACTION_DOWN
						|| action == MotionEvent.ACTION_MOVE) {
					x = event.getX() - mPHheight;
					y = event.getY() - mPHwidth;
					vx = 0;
					vy = 0;
				}
				float scaleX = mBitmap.getWidth() / ((float) v.getWidth());
				float scaleY = mBitmap.getHeight() / ((float) v.getHeight());
				float pointX = event.getX() * scaleX;
				float pointY = event.getY() * scaleY;
				mCanvas.drawCircle(pointX, pointY, 2, mPaint);
				return true;
			}
		};
		setOnTouchListener(onTouch);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// canvas.drawColor(0xffff9900); // Orange
		drawBackground(canvas);

		drawPenguin(canvas);

		doPenguinPhysics();

		// In 20ms (1/50th second) this view will need to be redrawn
		postInvalidateDelayed(20);
	}

	private void doPenguinPhysics() {
		if (!mEnableGravity) { // ! means not
			return;
		}
		if (y + 2 * mPHheight + vy + 1 >= this.getHeight()) {
			vy = -0.8f * vy;
		} else {
			vy = vy + 1;
		}
		x = x + vx;
		y = y + vy;
	}

	private void drawPenguin(Canvas canvas) {
		mPaint.setColor(0x80ffffff); // White
		mPaint.setStyle(Style.FILL_AND_STROKE);
		mPaint.setFilterBitmap(false);

		float angle = SystemClock.uptimeMillis() / 10.0f;
		canvas.translate(x, y);

		if (mTouching) {
			canvas.scale(1.2f, 1.2f, mPHwidth, mPHheight);
		}
		
		canvas.drawCircle(mPHwidth, mPHheight, mPHheight, mPaint);
		canvas.rotate(angle, mPHwidth, mPHheight);

		canvas.drawText(mPenguinName, 100, 100, mTextPaint);
		canvas.drawBitmap(mPenguin, 0, 0, mPaint);
	}

	private void drawBackground(Canvas canvas) {
		float scaleX = this.getWidth() / ((float) mBitmap.getWidth());
		float scaleY = this.getHeight() / ((float) mBitmap.getHeight());
		// Log.d("MainActivity","Scale:"+scaleX+","+scaleY);
		canvas.save();
		canvas.scale(scaleX, scaleY);
		canvas.drawBitmap(mBitmap, 0, 0, mPaint);
		canvas.restore();
	}


};