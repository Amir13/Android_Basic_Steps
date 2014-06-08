package course.android.course.network;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import com.course.android.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class NetworkHttpUrlConnection extends Activity {

	private TextView textview = null;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_http_result);
		
		textview = (TextView) findViewById(R.id.textViewResult);
		new MyHttpUrlConnection().execute();
		
	}
	
	private class MyHttpUrlConnection extends AsyncTask<Void, Void, String>{
		private static final String USER_NAME = "aporter";
		private static final String URL = "http://api.geonames.org/earthquakesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&username="
				+ USER_NAME;

		@Override
		protected String doInBackground(Void... arg0) {
			String data = null;
			HttpURLConnection huc = null;
			
			try {
				huc = (HttpURLConnection) new java.net.URL(URL).openConnection();
				
				InputStream in = new BufferedInputStream(huc.getInputStream());
				
				data = readStream(in);
				
			} catch (MalformedURLException e) {
				Log.e("MyHttpUrlConnection", "MalformedURLException");
			} catch (IOException e) {
				Log.e("MyHttpUrlConnection", "IOException");
			}
			
			return data;
		}
		
		@Override
		protected void onPostExecute(String result) {
			textview.setText(result);
		}
		
		private String readStream(InputStream in) {
			BufferedReader reader = null;
			StringBuffer data = new StringBuffer("");
			try {
				reader = new BufferedReader(new InputStreamReader(in));
				String line = "";
				while ((line = reader.readLine()) != null) {
					data.append(line);
				}
			} catch (IOException e) {
				Log.e("MyHttpUrlConnection", "IOException");
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return data.toString();
		}	
	}
	
}
