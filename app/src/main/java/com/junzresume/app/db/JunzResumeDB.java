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
	 * ���û�������д�����ݿ�
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
	 * ��֤�Ƿ��¼�ɹ�
	 * 
	 * @param account
	 * @param password
	 * @return �û��������Ƿ���ȷ
	 */
	public boolean userLoginCheck(String account, String password) {
		Cursor cursor = getResultByAccountCheck(account);
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

	/**
	 * �û��Ƿ����
	 * 
	 * @return
	 */
	public boolean userRegistCheck(String account) {
		Cursor cursor = getResultByAccountCheck(account);
		if (cursor.getCount() == 0)
			return true;
		return false;
	}

	public Cursor getResultByAccountCheck(String account) {
		String sql = "SELECT password FROM USER WHERE account=" + account;
		return db.rawQuery(sql, null);
	}
}
