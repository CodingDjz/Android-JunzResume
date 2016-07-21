package com.junzresume.app.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class JunzResumeDB {

	public static final int DB_VERSION = 1;
	private SQLiteDatabase db;
	public static final String DB_NAME = "junzResume";
	private static JunzResumeDB junzResumeDB;

	public JunzResumeDB(Context context) {
		db = new JunzResumeDatabaseHelper(context, DB_NAME, null, DB_VERSION)
				.getWritableDatabase();
	}

	public static JunzResumeDB getInstence(Context context) {
		if (junzResumeDB == null)
			junzResumeDB = new JunzResumeDB(context);
		return junzResumeDB;

	}

	/**
	 * 将用户名密码写入数据库
	 * 
	 * @param account
	 * @param password
	 */
	public void userRegist(String account, String password) {
		String sql = "INSERT INTO USER(account,password)VALUES(" + account
				+ "," + password + ")";
		db.execSQL(sql);
	}

	/**
	 * 验证是否登录成功
	 * 
	 * @param account
	 * @param password
	 * @return 用户名密码是否正确
	 */
	public boolean verifyUser(String account, String password) {
		String sql = "SELECT password FROM USER WHERE account=" + account;
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor != null && cursor.moveToFirst()) {
			do {
				String dbPwd = cursor.getString(cursor
						.getColumnIndex("password"));
				if (password.equals(dbPwd))
					return true;
			} while (cursor.moveToNext());

		}
		return false;
	}
}
