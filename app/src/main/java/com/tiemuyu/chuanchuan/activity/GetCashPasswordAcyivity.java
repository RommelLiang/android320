package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tiemuyu.chuanchuan.activity.bean.Base;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.fragment.MineFragment;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.DataContoler;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.view.PswInputView;

public class GetCashPasswordAcyivity extends BaseActivityG {

    private Intent intent;
    private String passKey;
    private String count;
    private final String TAG_GET_CASH = "TAG_GET_CASH";
    private PswInputView pswInputView;
    private boolean isShow = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_cash_password_acyivity);
        intent = getIntent();
        passKey = intent.getStringExtra("passKey");
        count = intent.getStringExtra("count");
        pswInputView = (PswInputView) findViewById(R.id.psw_input);
        final String phone = MineFragment.user.getUsername();
        pswInputView.setInputCallBack(new PswInputView.InputCallBack() {
            @Override
            public void onInputFinish(String result) {
                getCash(result,phone);
                pswInputView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (isShow) {
                            pswInputView.clearResult();
                            Toast.makeText(GetCashPasswordAcyivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                },2500);
            }
        });
        findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void getCash(String password,String phone) {
        Log.e("checkOldPassword: ",password + ":" +phone + ":" + count + ":" +passKey);
        String v = DataContoler.getLoginV(phone, password, passKey);
        Log.e("checkOldPassword: ",v );
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(GetCashPasswordAcyivity.this,
                        TAG_GET_CASH,
                        Constant.REQUEST_POST,
                        ParamsTools.chexkPassword(
                                UrlManager.cashMoney(),count,v,"1"),
                        GetCashPasswordAcyivity.this,
                        "提现",
                        false));
    }
    @Override
    public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        Log.e(resultTag, "successCallBack: "+callBackMsg);
        Base base = GsonUtils.fromData(callBackMsg, Base.class);
        if (resultTag.equals(TAG_GET_CASH)) {
            if (base.getCode() == 1) {
                Toast.makeText(this, "提现成功", Toast.LENGTH_SHORT).show();
                isShow = false;
                finish();
            } else {

            }
        }
    }

    @Override
    public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
        super.failCallBack(arg0, resultTag, isShowDiolog);
        Log.e(resultTag, "failCallBack: " + arg0.getLocalizedMessage());
    }
}
