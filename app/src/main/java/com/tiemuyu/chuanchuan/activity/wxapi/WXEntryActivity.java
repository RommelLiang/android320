package com.tiemuyu.chuanchuan.activity.wxapi;

import android.os.Bundle;

import com.tiemuyu.chuanchuan.activity.R;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

public class WXEntryActivity extends WXCallbackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxcallback);
    }
}
