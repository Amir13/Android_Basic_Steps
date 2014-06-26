package com.android.course.dataManagement;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.android.course.main.R;

public class ExternalFileActivity extends Activity {
	private final String fileName = "image3a.png";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_external_file);

		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {

			File outFile = new File(
					getExternalFilesDir(Environment.DIRECTORY_PICTURES),
					fileName);

			// Copy the image to external memory
			if (!outFile.exists()) {

				try {
					BufferedOutputStream bos = new BufferedOutputStream(
							new FileOutputStream(outFile));

					BufferedInputStream bis = new BufferedInputStream(
							getResources().openRawResource(R.raw.image3a));

					copyImage(bos, bis);

				} catch (FileNotFoundException e) {
					Log.e("ExternalFileActivity", e.getMessage());
				}

			}
			
			//Display the image
			ImageView view = (ImageView) findViewById(R.id.image);
			view.setImageURI(Uri.parse("file://"+ outFile.getAbsolutePath()));

		}

	}

	private void copyImage(BufferedOutputStream bos, BufferedInputStream bis) {
		final byte[] buf = new byte[1024];
		int numBytes;
		try {
			while (-1 != (numBytes = bis.read(buf))) {
				bos.write(buf, 0, numBytes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
				bos.close();
			} catch (IOException e) {
				Log.e("ExternalFileActivity", e.getMessage());

			}
		}
	}
}
