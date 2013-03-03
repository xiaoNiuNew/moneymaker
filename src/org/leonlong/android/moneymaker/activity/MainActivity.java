package org.leonlong.android.moneymaker.activity;

import net.miidi.ad.wall.AdWallManager;

import org.leonlong.android.moneymaker.R;
import org.leonlong.android.moneymaker.fragment.MenuFragment;
import org.leonlong.android.moneymaker.fragment.MoneyMarketFragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import cn.waps.AppConnect;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.ctsxa.mean.QuMiEarnPointsNotify;
import com.ctsxa.mean.QuMiOfConnect;
import com.dlnetwork.Dianle;
import com.emar.escore.sdk.YjfSDK;
import com.emar.escore.sdk.util.c;
import com.emar.escore.sdk.widget.UpdateScordNotifier;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;
//hello this miao!
public class MainActivity extends SlidingFragmentActivity implements
		QuMiEarnPointsNotify, UpdateScordNotifier {

	private Fragment mContent;
	private long time = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.app_name);

		setContentView(R.layout.activity_main);

		// check if the content frame contains the menu frame
		if (findViewById(R.id.menu_frame) == null) {
			setBehindContentView(R.layout.frame_menu);
			getSlidingMenu().setSlidingEnabled(true);
			getSlidingMenu()
					.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
			// show home as up so we can toggle
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		} else {
			// add a dummy view
			View v = new View(this);
			setBehindContentView(v);
			getSlidingMenu().setSlidingEnabled(false);
			getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		}

		// set the Above View Fragment
		if (savedInstanceState != null)
			mContent = getSupportFragmentManager().getFragment(
					savedInstanceState, "mContent");
		if (mContent == null)
			mContent = new MoneyMarketFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, mContent).commit();

		// set the Behind View Fragment
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, new MenuFragment()).commit();

		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindScrollScale(0.25f);
		sm.setFadeDegree(0.25f);
		sm.setBackground(getResources().getDrawable(R.drawable.bg_menu));

		getSupportActionBar().setBackgroundDrawable(
				getResources().getDrawable(R.drawable.bg_topbar));

		this.setTitle(getResources().getString(R.string.app_name)
				+ "\t\t\t\t\t\t\t10000金钱");

		// init AD
		// 万普
		AppConnect.getInstance(this);
		// 大头鸟
		com.datouniao.AdPublisher.AppConnect.getInstance(this);
		// 趣米
		QuMiOfConnect.ConnectQuMi(this, "43cc538404afe7e3", "5530fa1e7336f9d3");
		// 米迪
		AdWallManager.init(this, "11545", "c16bs2fzctkvk5fc", false);
		// 点乐
		Dianle.initDianleContext(this, "5c1e1c6a82c1d74752dde41f7ab5cd10");
		// 易积分
		YjfSDK.getInstance(this, this).initInstance();
		// 桔子
		com.juzi.main.AppConnect.getInstance(this);
		// show the explanation dialog
		// if (savedInstanceState == null)
		// new AlertDialog.Builder(this)
		// .setTitle(R.string.what_is_this)
		// .setMessage(R.string.responsive_explanation)
		// .show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		// getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);
	}

	public void switchContent(final Fragment fragment) {
		mContent = fragment;
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, fragment).commit();
		Handler h = new Handler();
		h.postDelayed(new Runnable() {
			public void run() {
				getSlidingMenu().showContent();
			}
		}, 50);
		if (fragment instanceof MoneyMarketFragment) {
			// this.setTitle("fuck");
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
		case KeyEvent.KEYCODE_MENU:
			if (getSlidingMenu().isMenuShowing()) {
				showContent();
			} else {
				showMenu();
			}
			return true;
		case KeyEvent.KEYCODE_BACK:
			if (System.currentTimeMillis() - time > 3000) {
				Toast.makeText(MainActivity.this,
						R.string.please_press_back_again, Toast.LENGTH_SHORT)
						.show();
				time = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
			return true;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		AppConnect.getInstance(this).finalize();
		com.datouniao.AdPublisher.AppConnect.getInstance(this).finalize();
		YjfSDK.getInstance(this, this).recordAppClose();
		com.juzi.main.AppConnect.getInstance(MainActivity.this).finalize();
		super.onDestroy();
	}

	@Override
	public void earnedQuMiPoints(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateScoreFailed(int arg0, int arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateScoreSuccess(int arg0, int arg1, int arg2, String arg3) {
		// TODO Auto-generated method stub

	}

}
