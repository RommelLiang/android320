package com.tiemuyu.chuanchuan.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.GetPassKey;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.fragment.MineFragment;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.DataContoler;
import com.tiemuyu.chuanchuan.activity.util.JsonTools;
import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.view.PswInputView;

import org.xutils.http.RequestParams;

public class LingQianPayActivity extends BaseActivityG {

    private String id;
    private String password;
    private String TAG_GETPASSKEY = "TAG_GETPASSKEY";
    private String TAG_CHECK_PASSWORD = "TAG_CHECK_PASSWORD";
    private String TAG_PAYACTION = "TAG_PAYACTION";
    private String phone;
    private PswInputView pswInputView;
    private String passKey;
    private int repos = 222;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ling_qian_pay);
        id = getIntent().getStringExtra("orderid");
        pswInputView = (PswInputView) findViewById(R.id.psw_input);
        pswInputView.setInputCallBack(new PswInputView.InputCallBack() {
            @Override
            public void onInputFinish(String result) {
                password = result;
                getPassKey();
            }
        });
        findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getPassKey() {
        MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,
                TAG_GETPASSKEY, Constant.REQUEST_GET, new RequestParams(UrlManager
                .GET_PASSKEY()), this, "获取passkey", false));
    }

    private void paytheOrder() {
        Log.e("paytheOrder", "paytheOrder: "+UrlManager.Get_PAYACTION() + id + "&payType=5");
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(this,
                        TAG_PAYACTION,
                        Constant.REQUEST_GET,
                        new RequestParams(
                                UrlManager.Get_PAYACTION() + id + "&payType=5"),
                        this,
                        "调用支付",
                        false));

    }
    private void checkOldPassword(String v) {
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(this,
                        TAG_CHECK_PASSWORD,
                        Constant.REQUEST_POST,
                        ParamsTools.chexkPassword(
                                UrlManager.cashMoney(), "0",v,"2"),
                        this,
                        "校验支付密码",
                        false));
    }
    @Override
    public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        if (resultTag.equals(TAG_GETPASSKEY)) {
            GetPassKey key = JsonTools.fromJson(callBackMsg, GetPassKey.class);
            passKey = key.getData().getPassKey();
            if (key != null) {
                phone = MineFragment.user.getUsername();
                final String loginV = DataContoler.getLoginV(phone, password, passKey);
                checkOldPassword(loginV);
            }
        }
        if (resultTag.equals(TAG_CHECK_PASSWORD)) {
            Log.e("TAG_CHECK_PASSWORD", "successCallBack: "+callBackMsg);
            paytheOrder();
        }
        if (resultTag.equals(TAG_PAYACTION)) {
            Toast.makeText(this, "支付完成", Toast.LENGTH_SHORT).show();
            setResult(repos);
            finish();
        }
    }

    @Override
    public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
        super.failCallBack(arg0, resultTag, isShowDiolog);
        Log.e("failCallBack: " + resultTag, arg0.getLocalizedMessage());
        if (resultTag.equals(TAG_CHECK_PASSWORD)){
            Toast.makeText(this, "密码校验失败", Toast.LENGTH_SHORT).show();
            pswInputView.clearResult();
        }
    }

    @Override
    public void failShowCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.failShowCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        if (resultTag.equals(TAG_CHECK_PASSWORD)){
            Toast.makeText(this, "密码不正确", Toast.LENGTH_SHORT).show();
            pswInputView.clearResult();
        }
    }
}
