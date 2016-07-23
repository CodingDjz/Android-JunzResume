package com.junzresume.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.ImageView;

import com.junzresume.app.R;

public class SplashActivity extends BaseActivity {
	long delayTime = 5 / 2 * 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		ImageView splash = new ImageView(this);
		splash.setBackgroundResource(R.drawable.splash_bgp3);
		setContentView(splash);

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				startActivity(new Intent(SplashActivity.this,
						LoginActivity.class));
				finish();

			}
		}, delayTime);
	}
}
