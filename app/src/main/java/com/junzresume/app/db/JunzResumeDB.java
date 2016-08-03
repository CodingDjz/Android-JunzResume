package com.junzresume.app.db;

import java.util.ArrayList;

import android.R.integer;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.junzresume.app.entity.ArrayListViewItem;
import com.junzresume.app.entity.PersonInfo;

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

	/**
	 * ͨ��ID��ѯ�û���ϸ��Ϣ�����ض�Ӧʵ����
	 * 
	 * @param id
	 * @return
	 */
	public PersonInfo selectPersonInfo(int id) {
		String sql = "SELECT * FROM user_info WHERE id=?";
		Cursor cursor = db.rawQuery(sql, new String[] { String.valueOf(id) });
		PersonInfo personInfo = new PersonInfo();
		personInfo.setId(id);
		if (cursor != null && cursor.moveToFirst()) {
			personInfo.setRealName(cursor.getString(cursor
					.getColumnIndex("real_name")));
			personInfo
					.setGender(cursor.getInt(cursor.getColumnIndex("gender")));
			personInfo.setBirthday(cursor.getString(cursor
					.getColumnIndex("birthday")));
			personInfo.setNativePlace(cursor.getString(cursor
					.getColumnIndex("native_place")));
			personInfo.setIsMarried(cursor.getInt(cursor
					.getColumnIndex("ismarried")));
			personInfo.setDegree(cursor.getString(cursor
					.getColumnIndex("degree")));
			personInfo.setSchool(cursor.getString(cursor
					.getColumnIndex("school")));
			personInfo.setProfessional(cursor.getString(cursor
					.getColumnIndex("professional")));
			personInfo
					.setEmail(cursor.getString(cursor.getColumnIndex("email")));
			personInfo.setContactNumber(cursor.getInt(cursor
					.getColumnIndex("contact_number")));

		}
		return personInfo;
	}

	/**
	 * ͨ��ID��ѯרҵ���ܣ�����List����
	 * 
	 * @param id
	 * @return
	 */
	public ArrayList<ArrayListViewItem> getProSkillByid(int id) {
		String sql = "SELECT table_key,skill FROM pro_skill WHERE id=" + id;
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor != null && cursor.moveToFirst()) {
			ArrayList<ArrayListViewItem> skillList = new ArrayList<>();
			int count = 0;
			do {
				int tableKey = cursor.getInt(cursor.getColumnIndex("table_key"));
				String skill = cursor.getString(cursor.getColumnIndex("skill"));
				skillList.add(new ArrayListViewItem(++count, tableKey, skill));
			} while (cursor.moveToNext());
			return skillList;
		}
		return null;
	}
}
