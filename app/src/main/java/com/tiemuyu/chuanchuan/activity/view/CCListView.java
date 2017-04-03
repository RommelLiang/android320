package com.tiemuyu.chuanchuan.activity.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by 梁文硕 on 2017/4/1.
 */

public class CCListView extends ListView {
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
}
