package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.JudgmentLegal;
import com.tiemuyu.chuanchuan.activity.util.MyCountTimer;
import com.tiemuyu.chuanchuan.activity.util.StringUtil;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.util.ToastHelper;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;

public class ForgetPayPasswordActivity extends BaseActivityG {

    private String token;
    private TextView forget_send;// 发送短信按钮
    private Button fp_next;// 下一步
    private EditText fp_name;// 手机号码
    private EditText fpcode_yanzheng;// 验证码输入框
    private String phone;
    private String code;
    private MyCountTimer timeCount;
    private final String TAG_ForgetCode = "TAG_ForgetCode";
    private final String TAG_YANZHENG = "TAG_YANZHENG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pay_password);

        forget_send = (TextView) findViewById(R.id.forget_send);
        fpcode_yanzheng = (EditText) findViewById(R.id.fpcode_yanzheng);
        fp_name = (EditText) findViewById(R.id.fp_name);
        fp_next = (Button) findViewById(R.id.btnActivityForgetPasswordNext);
        timeCount = new MyCountTimer(forget_send);
        forget_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = String.valueOf(fp_name.getText());
                if (phone == null || phone.equals("")) {
                    Toast.makeText(ForgetPayPasswordActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (JudgmentLegal.checkCellPhone(phone)) {
                    Toast.makeText(ForgetPayPasswordActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(ForgetPayPasswordActivity.this,
                        TAG_ForgetCode, Constant.REQUEST_GET, new RequestParams(UrlManager
                        .GET_ForgetCode() + phone), ForgetPayPasswordActivity.this, "获取忘记密码短信", false));
            }
        });
        fp_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = String.valueOf(fpcode_yanzheng.getText());
                if (StringUtil.isNull(code)) {
                    Toast.makeText(ForgetPayPasswordActivity.this, "请输入验证码", Toast.LENGTH_LONG).show();
                    return;
                }
                //验证码方法
                checkYanzhengCode();
            }
        });
        findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        if (resultTag.equals(TAG_ForgetCode)) {
            // 获取验证码
            codeResult(callBackMsg);
        } else if (resultTag.equals(TAG_YANZHENG)) {
            Intent intent = new Intent(ForgetPayPasswordActivity.this,SetPayPasswordActivity.class);
            intent.putExtra("code",0);
            startActivity(intent);
        }
    }

    @Override
    public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
        super.failCallBack(arg0, resultTag, isShowDiolog);
    }

    private void codeResult(String msg) {
        System.out.println("#####亲,短信验证码已发送至\" + phone + \",请注意查收功->" + msg);
        //高伟豪 忘记密码的token写的有点问题。是{code msg data{code msg data}}  做了双重结构
        try {
            JSONObject jsonObject = new JSONObject(msg);
            String s_data = jsonObject.getString("Data");
            System.out.println("######1" + s_data);

            JSONObject jsonObject2 = new JSONObject(s_data);
            String s_data2 = jsonObject2.getString("Data");
            System.out.println("######2" + s_data2);

            JSONObject jsonObject3 = new JSONObject(s_data2);
            String s_data3 = jsonObject3.getString("Token");
            System.out.println("######3" + s_data3);


            token = s_data3;
            timeCount.start();

            System.out.println("######" + token);

        } catch (JSONException e) {
            ToastHelper.show(this, "发生错误请重试");
            e.printStackTrace();
        }

    }

    protected void checkYanzhengCode() {

        MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,
                TAG_YANZHENG, Constant.REQUEST_GET, new RequestParams(UrlManager
                .Reset_YanzhengCode() + code + "&token=" + token + "&mobile=" + phone), this, "验证码是否正确", false));

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }
}
