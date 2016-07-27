package com.junzresume.app.activity;

import java.util.ArrayList;
import java.util.Calendar;

import android.R.integer;
import android.app.PendingIntent.CanceledException;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.junzresume.app.R;
import com.junzresume.app.R.array;
import com.junzresume.app.db.JunzResumeDB;
import com.junzresume.app.db.JunzResumeDatabaseHelper;
import com.junzresume.app.util.Util;

public class RegistDetailsInfoActivity extends BaseActivity implements
		OnItemSelectedListener, OnClickListener {

	Button completeBtn;
	EditText realNameView;
	RadioButton maleButtonView;
	RadioButton femaleButtonView;
	EditText emailView;
	EditText contactNumberView;
	EditText nativePlaceView;
	Spinner yearSpinner;
	Spinner monthSpinner;
	Spinner dateSpinner;
	ArrayAdapter<String> yearAdapter;
	ArrayAdapter<String> monthAdapter;
	ArrayAdapter<String> dateAdapter;
	Calendar calendar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist_details_info);
		initView();
		initListener();
		initBirthdaySpinner();
	}

	private void initView() {
		yearSpinner = (Spinner) findViewById(R.id.year_sp);
		monthSpinner = (Spinner) findViewById(R.id.month_sp);
		dateSpinner = (Spinner) findViewById(R.id.date_sp);
		calendar = Calendar.getInstance();
		completeBtn = (Button) findViewById(R.id.regist_detail_complete_btn);
		realNameView = (EditText) findViewById(R.id.real_name);
		maleButtonView = (RadioButton) findViewById(R.id.radio_male);
		femaleButtonView = (RadioButton) findViewById(R.id.radio_female);
		emailView = (EditText) findViewById(R.id.e_mail);
		contactNumberView = (EditText) findViewById(R.id.contact_number);
		nativePlaceView = (EditText) findViewById(R.id.native_place);
	}

	private void initListener() {
		monthSpinner.setOnItemSelectedListener(this);
		yearSpinner.setOnItemSelectedListener(this);
		completeBtn.setOnClickListener(this);

	}

	/**
	 * ï¿½ï¿½Ê¼ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ñ¡ï¿½ï¿½ï¿½ï¿½
	 */

	private void initBirthdaySpinner() {
		setYearSpinner();
		setMonthSpinner();
		setDateSpinner(
				Integer.parseInt(yearSpinner.getSelectedItem().toString()),
				Integer.parseInt(monthSpinner.getSelectedItem().toString()));
	}

	/**
	 * ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
	 * 
	 * @param calendar
	 */
	private void setYearSpinner() {
		ArrayList<String> yeardata = new ArrayList<>();
		for (int y = 0; y <= 70; y++) {
			int year = calendar.get(Calendar.YEAR) - y;
			yeardata.add(String.valueOf(year));
		}
		// R.layout.birthday_spinner_item
		yearAdapter = new ArrayAdapter<>(this, R.layout.birthday_spinner_item,
				yeardata);
		yearAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		yearSpinner.setAdapter(yearAdapter);
	}

	/**
	 * ï¿½ï¿½ï¿½ï¿½ï¿½Â·ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
	 * 
	 * @param calendar
	 * 
	 */
	private void setMonthSpinner() {
		ArrayList<String> monthData = new ArrayList<>();
		for (int m = 1; m <= 12; m++) {
			monthData.add(String.valueOf(m));
		}
		monthAdapter = new ArrayAdapter<>(this, R.layout.birthday_spinner_item,
				monthData);
		monthAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		monthSpinner.setAdapter(monthAdapter);
		;
	}

	/**
	 * ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Â»ï¿½ï¿½ï¿½Â·ï¿½ï¿½ï¿½ï¿½ï¿½
	 * 
	 * @param year
	 * @param month
	 */
	private void setDateSpinner(int year, int month) {
		ArrayList<String> dateData = new ArrayList<>();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
		int dayCounts = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int d = 1; d <= dayCounts; d++) {
			dateData.add(String.valueOf(d));
		}
		dateAdapter = new ArrayAdapter<>(this, R.layout.birthday_spinner_item,
				dateData);
		dateAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dateSpinner.setAdapter(dateAdapter);
	}

	/**
	 * ï¿½ï¿½ï¿½ï¿½É¡ï¿½ï¿½ï¿½ï¿½ï¿?
	 */
	private void completeAction() {
		String realName = realNameView.getText().toString();
		int gender = getGender();
		String birthday = getBirthday();
		String email = emailView.getText().toString();
		String contactNumber = contactNumberView.getText().toString();
		String nativePlace = nativePlaceView.getText().toString();
		if (TextUtils.isEmpty(realName) || gender == 0
				|| TextUtils.isEmpty(birthday) || TextUtils.isEmpty(email)
				|| TextUtils.isEmpty(contactNumber)
				|| TextUtils.isEmpty(nativePlace)) {
			Util.showToast(this, "ÌîÐ´ÐÅÏ¢²»ÄÜÎª¿Õ");
			return;
		}
		JunzResumeDB.getInstence(this).userDetailInfoRegist(Util.userId,
				realName, gender, birthday, email, contactNumber, nativePlace);
		Util.showToast(this, "ÐÅÏ¢ÌîÐ´³É¹¦£¡");
		finish();
	}

	/**
	 * ï¿½ï¿½ï¿½ï¿½Ô±ï¿½ï¿½ï¿?
	 * 
	 * @return
	 */
	private int getGender() {
		int gender = 0;
		if (maleButtonView.isChecked()) {
			gender = 1;
		} else if (femaleButtonView.isChecked()) {
			gender = 2;
		}
		return gender;
	}

	/*
	 * ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ö·ï¿?ï¿½ï¿½Ê½Y/M/D
	 * 
	 * @return
	 */
	private String getBirthday() {
		StringBuilder birthday = new StringBuilder();
		birthday.append(yearSpinner.getSelectedItem().toString()).append("/")
				.append(monthSpinner.getSelectedItem().toString()).append("/")
				.append(dateSpinner.getSelectedItem().toString());
		return birthday.toString();
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// ï¿½Â·Ýµï¿½ï¿½Ê±ï¿½ï¿½ï¿½ï¿?
		int selectYear = Integer.parseInt(yearSpinner.getSelectedItem()
				.toString());
		int selectMonth = Integer.parseInt(monthSpinner.getSelectedItem()
				.toString());
		setDateSpinner(selectYear, selectMonth);

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.regist_detail_complete_btn:
			completeAction();
			break;

		}
	}
}
