package com.junzresume.app.activity;

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
	 * ��ť��������
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
	 * �����á���ť�����¼�
	 */
	private void resetAction() {
		account.setText("");
		password.setText("");
		confirmPwd.setText("");
	}

	/**
	 * ��ע�ᡱ��ť�����¼�
	 */
	private void registAction() {
		String accountText = account.getText().toString();
		String passwordText = password.getText().toString();
		String confirmText = confirmPwd.getText().toString();
		if ("".equals(accountText) || "".equals(passwordText)
				|| "".equals(confirmText)) {
			Util.showToast(this, getString(R.string.registinfo_null));
			return;
		}
		if (!passwordText.equals(confirmText)) {
			Util.showToast(this, getString(R.string.password_not_match));
			return;
		}
		boolean canRegist = JunzResumeDB.getInstence(this).userRegistCheck(
				accountText);
		if (!canRegist) {
			Util.showToast(this, getString(R.string.exist_account));
			return;
		}
		// ע����Ϣд�����ݿ�
		JunzResumeDB.getInstence(this).userRegist(accountText, passwordText);
		Util.userId = JunzResumeDB.getInstence(this).getUserId(accountText);
		registAlert.show();
	}

	/**
	 * ��ʼ��ע��ѡ���
	 */

	private void initRegistCompleteAlert() {
		registAlert.setMessage("ע��ɹ�������ѡ��ȥ������ϸ��Ϣ��");
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
	
	private boolean isAccountSyntax(String account){
		//TODO
		return false;
	}

}
