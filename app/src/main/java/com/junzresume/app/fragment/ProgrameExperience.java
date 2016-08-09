package com.junzresume.app.fragment;

import java.util.ArrayList;
import java.util.zip.Inflater;

import com.junzresume.app.R;
import com.junzresume.app.adapter.ListViewCommonAdapter;
import com.junzresume.app.adapter.ListViewExpAdapter;
import com.junzresume.app.db.JunzResumeDB;
import com.junzresume.app.entity.ListViewItemCommon;
import com.junzresume.app.entity.ListViewItemExp;
import com.junzresume.app.util.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ProgrameExperience extends Fragment implements OnClickListener {

	View view;
	Context context;
	TextView fragTitle;
	ListView listView;
	Button addBtn;
	ArrayList<ListViewItemExp> itemList;
	ListViewExpAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_common, container, false);
		init();
		initAdapter();
		return view;
	}

	private void init() {
		context = getActivity();
		listView = (ListView) view.findViewById(R.id.arraylist_listview);
		fragTitle = (TextView) view.findViewById(R.id.frag_title);
		fragTitle.setText("��Ŀ����");
		addBtn = (Button) view.findViewById(R.id.add_btn);
		addBtn.setOnClickListener(this);
	}

	/**
	 * ��ʼ��������
	 */
	private void initAdapter() {
		itemList = JunzResumeDB.getInstence(context).getProgrameExpByid(
				Util.userId);
		if (itemList == null) {
			// TODO ��ϰʹ��snackbar
			// Snackbar.make(rootlayout, "Hello SnackBar!",
			// Snackbar.LENGTH_SHORT)
			// show();
			Util.showToast(context, "û����д��Ŀ��Ϣ");
			// û����ϢҲҪ�����µģ�����ֱ�����
			itemList = new ArrayList<ListViewItemExp>();
		}

		adapter = new ListViewExpAdapter(context,
				R.layout.fragment_listview_exp, itemList);
		listView.setAdapter(adapter);
	}

	/**
	 * �����Ӱ�ť��Ӧ
	 */
	private void addBtnAction() {
		Activity activity = getActivity();
		View dialogView = activity.getLayoutInflater().inflate(
				R.layout.dialog_exp, null);
		final EditText pName = (EditText) dialogView
				.findViewById(R.id.dialog_exp_name);
		final EditText pDesp = (EditText) dialogView
				.findViewById(R.id.dialog_exp_desp);
		pDesp.setLines(5);
		new AlertDialog.Builder(context).setTitle("��������Ŀ��Ϣ")
				.setView(dialogView)
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						String nameStr = pName.getText().toString();
						String despStr = pDesp.getText().toString();

						if (TextUtils.isEmpty(nameStr)
								|| TextUtils.isEmpty(despStr)) {
							Toast.makeText(context, "���벻��Ϊ��",
									Toast.LENGTH_SHORT).show();
							return;
						}
						JunzResumeDB.getInstence(context).insertProgrameExp(
								Util.userId, nameStr, despStr);
						// �Ȳ�ѯһ�£�ʹ�䲻Ϊnull
						itemList.clear();
						/**
						 * ���²�ѯ������List
						 */
						itemList.addAll(JunzResumeDB.getInstence(context)
								.getProgrameExpByid(Util.userId));
						adapter.notifyDataSetChanged();
					}
				}).setNegativeButton("ȡ��", null).show();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_btn:
			addBtnAction();
			break;

		default:
			break;
		}

	}
}
