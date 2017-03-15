package com.tiemuyu.chuanchuan.activity.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

/**
 * Created by Reinhard Tristan Eugen Heydrich on 2016/11/28 09:29
 */
public class ViewPagerAdapter extends PagerAdapter {
	private List<View> viewList;
	private boolean isCanScroll = true;
	public ViewPagerAdapter(List<View> viewList) {
		this.viewList = viewList;
	}

	@Override
	public void destroyItem(View view, int position, Object object) {
		((ViewPager)view).removeView(viewList.get(position));
	}

	@Override
	public Object instantiateItem(View view, int position) {
		((ViewPager) view).addView(viewList.get(position));
		return viewList.get(position);
	}

	@Override
	public int getCount() {
		return viewList.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {

		return view==object;
	}
	public void setScanScroll(boolean isCanScroll){
		this.isCanScroll = isCanScroll;
	}


	public boolean isCanScroll() {
		return isCanScroll;
	}

}