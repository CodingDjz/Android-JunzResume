package com.junzresume.app.activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.junzresume.app.R;
import com.junzresume.app.db.JunzResumeDB;
import com.junzresume.app.util.Util;

public class RegistActivity extends BaseActivity implements OnClickListener {
	private EditText account;
	private EditText password;
	private EditText confirmPwd;
	private Button registBtn;
	private Button resetBtn;
	private AlertDialog.Builder registAlert;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		initView();
		setListener();
		initRegistCompleteAlert();
	}

	private void initView() {
		account = (EditText) findViewById(R.id.regist_user_account);
		password = (EditText) findViewById(R.id.regist_user_pwd);
		confirmPwd = (EditText) findViewById(R.id.regist_user_confirm);
		registBtn = (Button) findViewById(R.id.regist_btn);
		resetBtn = (Button) findViewById(R.id.reset_btn);
		registAlert = new AlertDialog.Builder(this);
	}

	private void setListener() {
		registBtn.setOnClickListener(this);
		resetBtn.setOnClickListener(this);
	}

	/**
	 * 按钮监听方法
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.regist_btn:
			registAction();
			break;
		case R.id.reset_btn:
			resetAction();
			break;
		}

	}

	/**
	 * “重置”按钮触发事件
	 */
	private void resetAction() {
		account.setText("");
		password.setText("");
		confirmPwd.setText("");
	}

	/**
	 * “注册”按钮触发事件
	 */
	private void registAction() {
		String accountText = account.getText().toString();
		String passwordText = password.getText().toString();
		String confirmText = confirmPwd.getText().toString();
		// 是否有空值
		if ("".equals(accountText) || "".equals(passwordText)
				|| "".equals(confirmText)) {
			Util.showToast(this, getString(R.string.registinfo_null));
			return;
		}
		// 用户名正则匹配
		if (!isAccountSyntaxMatch(accountText)) {
			Util.showToast(this, getString(R.string.account_syntax_unmatch));
			return;
		}
		// 密码是否相等
		if (!passwordText.equals(confirmText)) {
			Util.showToast(this, getString(R.string.password_not_match));
			return;
		}
		// 用户是否已存在
		boolean canRegist = JunzResumeDB.getInstence(this).userIsRegist(
				accountText);
		if (!canRegist) {
			Util.showToast(this, getString(R.string.exist_account));
			return;
		}
		// 注册信息写入数据库
		JunzResumeDB.getInstence(this).userRegist(accountText, passwordText);
		Util.setUserId(JunzResumeDB.getInstence(this).getUserId(accountText));
		registAlert.show();
	}

	/**
	 * 初始化注册选择框
	 */

	private void initRegistCompleteAlert() {
		registAlert.setMessage(R.string.if_to_detailinfo);
		registAlert.setTitle(getString(R.string.regist_success));
		registAlert.setPositiveButton(
				getString(R.string.to_regist_detail_info),
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						startActivity(new Intent(RegistActivity.this,
								RegistDetailsInfoActivity.class));
						finish();

					}
				});
		registAlert.setNegativeButton(
				getString(R.string.not_to_regist_detail_info),
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						finish();

					}
				});
	}

	/**
	 * 用户名格式是否匹配
	 * 
	 * @param accountText
	 * @return
	 */
	private boolean isAccountSyntaxMatch(String accountText) {
		Pattern pattern = Pattern.compile("([0-9a-zA-Z]|[_])+");
		Matcher matcher = pattern.matcher(accountText);
		return matcher.matches();
	}

}
