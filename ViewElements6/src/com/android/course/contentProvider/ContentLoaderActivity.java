package com.android.course.contentProvider;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;

import com.android.course.main.R;

public class ContentLoaderActivity extends ListActivity implements
		LoaderManager.LoaderCallbacks<Cursor> {

	private ContactListAdapter adapter;

	// Contacts data items to extract
	static final String[] CONTACTS_ROWS = new String[] { Contacts._ID,
			Contacts.DISPLAY_NAME, Contacts.PHOTO_THUMBNAIL_URI };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Create and set empty adapter
		adapter = new ContactListAdapter(this,
				R.layout.activity_content_provider, null, 0);

		setListAdapter(adapter);

		// Initialize the loader
		getLoaderManager().initLoader(0, null, this);

	}

	// Called when a new Loader should be created
	// Returns a new CursorLoader
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {

		// String used to filter contacts with empty or missing names or are
		// unstarred
		String select = "((" + Contacts.DISPLAY_NAME + " NOTNULL) AND ("
				+ Contacts.DISPLAY_NAME + " != '' ) AND (" + Contacts.STARRED
				+ "== 1))";

		// String used for defining the sort order
		String sortOrder = Contacts._ID + " ASC";

		return new CursorLoader(this, Contacts.CONTENT_URI, CONTACTS_ROWS,
				select, null, sortOrder);

	}

	// Called when the Loader has finished loading its data
	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor data) {
		adapter.swapCursor(data);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		adapter.swapCursor(null);
	}

}
