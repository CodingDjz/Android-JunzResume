package com.junzresume.app.fragment;

import java.util.ArrayList;

import com.junzresume.app.R;
import com.junzresume.app.adapter.ListViewCommonAdapter;
import com.junzresume.app.adapter.ListViewExpAdapter;
import com.junzresume.app.db.JunzResumeDB;
import com.junzresume.app.entity.ListViewItemCommon;
import com.junzresume.app.entity.ListViewItemExp;
import com.junzresume.app.util.Util;

import android.app.AlertDialog;
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
		final EditText inputEdit = new EditText(context);
		inputEdit.setLines(3);

		new AlertDialog.Builder(context).setTitle("请输入技能信息").setView(inputEdit)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						String skill = inputEdit.getText().toString();
						if (TextUtils.isEmpty(skill)) {
							Toast.makeText(context, "输入不能为空",
									Toast.LENGTH_SHORT).show();
							return;
						}
						JunzResumeDB.getInstence(context).insertProSkill(
								Util.userId, skill);
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
