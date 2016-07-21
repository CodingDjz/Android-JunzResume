package com.junzresume.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.junzresume.app.R;
import com.junzresume.app.db.JunzResumeDB;

public class LoginActivity extends BaseActivity implements OnClickListener {
	private EditText account;
	private EditText password;
	private Button loginBtn;
	private TextView registStr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		initView();
		setListener();
	}

	private void initView() {
		account = (EditText) findViewById(R.id.user_account);
		password = (EditText) findViewById(R.id.user_pwd);
		loginBtn = (Button) findViewById(R.id.login_btn);
		registStr = (TextView) findViewById(R.id.regist_user);
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

	/**
	 * 登录按钮触发事件
	 */
	private void loginAction() {
		String accountText = account.getText().toString();
		String passwordText = password.getText().toString();
		if ("".equals(accountText) || "".equals(passwordText)) {
			Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		boolean ifLogin = JunzResumeDB.getInstence(this).verifyUser(
				accountText, passwordText);
		if (ifLogin) {
			startActivity(new Intent(this, MainActivity.class));
		} else {
			Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
		}
	}

	private void registAction() {
		startActivity(new Intent(this, RegistActivity.class));
	}
}
