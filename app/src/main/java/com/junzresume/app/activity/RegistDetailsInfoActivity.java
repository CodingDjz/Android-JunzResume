package com.junzresume.app.activity;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.PendingIntent.CanceledException;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.junzresume.app.R;
import com.junzresume.app.R.array;

public class RegistDetailsInfoActivity extends BaseActivity {

	Spinner yearSpinner;
	Spinner monthSpinner;
	Spinner dateSpinner;
	ArrayAdapter<String> yearAdapter;
	ArrayAdapter<String> monthAdapter;
	ArrayAdapter<String> dateAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist_details_info);
		initView();
		initBirthdaySpinner();
	}

	public void initView() {
		// sexSpinner = (Spinner) findViewById(R.id.sex_spinner);
		yearSpinner = (Spinner) findViewById(R.id.year_sp);
		monthSpinner = (Spinner) findViewById(R.id.month_sp);
		dateSpinner = (Spinner) findViewById(R.id.date_sp);
	}

	/**
	 * 初始化日期选择器
	 */
	public void initBirthdaySpinner() {
		Calendar calendar = Calendar.getInstance();
		ArrayList<String> yeardata = new ArrayList<>();
		for (int y = 0; y <= 70; y++) {
			int year = calendar.get(Calendar.YEAR) - y;
			yeardata.add(String.valueOf(year));
		}
		yearAdapter = new ArrayAdapter<>(this, R.layout.birthday_spinner_item,
				yeardata);
		yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		yearSpinner.setAdapter(yearAdapter);

	}
}
