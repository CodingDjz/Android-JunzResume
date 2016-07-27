package com.junzresume.app.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.junzresume.app.R;
import com.junzresume.app.db.JunzResumeDB;
import com.junzresume.app.util.Util;

public class LoginActivity extends BaseActivity implements OnClickListener {
	private EditText account;
	private EditText password;
	private Button loginBtn;
	private TextView registStr;
	private CheckBox remeberPwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		initView();
		setListener();

	}

	@Override
	protected void onResume() {
		super.onResume();
		getRemberPwd();
	}

	private void initView() {
		account = (EditText) findViewById(R.id.user_account);
		password = (EditText) findViewById(R.id.user_pwd);
		loginBtn = (Button) findViewById(R.id.login_btn);
		registStr = (TextView) findViewById(R.id.regist_user);
		remeberPwd = (CheckBox) findViewById(R.id.remeber_pwd);
	}

	private void setListener() {
		loginBtn.setOnClickListener(this);
		registStr.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_btn:
			loginAction();
			break;
		case R.id.regist_user:
			registAction();
			break;
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		remeberPwdSave();
	}

	/**
	 * 登录按钮触发事件
	 */
	private void loginAction() {
		String accountText = account.getText().toString();
		String passwordText = password.getText().toString();
		if ("".equals(accountText) || "".equals(passwordText)) {
			Util.showToast(this, getString(R.string.account_pwd_null));
			return;
		}
		boolean ifLogin = JunzResumeDB.getInstence(this).userLoginCheck(
				accountText, passwordText);
		if (ifLogin) {
			startActivity(new Intent(this, MainActivity.class));
		} else {
			Util.showToast(this, getString(R.string.account_pwd_wrong));
		}
	}

	/**
	 * 进入注册界面
	 */
	private void registAction() {

		startActivity(new Intent(this, RegistActivity.class));
	}

	/**
	 * 保存记住的密码
	 */
	private void remeberPwdSave() {
		SharedPreferences.Editor editor = getSharedPreferences(
				"remeber_password", MODE_PRIVATE).edit();
		if (!remeberPwd.isChecked()) {
			// 清除也要commit
			editor.clear();
		} else {
			String accountText = account.getText().toString();
			String passwordText = password.getText().toString();
			editor.putString("account", accountText);
			editor.putString("pssword", passwordText);
		}
		editor.commit();
	}

	/**
	 * 获得保存的账号密码
	 */
	private void getRemberPwd() {
		SharedPreferences pref = getSharedPreferences("remeber_password",
				MODE_PRIVATE);
		String remeberAccount = pref.getString("account", "");
		String remeberPassword = pref.getString("pssword", "");
		account.setText(remeberAccount);
		password.setText(remeberPassword);
		if (!"".equals(remeberAccount) || !"".equals(remeberPassword))
			remeberPwd.setChecked(true);
	}
}
