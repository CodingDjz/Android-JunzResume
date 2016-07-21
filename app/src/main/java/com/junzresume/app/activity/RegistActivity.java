package com.junzresume.app.activity;

import com.junzresume.app.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistActivity extends BaseActivity implements OnClickListener {

	private EditText account;
	private EditText password;
	private EditText confirmPwd;
	private Button registBtn;
	private Button resetBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		initView();
		setListener();
	}

	private void initView() {
		account = (EditText) findViewById(R.id.regist_user_account);
		password = (EditText) findViewById(R.id.regist_user_pwd);
		confirmPwd = (EditText) findViewById(R.id.regist_user_confirm);
		registBtn = (Button) findViewById(R.id.regist_btn);
		resetBtn = (Button) findViewById(R.id.reset_btn);

	}

	private void setListener() {
		registBtn.setOnClickListener(this);
		resetBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.regist_btn:
			String accountText = account.getText().toString();
			String passwordText = password.getText().toString();
			String confirmText = confirmPwd.getText().toString();
			if ("".equals(accountText) || "".equals(passwordText)
					|| "".equals(confirmText)) {
				Toast.makeText(this, "注册信息不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			if (passwordText.equals(confirmText)) {
				Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();

			}
			break;

		default:
			break;
		}

	}

}
