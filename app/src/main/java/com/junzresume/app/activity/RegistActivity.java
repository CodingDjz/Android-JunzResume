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
		// �Ƿ��п�ֵ
		if ("".equals(accountText) || "".equals(passwordText)
				|| "".equals(confirmText)) {
			Util.showToast(this, getString(R.string.registinfo_null));
			return;
		}
		// �û�������ƥ��
		if (!isAccountSyntaxMatch(accountText)) {
			Util.showToast(this, getString(R.string.account_syntax_unmatch));
			return;
		}
		// �����Ƿ����
		if (!passwordText.equals(confirmText)) {
			Util.showToast(this, getString(R.string.password_not_match));
			return;
		}
		// �û��Ƿ��Ѵ���
		boolean canRegist = JunzResumeDB.getInstence(this).userIsRegist(
				accountText);
		if (!canRegist) {
			Util.showToast(this, getString(R.string.exist_account));
			return;
		}
		// ע����Ϣд�����ݿ�
		JunzResumeDB.getInstence(this).userRegist(accountText, passwordText);
		Util.setUserId(JunzResumeDB.getInstence(this).getUserId(accountText));
		registAlert.show();
	}

	/**
	 * ��ʼ��ע��ѡ���
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
	 * �û�����ʽ�Ƿ�ƥ��
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
