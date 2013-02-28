package org.leonlong.android.moneymaker.activity;

import org.leonlong.android.moneymaker.R;
import org.leonlong.android.moneymaker.util.SettingsUtil;
import org.leonlong.android.moneymaker.util.BitmapUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class WelcomeActivity extends Activity {

	private ImageView welcomeIV = null;
	private Animation animation = null;
	private Handler handler = null;
	private Runnable runnable = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_welcome);
		welcomeIV = (ImageView) findViewById(R.id.welcome_iv);

		// 转换图片适应屏幕大小
		Bitmap welcome = BitmapFactory.decodeResource(getResources(),
				R.drawable.welcome);

		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		int screenWidth = display.getWidth();
		int screenHeight = display.getHeight();
		welcome = BitmapUtil.getBitmapByWidthAndHeight(welcome, screenWidth,
				screenHeight);

		welcomeIV.setImageBitmap(welcome);

		// 设置动画
		animation = AnimationUtils.loadAnimation(WelcomeActivity.this,
				R.anim.fade_out);
		animation.setFillAfter(true); // 动画结束后停在结束位置
		animation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				if (SettingsUtil.getIsFirstBoot(WelcomeActivity.this)) {
					intent.setClass(WelcomeActivity.this, GuideActivity.class);
				} else {
					intent.setClass(WelcomeActivity.this, MainActivity.class);
				}
				startActivity(intent);
				finish();
			}
		});

		// 启动动画
		handler = new Handler();
		runnable = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				findViewById(R.id.welcome_linearlayout).startAnimation(
						animation);
			}
		};
		handler.removeCallbacks(runnable);
		handler.postDelayed(runnable, 2500);
	}

}
