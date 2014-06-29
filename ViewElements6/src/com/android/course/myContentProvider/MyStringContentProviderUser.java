package com.android.course.myContentProvider;

import com.android.course.main.R;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class MyStringContentProviderUser extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ContentResolver content = getContentResolver();

		ContentValues values = new ContentValues();

		values.put(DataContract.DATA, "Record1");
		Uri first = content.insert(DataContract.CONTENT_URI, values);
		values.clear();
		
		values.put(DataContract.DATA, "Record2");
		content.insert(DataContract.CONTENT_URI, values);
		values.clear();

		values.put(DataContract.DATA, "Record3");
		content.insert(DataContract.CONTENT_URI, values);
		values.clear();

		// Delete a record
		content.delete(first, null, null);

		// Create and set cursor and list adapter
		Cursor cursor = content.query(DataContract.CONTENT_URI, null, null,
				null, null);

		setListAdapter(new SimpleCursorAdapter(this,
				R.layout.activity_my_content, cursor, DataContract.ALL_COLUMNS,
				new int[] { R.id.idString, R.id.data }, 0));
	}

}
