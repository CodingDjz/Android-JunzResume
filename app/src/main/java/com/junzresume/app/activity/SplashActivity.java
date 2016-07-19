package com.junzresume.app.activity;

import com.junzresume.app.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.ImageView;

public class SplashActivity extends BaseActivity {
	long delayTime = 3 * 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		ImageView splash = new ImageView(this);
		splash.setBackgroundResource(R.drawable.splash);
		setContentView(splash);

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				startActivity(new Intent(SplashActivity.this,
						MainActivity.class));

			}
		}, delayTime);
	}
}
