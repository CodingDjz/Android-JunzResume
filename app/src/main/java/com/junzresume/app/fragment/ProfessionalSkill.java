package com.junzresume.app.fragment;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import com.junzresume.app.R;
import com.junzresume.app.adapter.ArrayListViewAdapter;
import com.junzresume.app.db.JunzResumeDB;
import com.junzresume.app.entity.ArrayListViewItem;
import com.junzresume.app.util.Util;

public class ProfessionalSkill extends Fragment implements OnClickListener {

	View view;
	Context context;
	ListView listView;
	Button addBtn;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.drawer_arraylistview_layout,
				container, false);
		init();
		initAdapter();
		return view;
	}

	private void init() {
		context = getActivity();
		listView = (ListView) view.findViewById(R.id.arraylist_listview);
		addBtn = (Button) view.findViewById(R.id.add_btn);
	}

	/**
	 * 初始化适配器
	 */
	private void initAdapter() {
		ArrayList<ArrayListViewItem> itemList = JunzResumeDB.getInstence(
				context).getProSkillByid(Util.userId);
		if (itemList == null) {
			// TODO 练习使用snackbar
			// Snackbar.make(rootlayout, "Hello SnackBar!",
			// Snackbar.LENGTH_SHORT)
			// show();
			return;
		}

		ArrayListViewAdapter adapter = new ArrayListViewAdapter(context,
				R.layout.arraylistview_item, itemList);
		listView.setAdapter(adapter);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_btn:

			break;

		default:
			break;
		}

	}
}
