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
	public boolean userIsRegist(String account) {
		Cursor cursor = getResultByAccountCheck(account);
		if (cursor.getCount() == 0)
			return true;
		return false;
	}

	/**
	 * ���û�������д�����ݿ�
	 * 
	 * @param account
	 * @param password
	 */
	public void userRegist(String account, String password) {
		String sql = "INSERT INTO USER(account,password)VALUES(" + "'"
				+ account + "'" + "," + "'" + password + "'" + ")";
		db.execSQL(sql);
	}

	/**
	 * ����û�����ID
	 * 
	 * @param account
	 * @return
	 */
	public int getUserId(String account) {
		String sql = "SELECT id FROM user Where account=?";
		Cursor cursor = db.rawQuery(sql, new String[] { account });
		int id = -1;
		if (cursor != null && cursor.moveToFirst()) {
			id = cursor.getInt(cursor.getColumnIndex("id"));
		}
		return id;
	}

	/*
	 * ͨ���û�����ѯ���룬���ز�ѯ���Cursor
	 */
	public Cursor getResultByAccountCheck(String account) {
		String sql = "SELECT password FROM USER WHERE account=" + "'" + account
				+ "'";
		return db.rawQuery(sql, null);
	}

	/**
	 * �û���ϸ��Ϣ�������ݿ�
	 * 
	 * @param id
	 * @param realName
	 * @param gender
	 * @param birthday
	 * @param email
	 * @param contactNumber
	 * @param nativePlace
	 */
	public void userDetailInfoRegist(int id, String realName, int gender,
			String birthday, String nativePlace, int ismarried, String degree,
			String school, String professional, String email,
			String contactNumber) {

		String sql = "INSERT INTO user_info(id,real_name,gender,birthday,native_place,ismarried,degree,school,professional,email,contact_number)VALUES("
				+ id
				+ ",'"
				+ realName
				+ "',"
				+ gender
				+ ",'"
				+ birthday
				+ "','"
				+ nativePlace
				+ "',"
				+ ismarried
				+ ",'"
				+ degree
				+ "','"
				+ school
				+ "','"
				+ professional
				+ "','"
				+ email
				+ "',"
				+ contactNumber + ")";
		db.execSQL(sql);
	}
}
