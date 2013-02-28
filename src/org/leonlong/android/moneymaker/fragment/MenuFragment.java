package org.leonlong.android.moneymaker.fragment;

import org.leonlong.android.moneymaker.R;
import org.leonlong.android.moneymaker.activity.MainActivity;
import org.leonlong.android.moneymaker.util.RuntimeUtil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuFragment extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// String[] birds = getResources().getStringArray(R.array.functions);
		String[] birds = new String[] { "欢迎，" + RuntimeUtil.currentUserName,
				"金钱市场", "个人中心", "金钱兑换", "推荐好友", "帮助", "反馈" };
		ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_list_item_1,
				android.R.id.text1, birds);
		setListAdapter(menuAdapter);
	}

	@Override
	public void onListItemClick(ListView lv, View v, int position, long id) {
		Fragment newContent = null;
		// if (position % 2 == 0) {
		// newContent = new BirdGridFragment(0);
		// } else {
		// newContent = new SampleListFragment();
		// }
		switch (position) {
		case 0:
			break;
		case 1:
			newContent = new MoneyMarketFragment();
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		default:
			break;
		}
		if (newContent != null)
			switchFragment(newContent);
	}

	// the meat of switching the above fragment
	private void switchFragment(Fragment fragment) {
		if (getActivity() == null)
			return;

		if (getActivity() instanceof MainActivity) {
			MainActivity ra = (MainActivity) getActivity();
			ra.switchContent(fragment);
		}
	}
}
