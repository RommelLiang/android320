package com.tiemuyu.chuanchuan.activity.new_activities;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tiemuyu.chuanchuan.activity.ForgetPassword;
import com.tiemuyu.chuanchuan.activity.MainActivity;
import com.tiemuyu.chuanchuan.activity.MyApplication;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.GetPassKey;
import com.tiemuyu.chuanchuan.activity.bean.User;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.fragment.MineFragment;
import com.tiemuyu.chuanchuan.activity.util.AppManager;
import com.tiemuyu.chuanchuan.activity.util.CheckPhoneLength;
import com.tiemuyu.chuanchuan.activity.util.ClassJumpTool;
import com.tiemuyu.chuanchuan.activity.util.DataContoler;
import com.tiemuyu.chuanchuan.activity.util.DownloadService.DownloadStateListener;
import com.tiemuyu.chuanchuan.activity.util.GetCustomer;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.JsonTools;
import com.tiemuyu.chuanchuan.activity.util.JudgmentLegal;
import com.tiemuyu.chuanchuan.activity.util.LogHelper;
import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
import com.tiemuyu.chuanchuan.activity.util.PreferenceUtils;
import com.tiemuyu.chuanchuan.activity.util.SPUtils;
import com.tiemuyu.chuanchuan.activity.util.SetNotificationBarColer;
import com.tiemuyu.chuanchuan.activity.util.StringUtil;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp.HttpCallBack;
import com.tiemuyu.chuanchuan.activity.util.ToastHelper;
import com.tiemuyu.chuanchuan.activity.util.UTagAndAlias;
import com.tiemuyu.chuanchuan.activity.util.Utility;
import com.tiemuyu.chuanchuan.activity.view.ClearEditText;
import com.umeng.analytics.MobclickAgent;

import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

//import cn.sharesdk.framework.Platform;
//import cn.sharesdk.framework.PlatformActionListener;
//import cn.sharesdk.framework.ShareSDK;
//import cn.sharesdk.sina.weibo.SinaWeibo;
//import cn.sharesdk.tencent.qq.QQ;
//import cn.sharesdk.wechat.friends.Wechat;
//import com.baidu.location.LocationClient;
//import com.mob.tools.utils.UIHandler;
//import com.tiemuyu.chuanchuan.activity.fragment.MenuLeftFragment;
//import com.tiemuyu.chuanchuan.activity.util.DialogHelper;
//import com.tiemuyu.chuanchuan.activity.util.ClassJumpTool;
//import com.tiemuyu.chuanchuan.activity.util.SelectPicPopupWindow;
//import com.tiemuyu.chuanchuan.activity.util.UrlSelectPopupWindow;
//import com.tiemuyu.chuanchuan.activity.view.LoadingDialog;

/**
 * @项目名： android1128
 * @包名： com.tiemuyu.chuanchuan.activity
 * @类描述：
 * @创建人： hr
 * @创建时间： 2016/12/15
 * @version：
 */
public class LoginActivity extends BaseActivityG implements
        DownloadStateListener {


    //高伟豪 继承了HTTPcallback方法。任何的http请求都会调用类中的接口


    @ViewInject(R.id.login_name)
    private ClearEditText et_name;// 账号输入框

    @ViewInject(R.id.login_password)
    private EditText et_pass;// 密码输入框

    @ViewInject(R.id.login1)
    private Button bt_login;// 登录


    @ViewInject(R.id.return_button)
    private ImageView bt_back;// 回退


    @ViewInject(R.id.register_new)
    private TextView bt_zhuce;  //注册按钮


    @ViewInject(R.id.forget_password1)
    private TextView forget_password1;  //忘记密码按钮


    public static HttpCallBack mainHttpCallBack;


    private ArrayList<String> path = new ArrayList<String>();// 图片缓存地址


    private String name;
    private String pass;
    private String passkey;
    private String username;// 用户名
    private String login_v;// 用户登录信息

    private String c_type;
    private int n_count = 0;
    private Handler handler;
    private String coming_type = "0";// 1 设置界面进来的


    /**
     * 请求的TAG
     */
    private String TAG_LOGIN = "TAG_LOGIN";
    //    private String TAG_UOLOAD = "TAG_UOLOAD";
    private String TAG_GET_PASSKEY = "TAG_GET_PASSKEY";
    private String TAG_THIRD_LOGIN = "TAG_THIRD_LOGIN";
    private String TAG_GETUSERNAME = "TAG_GETUSERNAME";
    private String GET_AWARD_INFO = "GET_AWARD_INFO";


//    protected LoadingDialog dialog;
//    public GlobalVariable _global;

    protected boolean isWebview = false;
    protected boolean isShare = false;// 是否分享，默认false
    protected boolean isCollection = true;// 是否开启统计 true，开启;false 关闭
    protected boolean isSession = true;// 是否开启session统计

    private String TAG_APPACCESS = "TAG_APPACCESS";
    private String TAG_UOLOAD = "TAG_UOLOAD";
    private String TAG_APPEX = "TAG_APPEX";
    private String funFlag;// 类型


    private static final int MSG_USERID_FOUND = 1;
    private static final int MSG_LOGIN = 2;
    private static final int MSG_AUTH_CANCEL = 3;
    private static final int MSG_AUTH_ERROR = 4;
    private static final int MSG_AUTH_COMPLETE = 5;

    private boolean isTh = false;
    private IntentFilter myIntentFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        SetNotificationBarColer.init(this);
        SetNotificationBarColer.setTranslucent();
        bt_login = (Button) findViewById(R.id.login1);

        bt_back = (ImageView) findViewById(R.id.return_button);

        et_name = (ClearEditText) findViewById(R.id.login_name);// 账号输入框


        et_pass = (ClearEditText) findViewById(R.id.login_password);// 账号输入框


        bt_zhuce = (TextView) findViewById(R.id.register_new);

        forget_password1 = (TextView) findViewById(R.id.forget_password1);

        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        //  Constant.VERSION = Version.getAppVersionName(this);
        // _global = GlobalVariable.getInstance();

        initProcess();


    }


    /**
     * 加载的流程
     */
    protected void initProcess() {
        initData();
        initAppAccess();
        initUI();
        initListener();
    }


    /**
     * 实例化访问记录
     */
    protected void initAppAccess() {

    }

    protected void initListener() {
        // TODO Auto-generated method stub
        bt_login.setOnClickListener(this);
        bt_back.setOnClickListener(this);
        //todo   添加 注册按钮和注册跳转。

        bt_zhuce.setOnClickListener(this);
        forget_password1.setOnClickListener(this);


//        tv_forget.setOnClickListener(this);


//        tv_regist.setOnClickListener(this);
//        btn_left.setOnClickListener(this);
//        bt_wx_login.setOnClickListener(this);
//        bt_qq_login.setOnClickListener(this);
//        bt_xl_login.setOnClickListener(this);
    }

    protected void initData() {
//        // TODO Auto-generated method stub
//        c_type = getIntent().getStringExtra(ClassJumpTool.DATA_PACKET_NAME);
//        if (c_type != null)
//            coming_type = c_type;
//
//        isCollection = false;
    }

    protected void initUI() {
        // TODO Auto-generated method stub
//        ShareSDK.initSDK(LoginActivity.this);
//        setCenterView(R.layout.login);
//        x.view().inject(LoginActivity.this);
//
//        mLocationClient = WelcomeActivity.mLocationClient;
//
//        registerBoradcastReceiver();
//
//        tv_title.setText("登录");
//
//        v_right.setVisibility(View.VISIBLE);

//        v_right.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                n_count = n_count + 1;
//                if (n_count == 8) {
//                    urlSelectPopupWindow.showAtLocation(rl_all, Gravity.BOTTOM
//                            | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
//                    n_count = 0;
//                }
//
//            }
//        });

        // 实例化SelectPicPopupWindow
//        menuWindow = new SelectPicPopupWindow(LoginActivity.this, itemsOnClick,
//                "通过手机号重置", "通过关联邮箱重置");
//        urlSelectPopupWindow = new UrlSelectPopupWindow(LoginActivity.this,
//                urlitemsOnClick);
        //	 System.out.println("------sid---:"+sid);

        mainHttpCallBack = this;


        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        final User loginBean = (User) msg.obj;
                        //  LogHelper.d("---登录的user：" + loginBean.toString());

                        MineFragment.user = loginBean;
                        System.out.println("#####Minefragment.user：" + MineFragment.user.toString());
                        //MenuLeftFragment.isLogin = true;

                        PreferenceUtils.setPrefBoolean(LoginActivity.this, Constant.CC_IFLOGIN, true);
                        // 更新数据库
                        DBTools.loginDb(LoginActivity.this, loginBean);


                        // 发送广播  全局重新加载
                        Intent intent = new Intent();
                        intent.setAction(Constant.LOGINMSG);
                        sendBroadcast(intent);
                        System.out.println("#####进入第二个位置");


                        // 关闭登录页面
                        ClassJumpTool.startToBackActivity(LoginActivity.this,
                                MainActivity.class, MineFragment.user, 10);

                        break;

                }
            }
        };


    }


    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.login1:
                exLogin();

                break;
            case R.id.return_button:
                if (Utility.isFastDoubleClick()) // 连续点击
                    return;

                //starttobackactivity 的方法是 自动终结当前activity 会退到第二个acitvity 传递一个data 可以设置为null当没有的时候
                ClassJumpTool.startToBackActivity(this, MainActivity.class, null, 10);
                break;
            case R.id.register_new:

                if (Utility.isFastDoubleClick()) // 连续点击
                    return;
                ClassJumpTool.startToNextActivityForResult(this,
                        RegisterActivity.class, 10);
                break;
            case R.id.forget_password1:
//在这里高伟豪。。。跳转到发送验证码页面
                if (Utility.isFastDoubleClick()) // 连续点击
                    return;
                ClassJumpTool.startToNextActivityForResult(this,
                        ForgetPassword.class, 10);
                break;


        }
    }


    // 注册广播
    public void registerBoradcastReceiver() {
        // System.out.println("-----注册广播----");
        myIntentFilter = new IntentFilter();
        myIntentFilter.addAction(Constant.THIRD_ALLOGIN);// 第三方已经注册返回
        registerReceiver(mBroadcastReceiver, myIntentFilter);
    }

    // 广播内部类
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context arg0, Intent intent) {
            // TODO Auto-generated method stub
            String action = intent.getAction();
            LogHelper.d("----第三方登录注册后-有广播过来-" + action);
//            if (action.equals(Constant.THIRD_ALLOGIN)) {
//                oAuthId = intent.getStringExtra("oauthId");
//
//                et_name.setText(intent.getStringExtra("name"));
//                LogHelper.d("------有广播过来,第三方登录返回的oauthId:" + oAuthId);
//            }
        }

    };

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (!isTh) {
            LogHelper.d("----登录界面  onResume-");
            // DataContoler.addAccess(this, "登录");
            MobclickAgent.onResume(this);
        }
        isTh = false;
    }


    /**
     * 关闭当前activity
     */
    public void closeActivity() {
        AppManager.getAppManager().finishActivity(this);
        overridePendingTransition(R.anim.out_from_left, R.anim.out_from_right);
    }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        if (!isTh) {
            MobclickAgent.onPause(this);
        }
        isTh = false;
    }


    private ProgressDialog pd;

    private void initPd() {
        pd = new ProgressDialog(this);//加载的ProgressDialog
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);//选择加载风格 这里是圆圈 STYLE_HORIZONTAL 是水平进度条
        pd.setMessage("登录中....");
    }


    /**
     * 登录
     */
    private void exLogin() {

        name = et_name.getText().toString().trim();
        pass = et_pass.getText().toString().trim();

        if (StringUtil.isNull(name)) {
            Toast.makeText(this, "请输入账号", Toast.LENGTH_LONG).show();
            return;
        }
        boolean isNum = name.matches("[0-9]+");
        if (isNum) {
            int cheeck = CheckPhoneLength.cheeck(name);
            if (cheeck == 0) {
                ToastHelper.show(this, "手机号码过短");
                return;
            } else if (cheeck == 2) {
                ToastHelper.show(this, "手机号码过长");
                return;
            }
            if (!JudgmentLegal.isPhoneOrEmail(name)) {
                ToastHelper.show(this, "账号格式不对,请输入正确的手机号");
                return;
            }
            if (StringUtil.isNull(pass)) {
                ToastHelper.show(this, "请输入密码");
                return;
            }
            getloginPasskeyMethod(TAG_GET_PASSKEY);
            initPd();
            pd.show();
        } else {
            if (name.endsWith("@myappcc.com")) {
                if (StringUtil.isNull(pass)) {
                    ToastHelper.show(this, "请输入密码");
                    return;
                }
                getloginPasskeyMethod(TAG_GET_PASSKEY);
                initPd();
                pd.show();
            } else {
                ToastHelper.show(this, "账号格式不对,请输入手机号或者邮箱");
            }
        }



    }


    /**
     * @param @param tag 设定文件
     * @return void 返回类型
     * @throws
     * @Title: getPasskeyMethod
     * @Description: TODO 获取passkey
     */
    private void getloginPasskeyMethod(String tag) {
        getPasskeyMeth(tag);
    }


    /**
     * @param @param tag 设定文件
     * @return void 返回类型
     * @throws
     * @Title: getPasskeyMethod
     * @Description: TODO 获取passkey
     */
    protected void getPasskeyMeth(String tag) {
//        LogHelper.d("-----passkey请求的地址:" + UrlManager
//                .GET_PASSKEY());

        System.out.println("#####进入passkey");
        MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,
                tag, Constant.REQUEST_GET, new RequestParams(UrlManager
                .GET_PASSKEY()), this, "获取passkey", false));
    }


    @Override
    public void successCallBack(String resultTag, BaseBean baseBean,
                                String callBackMsg, boolean isShowDiolog) {
        // TODO Auto-generated method stub
//        dissMissDialog(isShowDiolog);
        successMethod(callBackMsg, resultTag);
        sucMethiod(callBackMsg, resultTag);

    }

    @Override
    public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
        super.failCallBack(arg0, resultTag, isShowDiolog);
        if (resultTag.equals(TAG_GET_PASSKEY)) {

        } else if (resultTag.equals(TAG_LOGIN)){
            ToastHelper.show(LoginActivity.this,"登录失败"+arg0.getLocalizedMessage());
            pd.dismiss();
        }
    }

    private void sucMethiod(String msg, String resultTag) {

        //叠加的方法做登录。
        if (resultTag.equals(TAG_GET_PASSKEY)) {
            GetPassKey key = JsonTools.fromJson(msg, GetPassKey.class);
            passkey = key.getData().getPassKey();


           /* if (JudgmentLegal.validatePhoneNumber(name))
//                System.out.println("手机登录需要的oauthid：  " );
//                oAuthId="";*/
                loginMethod(name, pass, passkey, "");


        } else if (resultTag.equals(TAG_LOGIN)) {
            Log.e("sucMethiod: ",msg );
            pd.dismiss();
//            LogHelper.d("----登录成功--" + msg);


            //设置数据
            String data = DataContoler.getJsonData(msg);
            if (data != null)
                PreferenceUtils.setPrefString(LoginActivity.this,
                        Constant.User, data);//

            //MenuLeftFragment.isLogin = true;
            PreferenceUtils.setPrefBoolean(LoginActivity.this, Constant.CC_IFLOGIN, true);
//            hideProdia();


            System.out.println("####非常重要：" + msg);

            final User user = DataContoler.parseLoginMsgAndSetUser(msg, pass, "");
            DBTools.loginDb(LoginActivity.this,user);
            user.getAccid();
            SPUtils.saveAccid(user.getAccid());
            setMsg(0, user);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    GetCustomer.httpConnPostString(user.getAccid(), user.getAccToken());
                }
            }).start();
            ToastHelper.show(this, "登录成功");
            SPUtils.setHasLogin(true);
            UTagAndAlias.delTag("-1");
            UTagAndAlias.addTag(user.getUserId()+"");
            UTagAndAlias.delAlia("0");
            UTagAndAlias.addAlia(user.getUserId()+"");
        } else if (resultTag.equals(TAG_GETUSERNAME)) {


        } else if (resultTag.equals(TAG_UOLOAD)) {

        }

    }


    /**
     * @return void 返回类型
     * @throws
     * @Title: loginMethod
     * @Description: TODO(登录请求)
     * 设定文件
     */
    private void loginMethod(String name, String pass, String passkey,
                             String oauthid) {
        System.out.println("---登录的参数-passkey>" + passkey);
        // String v1 = DataContoler.getLoginV(name, pass, passkey);
        login_v = DataContoler.getLoginV(name, pass, passkey);
        // System.out.println("-----v>"+v1);
        if (!StringUtil.isNull(login_v)) {
            LogHelper.e("----登录请求的oauthid" + oauthid);
            MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,

                    TAG_LOGIN, Constant.REQUEST_POST, ParamsTools.login(
                    UrlManager.LOGIN(), login_v, oauthid), LoginActivity.this,
                    "正在登录...", false));
        }

    }

    @Override
    public void failShowCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.failShowCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        if (resultTag.equals(TAG_LOGIN)){
            BaseBean baseBean1 = GsonUtils.fromData(callBackMsg, BaseBean.class);
            pd.dismiss();
            ToastHelper.show(LoginActivity.this,baseBean1.getMsg());
        }
    }

    /**
     * 数据返回成功，并且code=1时调用
     */
    protected void successMethod(String msg, String resultTag) {

        //高伟豪。只有在个别tag的时候调用这个方法。目前几个tag都没有用。
        Log.e( "successMethod: ", msg);

    }


//    @Override
//    public void startCallBack(String resultTag, final boolean isShowDiolog,
//                              final String showTitle) {
//        // TODO Auto-generated method stub
//        this.runOnUiThread(new Runnable() {
//
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
////                startDialog(isShowDiolog, showTitle);
//            }
//        });
//
//    }


//
//    @Override
//    public void reLoginCallBack(String resultTag, boolean isShowDiolog) {
//        // TODO Auto-generated method stub
//        System.out.println("baseactivity里面---reLoginCallBack-");
////        dissMissDialog(isShowDiolog);
////        reLogin(resultTag);
//    }
//


    /*******
     * callback接口
     ********/


//
    private void hideProdia() {
//            if (dialog != null && dialog.isShowing())
//                dialog.dismiss();

    }

    private void setMsg(int msg_id, Object object) {
        Message message = handler.obtainMessage();
        message.what = msg_id;
        message.obj = object;
        handler.sendMessage(message);
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
//            ShareSDK.stopSDK(this);
//            unregisterReceiver(mBroadcastReceiver);
    }

//        private void authorize(Platform plat) {
//            if (plat == null) {
//                return;
//            }
//            if (plat.isValid()) {// 授权过了
//                String userId = plat.getDb().getUserId();
//                if (!TextUtils.isEmpty(userId)) {
//                    // 已存在。进行登录操作、
//                    if (plat != null) {
//                        thirdLoginMethod(plat);
//                        return;
//                    }
//
//                    // plat.removeAccount();
//
//                }
//            }
//            // if(!plat.isValid())
//            // plat.authorize();
//
//            plat.setPlatformActionListener(this);
//            plat.SSOSetting(false);// 此处设置为false，则优先采用客户端授权的方法，设置true会采用网页方式
//            plat.showUser(null);
//        }


    private void upImg(String img) {
        MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,

                TAG_UOLOAD, Constant.REQUEST_POST, ParamsTools.upLodingImag(
                UrlManager.UPIMG(UrlManager.USERIMG), img), this, "上传头像中..",
                false));
    }

    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
        // TODO Auto-generated method stub
        super.onActivityResult(arg0, arg1, arg2);
        if (arg2 != null) {
            switch (arg1) {
                case 10:
//                        String phone = arg2
//                                .getStringExtra(ClassJumpTool.DATA_PACKET_BACK);
//                        if (phone != null) {
//                            et_name.setText(phone);
//
//                        }
//                        break;

                case 20:
                    // 第三方返回

                    break;
            }
        }

    }

    @Override
    public void onFinish() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onFailed() {
        // TODO Auto-generated method stub
        LogHelper.d("-----下载失败");
    }

    @Override
    public void onFinish(String img_url) {
        // TODO Auto-generated method stub
        LogHelper.d("-----下载成功:本地地址" + img_url);
//            // UserImg=img_url;
//            url_ls.clear();
//            thirdLoginBean.setUserImg(img_url);
        handler.sendEmptyMessage(8);

    }


    private void showDialog(String msg) {
//            dialog = new LoadingDialog(this, msg);
//            dialog.setCanceledOnTouchOutside(false);
//            dialog.show();

    }


    public HttpCallBack getMainHttpCallBack() {
        return mainHttpCallBack;
    }

    public void setMainHttpCallBack(HttpCallBack mainHttpCallBack) {
        this.mainHttpCallBack = mainHttpCallBack;
    }




}