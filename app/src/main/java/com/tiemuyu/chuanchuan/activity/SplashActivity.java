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
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.SPUtils;
import com.tiemuyu.chuanchuan.activity.util.VeData;


public class SplashActivity extends BaseActivityG implements AppInstallListener {

    private String TAG = "SplashActivity";
    private int GUIDE_VERSION_CODE = 1;
    private String GUIDE_VERSION_NAME = "is_first_" + R.string.versionCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
    }
    @Override
    protected void onStart() {
        super.onStart();
        OpenInstall.getInstall(this, this);
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

                } else {
                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                    mainIntent.putExtras(getIntent());
                    startActivity(mainIntent);

                }

                finish();
            }
        }, 2000);

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
     * @Description: 检测引导图版本，判断是否启动引导
     * @param @return
     * @return boolean
     * @throws
     */
    private boolean guideCheck(){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        int guideVer = sp.getInt(GUIDE_VERSION_NAME, 0);
        Log.e("guideCheck: ", "guideVer:" + guideVer+"  GUIDE_VERSION_CODE:" + GUIDE_VERSION_CODE);
        if(GUIDE_VERSION_CODE > 0 && GUIDE_VERSION_CODE > guideVer){
            return true;
        } else {
            return false;
        }
    }
}
