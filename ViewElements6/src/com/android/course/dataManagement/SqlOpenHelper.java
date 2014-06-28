package com.android.course.dataManagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlOpenHelper extends SQLiteOpenHelper {

	final public static String TABLE_NAME = "artist";
	final public static String DB_NAME = "my_db";
	final private static Integer VERSION_DB = 1;
	final private Context context;

	final static String COL_ID = "_id";
	final static String COL_ARTIST_NAME = "name";
	final static String[] COLUMNS = {COL_ID, COL_ARTIST_NAME};
	
	final private static String CREATE_CMD = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ARTIST_NAME
			+ " TEXT NOT NULL)";

	public SqlOpenHelper(Context context) {

		super(context, DB_NAME, null, VERSION_DB);
		this.context = context;

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_CMD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	void deleteDB(){
		context.deleteDatabase(DB_NAME);
	}
	
}
