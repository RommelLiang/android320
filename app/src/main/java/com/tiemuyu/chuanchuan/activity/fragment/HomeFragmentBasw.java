package com.tiemuyu.chuanchuan.activity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 梁文硕 on 2017/5/10.
 */

public abstract class HomeFragmentBasw extends Fragment {
	protected Context mContext;

	protected static final String LOG_FRAGMENT = "LOG_FRAGMENT";

	protected boolean isVisible;

	private boolean isPrepared;

	private boolean isFirst = true;

	View mView;

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		Log.w(LOG_FRAGMENT," setUserVisibleHint:"+getUserVisibleHint());
		if (isVisibleToUser){
			isVisible = true;
			onVisible();
		}else {
			isVisible = false;
			onInVisible();
		}

	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext =getActivity();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Log.w(LOG_FRAGMENT, " onCreateView");
		if (mView == null) {
			mView = inflater.inflate(getLayout(), container, false);
			initView(mView);
		}
		return mView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.w(LOG_FRAGMENT, " onActivityCreated");
		isPrepared = true;

	}

	@Override
	public void onResume() {
		super.onResume();
		if (getUserVisibleHint()){
			setUserVisibleHint(true);
		}
	}

	protected void onVisible(){

		if (!isPrepared || !isVisible || !isFirst){
			return;
		}
		loadData();
		isFirst = false;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	/**
	 * 初始化视图布局
	 * @return 返回需要加载的视图
	 */
	public abstract int getLayout();

	/**
	 * 初始化布局控件
	 * @param view
	 */
	public abstract void initView(View view);

	/**
	 * 视图不可见的时候调用
	 */
	public abstract void onInVisible();

	/**
	 * 加载数据
	 */
	public abstract void loadData();
}
