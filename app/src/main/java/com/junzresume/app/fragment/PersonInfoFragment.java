package com.junzresume.app.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.junzresume.app.R;
import com.junzresume.app.activity.BaseActivity;

public class PersonInfoFragment extends Fragment {
	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.drawer_person_info, container, false);
		view.setBackgroundColor(Color.BLACK);

		return view;
	}

}
