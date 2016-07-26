package com.junzresume.app.activity;

import java.util.ArrayList;
import java.util.Calendar;

import android.R.integer;
import android.app.PendingIntent.CanceledException;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.junzresume.app.R;
import com.junzresume.app.R.array;

public class RegistDetailsInfoActivity extends BaseActivity implements
		OnItemSelectedListener {

	Spinner yearSpinner;
	Spinner monthSpinner;
	Spinner dateSpinner;
	ArrayAdapter<String> yearAdapter;
	ArrayAdapter<String> monthAdapter;
	ArrayAdapter<String> dateAdapter;
	DatePicker datePicker;
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
	}

	private void initListener() {
		monthSpinner.setOnItemSelectedListener(this);
	}

	/**
	 * 初始化日期选择器
	 */

	private void initBirthdaySpinner() {
		setYearSpinner();
		setMonthSpinner();

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

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		int selectYear = Integer.parseInt(yearSpinner.getSelectedItem()
				.toString());
		int selectMonth = Integer.parseInt(monthSpinner.getSelectedItem()
				.toString());
		setDateSpinner(selectYear, selectMonth);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}
}
