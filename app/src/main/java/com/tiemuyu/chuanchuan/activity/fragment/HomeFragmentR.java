package com.tiemuyu.chuanchuan.activity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.tiemuyu.chuanchuan.activity.MainActivity;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.proxy.LoadingProxy;

import java.util.ArrayList;

/**
 * Created by 梁文硕 on 2017/5/8.
 */

public class HomeFragmentR extends BaseFragment {
	private View view;
	private SlidingTabLayout mSegmentTabLayout;
	private final String[] mTitles = {
			"定制成品", "最新报价", "裙装"
			, "外套", "上衣","裤装", "套装"
	};
	private String whoShow = "定制成品";
	private int mPostion = 0;
	private Activity mContext;

	public static HomeFragmentR getInstance(Activity mContext) {
		HomeFragmentR sf = new HomeFragmentR();
		sf.mContext = mContext;
		return sf;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.home_fragment_r, null);
		initView();
		return view;
	}

	private void initView() {
		mSegmentTabLayout = (SlidingTabLayout) view.findViewById(R.id.segmentTabLayout);
		for (String title : mTitles) {
			SimpleCardFragment instance = SimpleCardFragment.getInstance(title, mContext,this);
			mFragments.add(instance);
		}
		ViewPager vp = (ViewPager) view.findViewById(R.id.vp);
		vp.setAdapter(new MyPagerAdapter(MainActivity.mSupportFragmentManager));
		mSegmentTabLayout.setViewPager(vp);
	}

	private ArrayList<SimpleCardFragment> mFragments = new ArrayList<>();
	private class MyPagerAdapter extends FragmentPagerAdapter {
		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return mFragments.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return mTitles[position];
		}

		@Override
		public Fragment getItem(int position) {
			mPostion = position;
			return mFragments.get(position);
		}
	}

	public String getWhoShow(){
		return whoShow;
	}
}
