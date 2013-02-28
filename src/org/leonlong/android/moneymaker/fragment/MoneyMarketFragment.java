package org.leonlong.android.moneymaker.fragment;

import org.leonlong.android.moneymaker.R;
import org.leonlong.android.moneymaker.activity.MainActivity;
import org.leonlong.android.moneymaker.activity.OfferDescActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MoneyMarketFragment extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.list, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		MoneyMarketAdapter adapter = new MoneyMarketAdapter(getActivity());
		String[] money_areas = getResources().getStringArray(
				R.array.money_areas);
		String[] money_areas_desc = getResources().getStringArray(
				R.array.money_areas_desc);
		for (int i = 0; i < 10; i++) {
			adapter.add(new MoneyMarketItem(R.drawable.gold, money_areas[i],
					money_areas_desc[i]));
		}
		// adapter.add(new MoneyMarketItem(R.drawable.gold, "金钱一区——金蛇狂舞",
		// "金蛇狂舞金钱区，当前拥有超过80个赚钱广告，价值高达8000多金钱。不定期更新。"));
		// adapter.add(new MoneyMarketItem(R.drawable.gold, "金钱二区——金榜题名",
		// "金榜题名金钱区，当前拥有超过30个赚钱广告，价值高达3000多金钱。不定期更新。"));
		// adapter.add(new MoneyMarketItem(R.drawable.gold, "金钱三区——金戈铁马",
		// "金戈铁马金钱区，当前拥有超过40个赚钱广告，价值高达4000多金钱。不定期更新。"));
		// adapter.add(new MoneyMarketItem(R.drawable.gold, "金钱四区——金城千里",
		// "金城千里金钱区，当前拥有超过20个赚钱广告，价值高达2000多金钱。不定期更新。"));
		// adapter.add(new MoneyMarketItem(R.drawable.gold, "金钱五区——金门绣户",
		// "金门绣户金钱区，当前拥有超过20个赚钱广告，价值高达2000多金钱。不定期更新。"));
		// adapter.add(new MoneyMarketItem(R.drawable.gold, "金钱六区——金光盖地",
		// "金光盖地金钱区，当前拥有超过20个赚钱广告，价值高达2000多金钱。不定期更新。"));
		// adapter.add(new MoneyMarketItem(R.drawable.gold, "金钱七区——金石不渝",
		// "金石不渝金钱区，当前拥有超过25个赚钱广告，价值高达2500多金钱。不定期更新。"));
		// adapter.add(new MoneyMarketItem(R.drawable.gold, "金钱八区——金昭玉粹",
		// "金昭玉粹金钱区，当前拥有超过21个赚钱广告，价值高达2100多金钱。不定期更新。"));
		// adapter.add(new MoneyMarketItem(R.drawable.gold, "金钱九区——金台市骏",
		// "金台市骏金钱区，当前拥有超过25个赚钱广告，价值高达2500多金钱。不定期更新。"));
		// adapter.add(new MoneyMarketItem(R.drawable.gold, "金钱十区——金章紫绶",
		// "金章紫绶金钱区，当前拥有超过30个赚钱广告，价值高达3000多金钱。不定期更新。"));
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		// switchFragment(this);
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		bundle.putInt("position", position);
		intent.setClass(getActivity(), OfferDescActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
		super.onListItemClick(l, v, position, id);
	}

	private class MoneyMarketItem {
		public int iconRes;
		public String title;
		public String desc;

		public MoneyMarketItem(int iconRes, String title, String desc) {
			this.iconRes = iconRes;
			this.title = title;
			this.desc = desc;
		}
	}

	private class MoneyMarketAdapter extends ArrayAdapter<MoneyMarketItem> {

		public MoneyMarketAdapter(Context context) {
			super(context, 0);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(
						R.layout.item_money_market, null);
			}

			ImageView iv = (ImageView) convertView
					.findViewById(R.id.iv_item_money_market_icon);
			iv.setImageResource(getItem(position).iconRes);
			TextView tv_tile = (TextView) convertView
					.findViewById(R.id.tv_item_money_market_title);
			tv_tile.setText(getItem(position).title);
			TextView tv_desc = (TextView) convertView
					.findViewById(R.id.tv_item_money_market_desc);
			tv_desc.setText(getItem(position).desc);

			return convertView;
		}

	}

	private void switchFragment(Fragment fragment) {
		if (getActivity() == null)
			return;

		if (getActivity() instanceof MainActivity) {
			MainActivity ra = (MainActivity) getActivity();
			ra.switchContent(fragment);
		}
	}

}
