package com.junzresume.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.junzresume.app.R;
import com.junzresume.app.entity.DrawerItem;

import java.util.ArrayList;
import java.util.List;

public class DrawerAdapter extends BaseAdapter {

	List<DrawerItem> MenuItems;
	Context context;

	public DrawerAdapter(Context context) {
		this.context = context;
		MenuItems = new ArrayList<>();
		MenuItems.add(new DrawerItem(R.drawable.person_image, ""));
		MenuItems.add(new DrawerItem(R.drawable.person_info, "������Ϣ"));
		MenuItems.add(new DrawerItem(R.drawable.professional_skills, "רҵ����"));
		MenuItems.add(new DrawerItem(R.drawable.program_exp, "��������"));
		MenuItems.add(new DrawerItem(R.drawable.interesting_hobbies, "��Ȥ����"));
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
