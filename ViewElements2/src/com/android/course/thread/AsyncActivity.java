package com.android.course.thread;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.course.viewelement.R;


public class AsyncActivity extends Activity {

	private ImageView imageView;
	private ProgressBar progressbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_thread);

		imageView = (ImageView) findViewById(R.id.imageLoader);
		progressbar = (ProgressBar) findViewById(R.id.progressBar);

		Button buttonImage = (Button) findViewById(R.id.buttonImage);
		buttonImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new LoadImage().execute(R.drawable.image8);
			}
		});

		Button buttonToast = (Button) findViewById(R.id.buttonToast);
		buttonToast.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(
						getApplicationContext(),
						"Launching a message while image is loading with AsyncTask",
						Toast.LENGTH_SHORT).show();
			}
		});

	}

	class LoadImage extends AsyncTask<Integer, Integer, Bitmap> {

		@Override
		protected void onPreExecute() {
			progressbar.setVisibility(ProgressBar.VISIBLE);
		}

		@Override
		protected Bitmap doInBackground(Integer... params) {
			Bitmap tmp = BitmapFactory.decodeResource(getResources(),
					params[0]);
			for (int i = 0; i < 11; i++) {

				try {
					Thread.sleep(500);
				} catch (Exception e) {
					Log.e("AsyncActivity", e.getMessage());
				}

				publishProgress(i * 10);

			}
			return tmp;
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			progressbar.setProgress(values[0]);
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			progressbar.setVisibility(ProgressBar.INVISIBLE);
			imageView.setImageBitmap(result);
			Log.i("LoadImage", "Image was loaded");
		}

	}
}
