package com.tiemuyu.chuanchuan.activity.broadcastreceiver;

import java.util.List;

import android.content.Context;
import android.content.Intent;

//广播工具类
public class ReceiverTools {

	// 发送广播-发送单个
	public static void sendBrocat(Context context, String action) {
		Intent intent = new Intent();
		intent.setAction(action);
		context.sendBroadcast(intent);
	}

	// 发送广播-发送多个
	public static void sendBrocat(Context context, List<String> ls) {
		Intent intent = new Intent();
		
		for (String action : ls) {
			intent.setAction(action);
		}
		context.sendBroadcast(intent);
	}
}
