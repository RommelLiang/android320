package com.tiemuyu.chuanchuan.activity.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.util.ToastHelper;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler{
	
	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
	
    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
    	api = WXAPIFactory.createWXAPI(this, Constant.WE_chat_APP_ID);
        api.handleIntent(getIntent(), this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		Log.e( "微信支付onResp: ", resp.toString());
		if (resp.errCode == 0) {
			ToastHelper.show(this,"支付完成");

		} else if (resp.errCode == -1) {
			ToastHelper.show(this,"支付失败");
		} else if(resp.errCode == -2) {
			ToastHelper.show(this,"支付已取消");
		}
		Intent intent = new Intent();
		intent.setAction("tiemuyu.cc.wechat.pay");
		intent.putExtra("code",resp.errCode);
		sendBroadcast(intent);
		finish();
	}
}