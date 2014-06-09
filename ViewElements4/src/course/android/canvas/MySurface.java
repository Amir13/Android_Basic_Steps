package course.android.canvas;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.RelativeLayout;

import com.course.android.R;

public class MySurface extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_canvas);

		RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.simple_canvas);
		final MyView view = new MyView(getApplicationContext(),
				BitmapFactory.decodeResource(getResources(), R.drawable.b128));

		relativeLayout.addView(view);
	}

	private class MyView extends SurfaceView implements SurfaceHolder.Callback {

		private final Bitmap bitmap;
		private final int dimensions, dimensionsAdj;
		private final DisplayMetrics displayMetrics;
		private final int width, height;
		private float mX, mY, mDx, mDy, rotation;
		private final SurfaceHolder surfaceHolder;
		private final Paint painter = new Paint();
		private Thread drawingThread;

		private static final int MOVE_STEP = 1;
		private static final float ROT_STEP = 1.0f;

		public MyView(Context context, Bitmap bitmap) {
			super(context);

			dimensions = (int) getResources().getDimension(
					R.dimen.canvas_height);
			this.bitmap = Bitmap.createScaledBitmap(bitmap, dimensions, dimensions, false);
			
			dimensionsAdj = dimensions /2;
			
			displayMetrics = new DisplayMetrics();
			MySurface.this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
			
			width = displayMetrics.widthPixels;
			height = displayMetrics.heightPixels;
			
			Random r = new Random();
			mX = (float) r.nextInt(height);
			mY = (float) r.nextInt(width);
			mDx= (float) r.nextInt(height) / height;
			mDx *= r.nextInt(2) == 1? MOVE_STEP:-1*MOVE_STEP ; 
			mDy= (float) r.nextInt(width) / width;
			mDy *= r.nextInt(2) == 1? MOVE_STEP:-1*MOVE_STEP ;
			rotation = 1.0f;
			
			painter.setAntiAlias(true);
			
			surfaceHolder = getHolder();
			surfaceHolder.addCallback(this);
		}
		
		private void drawBubble(Canvas canvas) {
			canvas.drawColor(Color.DKGRAY);
			rotation += ROT_STEP;
			canvas.rotate(rotation, mY + dimensionsAdj, mX
					+ dimensionsAdj);
			canvas.drawBitmap(bitmap, mY, mX, painter);
		}

		private boolean move() {
			mX += mDx;
			mY += mDy;
			if (mX < 0 - dimensions
					|| mX > height + dimensions
					|| mY < 0 - dimensions
					|| mY > width + dimensions) {
				return false;
			} else {
				return true;
			}
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {

		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			drawingThread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					Canvas canvas = null;
					while (!Thread.currentThread().isInterrupted() && move()) {
						canvas = surfaceHolder.lockCanvas();
						if (null != canvas) {
							drawBubble(canvas);
							surfaceHolder.unlockCanvasAndPost(canvas);
						}
					}
				}
			});
			drawingThread.start(); 
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			if (null != drawingThread)
				drawingThread.interrupt();

		}

	}

}
