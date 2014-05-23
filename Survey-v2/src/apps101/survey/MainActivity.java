/*
Copyright (c) 2014 Lawrence Angrave
Dual licensed under Apache2.0 ((http://www.apache.org/licenses/LICENSE-2.0.txt) 
and MIT Open Source License (included below): 
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
package apps101.survey;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText mName;
	private EditText mPhone;
	private EditText mEmail;
	private EditText mComment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		// Look for these views after we've created them !

		mName = (EditText) findViewById(R.id.name);
		mPhone = (EditText) findViewById(R.id.phone);
		mEmail = (EditText) findViewById(R.id.email);
		mComment = (EditText) findViewById(R.id.comments);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void processForm(View duck) {
		Log.d("MainActivity", "processForm");
//
		String comments = mComment.getText().toString();
		Toast.makeText(this.getApplicationContext(), comments,
				Toast.LENGTH_LONG).show();

		duck.setVisibility(View.INVISIBLE);
		Toast.makeText(this.getApplicationContext(), R.string.app_name,
				Toast.LENGTH_LONG).show();
		
		
	}
}
