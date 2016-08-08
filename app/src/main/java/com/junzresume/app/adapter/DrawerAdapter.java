package com.junzresume.app.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.junzresume.app.R;
import com.junzresume.app.entity.DrawerItem;

public class DrawerAdapter extends BaseAdapter {

	List<DrawerItem> MenuItems;
	Context context;

	public DrawerAdapter(Context context) {
		this.context = context;
		MenuItems = new ArrayList<>();
		MenuItems.add(new DrawerItem(R.drawable.person_image, ""));
		MenuItems.add(new DrawerItem(R.drawable.person_info, "个人信息"));
		MenuItems.add(new DrawerItem(R.drawable.professional_skills, "专业技能"));
		MenuItems.add(new DrawerItem(R.drawable.program_exp, "项目经验"));
		MenuItems.add(new DrawerItem(R.drawable.interesting_hobbies, "兴趣爱好"));
	}

	@Override
	public boolean isEnabled(int position) {
		if (position == 0)
			return false;
		return super.isEnabled(position);
	}

	@Override
	public int getCount() {
		return MenuItems.size();
	}

	@Override
	public Object getItem(int position) {
		return MenuItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DrawerItem drawerItem = (DrawerItem) getItem(position);
		View view;
		if (convertView == null) {
			view = LayoutInflater.from(context).inflate(R.layout.drawer_item,
					null);
		} else {
			view = convertView;
		}
		ImageView imageView = (ImageView) view
				.findViewById(R.id.draweritem_image);
		TextView textView = (TextView) view.findViewById(R.id.draweritem_text);
		imageView.setImageResource(drawerItem.getItemImage());
		textView.setText(drawerItem.getItemName());

		return view;
	}

}
