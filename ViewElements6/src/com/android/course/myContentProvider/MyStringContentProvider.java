package com.android.course.myContentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.util.SparseArray;

public class MyStringContentProvider extends ContentProvider {

	// Data storage
	private static final SparseArray<DataRecord> db = new SparseArray<DataRecord>();

	// Delete some or all data items
	@Override
	public int delete(Uri uri, String arg1, String[] arg2) {

		int recordsRemoved = 0;

		// If last segment is the table name, delete all data items
		if (isTableUri(uri)) {
			recordsRemoved = db.size();
			db.clear();

		} else if (isItemUri(uri)) {
			// If last segment is the digit, delete data item with that ID

			Integer id = Integer.parseInt(uri.getLastPathSegment());

			if (db.get(id) != null) {
				db.remove(id);
				recordsRemoved++;
			}

		}
		return recordsRemoved;
	}

	private boolean isTableUri(Uri uri) {
		return uri.getLastPathSegment().equals(DataContract.DATA_TABLE);
	}

	private boolean isItemUri(Uri uri) {
		return uri.getLastPathSegment().matches("\\d+");
	}

	// Return MIME type for given uri
	@Override
	public String getType(Uri uri) {

		String type = DataContract.CONTENT_ITEM_TYPE;

		if (isTableUri(uri)) {
			type = DataContract.CONTENT_DIR_TYPE;
		}
		return type;
	}

	// Insert specified value into ContentProvider
	@Override
	public synchronized Uri insert(Uri uri, ContentValues values) {

		if (values.containsKey(DataContract.DATA)) {

			DataRecord record = new DataRecord(
					values.getAsString(DataContract.DATA));

			db.put(record.getID(), record);

			// return Uri associated with newly-added data item
			return Uri.withAppendedPath(DataContract.CONTENT_URI,
					String.valueOf(record.getID()));
		}
		return null;
	}

	// Initialize ContentProvider 
	// Nothing to do in this case
	@Override
	public boolean onCreate() {
		return true;
	}

	// return all or some rows from ContentProvider based on specified Uri
	// all other parameters are ignored 
	@Override
	public synchronized Cursor query(Uri uri, String[] arg1, String arg2, String[] arg3,
			String arg4) {

		MatrixCursor cursor = new MatrixCursor(DataContract.ALL_COLUMNS);
		
		if (isTableUri(uri)) {
			
			// Add all rows to cursor
			for (int i = 0; i < db.size(); i++) {
				DataRecord record = db.get(db.keyAt(i));
				cursor.addRow(new Object[]{ record.getID(), record.getData()});
			}
			
		} else if (isItemUri(uri)) {
			
			// Add single row to cursor
			Integer id = Integer.parseInt(uri.getLastPathSegment());
			
			if (db.get(id) != null) {
				DataRecord record = db.get(id);
				cursor.addRow(new Object[]{ record.getID(), record.getData()});
				
			}
			
		} 		
		return cursor;
	}

	@Override
	public synchronized int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		return 0;
	}

	
	
}
