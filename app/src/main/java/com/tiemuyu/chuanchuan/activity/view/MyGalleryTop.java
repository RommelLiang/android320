package com.tiemuyu.chuanchuan.activity.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

public class MyGalleryTop extends Gallery {
	public MyGalleryTop(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyGalleryTop(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		return false;
	}

}