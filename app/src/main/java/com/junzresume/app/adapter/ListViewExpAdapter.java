package com.junzresume.app.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.junzresume.app.R;
import com.junzresume.app.entity.ListViewItemExp;

public class ListViewExpAdapter extends ArrayAdapter<ListViewItemExp> {

	int resourceId;

	public ListViewExpAdapter(Context context, int resource,
			List<ListViewItemExp> objects) {
		super(context, resource, objects);
		resourceId = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		ListViewItemExp item = getItem(position);
		ViewHolder viewHolder;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(
					R.layout.fragment_listview_exp, null);
			viewHolder = new ViewHolder();
			viewHolder.seq = (TextView) view.findViewById(R.id.exp_seq_num);
			viewHolder.pName = (TextView) view
					.findViewById(R.id.exp_project_name);
			viewHolder.pDesp = (TextView) view
					.findViewById(R.id.exp_project_desp);
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		if (position % 2 == 0) {
			view.setBackgroundColor(Color.parseColor("#373737"));
		} else {
			view.setBackgroundColor(Color.parseColor("#000000"));

		}
		viewHolder.seq.setText(String.valueOf(item.getSeqNum()) + ".");
		viewHolder.pName.setText(item.getProgrameName());
		viewHolder.pDesp.setText(item.getProgrameDesp());
		return view;

	}

	class ViewHolder {
		TextView seq;
		TextView pName;
		TextView pDesp;
	}

}
