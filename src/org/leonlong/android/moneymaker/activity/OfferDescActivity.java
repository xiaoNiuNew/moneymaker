package org.leonlong.android.moneymaker.activity;

import net.miidi.ad.wall.AdWall;
import net.miidi.ad.wall.IAdWallGetPointsNotifier;

import org.leonlong.android.moneymaker.R;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.waps.AppConnect;
import cn.waps.UpdatePointsNotifier;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.ctsxa.mean.QuMiEarnPointsNotify;
import com.ctsxa.mean.QuMiOfConnect;
import com.datouniao.AdPublisher.GetAmountNotifier;
import com.dlnetwork.Dianle;
import com.dlnetwork.GetTotalMoneyListener;
import com.emar.escore.sdk.YjfSDK;
import com.emar.escore.sdk.widget.UpdateScordNotifier;

public class OfferDescActivity extends SherlockActivity implements
		UpdatePointsNotifier, GetAmountNotifier, QuMiEarnPointsNotify,
		IAdWallGetPointsNotifier, GetTotalMoneyListener, UpdateScordNotifier {

	private int position;
	private TextView pointTV;
	private Button startOfferBTN;
	private int point;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setBackgroundDrawable(
				getResources().getDrawable(R.drawable.bg_topbar));

		setContentView(R.layout.activity_offer_desc);

		pointTV = (TextView) findViewById(R.id.tv_point);

		startOfferBTN = (Button) findViewById(R.id.btn_start_offer);
		startOfferBTN.setOnClickListener(new StartOfferBtnOnClickListener());
		position = getIntent().getExtras().getInt("position");

		String[] money_areas = getResources().getStringArray(
				R.array.money_areas);
		setTitle(money_areas[position]);
		
		com.juzi.main.AppConnect.getInstance(this);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	class StartOfferBtnOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch (position) {
			// 万普平台
			case 0:
				AppConnect.getInstance(OfferDescActivity.this).showOffers(
						OfferDescActivity.this);
				break;
			case 1:

				break;
			// 大头鸟
			case 2:
				com.datouniao.AdPublisher.AppConnect.getInstance(
						OfferDescActivity.this).ShowAdsOffers();
				break;
			// 趣米
			case 3:
				QuMiOfConnect.getQumiConnectInstance().showOffers();
				break;
			// 米迪
			case 4:
				AdWall.showAppOffers(null);
				break;
			// 点乐
			case 5:
				Dianle.showOffers();
				break;
			// 易积分
			case 6:
				YjfSDK.getInstance(OfferDescActivity.this,
						OfferDescActivity.this).showAdlist(
						OfferDescActivity.this, 0);
				break;
			// 桔子
			case 7:
				com.juzi.main.AppConnect.getInstance(OfferDescActivity.this)
						.showOffers(OfferDescActivity.this);
				break;
			case 8:

				break;
			case 9:

				break;

			default:
				break;
			}
		}
	}

	private class GetPointTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			progressDialog = ProgressDialog.show(OfferDescActivity.this, "提示",
					"正在努力加载数据。。。");
			progressDialog.setCancelable(true);
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			refreshPoint();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			progressDialog.dismiss();
			/** 显示结果 */
			pointTV.setText(point + "");
			super.onPostExecute(result);
		}

	}

	private void refreshPoint() {
		// return point;
		try {
			switch (position) {
			case 0:
				AppConnect.getInstance(this).getPoints(this);
				break;
			case 1:

				break;
			case 2:
				com.datouniao.AdPublisher.AppConnect.getInstance(this);
				break;
			case 3:
				QuMiOfConnect.getQumiConnectInstance().showpoints();
				break;
			case 4:
				AdWall.getPoints(this);
				break;
			case 5:
				Dianle.getTotalMoney(this);
				break;
			case 6:
				YjfSDK.getInstance(OfferDescActivity.this,
						OfferDescActivity.this).getScore();
				break;
			case 7:
				point = Integer.valueOf(com.juzi.main.AppConnect.getInstance(
						OfferDescActivity.this).getPoints(
						OfferDescActivity.this));
				break;
			case 8:

				break;
			case 9:

				break;

			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void getUpdatePoints(String arg0, int arg1) {
		// TODO Auto-generated method stub
		point = arg1;
	}

	@Override
	public void getUpdatePointsFailed(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		new GetPointTask().execute((Void) null);
		super.onResume();
	}

	@Override
	public void GetAmountResponse(String arg0, float arg1) {
		// TODO Auto-generated method stub
		point = (int) arg1;
	}

	@Override
	public void GetAmountResponseFailed(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void earnedQuMiPoints(int arg0) {
		// TODO Auto-generated method stub
		point = arg0;
	}

	@Override
	public void onFailReceivePoints() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReceivePoints(String arg0, int arg1) {
		// TODO Auto-generated method stub
		point = arg1;
	}

	@Override
	public void getTotalMoneyFailed(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getTotalMoneySuccessed(String arg0, long arg1) {
		// TODO Auto-generated method stub
		point = (int) arg1;
	}

	@Override
	public void updateScoreFailed(int arg0, int arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateScoreSuccess(int arg0, int arg1, int arg2, String arg3) {
		// TODO Auto-generated method stub
		point = arg1;
	}
}
