package com.android.course.dataManagement;

import com.android.course.main.R;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class SqlActivity extends Activity {

	private SqlOpenHelper helper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sql);

		// Create a new DatabaseHelper
		helper = new SqlOpenHelper(this);

		// Get the underlying database for writing
		db = helper.getWritableDatabase();

		// start with an empty database
		db.delete(SqlOpenHelper.DB_NAME, null, null);

		// Insert records
		insertRecords();
		
		// Create a cursor

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
