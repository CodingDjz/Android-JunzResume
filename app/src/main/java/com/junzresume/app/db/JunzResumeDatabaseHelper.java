package com.junzresume.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class JunzResumeDatabaseHelper extends SQLiteOpenHelper {

	private static final String CREATE_USER = "CREATE TABLE user("
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT," + "account TEXT,"
			+ "password TEXT)";

	private static final String CREATE_USER_INFO = "CREATE TABLE user_info("
			+ "id INTEGER," + "real_name TEXT," + "gender INTEGER,"
			+ "birthday INTEGER," + "email TEXT," + "contact_number INTEGER,"
			+ "native_place TEXT)";

	private Context context;

	public JunzResumeDatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		this.context = context;
	}

	/**
	 * ��ݿⱻ����ʱ����
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_USER);
		db.execSQL(CREATE_USER_INFO);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
