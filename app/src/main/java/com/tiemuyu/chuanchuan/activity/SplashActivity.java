package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.fm.openinstall.OpenInstall;
import com.fm.openinstall.listener.AppInstallListener;
import com.fm.openinstall.model.AppData;
import com.fm.openinstall.model.Error;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.User;
import com.tiemuyu.chuanchuan.activity.bean.VesionCodeBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.CheckVersion;
import com.tiemuyu.chuanchuan.activity.util.ConnectionUtil;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.SPUtils;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.util.ToastHelper;
import com.tiemuyu.chuanchuan.activity.util.UTagAndAlias;
import com.tiemuyu.chuanchuan.activity.util.VeData;

import org.xutils.http.RequestParams;


public class SplashActivity extends BaseActivityG implements AppInstallListener {

	private String TAG = "SplashActivity";
	private int GUIDE_VERSION_CODE = 1;
	private String GUIDE_VERSION_NAME = "is_first_" + R.string.versionCode;


	public static final String TAG_VERSION = "TAG_VERSION";
	private boolean isGetVesion = false;
	private boolean isJump = false;
	private String TAGU;
	private String alia;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//去掉Activity上面的状态栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_splash);
		UTagAndAlias.getAllTag();
		User user = DBTools.getUser();
		if (user != null){
			TAGU =user.getUserId()+"";
			alia =user.getUserId()+"";
			SPUtils.setHasLogin(true);
		} else {
			if (SPUtils.getHasLogin()) {
				TAGU = "-1";
			} else {
				TAGU = "-200";
			}
			alia = "0";
		}
		if (!ConnectionUtil.isConn(this)) {
			ToastHelper.show(this, "亲，网络断了哦，请检查网络设置");
			jump();
		} else {
			UTagAndAlias.addAlia(alia);
			UTagAndAlias.addTag(TAGU);
			MyApplication.poolManager.addAsyncTask(
					new ThreadPoolTaskHttp(this,
							TAG_VERSION,
							Constant.REQUEST_GET,
							new RequestParams(
									UrlManager.getVersion()),
							this,
							"获取版本信息",
							false));
			jump();
		}
	}

	@Override
	public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
		super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
		Log.e(TAG, "successCallBack: " + callBackMsg);
		if (resultTag.equals(TAG_VERSION)) {
			VesionCodeBean vesionCodeBean = GsonUtils.fromData(callBackMsg, VesionCodeBean.class);
			if (CheckVersion.check(vesionCodeBean.getData().getNumber())) {
				SPUtils.saveIsVersion(true);
				if (vesionCodeBean.getData().getIsForce() == 1) {
					SPUtils.saveIsForceUp(true);
				}
			} else {
				SPUtils.saveIsVersion(false);
			}
		}
	}



	private void jump() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Log.d(TAG, "startActivity");
				if (guideCheck()) {
					SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(SplashActivity.this);
					SharedPreferences.Editor edit = sp.edit();
					edit.putInt(GUIDE_VERSION_NAME, GUIDE_VERSION_CODE);
					edit.commit();
					String nowDateShort = VeData.getNowDateShort();
					SPUtils.saveAddTime(String.valueOf(nowDateShort));
					Log.e(TAG, "onInstallFinish" + nowDateShort);
					startActivity(new Intent(SplashActivity.this, GuideActivity.class));
					finish();
				} else {
					Intent mainIntent = new Intent(SplashActivity.this, HuoDongActivity.class);
					mainIntent.putExtras(getIntent());
					startActivity(mainIntent);
					finish();
				}
			}
		}, 2000);
	}

	@Override
	public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
		super.failCallBack(arg0, resultTag, isShowDiolog);
		Log.e(TAG, "failCallBack: " + resultTag + ":" + arg0.getMessage());
		arg0.printStackTrace();
	}

	@Override
	protected void onStart() {
		super.onStart();
		OpenInstall.getInstall(this, this);
	}

	@Override
	public void onInstallFinish(AppData appData, Error error) {

		if (error == null) {
			Intent intent = getIntent();
			intent.putExtra("openInstall", appData);
			setIntent(intent);
		} else {
			Log.d(TAG, "error : " + error.toString());
		}
	}

	/**
	 * @param @return
	 * @return boolean
	 * @throws
	 * @Description: 检测引导图版本，判断是否启动引导
	 */
	private boolean guideCheck() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		int guideVer = sp.getInt(GUIDE_VERSION_NAME, 0);
		Log.e("guideCheck: ", "guideVer:" + guideVer + "  GUIDE_VERSION_CODE:" + GUIDE_VERSION_CODE);
		if (GUIDE_VERSION_CODE > 0 && GUIDE_VERSION_CODE > guideVer) {
			return true;
		} else {
			return false;
		}
	}
}
