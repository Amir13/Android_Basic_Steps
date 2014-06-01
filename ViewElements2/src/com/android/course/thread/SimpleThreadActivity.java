package com.android.course.thread;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.course.viewelement.R;

public class SimpleThreadActivity extends Activity {

	private Bitmap bitmap;
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_thread);

		imageView = (ImageView) findViewById(R.id.imageLoader);

		Button buttonImage = (Button) findViewById(R.id.buttonImage);
		buttonImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				loadImage();
			}
		});

		Button buttonToast = (Button) findViewById(R.id.buttonToast);
		buttonToast.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(),
						"Launching a message while image is loading",
						Toast.LENGTH_SHORT).show();
			}
		});

	}

	private void loadImage() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(2500);
				} catch (Exception e) {
					Log.e("SimpleThreadActivity", e.getMessage());
				}

				bitmap = BitmapFactory.decodeResource(getResources(),
						R.drawable.image8);

				imageView.post(new Runnable() {

					@Override
					public void run() {
						imageView.setImageBitmap(bitmap);
					}
				});
			}
		}).start();
	}

}
