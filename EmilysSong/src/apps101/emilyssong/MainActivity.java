/*
Copyright (c) 2013 Lawrence Angrave

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
package apps101.emilyssong;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/* An example activity that creates a media player 
 * object to play Emily Otnes's latest song.
 * This is not a complete Music Player!  
 */

public class MainActivity extends Activity {
	// This activity has a pointer (holds the memory address) to a MediaPlayer.
	// The following line DOES NOT create a Media Player !!
	// It just says that we have a pointer named 'dontcallme' and it can point
	// to MediaPlayers !
	// When this Activity is created 'dontcallme' will be pointing to nothing!

	MediaPlayer dontcallme;

	// When this Activity is created we set up the screen to use our layout
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Use Log messages to track the activity events
		// Error messages will appear in red in the LogCat window.
		// You'll need to add "import android.util.Log;" with the other import
		// statements at the top

		Log.e("Pickle", "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	// When this Activity is ready (and the layout has been been created)
	// Android will call
	// our onResume method. The onResume method can be called again if we are
	// paused for some reason.
	@Override
	protected void onResume() {
		Log.e("Pickle", "onResume"); // Let's tell the World! Well anyone
										// reading the logcat
										// window...

		// "MediaPlayer.create(...)" is the code that actually creates a media
		// player object
		// "dontcallme =" is the code that changes ('assigns') our pointer to
		// point to the new media player object
		// You need to add "import android.media.MediaPlayer;" 
		// import statement at the top of this file

		dontcallme = MediaPlayer.create(this,
				R.raw.excerpt_emilyotnes_weekdays_darling);
		// When 'create' returns the MediaPlayer is already in the prepared
		// state and ready to go!
		dontcallme.start();
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.e("Pickle", "onPause");
		// We're pausing. For this demo we will just stop the MediaPlayer and
		// then ask it to release
		// all of the valuable resources it's using.
		// If the user comes back to this app then onResume() will be called
		// again
		// (and we'll make a new Media player; see above)

		dontcallme.stop();
		dontcallme.release();
		super.onPause();
	}

	// Code to start the external WebBrowser app
	// We ask Android to open a facebook page for us.
	// It does so by starting a web browser and giving it the intent information
	// You'll need to add import statements to the top of this file-
	// import android.content.Intent;
	// import android.net.Uri;
	// import android.view.View;
	public void openFB(View v) {
		String url = "https://www.facebook.com/pages/Emily-Otnes/110931118993282";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}

	// Code to start the external WebBrowser app
	// We ask Android to open a bandcamp page for us.
	// It does so by starting a web browser and giving it the intent information
	// You'll need to add import statements to the top of this file-
	// import android.content.Intent;
	// import android.net.Uri;
	// import android.view.View;
	public void openBC(View v) {
		String url = "http://emilyotnesmusic.bandcamp.com/track/dont-call-me-darlin";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
}
