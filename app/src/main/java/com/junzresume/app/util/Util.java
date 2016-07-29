package com.junzresume.app.util;

import android.content.Context;
import android.widget.Toast;

public class Util {
	private static Toast toast;
	public static int userId;

	/**
	 * 显示Toast 不会重复显示
	 * 
	 * @param context
	 * @param text
	 */
	public static void showToast(Context context, String text) {
		if (toast == null) {
			toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		} else {
			toast.setText(text);
		}

		toast.show();
	}

	public static void setUserId(int thisUserId) {
		userId = thisUserId;
	}

	public static int getUserId() {
		return userId;
	}
}
