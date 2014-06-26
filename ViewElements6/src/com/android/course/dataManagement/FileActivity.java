package com.android.course.dataManagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.course.main.R;

public class FileActivity extends Activity {

	private final static String fileName = "TestFile.txt";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file);

		LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayout);

		// Check whether fileName already exists in directory used
		// by the openFileOutput() method.
		// If the text file doesn't exist, then create it now
		if (!getFileStreamPath(fileName).exists()) {

			try {
				FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);

				PrintWriter pw = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(fos)));

				pw.println("Line 1: This is a test of the File Writing API");
				pw.println("Line 2: This is a test of the File Writing API");
				pw.println("Line 3: This is a test of the File Writing API");

				pw.close();

			} catch (FileNotFoundException e) {
				Log.e("FileActivity", e.getMessage());
			}

		} else {
			
			try {
				FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);

				PrintWriter pw = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(fos)));

				pw.println("Line 1: This is a test of the File Writing API");
				pw.println("Line 2: This is a test of the File Writing API");
				pw.println("Line 3: This is a test of the File Writing API");
				pw.println("Line 4: This is a test of the File Writing API");
				pw.println("Line 5: This is a test of the File Writing API");
				pw.println("Line 6: This is a test of the File Writing API");

				pw.close();

			} catch (FileNotFoundException e) {
				Log.e("FileActivity", e.getMessage());
			}

		}

		// Read the data from the text file and display it

		try {
			FileInputStream fis = openFileInput(fileName);

			BufferedReader br = new BufferedReader(new InputStreamReader(fis));

			String line = "";

			while ((line = br.readLine()) != null) {
				TextView tv = new TextView(this);
				tv.setTextSize(24);
				tv.setText(line);

				ll.addView(tv);
			}

			br.close();

		} catch (IOException e) {
			Log.e("FileActivity", e.getMessage());
		}

	}

}
