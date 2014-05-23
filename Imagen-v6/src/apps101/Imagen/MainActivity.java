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
package apps101.Imagen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextPaint;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final int REQUEST_CODE = 1;
	private static final String TAG = MainActivity.class.getSimpleName();
	private Bitmap mBitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate!");
		setContentView(R.layout.activity_main);

		OnClickListener listener = new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Do some Intent magic to open the Gallery? Yes!
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.setType("image/*");
				startActivityForResult(
						Intent.createChooser(intent, "Select..."), REQUEST_CODE);
			}
		};
		findViewById(R.id.button1).setOnClickListener(listener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

			Uri uri = data.getData();

			Log.d(TAG, uri.toString());
			Toast.makeText(getApplicationContext(), uri.toString(),
					Toast.LENGTH_LONG).show();
			try {
				InputStream stream = getContentResolver().openInputStream(uri);

				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inJustDecodeBounds = true;

				BitmapFactory.decodeStream(stream, null, options);
				stream.close();

				int w = options.outWidth;
				int h = options.outHeight;
				Log.d(TAG, "Bitmap raw size:" + w + " x " + h);

				int displayW = getResources().getDisplayMetrics().widthPixels;
				int displayH = getResources().getDisplayMetrics().heightPixels;

				int sample = 1;

				while (w > displayW * sample || h > displayH * sample) {
					sample = sample * 2;
				}
				Log.d(TAG, "Sampling at " + sample);

				options.inJustDecodeBounds = false;
				options.inSampleSize = sample;

				stream = getContentResolver().openInputStream(uri);
				Bitmap bm = BitmapFactory.decodeStream(stream, null, options);
				stream.close();
				if (mBitmap != null) {
					mBitmap.recycle();
				}
				// Make a mutable bitmap...
				mBitmap = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(),
						Bitmap.Config.ARGB_8888);
				Canvas c = new Canvas(mBitmap);
				c.drawBitmap(bm, 0, 0, null);
				TextPaint tp = new TextPaint();
				tp.setTextSize(bm.getHeight() / 2);
				tp.setColor(0x800000ff); // AARRGGBB
				// 0xff....... Fully opaque
				// 0x00....... Fully transparent (useless!)
				c.drawText("Gotcha", 0, bm.getHeight() / 2, tp);

				bm.recycle();

				ImageView v = (ImageView) findViewById(R.id.imageView1);
				v.setImageBitmap(mBitmap);
			} catch (Exception e) {
				Log.e(TAG, "Decoding Bitmap", e);
			}

		}
	}

	public void saveAndShare(View v) {
		if (mBitmap == null) {
			return;
		}
		File path = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		Log.d(TAG, "saveAndShare path = " + path);
		path.mkdirs();

		// Note, for display purposes
		// SimpleDateFormat.getTimeInstance()
		// getDateTimeInstance() or getDateIntance
		// are more appropriate.
		// For filenames we can use the following specification
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());

		String filename = "Imagen_" + timestamp + ".jpg";
		// Alternatively ... use System.currentTimeMillis()

		// Creating a new File object in Java does not create a new
		// file on the device. The file object just represents
		// a location or path that may or may not exist
		File file = new File(path, filename);
		FileOutputStream stream;
		try {
			// This can fail if the external storage is mounted via USB
			stream = new FileOutputStream(file);
			mBitmap.compress(CompressFormat.JPEG, 100, stream);
			stream.close();
		} catch (Exception e) {
			Log.e(TAG, "saveAndShare (compressing):", e);
			return; // Do not continue
		}
		
		Uri uri = Uri.fromFile(file);
		
		// Tell Android that a new public picture exists
		
		Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		intent.setData(uri);
		sendBroadcast(intent);

		// Send the public picture file to my friend... 
		Intent share = new Intent(Intent.ACTION_SEND);
		share.setType("image/jpeg");
		share.putExtra(Intent.EXTRA_STREAM, uri);
		startActivity(Intent.createChooser(share, "Share using..."));
	}
}
