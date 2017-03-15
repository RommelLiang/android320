package com.tiemuyu.chuanchuan.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.util.AppManager;
import com.tiemuyu.chuanchuan.activity.util.StringUtil;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.util.Utility;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by CC2.0 on 2017/1/17.
 */

//public class TestActivity extends AppCompatActivity

public class TestActivity extends Activity   implements
        ThreadPoolTaskHttp.HttpCallBack,View.OnClickListener
{

//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.register_layout);
//    }
@ViewInject(R.id.reg_phone_new)
private EditText et_account;// 账号输入

    @ViewInject(R.id.reg_pswd_new)
    private EditText et_pass;// 密码输入

    @ViewInject(R.id.ivcode_new)
    private EditText et_yaoqingma;// 验证码输入

    @ViewInject(R.id.login1)
    private Button bt_next_one;// 下一步


    private String name;// 账号
    private String pass;// 密码
    private String yaoqingma;// 邀请码
    private String TAG_GETCODE = "TAG_GETCODE";
    private String token;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.register_layout);





        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        //  Constant.VERSION = Version.getAppVersionName(this);
        // _global = GlobalVariable.getInstance();

//        initProcess();


    }






    /**
     * 加载的流程
     */
    protected void initProcess() {
        initData();
        initUI();
        initListener();
    }

    protected void initData() {
        // TODO Auto-generated method stub

    }

    protected void initUI() {
        // TODO Auto-generated method stub
//        setCenterView(R.layout.register);
        this.setContentView(R.layout.register_layout);

        x.view().inject(this);
//        timeCount = new MyCountTimer(bt_getcode);
        // 实例化SelectPicPopupWindow



//        menuWindow = new SelectPicPopupWindow(RegistActivity.this,
//                itemsOnClick, "", "");


//
//
//
//
//
//        filter = new IntentFilter();
//        // 设置短信拦截参数
//        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
//        filter.setPriority(Integer.MAX_VALUE);
//        smsReceiver = new SmsReceiver();
//        registerReceiver(smsReceiver, filter);
//        SmsReceiver.setOnSmsListener(this);
    }





    protected void initListener() {
        // TODO Auto-generated method stub
        bt_next_one.setOnClickListener(this);
//        bt_next_two.setOnClickListener(this);
//        bt_getcode.setOnClickListener(this);
//        bt_finish.setOnClickListener(this);
//        iv.setOnClickListener(this);
//        ll_xy.setOnClickListener(this);
//        // bt_ll_wander.setOnClickListener(this);
//
//        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(CompoundButton arg0, boolean isCheck) {
//                // TODO Auto-generated method stub
//                if (isCheck)
//                    rl_yaoqing.setVisibility(View.VISIBLE);
//                else
//                    rl_yaoqing.setVisibility(View.GONE);
//            }
//        });
    }





    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.login1:
                // 下一步
                if (Utility.isFastDoubleClick()) // 连续点击
                    return;
//                getCodeMethod();
                break;

//            case R.id.bt_next_two:
//                if (Utility.isFastDoubleClick()) // 连续点击
//                    return;
//                get_code = et_code.getText().toString().trim();
//                if (StringUtil.isNull(get_code)) {
//                    ToastHelper.show(this, "请输入验证码");
//                    return;
//                }
//                if (Utility.isFastDoubleClick()) // 连续点击
//                    return;
//
//                getPasskeyMeth(TAG_GETPASSKEY);
//                break;
//            case R.id.bt_code_time:
//
//                // requestMethod(this, URL.GET_CODE(), ParamsTools.getCode(name,
//                // ""),
//                // Constant.REQUEST_POST, TAG_GETCODE, "", "获取验证码中", null,
//                // null, false);
//                MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,
//
//                        TAG_GETCODE, Constant.REQUEST_POST, ParamsTools.getCode(
//                        UrlManager.GET_CODE(), name, ""), this, "获取验证码中", false));
//                break;
//            case R.id.iv_touxiang:
//                // 显示窗口
//
//
//                //高伟豪   点击头像进入
//
//                exFatu();
//
//
//
//
//
//                break;
//            case R.id.bt_regist_finish:
//                // String img=usrImg;
//                if (Utility.isFastDoubleClick()) // 连续点击
//                    return;
//                nc = et_nicheng.getText().toString().trim();
//                if (user != null) {
//                    if (!nc.equals(user.getNickName())
//                            || !usrImg.equals(user.getUserImg())) {
//                        System.out.println("----user 设置");
//                        MyApplication.poolManager
//                                .addAsyncTask(new ThreadPoolTaskHttp(this,
//
//                                        TAG_REGIST_MODIFY, Constant.REQUEST_POST,
//                                        ParamsTools.modify(
//                                                UrlManager.MODIFY_ZILIAO(), usrImg,
//                                                nc), this, "设置中...", true));
//
//                    } else {
//                        System.out.println("----user 关闭");
//                        toRegistSuecc();
//
//                        closeActivity();
//                        AppManager.getAppManager().finishActivity(
//                                LoginActivity.class);
//                        // closeActivity();
//                        // setView(false, false, false, true);
//                    }
//                } else {
//                    System.out.println("----user is  null");
//                }
//
//                break;

//            case R.id.tv_title_right:
//                // 右上角的完成
//
//                String url = DBTools.getAppUrls(this).getRegisterSuccess();
//                if (!StringUtil.isNull(url)) {
//                    htmlBean.setTitle("");
//                    htmlBean.setUrl(url);
//                    ClassJumpTool.startToNextActivity(this, WebViewActivity.class,
//                            htmlBean);
//                }
//
//
//                //ClassJumpTool.startToNextActivity(this, RewardRegistActivity.class);
//                closeActivity();
//
//                AppManager.getAppManager().finishActivity(LoginActivity.class);
//                break;
//            case R.id.ll_xy:
//                // 协议
//                // String url=PreferenceUtils.getPrefString(RegistActivity.this,
//                // "AgreementRegister","");
//                String url1 = DBTools.getAppUrls(this).getAgreementRegister();
//                if (!StringUtil.isNull(url1)) {
////                    htmlBean.setTitle("");
////                    htmlBean.setUrl(url1);
////                    ClassJumpTool.startToNextActivity(this, WebViewActivity.class,
////                            htmlBean);
//                }
//
//                break;
        }
    }




    public void startCallBack(String resultTag,boolean isShowDiolog,String showTitle){}
    public void cancelCallBack(String resultTag){}

    public void reLoginCallBack(String resultTag,boolean isShowDiolog){}

    @Override
    public void failCallBack(Throwable arg0, String resultTag,
                             boolean isShowDiolog) {
        // TODO Auto-generated method stub
//        if (resultTag.equals(TAG_GETCODE)) {
//            System.out.println("---获取验证失败->" + arg0.toString());
//            ToastHelper.show(this, "获取验证码失败");
//        }

//        else if (resultTag.equals(TAG_REGIST)) {
//            ToastHelper.show(this, "注册失败");
//        } else if (resultTag.equals(TAG_REGIST_MODIFY)) {
//            ToastHelper.show(this, "修改资料失败");
//        }
    }

    @Override
    public void failShowCallBack(String resultTag, BaseBean baseBean,
                                 String callBackMsg, boolean isShowDiolog) {
        // TODO Auto-generated method stub

    }





    @Override
    public void successCallBack(String resultTag, BaseBean baseBean,
                                String callBackMsg, boolean isShowDiolog) {
        // TODO Auto-generated method stub
//        successParse(callBackMsg, resultTag);
    }



}
