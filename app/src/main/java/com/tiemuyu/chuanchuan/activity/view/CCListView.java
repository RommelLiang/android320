package com.tiemuyu.chuanchuan.activity.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by 梁文硕 on 2017/4/1.
 */

public class CCListView extends ListView {
	private float lastY;
	public CCListView(Context context) {
		super(context);
	}
	public CCListView(Context context,AttributeSet as) {
		super(context,as);  }
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			/*//确保 ScrollView 不会拦截按下事件
			requestDisallowInterceptTouchEvent(true);*/
		} else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
			if (lastY > ev.getY()) {//向上滑动的时候
				if (!canScrollList(1)) {
					//如果 ListView 不能在这个方向上滑动就把事件交给上层 ScrollView 处理
					requestDisallowInterceptTouchEvent(false);
					return false;
				}
			} else if (ev.getY() > lastY) {//向下滑动的时候
				if (!canScrollList(-1)) {
					//如果 ListView 不能在这个方向上滑动就把事件交给上层 ScrollView 处理
					requestDisallowInterceptTouchEvent(false);
					return false;
				}
			}
		}
		lastY = ev.getY();//记录上次触摸的位置
		return super.dispatchTouchEvent(ev);
	}
}
