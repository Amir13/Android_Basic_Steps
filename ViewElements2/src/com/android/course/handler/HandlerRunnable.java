package com.android.course.handler;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.course.viewelement.R;

public class HandlerRunnable extends Activity {

	private ImageView imageView;
	private ProgressBar progressBar;
	private Bitmap bitmap;
	private final Handler handler = new Handler();
	private TextView textViewer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_thread);

		imageView = (ImageView) findViewById(R.id.imageLoader);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		textViewer = (TextView) findViewById(R.id.textViewLoader);

		textViewer
				.setText("This example use a Handler to load the image through Runnables");

		Button buttonImage = (Button) findViewById(R.id.buttonImage);
		buttonImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new LoadImage(R.drawable.image8)).start();
			}
		});

		Button buttonToast = (Button) findViewById(R.id.buttonToast);
		buttonToast.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(
						getApplicationContext(),
						"Launching a message while image is loading using a Handler with Runnables",
						Toast.LENGTH_SHORT).show();
			}
		});

	}

	private class LoadImage implements Runnable {
		private int imageId;

		public LoadImage(int imageId) {
			this.imageId = imageId;
		}

		@Override
		public void run() {

			handler.post(new Runnable() {

				@Override
				public void run() {
					progressBar.setVisibility(ProgressBar.VISIBLE);
					bitmap = BitmapFactory.decodeResource(getResources(), imageId);
				}
			});

			

			for (int i = 0; i <= 10; i++) {

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Log.e("HandlerRunnable", e.getMessage());
				}

				final int progress = i;

				handler.post(new Runnable() {

					@Override
					public void run() {
						progressBar.setProgress(progress * 10);
					}
				});

			}

			handler.post(new Runnable() {

				@Override
				public void run() {
					imageView.setImageBitmap(bitmap);
				}
			});

			handler.post(new Runnable() {

				@Override
				public void run() {
					progressBar.setVisibility(ProgressBar.INVISIBLE);
				}
			});
		}

	}
}
