package com.tiemuyu.chuanchuan.activity.proxy.impl;

import android.app.Activity;
import android.content.Context;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.tiemuyu.chuanchuan.activity.proxy.proxyInter.ProgressLoad;

/**
 * Created by 梁文硕 on 2017/4/27.
 */

public class ProgressLoadIMPL implements ProgressLoad {
	private static KProgressHUD SKProgressHUD;
	public static String lable = "Please wait...";
	private boolean cancellable = true;
	private Context activity;

	@Override
	public void create(Context mContext) {
		SKProgressHUD = KProgressHUD.create(mContext);
		activity = mContext;
	}

	public void show() {
		if (!((Activity) activity).isFinishing()) {
			SKProgressHUD
					.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
					.setLabel(lable)
					.setCancellable(cancellable)
					.setAnimationSpeed(1)
					.setDimAmount(0.5f).show();
		}
	}

	public void setLable(String mLable) {
		if (mLable != null) {
			if (!mLable.equals("")) {
				lable = mLable;
			}
		} else {
			lable = "Please wait...";
		}
	}

	public void setCancellable(boolean mCancellable) {
		cancellable = mCancellable;
	}

	public void dismiss() {
		if (SKProgressHUD.isShowing()) {
			SKProgressHUD.dismiss();
		}
	}
}
