package com.junzresume.app.adapter;

import java.util.List;

import com.junzresume.app.R;
import com.junzresume.app.entity.ListViewItemCommon;
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

public class ListViewCommonAdapter extends ArrayAdapter<ListViewItemCommon> {

	int resourceId;

	public ListViewCommonAdapter(Context context, int resource,
			List<ListViewItemCommon> objects) {
		super(context, resource, objects);
		resourceId = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ListViewItemCommon item = getItem(position);
		View view;
		ViewHolder viewHolder = null;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(
					R.layout.fragment_listview_item, null);
			viewHolder = new ViewHolder();
			viewHolder.seq = (TextView) view.findViewById(R.id.seq_num);
			viewHolder.text = (TextView) view.findViewById(R.id.item_text);
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		// 条状上色
		if (position % 2 == 0) {
			view.setBackgroundColor(Color.parseColor("#373737"));
		} else {// 必须写此分支，因为convertView可能已经被设置过
			view.setBackgroundColor(Color.parseColor("#000000"));

		}
		viewHolder.seq.setText(String.valueOf(item.getSeqNum()) + ".");
		viewHolder.text.setText(item.getText());

		return view;
	}

	class ViewHolder {
		TextView seq;
		TextView text;
	}
}
