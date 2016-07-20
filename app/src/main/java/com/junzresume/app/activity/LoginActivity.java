package com.junzresume.app.activity;

import android.os.Bundle;
import android.view.Window;

import com.junzresume.app.R;

public class LoginActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
	}

}
