package com.tiemuyu.chuanchuan.activity.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 梁文硕 on 2017/4/13.
 */

public class ClotherViewPager extends PagerAdapter {
	private List<View> viewList;
	public ClotherViewPager(List<View> viewList) {
		this.viewList = viewList;
	}
	@Override
	public int getCount() {
		return viewList.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view==object;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(viewList.get(position));
		viewList.get(position).setTag("myView"+position);
		return viewList.get(position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(viewList.get(position));
	}
}
