//package com.tiemuyu.chuanchuan.activity.new_activities;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.BroadcastReceiver;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.provider.MediaStore;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.tiemuyu.chuanchuan.activity.MainActivity;
//import com.tiemuyu.chuanchuan.activity.MyApplication;
//import com.tiemuyu.chuanchuan.activity.R;
//import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
//import com.tiemuyu.chuanchuan.activity.bean.GetPassKey;
//import com.tiemuyu.chuanchuan.activity.bean.TokenResultBean;
//import com.tiemuyu.chuanchuan.activity.bean.User;
//import com.tiemuyu.chuanchuan.activity.broadcastreceiver.SmsReceiver;
//import com.tiemuyu.chuanchuan.activity.chat_tools.inter.IOnSmsListener;
//import com.tiemuyu.chuanchuan.activity.chat_tools.view.CircleImageView;
//import com.tiemuyu.chuanchuan.activity.constant.Constant;
//import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
//import com.tiemuyu.chuanchuan.activity.db.DBTools;
//import com.tiemuyu.chuanchuan.activity.fragment.MineFragment;
//import com.tiemuyu.chuanchuan.activity.util.AESHelper;
//import com.tiemuyu.chuanchuan.activity.util.AppManager;
//import com.tiemuyu.chuanchuan.activity.util.BitmapUtil;
//import com.tiemuyu.chuanchuan.activity.util.ClassJumpTool;
//import com.tiemuyu.chuanchuan.activity.util.DataContoler;
//import com.tiemuyu.chuanchuan.activity.util.DownloadService;
//import com.tiemuyu.chuanchuan.activity.util.JsonTools;
//import com.tiemuyu.chuanchuan.activity.util.JudgmentLegal;
//import com.tiemuyu.chuanchuan.activity.util.MyCountTimer;
//import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
//import com.tiemuyu.chuanchuan.activity.util.PreferenceUtils;
//import com.tiemuyu.chuanchuan.activity.util.StringUtil;
//import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
//import com.tiemuyu.chuanchuan.activity.util.ToastHelper;
//import com.tiemuyu.chuanchuan.activity.util.Utility;
//import com.umeng.analytics.MobclickAgent;
//
//
//import org.xutils.http.RequestParams;
//import org.xutils.x;
//import org.xutils.view.annotation.ViewInject;
//
//
//import org.xutils.view.annotation.ViewInject;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.security.InvalidAlgorithmParameterException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.UUID;
//
//import static com.tiemuyu.chuanchuan.activity.R.id.tv_title;
//
///**
// * Created by CC2.0 on 2017/1/16.
// */
//
//public class ZhuceActivity extends BaseActivity
//
//        implements IOnSmsListener, ThreadPoolTaskHttp.HttpCallBack,View.OnClickListener
//{
//
//
//    /** 布局一 */
//    @ViewInject(R.id.ll_regist_one)
//    private LinearLayout ll_regist_one;// 布局一
//
//    @ViewInject(R.id.et_regist_account)
//    private EditText et_account;// 账号输入
//
//    @ViewInject(R.id.et_regist_pass)
//    private EditText et_pass;// 密码输入
//
//    @ViewInject(R.id.et_regist_yaoqing)
//    private EditText et_yaoqingma;// 验证码输入
//
//    @ViewInject(R.id.cb_regist_yaoqing)
//    private CheckBox cb;// 邀请框
//
//    @ViewInject(R.id.bt_regist_one_next)
//    private Button bt_next_one;// 下一步
//
//    @ViewInject(R.id.rl_regist_yaoqing)
//    private RelativeLayout rl_yaoqing;// 邀请布局
//
//    @ViewInject(R.id.ll_xy)
//    private LinearLayout ll_xy;// 协议
//
////    private HtmlBean htmlBean = new HtmlBean();
//
//    /** 布局二 */
//    @ViewInject(R.id.ll_regist_two)
//    private LinearLayout ll_regist_two;// 布局二
//
//    @ViewInject(R.id.tv_regist_two_phone)
//    private TextView tv_tishi;// 提示
//
//    @ViewInject(R.id.et_code)
//    private EditText et_code;// 验证码
//
//    @ViewInject(R.id.bt_code_time)
//    private Button bt_getcode;
//
//    @ViewInject(R.id.bt_next_two)
//    private Button bt_next_two;// 下一步
//
//    /** 布局三 */
//
////    @ViewInject(R.id.ll_regist_thrid)
//    private LinearLayout ll_regist_third;
//
//    @ViewInject(R.id.iv_touxiang)
//    private CircleImageView iv;// 头像
//
//    @ViewInject(R.id.et_nicheng)
//    private EditText et_nicheng;// 昵称
//
//    @ViewInject(R.id.bt_regist_finish)
//    private Button bt_finish;// 完成
//
//    // private String sid;//sid
//    private String name;// 账号
//    private String pass;// 密码
//    private String yaoqingma;// 邀请码
//    private String get_code;// 验证码
//    private String v1;// 用户登录信息
//    private String passkey;
//
//    private String usrImg = "";// 头像地址
//    // private String nickName;// 昵称
//    private String token;
//    private User user;
//
//    private MyCountTimer timeCount;
////    private SelectPicPopupWindow menuWindow;
//
//    /** 返回的图片地址 */
//    private String ImgLocation;
//    /** 拍照无 */
//    public static final int NONE = 4;
//    /** 拍照 */
//    public static final int PHOTOHRAPH = 5;
//    /** 缩放 */
//    public static final int PHOTOZOOM = 6;
//    /** 结果 */
//    public static final int PHOTORESOULT = 7;
//    /** 标识 */
//    public static final String IMAGE_UNSPECIFIED = "image/*";
//
//    private String cli_path;
//    private String key, nc;
//
//    public static boolean isRegist = false;// 是否注册成功
//
//    /** 短信监听 */
//    private BroadcastReceiver smsReceiver;
//    private IntentFilter filter;
//
//    private String TAG_GETCODE = "TAG_GETCODE";
//
//    private String TAG_TRY = "TAG_TRY";
//
//    private String TAG_GETPASSKEY = "TAG_GETPASSKEY";
//    private String TAG_REGIST = "TAG_REGIST";
//    private String TAG_REGIST_MODIFY = "TAG_REGIST_MODIFY";
//    private String TAG_UPIMAGLOAD = "TAG_UPIMAGLOAD";
//    private String TAG_UOLOAD = "TAG_UOLOAD";
//
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        this.setContentView(R.layout.register_layout);
//
//
//
//
//
//        // 添加Activity到堆栈
//        AppManager.getAppManager().addActivity(this);
//        //  Constant.VERSION = Version.getAppVersionName(this);
//        // _global = GlobalVariable.getInstance();
//
////        initProcess();
//
//
//    }
//
//
//
//
//    /**
//     * 加载的流程
//     */
//    protected void initProcess() {
//        initData();
//        initUI();
//        initListener();
//    }
//
//    protected void initData() {
//        // TODO Auto-generated method stub
//
//    }
//
//    protected void initUI() {
//        // TODO Auto-generated method stub
////        setCenterView(R.layout.register);
//        this.setContentView(R.layout.register);
//
//        x.view().inject(this);
//        timeCount = new MyCountTimer(bt_getcode);
//        // 实例化SelectPicPopupWindow
//
//
//
////        menuWindow = new SelectPicPopupWindow(RegistActivity.this,
////                itemsOnClick, "", "");
//
//
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
//    }
//
////    public void setCenterView(int layout) {
//////        centerLayout.removeAllViews();
//////        LayoutInflater inflater = getLayoutInflater();
////        View addView = inflater.inflate(layout, null);
//////        centerLayout.addView(addView, new LayoutParams(
//////                LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
////    }
//
//
//
//    @Override
//    protected void onResume() {
//        // TODO Auto-generated method stub
//        super.onResume();
//        DataContoler.addAccess(this, "注册");
//        MobclickAgent.onResume(this);
//    }
//
//    @Override
//    protected void onPause() {
//        // TODO Auto-generated method stub
//        super.onPause();
//        MobclickAgent.onPause(this);
//    }
//
//    @Override
//    protected void onDestroy() {
//        // TODO Auto-generated method stub
//        super.onDestroy();
//        unregisterReceiver(smsReceiver);
//    }
//
//    protected void initListener() {
//        // TODO Auto-generated method stub
//        bt_next_one.setOnClickListener(this);
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
//    }
//
//    public void onClick(View v) {
//        // TODO Auto-generated method stub
//        switch (v.getId()) {
//            case R.id.bt_regist_one_next:
//                // 下一步
//                if (Utility.isFastDoubleClick()) // 连续点击
//                    return;
//                getCodeMethod();
//                break;
//
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
//
////            case R.id.tv_title_right:
////                // 右上角的完成
////
////                String url = DBTools.getAppUrls(this).getRegisterSuccess();
////                if (!StringUtil.isNull(url)) {
////                    htmlBean.setTitle("");
////                    htmlBean.setUrl(url);
////                    ClassJumpTool.startToNextActivity(this, WebViewActivity.class,
////                            htmlBean);
////                }
////
////
////                //ClassJumpTool.startToNextActivity(this, RewardRegistActivity.class);
////                closeActivity();
////
////                AppManager.getAppManager().finishActivity(LoginActivity.class);
////                break;
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
//        }
//    }
//
//
//
//
//
//    /**
//     * 关闭当前activity
//     * */
//    public void closeActivity() {
//        AppManager.getAppManager().finishActivity(this);
//        overridePendingTransition(R.anim.out_from_left, R.anim.out_from_right);
//    }
//
//
//
//    /**
//     * @Title: getPasskeyMethod
//     * @Description: TODO 获取passkey
//     * @param @param tag 设定文件
//     * @return void 返回类型
//     * @throws
//     */
//    protected void getPasskeyMeth(String tag) {
//        MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,
//                tag, Constant.REQUEST_GET, new RequestParams(UrlManager
//                .GET_PASSKEY()), this, "获取passkey", false));
//    }
//
//
//
//    /**
//     * @Title: getCodeMethod
//     * @Description: TODO 获取验证码
//     *  设定文件
//     * @return void 返回类型
//     * @throws
//     */
//    private void getCodeMethod() {
//        name = et_account.getText().toString().trim();
//        pass = et_pass.getText().toString().trim();
//        yaoqingma = et_yaoqingma.getText().toString().trim();
//
//        if (StringUtil.isNull(name)) {
//            ToastHelper.show(this, "请输入账号");
//            return;
//        }
//
//        if (StringUtil.isNull(pass)) {
//            ToastHelper.show(this, "请输入密码");
//            return;
//        }
//
//        if (cb.isChecked()) {
//            if (StringUtil.isNull(yaoqingma)) {
//                ToastHelper.show(this, "请输入邀请码");
//                return;
//            }
//        }
//
//        if (!JudgmentLegal.validatePhoneNumber(name)) {
//            ToastHelper.show(this, "格式不对，请输入手机号");
//            return;
//        }
//        if (!JudgmentLegal.isPass(pass)) {
//            ToastHelper.show(this, "请输入6-16位密码");
//            return;
//        }
//
//        // if(!StringUtil.isNull(sid))
//        // requestMethod(this, URL.GET_CODE(),
//        // ParamsTools.getCode(name, yaoqingma), Constant.REQUEST_POST,
//        // TAG_GETCODE, "", "获取验证码中", null, null, true);
//
//        MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,
//
//                TAG_GETCODE, Constant.REQUEST_POST, ParamsTools.getCode(
//                UrlManager.GET_CODE(), name, yaoqingma), this, "获取验证码中", true));
//    }
//
//    /** 布局的显示隐藏 **/
//    private void setView(boolean one_show, boolean two_show, boolean thrid_show) {
//        if (one_show) {
//            ll_regist_one.setVisibility(View.VISIBLE);
//        } else {
//            ll_regist_one.setVisibility(View.GONE);
//        }
//
//        if (two_show) {
//            ll_regist_two.setVisibility(View.VISIBLE);
//        } else {
//            ll_regist_two.setVisibility(View.GONE);
//        }
//
//        if (thrid_show) {
//            ll_regist_third.setVisibility(View.VISIBLE);
//        } else {
//            ll_regist_third.setVisibility(View.GONE);
//        }
//
//    }
//
//    // @Override
//    // protected void successMethod(String msg, String resultTag, int code) {
//    // // TODO Auto-generated method stub
//    // super.successMethod(msg, resultTag, code);
//    // successParse(msg, resultTag);
//    // }
//
//    @Override
//    public void successCallBack(String resultTag, BaseBean baseBean,
//                                String callBackMsg, boolean isShowDiolog) {
//        // TODO Auto-generated method stub
//        successParse(callBackMsg, resultTag);
//    }
//
//    private void successParse(String msg, String resultTag) {
//
//   if (resultTag.equals(TAG_UOLOAD)) {
//            System.out.println("-----上传图片成功:" + msg);
//            // 上传图片成功
////            upload_imag_success(msg, resultTag);
//        }
//
//       else if (resultTag.equals(TAG_GETCODE)) {
//            // 获取验证码
//            codeResult(msg);
//
//        } else if (resultTag.equals(TAG_REGIST)) {
//            // 注册
//            registResult(msg);
//
//        }
//
////		else if(resutlTag.equals(TAG_TRY)){
////		//TODO NOTHING
////
////		}
////
//
//        else if (resultTag.equals(TAG_REGIST_MODIFY)) {
//            // 设置昵称、头像
//
//            ToastHelper.show(this, "设置成功");
//            user.setUserImg(usrImg);
//            user.setNickName(nc);
//
//            MineFragment.user = user;
//
//            Intent intent = new Intent();
//            intent.setAction(Constant.LOGINMSG);
//            sendBroadcast(intent);
//
//            new Thread(new Runnable() {
//
//                @Override
//                public void run() {
//                    // TODO Auto-generated method stub
//                    DBTools.loginDb(ZhuceActivity.this, user);
//                }
//            }).start();
//
//            setView(false, false, false);
//
//            // ClassJumpTool.startToNextActivity(this,
//            // RewardRegistActivity.class);
//
//            toRegistSuecc();
//
//            AppManager.getAppManager().finishActivity(this);
//            AppManager.getAppManager().finishActivity(LoginActivity.class);//存在的意义
//
//        } else if (resultTag.equals(TAG_GETPASSKEY)) {
//            // 获取passkey
//            System.out.println("----注册获取passkey->" + msg);
//            getPasskeyAndRegist(msg);
////        } else if (resultTag.equals(TAG_UPIMAGLOAD)) {
////            // 上传头像
////            System.out.println("----上传头像：" + msg);
////            UpImgResultBean resultBean = JsonTools.fromJson(msg,
////                    UpImgResultBean.class);
////            UpImgBean imgBean = resultBean.getData();
////
////            if (!StringUtil.isNull(imgBean.getImageUrl())) {
////                usrImg = imgBean.getImageUrl();
////                ImageLoader.getInstance().displayImage(usrImg, iv);
////            }
//
//        }
//    }
//
//    private void toRegistSuecc() {
////        String url = DBTools.getAppUrls(this).getRegisterSuccess();
////        if (!StringUtil.isNull(url)) {
////            htmlBean.setTitle("");
////            htmlBean.setUrl(url);
////            ClassJumpTool.startToNextActivity(this, WebViewActivity.class,
////                    htmlBean);
////        }
//    }
//
//    // 获取passkey并注册
//    private void getPasskeyAndRegist(String msg) {
//        GetPassKey key = JsonTools.fromJson(msg, GetPassKey.class);
//        if (key != null && key.getCode() == Constant.REQUEST_OK) {
//            passkey = key.getData().getPassKey();
//            // System.out.println("----passkey--" + passkey);
//            String x1 = name + "," + pass;
//            try {
//                v1 = AESHelper.getAesString(x1, passkey);
//            } catch (InvalidAlgorithmParameterException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (UnsupportedEncodingException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            if (!StringUtil.isNull(v1)) {
//                MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(
//                        this,
//
//                        TAG_REGIST, Constant.REQUEST_POST, ParamsTools.regist(
//                        UrlManager.REGIST() + "android",//MyApplication.mChannel,
//                        v1, get_code, yaoqingma, "", token), this,
//                        "正在注册...", true));
//            }
//
//        }
//    }
//
//    /**
//     * @Title: registResult
//     * @Description: TODO 注册结果操作
//     * @param @param msg 设定文件
//     * @return void 返回类型
//     * @throws
//     */
//    private void registResult(String msg) {
//        if (isOk(msg)) {
//            ToastHelper.show(this, "注册成功");
//            isRegist = true;
//            System.out.println("----注册返回信息:" + msg);
////            btn_left.setVisibility(View.GONE);
//            user = DataContoler.parseLoginMsgAndSetUser(msg, pass, "");
//            // bean=resultBean.getData();
//            if (user != null) {
//
//                ImageLoader.getInstance().displayImage(user.getUserImg(), iv);
//                usrImg = user.getUserImg();
//                et_nicheng.setText(user.getNickName());
//                System.out.println("----注册后user返回信息:" + user.getEmail());
//                //MenuLeftFragment.isLogin = true;
//                PreferenceUtils.setPrefBoolean(this, Constant.CC_IFLOGIN, true);
//
//
//                //设置数据
//                String data = DataContoler.getJsonData(msg);
//                if (data != null)
//                    PreferenceUtils.setPrefString(ZhuceActivity.this,
//                            Constant.User, data);//
//
//                setView(false, false, true);
////                setRightButton("跳过");
//
//                MineFragment.user = user;
//                DBTools.loginDb(ZhuceActivity.this, user);
//
//                Intent intent = new Intent();
//                intent.setAction(Constant.LOGINMSG);
//                sendBroadcast(intent);
//
//                // intent.setAction(Constant.LOGINMSG);
//                // sendBroadcast(intent);
//            }
//
//        } else {
//            BaseBean baseBean = JsonTools.fromJson(msg, BaseBean.class);
//            ToastHelper.show(this, baseBean.getMsg());
//            System.out.println("-----注册失败" + baseBean.getMsg());
//        }
//    }
//
//    /**
//     * @Title: codeResult
//     * @Description: TODO 获取验证码结果操作
//     * @param @param msg 设定文件
//     * @return void 返回类型
//     * @throws
//     */
//    private void codeResult(String msg) {
//        System.out.println("---获取验证成功->" + msg);
//        TokenResultBean bean = JsonTools.fromJson(msg, TokenResultBean.class);
//        if(bean!=null){
//            token = bean.getData().getToken();
//
//            setView(false, true, false);
//            tv_tishi.setText("亲,短信验证码已发送至" + name + ",请注意查收");
//            timeCount.start();
//        }
//
//
//    }
//
//    @Override
//    public void failCallBack(Throwable arg0, String resultTag,
//                             boolean isShowDiolog) {
//        // TODO Auto-generated method stub
//        if (resultTag.equals(TAG_GETCODE)) {
//            System.out.println("---获取验证失败->" + arg0.toString());
//            ToastHelper.show(this, "获取验证码失败");
//        } else if (resultTag.equals(TAG_REGIST)) {
//            ToastHelper.show(this, "注册失败");
//        } else if (resultTag.equals(TAG_REGIST_MODIFY)) {
//            ToastHelper.show(this, "修改资料失败");
//        }
//    }
//
//    @Override
//    public void failShowCallBack(String resultTag, BaseBean baseBean,
//                                 String callBackMsg, boolean isShowDiolog) {
//        // TODO Auto-generated method stub
//
//    }
//
//    private void showDialog() {
//
//    }
//
//    private boolean isOk(String msg) {
//        BaseBean baseBean = JsonTools.fromJson(msg, BaseBean.class);
//        if (baseBean.getCode() == Constant.REQUEST_OK) {
//            return true;
//        }
//        return false;
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // TODO Auto-generated method stub
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case PHOTOHRAPH:
//                File picture = new File(Constant.BASE_IMAGE_CACHE + "/"
//                        + ImgLocation);
//                startPhotoZoom(Uri.fromFile(picture));
//
//                break;
//
//            case PHOTORESOULT:
//                if (data == null) {
//                    return;
//                }
//                Bundle bundle = data.getExtras();
//                if (bundle != null) {
//                    Bitmap bitmap = bundle.getParcelable("data");//
//                    System.out.println("--裁剪返回的bitmap->" + bitmap);
//                    if (bitmap != null) {
//                        Bitmap bitmap2 = BitmapUtil.compressImage(bitmap);// 压缩
//                        String p = Constant.BASE_IMAGE_NEW + "/"
//                                + UUID.randomUUID().toString() + ".jpg";
//                        if (bitmap2 != null) {
//                            cli_path = BitmapUtil.saveMyBitmap(bitmap2, p);// 保存到SD卡
//                            System.out.println("---->cli_path:" + cli_path);
//                            if (cli_path != null) {
//                                MyApplication.poolManager
//                                        .addAsyncTask(new ThreadPoolTaskHttp(this,
//                                                TAG_UPIMAGLOAD,
//                                                Constant.REQUEST_POST,
//                                                ParamsTools.upLodingImag(UrlManager
//                                                                .UPIMG(UrlManager.USERIMG),
//                                                        cli_path), this,
//                                                "上传头像中...", true));
//                            }
//                        }
//
//                    }
//                }
//
//                break;
//            case PHOTOZOOM:
//                if (data != null) {
//                    if (data.getData() != null)
//                        startPhotoZoom(data.getData());
//                }
//
//                break;
//        }
//
//    }
//
//    /**
//     * 发图
//     */
//    public  void  exFatu()
//    {
//        if (Utility.isFastDoubleClick()) // 连续点击
//            return;
//        System.out.println("**************调用photoMOMENT");
//
//            AlertDialog.Builder multiDia=new AlertDialog.Builder(this);
//            multiDia.setTitle("选择照片： ");
//            multiDia.setPositiveButton("相册", new DialogInterface.OnClickListener() {
//
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    // TODO Auto-generated method stub
//                    System.out.println("********进入相册");
////                    ToastHelper.show(this, "点击相册");
//                    photoAlbum();
//
////                    Toast.makeText(, "点击相册", Toast.LENGTH_SHORT).show();
//                }
//            });
//            multiDia.setNeutralButton("照相机", new DialogInterface.OnClickListener() {
//
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    // TODO Auto-generated method stub
//                    System.out.println("**********进入照相机");
////                    ToastHelper.show(this, "点击照相机");
//                    takePhoto();
////                    Toast.makeText(getActivity(), "点击照相机", Toast.LENGTH_SHORT).show();
//                }
//            });
//            multiDia.create().show();
//
//
//
//
//
//        }
//
//
//
//    /** 拍照 */
//    private void takePhoto() {
//        ImgLocation = UUID.randomUUID().toString() + ".jpg";
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT,
//                Uri.fromFile(new File(Constant.BASE_IMAGE_CACHE, ImgLocation)));
//        startActivityForResult(intent, PHOTOHRAPH);
//
//    }
//
//    /** 相册 */
//    private void photoAlbum() {
//        Intent intent = new Intent(Intent.ACTION_PICK, null);
//        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                IMAGE_UNSPECIFIED);
//        startActivityForResult(intent, PHOTOZOOM);
//    }
//
//    public void startPhotoZoom(Uri uri) {
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
//        intent.putExtra("crop", "true");
//        // aspectX aspectY 是宽高的比例
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        // outputX outputY 是裁剪图片宽 、高
//        intent.putExtra("outputX", 320);
//        intent.putExtra("outputY", 320);
//        intent.putExtra("return-data", true);
//        startActivityForResult(intent, PHOTORESOULT);
//    }
//
//    @Override
//    public void smsListener(String code) {
//        // TODO Auto-generated method stub
//        System.out.println("----获取的短信验证码:" + code);
//        et_code.setText(code);
//    }
//
//
//
//
//
//
//
//    public void startCallBack(String resultTag,boolean isShowDiolog,String showTitle){}
//    public void cancelCallBack(String resultTag){}
//
//    public void reLoginCallBack(String resultTag,boolean isShowDiolog){}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
