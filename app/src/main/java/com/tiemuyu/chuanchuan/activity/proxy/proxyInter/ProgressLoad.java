package com.tiemuyu.chuanchuan.activity.proxy.proxyInter;

import android.content.Context;

/**
 * Created by 梁文硕 on 2017/4/27.
 */

public interface ProgressLoad {
	void create(Context mContext);
	void show();
	void setLable(String mLable);
	void setCancellable(boolean mCancellable);
	void dismiss();
}
