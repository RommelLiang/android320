package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.fm.openinstall.OpenInstall;
import com.fm.openinstall.listener.AppInstallListener;
import com.fm.openinstall.model.AppData;
import com.fm.openinstall.model.Error;

public class SplashActivity extends AppCompatActivity implements AppInstallListener {

    private String TAG = "SplashActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                mainIntent.putExtras(getIntent());
                startActivity(mainIntent);
                finish();
            }
        }, 2000);

    }

    @Override
    public void onInstallFinish(AppData appData, Error error) {
        Log.d(TAG, "onInstallFinish");
        if (error == null) {
            Intent intent = getIntent();
            intent.putExtra("openInstall", appData);
            setIntent(intent);
        } else {
            Log.d(TAG, "error : " + error.toString());
        }
    }
}
