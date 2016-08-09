package com.junzresume.app.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.junzresume.app.R;
import com.junzresume.app.db.JunzResumeDB;
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
	EditText schoolView;
	EditText professionalView;
	Spinner yearSpinner;
	Spinner monthSpinner;
	Spinner dateSpinner;
	Spinner degreeSpinner;
	Spinner isMarriedSpinner;
	ArrayAdapter<String> yearAdapter;
	ArrayAdapter<String> monthAdapter;
	ArrayAdapter<String> dateAdapter;
	ArrayAdapter<String> degreeAdapter;
	ArrayAdapter<String> isMarryAdapter;
	Calendar calendar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist_details_info);
		initView();
		initListener();
		initSpinner();
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
		degreeSpinner = (Spinner) findViewById(R.id.degree_sp);
		isMarriedSpinner = (Spinner) findViewById(R.id.is_marride);
		schoolView = (EditText) findViewById(R.id.school);
		professionalView = (EditText) findViewById(R.id.professional_edit);
	}

	private void initListener() {
		monthSpinner.setOnItemSelectedListener(this);
		yearSpinner.setOnItemSelectedListener(this);
		completeBtn.setOnClickListener(this);

	}

	/**
	 * 初始化日期选择器
	 */

	private void initSpinner() {
		setYearSpinner();
		setMonthSpinner();
		setDateSpinner(
				Integer.parseInt(yearSpinner.getSelectedItem().toString()),
				Integer.parseInt(monthSpinner.getSelectedItem().toString()));
		setDegreeSpinner();
		setIsMarriedSpinner();
	}

	/**
	 * 设置年下拉表
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
	 * 设置月份下拉表
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
	 * 根据年月获得月份天数
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

	private void setDegreeSpinner() {
		ArrayList<String> degree = new ArrayList<>();
		degree.add("博士");
		degree.add("硕士");
		degree.add("本科");
		degree.add("高中");
		degree.add("初中");
		degree.add("小学");

		degreeAdapter = new ArrayAdapter<>(this,
				R.layout.birthday_spinner_item, degree);
		degreeAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		degreeSpinner.setAdapter(degreeAdapter);
	}

	private void setIsMarriedSpinner() {
		ArrayList<String> isMarried = new ArrayList<>();
		isMarried.add("未婚");
		isMarried.add("已婚");
		isMarryAdapter = new ArrayAdapter<>(this,
				R.layout.birthday_spinner_item, isMarried);
		isMarryAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		isMarriedSpinner.setAdapter(isMarryAdapter);
	}

	/**
	 * “完成”动作
	 */
	private void completeAction() {
		String realName = realNameView.getText().toString();
		int gender = getGender();
		String birthday = getBirthday();
		String nativePlace = nativePlaceView.getText().toString();
		int ismarried = getIsMarried();
		String degree = degreeSpinner.getSelectedItem().toString();
		String school = schoolView.getText().toString();
		String professional = professionalView.getText().toString();
		String email = emailView.getText().toString();
		String contactNumber = contactNumberView.getText().toString();

		// 判空
		if (TextUtils.isEmpty(realName) || gender == 0
				|| TextUtils.isEmpty(birthday) || TextUtils.isEmpty(email)
				|| TextUtils.isEmpty(contactNumber)
				|| TextUtils.isEmpty(nativePlace)) {
			Util.showToast(this, getString(R.string.regist_detailinfo_null));
			return;
		}
		// 邮箱格式
		if (!isEmailSyntaxMatch(email)) {
			Util.showToast(this, getString(R.string.email_syntax_match));
			return;
		}
		JunzResumeDB.getInstence(this).userDetailInfoRegist(Util.userId,
				realName, gender, birthday, nativePlace, ismarried, degree,
				school, professional, email, contactNumber);
		Util.showToast(this, getString(R.string.regist_detail_complete_success));
		finish();
	}

	/**
	 * 获得性别代号
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
	 * 获得生日字符串 格式Y/M/D
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

	private int getIsMarried() {
		String isMarried = isMarriedSpinner.getSelectedItem().toString();
		int isMarriedId = 0;
		if ("未婚".equals(isMarried)) {
			isMarriedId = 1;
		} else if ("已婚".equals(isMarried)) {
			isMarriedId = 2;
		}
		return isMarriedId;
	}

	/**
	 * 邮箱格式是否匹配
	 * 
	 * @param emailText
	 * @return
	 */
	private boolean isEmailSyntaxMatch(String emailText) {
		Pattern pattern = Pattern.compile("\\w+@[a-zA-Z0-9]+\\.[a-zA-Z]+\\s*");
		Matcher matcher = pattern.matcher(emailText);
		return matcher.matches();
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// 月份点击时监听
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
