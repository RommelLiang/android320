package com.tiemuyu.chuanchuan.activity.util;

import android.content.Context;

import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Created by 梁文硕 on 2017/4/27.
 */

public class ProgressHUD {

	private static KProgressHUD SKProgressHUD;
	private static String lable = "Please wait...";
	private boolean cancellable = true;

	public ProgressHUD(Context mContext) {
		SKProgressHUD = KProgressHUD.create(mContext);
		SKProgressHUD
				.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
				.setLabel(lable)
				.setCancellable(cancellable)
				.setAnimationSpeed(1)
				.setDimAmount(0.5f);
	}

	public static ProgressHUD create(Context mContext) {
		return new ProgressHUD(mContext);
	}

	public void show(){
		SKProgressHUD.show();
	}

	public void setLable(String mLable) {
		lable = mLable;
	}

	public void setCancellable(boolean mCancellable) {
		cancellable = mCancellable;
	}

	public void dismiss(){
		if (SKProgressHUD.isShowing()) {
			SKProgressHUD.dismiss();
		}
	}
}
