package com.android.course.dataManagement;

import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.course.main.R;

public class SqlActivity extends ListActivity {

	private SqlOpenHelper helper;
	private SQLiteDatabase db;
	private SimpleCursorAdapter cursorAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sql);

		// Create a new DatabaseHelper
		helper = new SqlOpenHelper(this);

		// Get the underlying database for writing
		db = helper.getWritableDatabase();

		// start with an empty database
		db.delete(SqlOpenHelper.TABLE_NAME, null, null);

		// Insert records
		insertRecords();

		// Create a cursor
		Cursor cursor = db.query(SqlOpenHelper.TABLE_NAME,
				SqlOpenHelper.COLUMNS, null, new String[] {}, null, null, null);

		cursorAdapter = new SimpleCursorAdapter(this, R.layout.sql_layout,
				cursor, SqlOpenHelper.COLUMNS,
				new int[] { R.id._id, R.id.name }, 0);

		setListAdapter(cursorAdapter);

		Button fixButton = (Button) findViewById(R.id.fix_button);
		fixButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// execute database operations
				// delete the error with the mistake
				db.delete(SqlOpenHelper.TABLE_NAME,
						SqlOpenHelper.COL_ARTIST_NAME + "=?",
						new String[] { "Lady Gaga" });

				// Insert the new record with the correct information
				ContentValues values = new ContentValues();
				values.put(SqlOpenHelper.COL_ARTIST_NAME, "Johny Cash");

				db.update(SqlOpenHelper.TABLE_NAME, values,
						SqlOpenHelper.COL_ARTIST_NAME + "=?",
						new String[] { "Jawny Cash" });
				
				values.clear();
				
				values.put(SqlOpenHelper.COL_ARTIST_NAME, "Paly !!");
				db.insert(SqlOpenHelper.TABLE_NAME, null, values);
				
				

				// Redisplay data
				cursorAdapter.getCursor().requery();
				cursorAdapter.notifyDataSetChanged();
			}
		});

	}

	@Override
	protected void onDestroy() {
		
		db.close();
		helper.deleteDB();
		
		super.onDestroy();
		
		
	}
	
	private void insertRecords() {

		ContentValues values = new ContentValues();

		values.put(SqlOpenHelper.COL_ARTIST_NAME, "Frank Sinatra");
		db.insert(SqlOpenHelper.TABLE_NAME, null, values);

		values.clear();

		values.put(SqlOpenHelper.COL_ARTIST_NAME, "Lady Gaga");
		db.insert(SqlOpenHelper.TABLE_NAME, null, values);

		values.clear();

		values.put(SqlOpenHelper.COL_ARTIST_NAME, "Jawny Cash");
		db.insert(SqlOpenHelper.TABLE_NAME, null, values);

		values.clear();

		values.put(SqlOpenHelper.COL_ARTIST_NAME, "Ludwig von Beethoven");
		db.insert(SqlOpenHelper.TABLE_NAME, null, values);

		values.clear();

	}
}
