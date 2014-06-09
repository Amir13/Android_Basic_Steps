package course.android.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.course.android.R;

import course.android.animation.FrameAnimation;
import course.android.animation.GraphAnimation;
import course.android.animation.TransitionView;
import course.android.canvas.MySurface;
import course.android.canvas.SimpleCanvas;
import course.android.graphics.GraphicsProgrammatically;
import course.android.graphics.GraphicsXml;
import course.android.graphics.ShapeProgramatically;
import course.android.graphics.ShapeXml;
import course.android.network.NetworkAndroidHttpClient;
import course.android.network.NetworkHttpUrlConnection;
import course.android.network.NetworkJson;
import course.android.network.NetworkSocket;
import course.android.network.NetworkXml;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Button btnSocket = (Button) findViewById(R.id.btnSocket);
		btnSocket.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getApplicationContext(),
						NetworkSocket.class);
				startActivity(intent);

			}
		});

		final Button btnHttp = (Button) findViewById(R.id.btnHttp);
		btnHttp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getApplicationContext(),
						NetworkHttpUrlConnection.class);
				startActivity(intent);

			}
		});

		final Button btnAndroid = (Button) findViewById(R.id.btnAndroid);
		btnAndroid.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getApplicationContext(),
						NetworkAndroidHttpClient.class);
				startActivity(intent);

			}
		});

		final Button btnJson = (Button) findViewById(R.id.btnJson);
		btnJson.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getApplicationContext(),
						NetworkJson.class);
				startActivity(intent);

			}
		});

		final Button btnXml = (Button) findViewById(R.id.btnXml);
		btnXml.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getApplicationContext(),
						NetworkXml.class);
				startActivity(intent);

			}
		});

		final Button btnGraphXml = (Button) findViewById(R.id.btnGraphXml);
		btnGraphXml.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getApplicationContext(),
						GraphicsXml.class);
				startActivity(intent);

			}
		});

		final Button btnGraphProg = (Button) findViewById(R.id.btnGraphProg);
		btnGraphProg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getApplicationContext(),
						GraphicsProgrammatically.class);
				startActivity(intent);

			}
		});

		final Button btnShapeXml = (Button) findViewById(R.id.btnShapeXml);
		btnShapeXml.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getApplicationContext(),
						ShapeXml.class);
				startActivity(intent);

			}
		});

		final Button btnShapeProgra = (Button) findViewById(R.id.btnShapeProgra);
		btnShapeProgra.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getApplicationContext(),
						ShapeProgramatically.class);
				startActivity(intent);

			}
		});
		final Button btnCanvas = (Button) findViewById(R.id.btnCanvas);
		btnCanvas.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getApplicationContext(),
						SimpleCanvas.class);
				startActivity(intent);

			}
		});

		final Button btnSurface = (Button) findViewById(R.id.btnSurface);
		btnSurface.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getApplicationContext(),
						MySurface.class);
				startActivity(intent);

			}
		});

		final Button btnTransition = (Button) findViewById(R.id.btnTransition);
		btnTransition.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getApplicationContext(),
						TransitionView.class);
				startActivity(intent);

			}
		});
		
		final Button btnAnimation = (Button) findViewById(R.id.btnAnimation);
		btnAnimation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getApplicationContext(),
						FrameAnimation.class);
				startActivity(intent);

			}
		});
		
		final Button btnAnimation2 = (Button) findViewById(R.id.btnAnimation2);
		btnAnimation2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent intent = new Intent(getApplicationContext(),
						GraphAnimation.class);
				startActivity(intent);
				
			}
		});

	}

}
