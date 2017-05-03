package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.GetPassKey;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.new_activities.LoginActivity;
import com.tiemuyu.chuanchuan.activity.util.AESHelper;
import com.tiemuyu.chuanchuan.activity.util.AppManager;
import com.tiemuyu.chuanchuan.activity.util.DataContoler;
import com.tiemuyu.chuanchuan.activity.util.JsonTools;
import com.tiemuyu.chuanchuan.activity.util.JudgmentLegal;
import com.tiemuyu.chuanchuan.activity.util.MyCountTimer;
import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
import com.tiemuyu.chuanchuan.activity.util.PreferenceUtils;
import com.tiemuyu.chuanchuan.activity.util.SetNotificationBarColer;
import com.tiemuyu.chuanchuan.activity.util.StringUtil;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.util.ToastHelper;
import com.tiemuyu.chuanchuan.activity.util.Utility;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;

import static com.tiemuyu.chuanchuan.activity.fragment.MineFragment.user;

/**
 * Created by Weihao Gao on 2017/2/5.
 */

public class ForgetPassword extends BaseActivityG {


    /** 布局一 */

    @ViewInject(R.id.forget_send)
    private TextView forget_send;// 发送短信按钮

    @ViewInject(R.id.fp_next)
    private Button fp_next;// 下一步


    @ViewInject(R.id.forget_lay)
    private RelativeLayout forget_lay;// 手机号码

    @ViewInject(R.id.fpcode_yanzheng)
    private EditText fpcode_yanzheng;// 验证码输入框


    @ViewInject(R.id.fp_lay4)
    private RelativeLayout fp_lay4;// 验证码输入框fp_name1



    @ViewInject(R.id.fp_name)
    private EditText fp_name;// 账号输入框


    /** 布局二 */

    @ViewInject(R.id.fp_finish)
    private Button fp_finish;// 完成按钮


    @ViewInject(R.id.fp_name1)
    private EditText fp_name1;// 请输入新的密码


    @ViewInject(R.id.forget_lay1)
    private RelativeLayout forget_lay1;// 密码输入框


    @ViewInject(R.id.fp_lay3)
    private RelativeLayout fp_lay3;// 充填密码框


    @ViewInject(R.id.fp_password2)
    private TextView fp_password2;// 重新填写密码





    private String name;// 账号

    private String TAG_ForgetCode = "TAG_ForgetCode";
    private String token;
    private  String get_code;
    private String TAG_RESETGETPASSKEY = "TAG_RESETGETPASSKEY";
    private String TAG_RESETPASSWORD = "TAG_RESETPASSWORD";
    private String TAG_YANZHENG = "TAG_YANZHENG";

    private MyCountTimer timeCount;


    private String passkey;
    private String v1;// 用户登录信息

    private String pass1;// 密码
    private String pass2;// 密码




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.setContentView(R.layout.register_layout);

        this.setContentView(R.layout.forgetpassword_layout);
        SetNotificationBarColer.init(this);
        SetNotificationBarColer.setTranslucent();

        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        //  Constant.VERSION = Version.getAppVersionName(this);
        // _global = GlobalVariable.getInstance();

        initProcess();

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "测试成功", Toast.LENGTH_LONG).show();
//            }
//        });
        findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



    /**
     * 加载的流程
     */
    protected void initProcess() {
        initUI();

        initListener();
    }




    protected void initUI() {

//        private TextView forget_send;// 账号输入

        fp_name1=(EditText) findViewById(R.id.fp_name1);

        forget_send = (TextView) findViewById(R.id.forget_send);
        fp_lay3=(RelativeLayout) findViewById(R.id.fp_lay3);  //第二个界面的时候显示
        fp_lay4=(RelativeLayout) findViewById(R.id.fp_lay4);  //
        forget_lay1=(RelativeLayout) findViewById(R.id.forget_lay1);  //  第二个页面
        forget_lay=(RelativeLayout) findViewById(R.id.forget_lay);  //第一个页面

        fp_password2=(EditText) findViewById(R.id.fp_password2);

        fpcode_yanzheng=(EditText)findViewById(R.id.fpcode_yanzheng);
        fp_name=(EditText)findViewById(R.id.fp_name);
        fp_next=(Button) findViewById(R.id.fp_next);
        fp_finish=(Button) findViewById(R.id.fp_finish);

        timeCount = new MyCountTimer(forget_send);


    }

    protected void initListener() {
        // TODO Auto-generated method stub
        forget_send.setOnClickListener(this);
        fp_next.setOnClickListener(this);
        fp_finish.setOnClickListener(this);


    }


    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.forget_send:
                // 发送验证短信
                if (Utility.isFastDoubleClick()) // 连续点击
                    return;
                Toast.makeText(this, "发验证码短信成功", Toast.LENGTH_LONG).show();
                getForgetCodeMethod();
                break;
            //点击下一步之后。。。做验证码check方法
            case R.id.fp_next:
                // 验证短信信息

                get_code = fpcode_yanzheng.getText().toString().trim();
                System.out.println("#####"+get_code);
//                Toast.makeText(this, get_code, Toast.LENGTH_LONG).show();

                if (StringUtil.isNull(get_code)) {
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_LONG).show();
                    return;
                }

                //验证码方法
                checkYanzhengCode();

                break;

            case R.id.fp_finish:
                // 点击完成之后的操作。

                pass1=fp_name1.getText().toString().trim();


                pass2=fp_password2.getText().toString().trim();
                if(!pass1.equals(pass2))
                {
                    ToastHelper.show(this,"两次输入密码不同,请检查重新输入");
                    break;
                }

                Toast.makeText(this, get_code, Toast.LENGTH_LONG).show();


                getPasskeyMeth(TAG_RESETGETPASSKEY);
                break;


        }
    }






    /**
     * @Title: getPasskeyMethod
     * @Description: TODO 检查验证码是否正确进入下个界面
     * @param @param tag 设定文件
     * @return void 返回类型
     * @throws
     */
    protected void     checkYanzhengCode() {

       System.out.println( "#####"+UrlManager.Reset_YanzhengCode()+get_code+"&token="+ token+ "&mobile="+name);

        MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,
                TAG_YANZHENG, Constant.REQUEST_GET, new RequestParams(UrlManager
                .Reset_YanzhengCode()+get_code+"&token="+ token+ "&mobile="+name), this, "验证码是否正确", false));

    }


    /**
     * @Title: getPasskeyMethod
     * @Description: TODO 获取passkey
     * @param @param tag 设定文件
     * @return void 返回类型
     * @throws
     */
    protected void getForgetCodeMethod() {

        name = fp_name.getText().toString().trim();
        if (StringUtil.isNull(name)) {
            Toast.makeText(this,"请输入账号",Toast.LENGTH_LONG).show();
            return;
        }
        if (!JudgmentLegal.isPhoneOrEmail(name)) {
            ToastHelper.show(this, "账号格式不对,请输入手机号或者邮箱");
            return;
        }

        System.out.println("######getForgetCodeMethod");
        MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,
                TAG_ForgetCode, Constant.REQUEST_GET, new RequestParams(UrlManager
                .GET_ForgetCode()+name), this, "获取忘记密码短信", false));
    }


    /**
     * @Title: codeResult
     * @Description: TODO 获取验证码结果操作
     * @param @param msg 设定文件
     * @return void 返回类型
     * @throws
     */
    private void codeResult(String msg) {
        System.out.println("#####亲,短信验证码已发送至\" + phone + \",请注意查收功->" + msg);
        //高伟豪 忘记密码的token写的有点问题。是{code msg data{code msg data}}  做了双重结构
        try{
            JSONObject jsonObject = new JSONObject(msg);
            String s_data = jsonObject.getString("Data");
            System.out.println("######1"+s_data);

            JSONObject jsonObject2 = new JSONObject(s_data);
            String s_data2 = jsonObject2.getString("Data");
            System.out.println("######2"+s_data2);

            JSONObject jsonObject3 = new JSONObject(s_data2);
            String s_data3 = jsonObject3.getString("Token");
            System.out.println("######3"+s_data3);


            token=s_data3;
            ToastHelper.show(this,"亲,短信验证码已发送至" + name + ",请注意查收");
            timeCount.start();

            System.out.println("######"+token);

        }
        catch (JSONException e) {
            ToastHelper.show(this,"发生错误请重试");
            e.printStackTrace();
        }

//// TODO: 2017/1/17 跳转             高伟豪。获取验证码成功点击下一步。
    }



    @Override
    public void failShowCallBack(String resultTag, BaseBean baseBean,
                                 String callBackMsg, boolean isShowDiolog) {
        // TODO Auto-generated method stub
        super.failShowCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        if (resultTag.equals(TAG_ForgetCode) || resultTag.equals(TAG_RESETGETPASSKEY)
                || resultTag.equals(TAG_YANZHENG)
                || resultTag.equals(TAG_RESETPASSWORD)) {
            ToastHelper.show(this, baseBean.getMsg());
        }
    }

//TAG_RESETPASSWORD   TAG_YANZHENG  TAG_RESETGETPASSKEY  TAG_ForgetCode


    @Override
    public void successCallBack(String resultTag, BaseBean baseBean,
                                String callBackMsg, boolean isShowDiolog) {
        // TODO Auto-generated method stub
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);

        successParse(callBackMsg, resultTag);
    }


    private void successParse(String msg, String resultTag) {


        if (resultTag.equals(TAG_ForgetCode)) {
            // 获取验证码

            System.out.println("#####"+msg);
            codeResult(msg);

        }
        else if (resultTag.equals( TAG_RESETGETPASSKEY))
        {
            //点击完成之后重置密码之后的返回
            System.out.println("#####点击完成之后->" + msg);
            //要根据重置回调的结果设置
            getPasskeyAndResetPassword(msg);
        }        else if (resultTag.equals(TAG_YANZHENG)) {
            // 重置登录密码成功
            setview();

            //这里验证码成功则转入下个页面验证码不成功则poop提醒
            System.out.println("#####TAG_YANZHENG"+msg);
        }

        //密码重置成功
        else if (resultTag.equals(TAG_RESETPASSWORD)) {
            // 重置登录密码成功  则删除旧的activity然后跑走。
            setLogin(msg);
            System.out.println("#####TAG_RESETPASSWORD"+msg);
        }



    }


    private void setLogin(String msg)
    {

        if (isOk(msg)) {
            ToastHelper.show(this,"重置密码成功,请记住你的新密码!");
//            isRegist = true;
            System.out.println("#####重置返回:" + msg);
//            btn_left.setVisibility(View.GONE);


            user = DataContoler.parseForgetPasswordAndSetUser(msg, pass1, "");
            // bean=resultBean.getData();
            if (user != null) {

                System.out.println("----注册后user返回信息:" + user.getEmail());
                PreferenceUtils.setPrefBoolean(this, Constant.CC_IFLOGIN, true);

                DBTools.loginDb(ForgetPassword.this, user);

                Intent intent = new Intent();
                intent.setAction(Constant.AULOGIN_ACTION);
                sendBroadcast(intent);

                AppManager.getAppManager().finishActivity(this);
                overridePendingTransition(R.anim.out_from_left, R.anim.out_from_right);
//                AppManager.getAppManager().finishActivity(ForgetPassword.class);
                AppManager.getAppManager().finishActivity(LoginActivity.class);

            }

        } else {
            BaseBean baseBean = JsonTools.fromJson(msg, BaseBean.class);
            Toast.makeText(this, baseBean.getMsg(), Toast.LENGTH_LONG).show();

//            ToastHelper.show(this, baseBean.getMsg());
            System.out.println("-----注册失败" + baseBean.getMsg());
        }





    }

    private boolean isOk(String msg) {
        BaseBean baseBean = JsonTools.fromJson(msg, BaseBean.class);
        if (baseBean.getCode() == Constant.REQUEST_OK) {
            return true;
        }
        return false;
    }



    private void setview () {
        fp_lay3.setVisibility(View.VISIBLE);
        fp_lay4.setVisibility(View.GONE);

        forget_lay1.setVisibility(View.VISIBLE);
        forget_lay.setVisibility(View.GONE);
        fp_finish.setVisibility(View.VISIBLE);
        fp_next.setVisibility(View.GONE);
        //隐藏手机号码和下一步



    }






        // 获取passkey并注册
    private void getPasskeyAndResetPassword(String msg) {
        GetPassKey key = JsonTools.fromJson(msg, GetPassKey.class);
        if (key != null && key.getCode() == Constant.REQUEST_OK) {
            passkey = key.getData().getPassKey();
            // System.out.println("----passkey--" + passkey);
            String x1 = name + "," + pass1;
            System.out.println("#####"+x1);
            try {
                v1 = AESHelper.getAesString(x1, passkey);
            } catch (InvalidAlgorithmParameterException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (!StringUtil.isNull(v1)) {

                System.out.println("#####getPasskeyAndRegist");

                MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(
                        this,
                        TAG_RESETPASSWORD, Constant.REQUEST_POST, ParamsTools.resetPass(
                        UrlManager.Reset_Password(),name, v1), this,
                        "正在重置密码...", true));
            }

        }
    }

}
