package song.example.playsong;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	MediaPlayer song;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		song = MediaPlayer.create(this, R.raw.teardrops);
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		song.start();
		super.onResume();
		Log.e("PlaySong app", "onResume");
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		song.stop();
		super.onPause();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void openFB(View v){
		String url = "http://www.facebook.com";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
		
	}

	public void openBC(View v){
		String url = "http://www.facebook.com";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
		
	}
}
