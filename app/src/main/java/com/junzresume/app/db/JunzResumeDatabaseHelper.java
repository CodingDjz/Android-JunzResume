package com.junzresume.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class JunzResumeDatabaseHelper extends SQLiteOpenHelper {

	public static final String CREATE_USER = "CREATE TABLE USER(id INTEGER PRIMARY KEY AUTOINCREMENT,account TEXT,password TEXT);";
	private Context context;

	public JunzResumeDatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_USER);
		Log.v("JunzResumeDatabaseHelper", "Create Table +'USER' SUCCESSFUL!");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
