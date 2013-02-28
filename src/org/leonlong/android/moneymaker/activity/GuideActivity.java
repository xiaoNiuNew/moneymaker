package org.leonlong.android.moneymaker.activity;

import java.util.ArrayList;
import java.util.List;

import org.leonlong.android.moneymaker.R;
import org.leonlong.android.moneymaker.adapter.GuideViewPageAdapter;
import org.leonlong.android.moneymaker.util.SettingsUtil;
import org.leonlong.android.moneymaker.util.BitmapUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GuideActivity extends Activity implements OnPageChangeListener {

	private ViewPager vp;
	private Button startExperienceBTN;
	private GuideViewPageAdapter adapter;
	private List<View> views;

	private static final int[] guide_imgs = { R.drawable.guide1,
			R.drawable.guide2, R.drawable.guide3, R.drawable.guide4 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_guide);

		vp = (ViewPager) findViewById(R.id.vp_guide);
		startExperienceBTN = (Button) findViewById(R.id.btn_start_experience);
		startExperienceBTN.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SettingsUtil.setIsFirstBoot(GuideActivity.this, false);
				Intent intent = new Intent();
				intent.setClass(GuideActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});

		views = new ArrayList<View>();

		LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);

		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		int screenWidth = display.getWidth();
		int screenHeight = display.getHeight();

		for (int i = 0; i < guide_imgs.length; i++) {
			ImageView iv = new ImageView(this);
			iv.setLayoutParams(mParams);
			// iv.setImageResource(guide_imgs[i]);
			iv.setImageBitmap(BitmapUtil.getBitmapByWidthAndHeight(
					BitmapFactory.decodeResource(getResources(), guide_imgs[i]),
					screenWidth, screenHeight));
			views.add(iv);
		}

		adapter = new GuideViewPageAdapter(views);
		vp.setAdapter(adapter);
		vp.setOnPageChangeListener(this);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		if (arg0 == 3) {
			startExperienceBTN.setVisibility(View.VISIBLE);
		} else {
			startExperienceBTN.setVisibility(View.GONE);
		}
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub

	}

}
