package course.android.network;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;

import com.course.android.R;


import android.app.Activity;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class NetworkAndroidHttpClient extends Activity{
	private TextView textview = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_http_result);

		textview = (TextView) findViewById(R.id.textViewResult); 
				
		new HttpReqest().execute();

		
	}

	private class HttpReqest extends AsyncTask<Void, Void, String>{

		private static final String USER_NAME = "aporter";

		private static final String URL = "http://api.geonames.org/earthquakesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&username="
				+ USER_NAME;

		AndroidHttpClient ahc = AndroidHttpClient.newInstance("");
		
		@Override
		protected String doInBackground(Void... arg0) {
		
			HttpGet request = new HttpGet(URL);
			ResponseHandler<String> responseHanlder = new BasicResponseHandler();
			
			
			try {
				return ahc.execute(request, responseHanlder);
			} catch (ClientProtocolException e) {
				Log.e("NetworkAndroidHttpClient.HttpReqest", e.getMessage());
			} catch (IOException e) {
				Log.e("NetworkAndroidHttpClient.HttpReqest", e.getMessage());
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(java.lang.String result) {
			
			if (result != null) {
				ahc.close();
			}
			
			textview.setText(result);
			
		}
		
		
	}
	
}
