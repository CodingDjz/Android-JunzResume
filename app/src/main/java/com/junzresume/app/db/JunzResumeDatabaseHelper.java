package com.junzresume.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class JunzResumeDatabaseHelper extends SQLiteOpenHelper {

	private static final String CREATE_USER = "CREATE TABLE user("
			+ "id INTEGER PRIMARY KEY," + "account TEXT," + "password TEXT)";

	private static final String CREATE_USER_INFO = "CREATE TABLE user_info("
			+ "id INTEGER," + "real_name TEXT," + "gender INTEGER,"
			+ "birthday TEXT," + "native_place TEXT," + "ismarried INTEGER,"
			+ "degree TEXT," + "school TEXT," + "professional TEXT,"
			+ "email TEXT," + "contact_number INTEGER)";

	private static final String CREATE_PRO_SKILL = "CREATE TABLE pro_skill("
			+ "table_key INTEGER PRIMARY KEY," + "id INTEGER," + "skill TEXT)";

	private static final String CREATE_INTERESTS_HOBBIES = "CREATE TABLE interests_hobbies("
			+ "table_key INTEGER PRIMARY KEY,"
			+ "id INTEGER,"
			+ "interest TEXT)";

	private static final String CTEATE_PROGRAME_EXPERIENCE = "CREATE TABLE programe_experience("
			+ "id INTEGER,"
			+ "table_key INTEGER PRIMARY KEY,"
			+ "programe_name TEXT," + "programe_desp TEXT)";

	private Context context;

	public JunzResumeDatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		this.context = context;
	}

	/**
	 * 数据库被创建时调用
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_USER);
		db.execSQL(CREATE_USER_INFO);
		db.execSQL(CREATE_PRO_SKILL);
		db.execSQL(CREATE_INTERESTS_HOBBIES);
		db.execSQL(CTEATE_PROGRAME_EXPERIENCE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
