package com.junzresume.app.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;

import com.junzresume.app.R;
import com.junzresume.app.adapter.DrawerAdapter;

public class MainActivity extends BaseActivity implements OnClickListener {

	ImageView titleMenuImg;
	ImageView titleExitImg;
	DrawerLayout drawerLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
		setListener();

		DrawerAdapter adapter = new DrawerAdapter(this);
		ListView listView = (ListView) findViewById(R.id.left_drawer);
		listView.setAdapter(adapter);

	}

	private void initView() {
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		titleMenuImg = (ImageView) findViewById(R.id.title_menu_img);
		titleExitImg = (ImageView) findViewById(R.id.title_exit_img);
	}

	private void setListener() {
		titleMenuImg.setOnClickListener(this);
		titleExitImg.setOnClickListener(this);
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
}
