package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
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

public class SetPayPasswordActivity extends BaseActivityG {
    private TextView tv_one, tv_two, tv_head;
    private PswInputView pswInputView;
    private String password;
    private String oldPassword;
    private Intent intent;
    private int code;
    private final String TAG_ADDPASSWORD = "TAG_ADDPASSWORD";
    private final String TAG_GETPASSKEY = "TAG_GETPASSKEY";
    private final String TAG_CHECK_PASSWORD = "TAG_CHECK_PASSWORD";
    private String passKey;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pay_password);
        intent = getIntent();
        code = intent.getIntExtra("code", 0);
        pswInputView = (PswInputView) findViewById(R.id.psw_input);
        tv_one = (TextView) findViewById(R.id.tv_one);
        tv_two = (TextView) findViewById(R.id.tv_two);
        tv_head = (TextView) findViewById(R.id.tv_head);
        findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (code == 0) {
            //设置密码
            setPassword();
        } else {
            //修改密码
            tv_head.setText("修改支付密码");
            tv_one.setText("请输入旧的支付密码");
            pswInputView.setInputCallBack(new PswInputView.InputCallBack() {
                @Override
                public void onInputFinish(String result) {
                    oldPassword = result;
                    getPassKey();
                }
            });
        }

    }

    private void setPassword() {
        pswInputView.setInputCallBack(new PswInputView.InputCallBack() {
            @Override
            public void onInputFinish(String result) {
                if (password == null) {
                    password = result;
                    pswInputView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pswInputView.clearResult();
                            tv_head.setText("确认支付密码");
                            tv_one.setText("确认支付密码");
                            tv_two.setText("为了保障您的资金安全，需要进行验证");
                            Toast.makeText(SetPayPasswordActivity.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                        }
                    }, 1000);
                } else {
                    if (password.equals(result)) {
                        //发送网络请求
                        getPassKey();
                    } else {
                        Toast.makeText(SetPayPasswordActivity.this, "两次输入不一致", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    private void addPassword(String v) {
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(SetPayPasswordActivity.this,
                        TAG_ADDPASSWORD,
                        Constant.REQUEST_POST,
                        ParamsTools.addPassword(
                                UrlManager.addPayPassword(), v),
                        SetPayPasswordActivity.this, "添加支付密码",
                        false));
    }

    private void getPassKey() {
        MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,
                TAG_GETPASSKEY, Constant.REQUEST_GET, new RequestParams(UrlManager
                .GET_PASSKEY()), this, "获取passkey", false));
    }

    @Override
    public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        Log.e("successCallBack: " +resultTag, callBackMsg);
        if (resultTag.equals(TAG_GETPASSKEY)) {
            GetPassKey key = JsonTools.fromJson(callBackMsg, GetPassKey.class);
            passKey = key.getData().getPassKey();
            if (key != null) {
                //addPassword();
                phone = MineFragment.user.getUsername();
                final String loginV = DataContoler.getLoginV(phone, password, passKey);
                if (code == 0) {
                    addPassword(loginV);
                } else {
                    phone = MineFragment.user.getUsername();
                    checkOldPassword(oldPassword);
                }
            }
        }
        if (resultTag.equals(TAG_ADDPASSWORD)) {
            Log.e("TAG_ADDPASSWORD: ", callBackMsg);
            Toast.makeText(this, "设置成功", Toast.LENGTH_SHORT).show();
            finish();
        }
        if (resultTag.equals(TAG_CHECK_PASSWORD)) {
            pswInputView.clearResult();
            tv_head.setText("请设置支付密码");
            tv_one.setText("请设置支付密码");
            tv_two.setText("支付密码由6位数字组成，用于提取零钱操作的安\n全确认，建议不要与银行卡取款密码相同");
            pswInputView.clearResult();
            pswInputView.setInputCallBack(new PswInputView.InputCallBack() {
                @Override
                public void onInputFinish(String result) {
                    if (password == null) {
                        password = result;
                        pswInputView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                pswInputView.clearResult();
                                tv_head.setText("确认支付密码");
                                tv_one.setText("确认支付密码");
                                tv_two.setText("为了保障您的资金安全，需要进行验证");
                                Toast.makeText(SetPayPasswordActivity.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                            }
                        }, 1000);
                    } else {
                        if (password.equals(result)) {
                            //发送网络请求,更新密码
                            String loginV = DataContoler.getLoginV(phone, password, passKey);
                            addPassword(loginV);
                        } else {
                            Toast.makeText(SetPayPasswordActivity.this, "两次输入不一致", Toast.LENGTH_SHORT).show();
                            pswInputView.clearResult();
                        }
                    }
                }
            });
        }
    }

    @Override
    public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
        super.failCallBack(arg0, resultTag, isShowDiolog);
        Log.e("failCallBack: " +resultTag, arg0.getLocalizedMessage());
        if (resultTag.equals(TAG_CHECK_PASSWORD)){
            Toast.makeText(this, "密码校验失败", Toast.LENGTH_SHORT).show();
            pswInputView.clearResult();
        }
    }

    @Override
    public void failShowCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.failShowCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        Log.e("failShowCallBack: " +resultTag, callBackMsg);
        if (resultTag.equals(TAG_CHECK_PASSWORD)){
            Toast.makeText(this, "密码不正确", Toast.LENGTH_SHORT).show();
            pswInputView.clearResult();
        }
    }

    private void checkOldPassword(String result) {
        String v = DataContoler.getLoginV(phone, result, passKey);
        Log.e("SetPassword", "checkOldPassword: "+result);
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(SetPayPasswordActivity.this,
                        TAG_CHECK_PASSWORD,
                        Constant.REQUEST_POST,
                        ParamsTools.chexkPassword(
                                UrlManager.cashMoney(), "0",v,"2"),
                        SetPayPasswordActivity.this,
                        "校验支付密码",
                        false));
    }
}
