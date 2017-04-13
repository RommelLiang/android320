package com.tiemuyu.chuanchuan.activity.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by 梁文硕 on 2017/4/13.
 */

public class CustomScrollView extends ScrollView {
	private GestureDetector mGestureDetector;

	public CustomScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mGestureDetector = new GestureDetector(context, new YScrollDetector());
		setFadingEdgeLength(0);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			//将事件先传递给自身的触摸响应，否则 ScrollView 自己的滑动会受到影响
			onTouchEvent(ev);
			//这里直接返回 false 不会影响到自身，因为如果子 View 没有处理事件，那么最后事件还是会返还到这里的
			return false;
		}
		return super.onInterceptTouchEvent(ev);
	}

	// Return false if we're scrolling in the x direction
	class YScrollDetector extends SimpleOnGestureListener {
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
		                        float distanceX, float distanceY) {
			return (Math.abs(distanceY) > Math.abs(distanceX));
		}
	}

	private float mPosX, mPosY, mCurPosX, mCurPosY;

}
