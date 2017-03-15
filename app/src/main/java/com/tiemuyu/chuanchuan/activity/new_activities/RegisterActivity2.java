package com.tiemuyu.chuanchuan.activity.new_activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;


import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.auth.AuthService;

import java.io.File;

import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.fragment.HomeFragment;
import com.tiemuyu.chuanchuan.activity.fragment.MineFragment;
import com.tiemuyu.chuanchuan.activity.new_activities.LoginActivity;
import com.tiemuyu.chuanchuan.activity.util.ClassJumpTool;
import com.tiemuyu.chuanchuan.activity.util.ConnectionUtil;
import com.tiemuyu.chuanchuan.activity.util.PreferenceUtils;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp.HttpCallBack;
import com.tiemuyu.chuanchuan.activity.util.ToastHelper;
import com.tiemuyu.chuanchuan.activity.view.PromptDialog;
import com.tiemuyu.chuanchuan.activity.view.URL;
import com.umeng.analytics.MobclickAgent;

import org.xutils.http.RequestParams;
import org.xutils.http.cookie.DbCookieStore;

/**
 * Created by Administrator on 2016/8/4.
 */
public class RegisterActivity2 extends Activity implements ThreadPoolTaskHttp.HttpCallBack {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏为透明
        setContentView(R.layout.activity_setting);
        initUI();

    }






    protected  void initUI()
    {
    }


    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }








    @Override
    public void successCallBack(String resultTag, BaseBean baseBean,
                                String callBackMsg, boolean isShowDiolog) {
        // TODO Auto-generated method stub
        ToastHelper.show(this, callBackMsg+"  来自tag  "+resultTag);

    }


    @Override
    public void failCallBack(Throwable arg0, String resultTag,boolean isShowDiolog)
    {}/**请求失败*/

    @Override
    public void startCallBack(String resultTag,boolean isShowDiolog,String showTitle)
    {} /** 开始*/

    @Override
    public void cancelCallBack(String resultTag){}
    /**取消*/

    @Override
    public void failShowCallBack(String resultTag, BaseBean baseBean,String callBackMsg,boolean isShowDiolog){}/** 请求成功，但code!=1 */

    @Override
    public void reLoginCallBack(String resultTag,boolean isShowDiolog){}/** 请求成功,但要重新登录 */



}
