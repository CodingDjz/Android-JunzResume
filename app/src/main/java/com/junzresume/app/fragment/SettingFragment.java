package com.junzresume.app.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.junzresume.app.R;
import com.junzresume.app.activity.RegistDetailsInfoActivity;
import com.junzresume.app.db.JunzResumeDB;
import com.junzresume.app.util.Util;

public class SettingFragment extends Fragment implements
		android.view.View.OnClickListener {

	View view;
	TextView uploadImage;
	TextView comDetail;
	TextView changePwd;
	TextView authorInfo;
	final Activity activity = getActivity();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = LayoutInflater.from(getActivity()).inflate(
				R.layout.fragment_setting, null);
		initView();
		initListener();
		return view;

	}

	private void initView() {
		uploadImage = (TextView) view
				.findViewById(R.id.fragment_upload_headimage);
		comDetail = (TextView) view
				.findViewById(R.id.fragment_complete_detailinfo);
		changePwd = (TextView) view.findViewById(R.id.fragment_exchange_pwd);
		authorInfo = (TextView) view.findViewById(R.id.fragment_about_author);
	}

	private void initListener() {
		comDetail.setOnClickListener(this);
		uploadImage.setOnClickListener(this);
		changePwd.setOnClickListener(this);
		authorInfo.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fragment_complete_detailinfo:
			completeDetialInfoAction();
			break;
		case R.id.fragment_exchange_pwd:
			changePwdAction();
		case R.id.fragment_about_author:
			aboutAuthor();
		}
	}

	/*
	 * ������Ϣ��ť��Ӧ
	 */
	private void completeDetialInfoAction() {
		if (JunzResumeDB.getInstence(getActivity()).selectPersonInfo(
				Util.userId) != null) {
			Util.showToast(getActivity(), "����д����ϸ��Ϣ");
			return;
		}
		startActivity(new Intent(getActivity(), RegistDetailsInfoActivity.class));
	}

	/*
	 * �������밴ť��Ӧ
	 */
	private void changePwdAction() {

		View view = LayoutInflater.from(activity).inflate(
				R.layout.dialog_exchange_pwd, null);
		final EditText oldPwd = (EditText) view
				.findViewById(R.id.dialog_old_pwd);
		final EditText newPwd = (EditText) view
				.findViewById(R.id.dialog_new_pwd);
		final EditText confirmPwd = (EditText) view
				.findViewById(R.id.dialog_confirm_pwd);
		new AlertDialog.Builder(activity).setTitle("��������").setView(view)
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						String oldPwdStr = oldPwd.getText().toString();
						String newPwdStr = newPwd.getText().toString();
						String confirmPwdStr = confirmPwd.getText().toString();
						if (TextUtils.isEmpty(oldPwdStr)
								|| TextUtils.isEmpty(newPwdStr)
								|| TextUtils.isEmpty(confirmPwdStr)) {
							Util.showToast(activity, "���벻��Ϊ��");
							return;
						}
						if (newPwdStr.equals(confirmPwdStr)) {
							Util.showToast(activity, "�����벻һ��");
						}
						boolean isUpdate = JunzResumeDB.getInstence(activity)
								.updatePwd(Util.userId, oldPwdStr, newPwdStr);
						if (isUpdate) {
							Util.showToast(activity, "������ĳɹ�");
						} else {
							Util.showToast(activity, "�������ʧ��");
						}

					}
				}).setNegativeButton("ȡ��", null).show();
	}

	/*
	 * �������ߵ����Ӧ
	 */
	public void aboutAuthor() {
		new AlertDialog.Builder(activity).setTitle("��������").setView(view)
				.setNegativeButton("ȷ��", null).show();
	}
}
