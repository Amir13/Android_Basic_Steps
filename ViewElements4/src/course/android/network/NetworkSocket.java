package course.android.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.course.android.R;

public class NetworkSocket extends Activity {

	private TextView textview = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_http_result);

		textview = (TextView) findViewById(R.id.textViewResult); 
				
		new HttpSocketTask().execute();

	}

	private class HttpSocketTask extends AsyncTask<Void, Void, String> {

		private static final String HOST = "api.geonames.org";
		private static final int PORT = 80;
		private static final String USER_NAME = "aporter";
		private static final String HTTP_GET_COMMAND = "GET /earthquakesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&username="
				+ USER_NAME
				+ " HTTP/1.1"
				+ "\n"
				+ "Host: "
				+ HOST
				+ "\n"
				+ "Connection: close" + "\n\n";

		@Override
		protected String doInBackground(Void... arg0) {

			Socket socket = null;
			String data = null;

			try {
				socket = new Socket(HOST, PORT);
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(
						socket.getOutputStream()), true);
				pw.println(HTTP_GET_COMMAND);

				data = readStream(socket.getInputStream());

			} catch (UnknownHostException e) {
				Log.e("HttpSocketTask", e.getMessage());
			} catch (IOException e) {
				Log.e("HttpSocketTask", e.getMessage());
			} finally {
				if (socket != null)
					try {
						socket.close();
					} catch (IOException e) {
						Log.e("HttpSocketTask", e.getMessage());
					}
			}
			return data;
		}
		
		@Override
		protected void onPostExecute(String result) {
			textview.setText(result);
		}
		

		private String readStream(InputStream in) {
			BufferedReader reader = null;
			StringBuffer data = new StringBuffer();
			try {
				reader = new BufferedReader(new InputStreamReader(in));
				String line = "";
				while ((line = reader.readLine()) != null) {
					data.append(line);
				}
			} catch (IOException e) {
				Log.e("HttpSocketTask", "IOException");
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						Log.e("HttpSocketTask", "IOException");
					}
				}
			}
			return data.toString();
		}

	}

}
