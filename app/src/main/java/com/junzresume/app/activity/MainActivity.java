package com.junzresume.app.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.junzresume.app.R;
import com.junzresume.app.adapter.DrawerAdapter;
import com.junzresume.app.fragment.InterestingHobbies;
import com.junzresume.app.fragment.PersonInfoFragment;
import com.junzresume.app.fragment.ProfessionalSkill;
import com.junzresume.app.fragment.ProgrameExperience;
import com.junzresume.app.fragment.SettingFragment;

public class MainActivity extends BaseActivity implements OnClickListener,
		OnItemClickListener {

	ImageView titleMenuImg;
	ImageView titleExitImg;
	DrawerLayout drawerLayout;
	ListView drawerListView;
	List<Fragment> fragList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
		setListener();

	}

	private void initView() {
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		titleMenuImg = (ImageView) findViewById(R.id.title_menu_img);
		titleExitImg = (ImageView) findViewById(R.id.title_exit_img);
		DrawerAdapter adapter = new DrawerAdapter(this);
		drawerListView = (ListView) findViewById(R.id.left_drawer);
		drawerListView.setAdapter(adapter);
		// 将列表Fragment添加到List
		fragList = new ArrayList<>();
		fragList.add(new SettingFragment());
		fragList.add(new PersonInfoFragment());
		fragList.add(new ProfessionalSkill());
		fragList.add(new ProgrameExperience());
		fragList.add(new InterestingHobbies());
	}

	private void setListener() {
		titleMenuImg.setOnClickListener(this);
		titleExitImg.setOnClickListener(this);
		drawerListView.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_menu_img:
			drawerLayout.openDrawer(Gravity.LEFT);
			break;
		case R.id.title_exit_img:
			finish();
			break;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		selectAction(position);
	}

	/**
	 * 侧边栏点击事件
	 * 
	 * @param position
	 */
	public void selectAction(int position) {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.replace(R.id.content_framelayout, fragList.get(position))
				.commit();

		drawerListView.setItemChecked(position, true);
		drawerLayout.closeDrawer(drawerListView);
	}
}
