package com.tiemuyu.chuanchuan.activity.util;

import java.io.Serializable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.alipay.android.phone.mrpc.core.ac;
//import com.tiemuyu.chuanchuan.activity.ListWebViewActivity;
import com.tiemuyu.chuanchuan.activity.new_activities.LoginActivity;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.bean.DataPacket;
import com.tiemuyu.chuanchuan.activity.bean.HtmlBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.db.DBTools;

//跳转工具类
public class ClassJumpTool {
	/**
	 * Activity间跳转的参数名
	 */
	public static final String DATA_PACKET_NAME = "Intent_Data_Packet";
	public static final String DATA_PACKET_BACK = "DATA_PACKET_BACK";

	/**
	 * 传参数
	 */
	public static final String DATA_PACKET_SIZE = "DATA_PACKET_SIZE";
	public static final String DATA_OBJECT_SIZE = "DATA_OBJECT_SIZE";
	
	public static void nextActivity(Activity activity,Class<?> nextActivity){
		Intent intent = new Intent(activity, nextActivity);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.in_from_right,
				R.anim.out_from_left);
	}

	/**
	 * @param activity 当前activity
	 * @param nextActivity 下个activity
	 * @param dataPacket 传递的数据
	 */
	public static void startToNextActivity(Activity activity,Class<?> nextActivity, DataPacket dataPacket) {
		Intent intent = new Intent(activity, nextActivity);
		intent.putExtra(DATA_PACKET_NAME, dataPacket);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.in_from_right,
				R.anim.out_from_left);
	}
	
	/**
	 *  activity 当前activity
	 * @param nextActivity 下个activity
	 * @param dataPacket 传递的数据
	 */
	public static void startToNextActivity(Context context,Class<?> nextActivity, DataPacket dataPacket) {
		Intent intent = new Intent(context, nextActivity);
		intent.putExtra(DATA_PACKET_NAME, dataPacket);
		context.startActivity(intent);
		((Activity) context).overridePendingTransition(R.anim.in_from_right,
				R.anim.out_from_left);
	}
	
	/**
	 * 跳转到另外一个activity
	 * 
	 * @param nextActivity
	 *            下个Activity
	 * @param objects
	 *            单项数据集合
	 */
	public static void startToNextActivity(Activity activity,Class<?> nextActivity,
			Serializable... objects) {
		Intent intent = new Intent(activity, nextActivity);
		if (objects != null && objects.length > 0) {
			intent.putExtra(DATA_OBJECT_SIZE, objects.length);
			for (int i = 0; i < objects.length; i++) {
				String tag = DATA_PACKET_NAME + i;
				intent.putExtra(tag, objects[i]);
			}
		}
activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
	}

	/**
	 * 跳转到另外一个activity
	 * 
	 * @param nextActivity
	 *            下个Activity
	 * @param requestCode
	 *            回调请求码
	 * @param objects
	 *            单项数据集合
	 */
	public static void startToNextActivityForResult(Activity activity,Class<?> nextActivity,
			int requestCode, Serializable... objects) {
		Intent intent = new Intent(activity, nextActivity);
		if (objects != null && objects.length > 0) {
			intent.putExtra(DATA_OBJECT_SIZE, objects.length);
			for (int i = 0; i < objects.length; i++) {
				String tag = DATA_PACKET_NAME + i;
				intent.putExtra(tag, objects[i]);
			}
		}
		activity.startActivityForResult(intent, requestCode);
		activity.overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
	}

	/**
	 * @Title: startToNextActivityForResult
	 * @Description: TODO 跳到下一个activity，不传数据
	 * @param @param nextActivity
	 * @param @param requestCode 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void startToNextActivityForResult(Activity activity,Class<?> nextActivity,
			int requestCode) {
		Intent intent = new Intent(activity, nextActivity);

		activity.startActivityForResult(intent, requestCode);
		activity.overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
	}

	/**
	 * 跳转到另外一个activity
	 * 
	 * @param nextActivity
	 *            下一个Activity
	 * @param dataPacket
	 *            数据包
	 */

	public static void startToNextActivity(Activity activity,Class<?> nextActivity,
			DataPacket... dataPacket) {
		Intent intent = new Intent(activity, nextActivity);
		if (dataPacket != null && dataPacket.length > 0) {
			intent.putExtra(DATA_PACKET_SIZE, dataPacket.length);
			for (int i = 0; i < dataPacket.length; i++) {
				String tag = DATA_PACKET_NAME + i;
				intent.putExtra(tag, dataPacket[i]);
			}
		}
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
	}


	public static void startToNextActivity(Activity activity,Class<?> nextActivity, String data) {
		Intent intent = new Intent(activity, nextActivity);
		intent.putExtra(DATA_PACKET_NAME, data);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
	}

	/**
	 * 跳转到另外一个activity
	 * 
	 * @param nextActivity
	 *            下一个Activity
	 * @param requestCode
	 *            回调请求码
	 * @param dataPacket
	 *            数据包
	 */

	public static void startToNextActivityForResult(Activity activity,Class<?> nextActivity,
			int requestCode, DataPacket... dataPacket) {
		Intent intent = new Intent(activity, nextActivity);
		if (dataPacket != null && dataPacket.length > 0) {
			intent.putExtra(DATA_PACKET_SIZE, dataPacket.length);
			for (int i = 0; i < dataPacket.length; i++) {
				String tag = DATA_PACKET_NAME + i;
				intent.putExtra(tag, dataPacket[i]);
			}
		}
		activity.startActivityForResult(intent, requestCode);
		activity.overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
	}

	public static void startToNextActivityForResult(Activity activity,Class<?> nextActivity,
			int requestCode, DataPacket dataPacket) {
		Intent intent = new Intent(activity, nextActivity);
		if (dataPacket != null)
			intent.putExtra(DATA_PACKET_NAME, dataPacket);
		activity.startActivityForResult(intent, requestCode);
		activity.overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
	}

	/**
	 * 跳转到下一个activity
	 * 
	 * @param nextActivity
	 */
	public static void startToNextActivity(Activity activity,Class<?> nextActivity) {
		startToNextActivity(activity,nextActivity, new DataPacket[] {});
	}

	/**
	 * Intent intent = new Intent(LoginActivity.this, MainActivity.class);
	 * intent.putExtra("loginBean", loginBean); setResult(10, intent);
	 * AppManager.getAppManager().finishActivity( LoginActivity.this);
	 * */

	/**
	 * 返回上一个activity
	 * 
	 *  nextActivity
	 */
	public static void startToBackActivity(Activity activity,Class<?> backActivity,
			DataPacket dataPacket, int backCode) {
		Intent intent = new Intent(activity, backActivity);
		if (dataPacket != null) {
			intent.putExtra(DATA_PACKET_BACK, dataPacket);
		}
		System.out.println("##### 在starttoback里面");
		activity.setResult(backCode, intent);
		AppManager.getAppManager().finishActivity(activity);
		activity.overridePendingTransition(R.anim.out_from_left, R.anim.out_from_right);
	}

	public static void startToBackActivitys(Activity activity,Class<?> backActivity, String ss,
			int backCode) {
		Intent intent = new Intent(activity, backActivity);
		if (ss != null) {
			intent.putExtra(DATA_PACKET_BACK, ss);
		}

		activity.setResult(backCode, intent);
		AppManager.getAppManager().finishActivity(activity);
		activity.overridePendingTransition(R.anim.out_from_left, R.anim.out_from_right);
	}
	
	//fragment跳转到act--(侧滑栏的跳转)
	public static void jumpToAct(Activity activity,String url,boolean isTologin){
		
//		if(isTologin){
//			if (PreferenceUtils.getPrefBoolean(activity, Constant.CC_IFLOGIN, false)) {
//				//已登录
//				if (!StringUtil.isNull(url)) {
//					HtmlBean htmlBean=new HtmlBean();
//					htmlBean.setTitle("");
//					htmlBean.setUrl(url);
//					startToNextActivity(activity,ListWebViewActivity.class, htmlBean);
//				}
//
//			}else{
//				//未登录
//				Intent intt = new Intent();
//				intt.setClass(activity, LoginActivity.class);
//				activity.startActivityForResult(intt, Constant.REQUEST_RESULTCODE1);
//				activity.overridePendingTransition(R.anim.in_from_right,
//						R.anim.out_from_left);
//			}
//		}else{
//			if (!StringUtil.isNull(url)) {
//				HtmlBean htmlBean=new HtmlBean();
//				htmlBean.setTitle("");
//				htmlBean.setUrl(url);
//				startToNextActivity(activity,ListWebViewActivity.class, htmlBean);
//			}
//		}
		
	}
	
	
	public static void finishCurrAct(Activity activity){
		AppManager.getAppManager().finishActivity(activity);
		activity.overridePendingTransition(R.anim.out_from_left, R.anim.out_from_right);
	}
}
