package com.tiemuyu.chuanchuan.activity.proxy;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.tiemuyu.chuanchuan.activity.proxy.impl.ProgressLoadIMPL;
import com.tiemuyu.chuanchuan.activity.proxy.proxyInter.ProgressLoad;

/**
 * Created by 梁文硕 on 2017/4/27.
 */

public class LoadingProxy {
	private static LoadingProxy mLoadingProxy;
	private ProgressLoad mProgressLoad;
	private Context mContext;
	private String lable;

	public LoadingProxy(Context mContext) {
		mProgressLoad = new ProgressLoadIMPL();
		mProgressLoad.create(mContext);
		this.mContext = mContext;
	}

	public static LoadingProxy getInstance(Context mContext) {
		mLoadingProxy = new LoadingProxy(mContext);
		return mLoadingProxy;
	}


	public void show() {
		if (!((Activity) mContext).isFinishing()) {
			String localClassName = ((Activity) mContext).getLocalClassName();
			Log.e("show: ", localClassName);
			mProgressLoad.setLable(lable);
			mProgressLoad.show();
		}
	}

	public LoadingProxy setLable(String mLable) {
		if (!mLable.equals("")){
			lable = mLable;
		}
		return this;
	}

	public LoadingProxy setCancellable(boolean mCancellable) {
		mProgressLoad.setCancellable(mCancellable);
		return this;
	}

	public void dismiss() {
		mLoadingProxy.setLable(ProgressLoadIMPL.lable);
		mProgressLoad.dismiss();
		lable = "";
	}


}
