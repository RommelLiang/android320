package com.tiemuyu.chuanchuan.activity.util;

import android.util.Log;

import com.tiemuyu.chuanchuan.activity.BuildConfig;

/**
 * Created by 梁文硕 on 2017/4/12.
 */

public class CheckVersion {
	private static boolean isNew = false;
	public static boolean check(String serviceCode){
		String[] service = serviceCode.split("\\.");
		String[] app = BuildConfig.VERSION_NAME.split("\\.");
		Log.e("check: ",serviceCode + ":"+app);
		Log.e("check: ",BuildConfig.VERSION_NAME + ":"+service.length);
		for ( String s : app ) {
			Log.e("check:String ", s);
		}
		for ( int i = 0; i < app.length; i++ ) {
			Log.e( "check: service",service[i] );
			Log.e( "check: app",app[i] );
		}
		if (Integer.parseInt(service[0])>Integer.parseInt(app[0])){
			isNew = true;
			return isNew;
		}
		if (Integer.parseInt(service[1])>Integer.parseInt(app[1])){
			isNew = true;
			return isNew;
		}
		if (Integer.parseInt(service[2])>Integer.parseInt(app[2])){
			isNew = true;
			return isNew;
		}
		return isNew;
	}
}
