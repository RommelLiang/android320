package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tiemuyu.chuanchuan.activity.bean.AccountMannger;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;

import org.xutils.http.RequestParams;

public class PasswordManngerActivity extends BaseActivityG {

    private TextView tv_one,tv_two;
    private RelativeLayout rl_one,rl_two;
    private View view;
    private final String TAG_GETMESSAGE = "TAG_GETMESSAGE";
    private AccountMannger accountMannger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_mangger);
        getMessage();
        tv_one = (TextView) findViewById(R.id.tv_one);
        tv_two = (TextView) findViewById(R.id.tv_two);
        rl_one = (RelativeLayout) findViewById(R.id.rl_one);
        rl_two = (RelativeLayout) findViewById(R.id.rl_two);
        view = findViewById(R.id.view);
        findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void getMessage(){
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(this,
                        TAG_GETMESSAGE,
                        Constant.REQUEST_GET,
                        new RequestParams(
                                UrlManager.accountManage()),
                        this,
                        "获取账户信息",
                        false));
    }
    @Override
    public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        if (resultTag.equals(TAG_GETMESSAGE)) {
            Log.e(TAG_GETMESSAGE, "successCallBack: "+callBackMsg);
            accountMannger = GsonUtils.fromData(callBackMsg, AccountMannger.class);
            if(accountMannger.getData().getPayPwd() == null || accountMannger.getData().getPayPwd().equals("")){
                rl_two.setVisibility(View.GONE);
                view.setVisibility(View.GONE);
                rl_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PasswordManngerActivity.this,SetPayPasswordActivity.class);
                        intent.putExtra("code",0);
                        startActivity(intent);
                    }
                });
            } else {
                tv_one.setText("修改支付密码");
                rl_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PasswordManngerActivity.this,SetPayPasswordActivity.class);
                        intent.putExtra("code",1);
                        startActivity(intent);
                    }
                });

                rl_two.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PasswordManngerActivity.this,ForgetPayPasswordActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
    public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
        super.failCallBack(arg0, resultTag, isShowDiolog);
        Log.e("failCallBack: " , arg0.getMessage());
    }
}
