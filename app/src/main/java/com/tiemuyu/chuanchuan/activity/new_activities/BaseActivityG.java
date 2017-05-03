package com.tiemuyu.chuanchuan.activity.new_activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.tiemuyu.chuanchuan.activity.MyApplication;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.SettingActivity;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.fragment.MineFragment;
import com.tiemuyu.chuanchuan.activity.proxy.LoadingProxy;
import com.tiemuyu.chuanchuan.activity.util.AppManager;
import com.tiemuyu.chuanchuan.activity.util.ClassJumpTool;
import com.tiemuyu.chuanchuan.activity.util.PreferenceUtils;
import com.tiemuyu.chuanchuan.activity.util.SPUtils;
import com.tiemuyu.chuanchuan.activity.util.SetNotificationBarColer;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;

import org.xutils.http.RequestParams;

/**
 * Created by CC2.0 on 2017/1/18.
 */

public class BaseActivityG extends FragmentActivity implements
		View.OnClickListener, ThreadPoolTaskHttp.HttpCallBack {

	public LoadingProxy mInstance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.base_layout);
		// 添加Activity到堆栈
		AppManager.getAppManager().addActivity(this);
		SPUtils.init(this);
		mInstance = LoadingProxy.getInstance(this);
	}
//
///**
// * 加载的流程
// */
//protected void initProcess(){
//        initData();
//        initAppAccess();
//        initUI();
//        initListener();
//        }
//
///**
// * 接收数据
// */
//protected void initData(){
//
//        }
//
///**
// * 实例化访问记录
// */
//protected void initAppAccess(){
//
//        }
//
///**
// * 初始化界面
// */
//protected void initUI(){}
//
//
//
///**
// * 初始化监听事件
// */
//protected void initListener() {
//        }

	@Override
	public void onClick(View v) {

	}


	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// 结束Activity&从堆栈中移除
		AppManager.getAppManager().finishActivity(this);
	}


//@Override
//public boolean onKeyDown(int keyCode, KeyEvent event) {
////        if (isWebview) {
////        onKeyBack(keyCode, event);
////        } else {
////        if (keyCode == KeyEvent.KEYCODE_BACK) {
////        LogHelper.e("-----执行了base finish");
////        finish();
////        overridePendingTransition(R.anim.out_from_left,
////        R.anim.out_from_right);
////        }
////        return false;
////        }
////
////        return true;
//    return true;
//        }

//protected void onKeyBack(int keyCode, KeyEvent event) {
//
//        }

	/**
	 * 关闭当前activity
	 */

	public void closeActivity() {
		AppManager.getAppManager().finishActivity(this);
		overridePendingTransition(R.anim.out_from_left, R.anim.out_from_right);
	}


	/**
	 * @param @param tag 设定文件
	 * @return void 返回类型
	 * @throws
	 * @Title: getPasskeyMethod
	 * @Description: TODO 获取passkey
	 */
	protected void getPasskeyMeth(String tag) {
		System.out.println("-----passkey请求的地址:" + UrlManager.GET_PASSKEY());
		MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,
				tag, Constant.REQUEST_GET, new RequestParams(UrlManager
				.GET_PASSKEY()), this, "获取passkey", false));

	}


	/**
	 * 数据返回成功，并且code=1时调用
	 */
	protected void successMethod(String msg, String resultTag) {

//        if (resultTag.equals(TAG_APPACCESS)) {
//        DataContoler.sendAppaccSucc(BaseActivity.this);
//
//        } else if (resultTag.equals(TAG_UOLOAD)) {
//        LogHelper.d("-----上传图片成功:" + msg);
//        // 上传图片成功
//        upload_imag_success(msg, resultTag);
//        } else if (resultTag.equals(TAG_APPEX)) {
//        // 清除错误数据
//        DBTools.removeAllExAccessList(BaseActivity.this);
//        MyApplication.exs.clear();
//        }

	}


	protected void reLogin(String resultTag) {
		//LogHelper.d("-----重新登录");
		System.out.println("baseactivity里面-----重新登录");
		if (DBTools.getUser() != null) {
			System.out.println("#####DBTools.getUser() != null");

			Intent intent = new Intent();
//            intent.setAction(Constant.LOGINMSG);
			intent.setAction(Constant.AULOGIN_ACTION);
			sendBroadcast(intent);
		} else {
			SettingActivity.removeCookie(BaseActivityG.this);
			DBTools.removeDb(BaseActivityG.this);
			MineFragment.user = null;
			PreferenceUtils.setPrefBoolean(this, Constant.CC_IFLOGIN, false);

			Intent intent = new Intent();
			intent.setAction(Constant.GBNAMERESP);
			sendBroadcast(intent);
			ClassJumpTool.startToNextActivity(this, LoginActivity.class);
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// addSession();
		String localClassName = getLocalClassName();
		if (!localClassName.equals("SplashActivity") && !localClassName.equals("GuideActivity") && !localClassName.equals("HuoDongActivity")) {
			SetNotificationBarColer.init(this, getResources().getColor(R.color.ColorLaunchBackground));
			SetNotificationBarColer.setColor();
		}
		if (PreferenceUtils.getPrefBoolean(this, Constant.CC_IFLOGIN, false)) {
			if (MineFragment.user == null) {
				System.out.println("----baseact-onResume  重置user信息");
				MineFragment.user = DBTools.getUser();
				UrlManager.BASEURL = PreferenceUtils.getPrefString(this, Constant.BASE_URL_NAME,
						UrlManager.DEFAULT_BASEURL);

				Intent intent = new Intent();
				intent.setAction(Constant.RESETDATA);
				sendBroadcast(intent);
			}
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

	}


	/*****************************activity母版 ********************************************/


	/**********************************
	 * 新的请求回调
	 ********************************************/

	@Override
	public void failCallBack(Throwable arg0, String resultTag,
	                         boolean isShowDiolog) {
		// TODO Auto-generated method stub
//        dissMissDialog(isShowDiolog);
//        if (resultTag.equals(TAG_UOLOAD)) {
//            ToastHelper.show(this, "上传失败");
//            dissMissDialog(true);
//        }
		mInstance.dismiss();
	}

	@Override
	public void startCallBack(String resultTag, final boolean isShowDiolog,
	                          final String showTitle) {
		// TODO Auto-generated method stub
		this.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
//                startDialog(isShowDiolog, showTitle);
			}
		});

	}

	@Override
	public void cancelCallBack(String resultTag) {
		// TODO Auto-generated method stub
	}

	@Override
	public void reLoginCallBack(String resultTag, boolean isShowDiolog) {
		// TODO Auto-generated method stub
		System.out.println("baseactivity里面---reLoginCallBack-");
		reLogin(resultTag);
	}

	@Override
	public void successCallBack(String resultTag, BaseBean baseBean,
	                            String callBackMsg, boolean isShowDiolog) {
		// TODO Auto-generated method stub
		successMethod(callBackMsg, resultTag);
	}

	@Override
	public void failShowCallBack(String resultTag, BaseBean baseBean,
	                             String callBackMsg, boolean isShowDiolog) {
		// TODO Auto-generated method stub
		// dissMissDialog(isShowDiolog);
		mInstance.dismiss();

	}

	/***************************************************************************/


}
