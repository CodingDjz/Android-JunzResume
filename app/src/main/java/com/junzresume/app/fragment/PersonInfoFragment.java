package com.junzresume.app.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.junzresume.app.R;
import com.junzresume.app.db.JunzResumeDB;
import com.junzresume.app.entity.PersonInfo;
import com.junzresume.app.util.Util;

public class PersonInfoFragment extends Fragment {
	View view;

	PersonInfo personInfo;

	TextView realName;
	ImageView gender;
	TextView birthday;
	TextView nativePlace;
	TextView degree;
	TextView school;
	TextView professional;
	TextView email;
	TextView contactNumber;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_person_info, container, false);
		initView();
		initInfo();
		return view;
	}

	private void initView() {
		personInfo = JunzResumeDB.getInstence(getActivity()).selectPersonInfo(
				Util.userId);
		realName = (TextView) view.findViewById(R.id.info_realname);
		gender = (ImageView) view.findViewById(R.id.info_gender);
		birthday = (TextView) view.findViewById(R.id.info_birthday);
		nativePlace = (TextView) view.findViewById(R.id.info_nativeplace);
		degree = (TextView) view.findViewById(R.id.info_degree);
		school = (TextView) view.findViewById(R.id.info_school);
		professional = (TextView) view.findViewById(R.id.info_professional);
		email = (TextView) view.findViewById(R.id.info_email);
		contactNumber = (TextView) view.findViewById(R.id.info_contactnumber);
	}

	/**
	 * 初始化信息到View
	 */
	private void initInfo() {
		if (personInfo == null) {
			Util.showToast(getActivity(), "没有填写详细信息");
			return;
		}
		realName.setText(personInfo.getRealName());
		int resId = 0;
		if (personInfo.getGender() == 1) {
			resId = R.drawable.male;
		} else if (personInfo.getGender() == 2) {
			resId = R.drawable.female;
		}
		gender.setBackgroundResource(resId);
		birthday.setText(personInfo.getBirthday());
		nativePlace.setText(personInfo.getNativePlace());
		degree.setText(personInfo.getDegree());
		school.setText(personInfo.getSchool());
		professional.setText(personInfo.getProfessional());
		email.setText(personInfo.getEmail());
		contactNumber.setText(String.valueOf(personInfo.getContactNumber()));
		((TextView) view.findViewById(R.id.info_nativeplace_text))
				.setText(getString(R.string.regist_detail_native_place));
		((TextView) view.findViewById(R.id.info_email_text))
				.setText(getString(R.string.regist_detail_email));
		((TextView) view.findViewById(R.id.info_contactnumber_text))
				.setText(getString(R.string.regist_detail_contact_num));

	}
}
