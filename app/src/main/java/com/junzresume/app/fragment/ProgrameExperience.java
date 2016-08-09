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
		fragTitle.setText("项目经验");
		addBtn = (Button) view.findViewById(R.id.add_btn);
		addBtn.setOnClickListener(this);
	}

	/**
	 * 初始化适配器
	 */
	private void initAdapter() {
		itemList = JunzResumeDB.getInstence(context).getProgrameExpByid(
				Util.userId);
		if (itemList == null) {
			// TODO 练习使用snackbar
			// Snackbar.make(rootlayout, "Hello SnackBar!",
			// Snackbar.LENGTH_SHORT)
			// show();
			Util.showToast(context, "没有填写项目信息");
			// 没有信息也要创建新的，方便直接添加
			itemList = new ArrayList<ListViewItemExp>();
		}

		adapter = new ListViewExpAdapter(context,
				R.layout.fragment_listview_exp, itemList);
		listView.setAdapter(adapter);
	}

	/**
	 * 点击添加按钮响应
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
		new AlertDialog.Builder(context).setTitle("请输入项目信息")
				.setView(dialogView)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						String nameStr = pName.getText().toString();
						String despStr = pDesp.getText().toString();

						if (TextUtils.isEmpty(nameStr)
								|| TextUtils.isEmpty(despStr)) {
							Toast.makeText(context, "输入不能为空",
									Toast.LENGTH_SHORT).show();
							return;
						}
						JunzResumeDB.getInstence(context).insertProgrameExp(
								Util.userId, nameStr, despStr);
						// 先查询一下，使其不为null
						itemList.clear();
						/**
						 * 重新查询并更新List
						 */
						itemList.addAll(JunzResumeDB.getInstence(context)
								.getProgrameExpByid(Util.userId));
						adapter.notifyDataSetChanged();
					}
				}).setNegativeButton("取消", null).show();

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
