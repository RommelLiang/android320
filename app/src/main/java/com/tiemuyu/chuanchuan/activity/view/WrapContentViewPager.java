package com.tiemuyu.chuanchuan.activity.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by 梁文硕 on 2017/4/13.
 */

public class WrapContentViewPager extends ViewPager {

	private int mCurrentPagePosition = 0;
	private int height;
	public WrapContentViewPager(Context context) {
		super(context);
	}

	public WrapContentViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		Log.e( "getCurrentItem() ",getCurrentItem()+"" );
		findViewWithTag("myView"+getCurrentItem());
		View child = findViewWithTag("myView"+getCurrentItem());
		if (child != null) {
			Log.e("onMeasure: ", "oo"+mCurrentPagePosition);
			child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
			int h = child.getMeasuredHeight();
			heightMeasureSpec = MeasureSpec.makeMeasureSpec(h, MeasureSpec.EXACTLY);
			Log.e("onMeasure: ",getChildCount()+":" +h + ":mCurrentPagePosition:" + mCurrentPagePosition);
		} else {

		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	public void reMeasureCurrentPage(int position) {
		mCurrentPagePosition = position;
		requestLayout();
	}
}
