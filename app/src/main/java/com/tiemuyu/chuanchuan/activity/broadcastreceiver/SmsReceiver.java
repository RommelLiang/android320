package com.tiemuyu.chuanchuan.activity.broadcastreceiver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tiemuyu.chuanchuan.activity.chat_tools.inter.IOnSmsListener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Log;

/**
 * @author hw
 * 短信验证码广播
 *
 */
public class SmsReceiver extends BroadcastReceiver {
	
	private String patternCoder = "(?<!\\d)\\d{6}(?!\\d)";
	public static IOnSmsListener onSmsListener;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Object[] objs = (Object[]) intent.getExtras().get("pdus");
		for (Object obj : objs) {
			byte[] pdu = (byte[]) obj;
			SmsMessage sms = SmsMessage.createFromPdu(pdu);
			String message = sms.getMessageBody();
			//Log.d("----短信内容", "message：" + message);
			// 短息的手机号。。+86开头？
			String from = sms.getOriginatingAddress();
			if (!TextUtils.isEmpty(from)) {
				String code = patternCode(message);
				if (!TextUtils.isEmpty(code)) {
					onSmsListener.smsListener(code);
				}
			}
		}

	}
	private String patternCode(String patternContent) {
		if (TextUtils.isEmpty(patternContent)) {
			return null;
		}
		Pattern p = Pattern.compile(patternCoder);
		Matcher matcher = p.matcher(patternContent);
		if (matcher.find()) {
			return matcher.group();
		}
		return null;
	}
	public static IOnSmsListener getOnSmsListener() {
		return onSmsListener;
	}
	public static void setOnSmsListener(IOnSmsListener onSmsListener) {
		SmsReceiver.onSmsListener = onSmsListener;
	}

	
	
}
