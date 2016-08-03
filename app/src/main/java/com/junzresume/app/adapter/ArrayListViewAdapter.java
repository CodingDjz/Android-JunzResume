package com.junzresume.app.adapter;

import java.util.List;

import com.junzresume.app.R;
import com.junzresume.app.entity.ArrayListViewItem;
import com.junzresume.app.entity.DrawerItem;

import android.R.integer;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ArrayListViewAdapter extends ArrayAdapter<ArrayListViewItem> {

	int resourceId;

	public ArrayListViewAdapter(Context context, int resource,
			List<ArrayListViewItem> objects) {
		super(context, resource, objects);
		resourceId = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ArrayListViewItem item = (ArrayListViewItem) getItem(position);
		View view;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(
					R.layout.arraylistview_item, null);
		} else {
			view = convertView;
		}
		// Ìõ×´ÉÏÉ«
		if (position % 2 == 0) {
			view.setBackgroundColor(0x373737);
		}
		TextView id = (TextView) view.findViewById(R.id.seq_num);
		TextView text = (TextView) view.findViewById(R.id.item_text);
		id.setText(item.getSeqNum());
		text.setText(item.getText());

		return view;
	}
}
