package com.tiemuyu.chuanchuan.activity.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tiemuyu.chuanchuan.activity.AboutUs;
import com.tiemuyu.chuanchuan.activity.GetPathFromUri4kitkat;
import com.tiemuyu.chuanchuan.activity.MainActivity;
import com.tiemuyu.chuanchuan.activity.MyApplication;
//import com.tiemuyu.chuanchuan.activity.MyBody;
import com.tiemuyu.chuanchuan.activity.MyBody;
import com.tiemuyu.chuanchuan.activity.MyInfo;
import com.tiemuyu.chuanchuan.activity.MyOrder;
import com.tiemuyu.chuanchuan.activity.MySaveItem;
import com.tiemuyu.chuanchuan.activity.MyShaitu;
import com.tiemuyu.chuanchuan.activity.MyWallet;
import com.tiemuyu.chuanchuan.activity.MyWebview;
import com.tiemuyu.chuanchuan.activity.PublishPicToGetPriceActivity;
//import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.SettingActivity;
import com.tiemuyu.chuanchuan.activity.ShareApp;
import com.tiemuyu.chuanchuan.activity.UserOrderActivity;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.GetPassKey;
import com.tiemuyu.chuanchuan.activity.bean.User;
import com.tiemuyu.chuanchuan.activity.chat_tools.view.CircleImageView;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.http.HttpTools;
import com.tiemuyu.chuanchuan.activity.new_activities.FatuActivity;
import com.tiemuyu.chuanchuan.activity.new_activities.LoginActivity;
import com.tiemuyu.chuanchuan.activity.new_activities.RegisterActivity;
import com.tiemuyu.chuanchuan.activity.util.AppManager;
import com.tiemuyu.chuanchuan.activity.util.ClassJumpTool;
import com.tiemuyu.chuanchuan.activity.util.ConnectionUtil;
import com.tiemuyu.chuanchuan.activity.util.DataContoler;
import com.tiemuyu.chuanchuan.activity.util.JsonTools;
import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
import com.tiemuyu.chuanchuan.activity.util.PreferenceUtils;
import com.tiemuyu.chuanchuan.activity.util.ServerUtils;
import com.tiemuyu.chuanchuan.activity.util.StringUtil;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.util.ToastHelper;
import com.tiemuyu.chuanchuan.activity.view.URL;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.text.DecimalFormat;

/**
 * Created by Lonze on 2016/8/23.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {

    public static boolean isFirst = true;

    private SharedPreferences sp;
    View view;


    public static User user;

    private CircleImageView iv_head;// 头像

    private TextView tv_nick;// 昵称

    private TextView tv_ye;// 余额

    private TextView tv_cb;// 穿币

    private IntentFilter myIntentFilter;

    private MainActivity mainActivity;

    private String passkey;
    private String login_v;

    private String TAG_MINEFRESH = "TAG_MINEFRESH";


    @ViewInject(R.id.imageView)
    private ImageView bt_jumplogin;// 登录

    @ViewInject(R.id.order)
    private LinearLayout myOrder;// 我的订单mine_shaitu



    @ViewInject(R.id.mf_shaitu)
    private LinearLayout mf_shaitu;// 我的订单mine_shaitu



    @ViewInject(R.id.purse)
    private LinearLayout myWallet;


    @ViewInject(R.id.document)
    private LinearLayout myInfo;

    @ViewInject(R.id.body)
    private LinearLayout myBody;


    @ViewInject(R.id.mf_invitefriend)
    private LinearLayout mf_invitefriend;

    @ViewInject(R.id.mf_aboutme)
    private LinearLayout mf_aboutme;    //mf_saveitem



    @ViewInject(R.id.mf_saveitem)
    private LinearLayout mf_saveitem;    //我的收藏页面






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        sp = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        view = inflater.inflate(R.layout.mine_fragment_layout, null);//需要根据fragment的布局进行修改
//
//        bt_jumplogin=(ImageView) view.findViewById(R.id.imageView);
        initView(view);
        bt_jumplogin=(ImageView) view.findViewById(R.id.imageView);

        myOrder=(LinearLayout) view.findViewById(R.id.order);


        myInfo=(LinearLayout) view.findViewById(R.id.document);
        myWallet=(LinearLayout) view.findViewById(R.id.purse);

        myBody=(LinearLayout) view.findViewById(R.id.body);
        mf_invitefriend=(LinearLayout) view.findViewById(R.id.mf_invitefriend);
        mf_aboutme=(LinearLayout) view.findViewById(R.id.mf_aboutme);

        mf_shaitu=(LinearLayout) view.findViewById(R.id.mf_shaitu);

        mf_saveitem=(LinearLayout) view.findViewById(R.id.mf_saveitem);



        return view;












    }



    private void initView(View v) {

        iv_head = (CircleImageView) v.findViewById(R.id.imageView);
        tv_nick = (TextView) v.findViewById(R.id.textView2);// 昵称
        tv_ye = (TextView) v.findViewById(R.id.textView3);// 零钱
        tv_cb = (TextView) v.findViewById(R.id.textView4);// 穿币


        mainActivity = (MainActivity) getActivity();


        registerBroadcastReceiver();
        initDbData();
        login();

    }

    /**
     * 读取数据库中的数据
     * */
    private void initDbData() {

        user = DBTools.getUser();



        if (PreferenceUtils.getPrefBoolean(getActivity(), Constant.CC_IFLOGIN, false))
        {
            setView(user);

        }


    }


    // 注册广播
    public void registerBroadcastReceiver() {
        // System.out.println("-----注册广播----");
        myIntentFilter = new IntentFilter();
        myIntentFilter.addAction(Constant.GBNAMERESP);// 消息返回报文
        myIntentFilter.addAction(Constant.LOGINMSG);// 登录返回报文
        myIntentFilter.addAction(Constant.APPCHECK);// App更新监测返回
        myIntentFilter.addAction(Constant.UPDATA_ACCOUNT);// 更新资金信息
        myIntentFilter.addAction(Constant.UPUSERDATA);// 更新用户信息
        myIntentFilter.addAction(Constant.AULOGIN_ACTION);// 执行自动登录操作
        myIntentFilter.addAction(Constant.RESETDATA);// 执行页面刷新
        getActivity().registerReceiver(mBroadcastReceiver, myIntentFilter);
    }










    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        System.out.println("#####   回退到minefragment");

    }



    // 广播内部类
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context arg0, Intent intent) {
            // TODO Auto-generated method stub


            System.out.println("#######有一个接收的行为");
            String action = intent.getAction();
            // LogHelper.d("-----有广播过来-" + action);
            if (action.equals(Constant.GBNAMERESP)) {
                // LogHelper.d("-----有广播过来11111-");
                System.out.println("#####有广播过来");
                loginOutUpView();
            } else if (action.equals(Constant.LOGINMSG)) {

                System.out.println("#####有收到登录成功广播");

                setView(user);
//                requesAward(Constant.RE_INVITE);
            } else if (action.equals(Constant.UPUSERDATA)
                    || action.equals(Constant.RESETDATA)) {
                // 更新账户信息
                setView(user);
            }

            else if (action.equals(Constant.AULOGIN_ACTION)) {

                System.out.println("#####action.equals(Constant.AULOGIN_ACTION))");

                user = DBTools.getUser();
                if (user != null) {
                    System.out.println("#####action.equals(Constant.AULOGIN_ACTION))");

//                    if (user.getIsThird() == 0) {

                        System.out.println("第一个地方哦gwh");
                        autoLogin();

                }



                }

            }









    };



    private void login() {

        if (user != null) {
            if (ConnectionUtil.isConn(getActivity())) {
                    System.out.println("第二个地方哦gwh");

                    autoLogin();

            }

        }
    }


    /**
     * 自动登录
     */
    private void autoLogin() {
        System.out.println("-->自动登录----");
        System.out.println("-->自动登录----gwh");
        getPasskeyMethod(HttpTools.TAG_GETPASSKEY);
    }






    private void getPasskeyMethod(String tag) {


        System.out.println("--getPasskeyMethod");

//
//        MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(
//                mainActivity,
//
//                tag, Constant.REQUEST_GET, new RequestParams(UrlManager
//                .GET_PASSKEY()), callBack, "获取passkey", false));


//        MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(
//                mainActivity,
//
//                tag, Constant.REQUEST_GET, new RequestParams(UrlManager
//                .GET_PASSKEY()), callBack, "获取passkey", false));


        MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(getActivity(),
                tag, Constant.REQUEST_GET, new RequestParams(UrlManager
                .GET_PASSKEY()), this, "获取passkey", false));


        //callback 换成this貌似也可以
    }



    private void successParse(String msg, String resultTag) {
        if (resultTag.equals(HttpTools.TAG_AULOGIN)) {
            System.out.println("---自动登录成功--:" + msg);
            ToastHelper.show(getActivity(), "自动登录成功");



            // 设置数据
            String data = DataContoler.getJsonData(msg);
            if (data != null)
                PreferenceUtils.setPrefString(getActivity(), Constant.User,
                        data);//

            loginSuc(msg);

        } else if (resultTag.equals(HttpTools.TAG_GETPASSKEY)) {
            // LogHelper.d("---获取pass->"+msg);
            System.out.println("########################################TAG_GETPASSKEY");
            GetPassKey key = JsonTools.fromJson(msg, GetPassKey.class);
            if (key != null) {
                // LogHelper.d("---获取pass1111111->");

                passkey = key.getData().getPassKey();
                PreferenceUtils.setPrefString(getActivity(), Constant.PASSKEY,
                        passkey);
                loginMethod();
            }

        }
        else if (resultTag.equals(TAG_MINEFRESH))
        {
            System.out.println("########################################TAG_MINEFRESH"+msg);

            //如果登陆了。但是后台掉线的话，重新relogin。如果后台没有掉线。则只是重新启动app
           // ToastHelper.show(getActivity(), msg);
            updateResult(msg);


        }
//        else if (resultTag.equals(HttpTools.TAG_TRY)) {
//
//            ToastHelper.show(getActivity(), "TAG_U3D_CREATHEAD   "+msg);
//            System.out.println(msg);
//            PersonInfoResultBean infoResultBean = JsonTools.fromJson(msg,
//                    PersonInfoResultBean.class);
//
//
//
//            if (infoResultBean != null) {
//
//
//                infoBean = infoResultBean.getData();
//
//                System.out.println(infoBean.toString());
//
//                if (infoBean != null) {
//                    setInfoToView(infoBean);
//                }
//            }
//
//        }
    }



    private void updateResult(String msg) {
        if (isOk(msg)) {
//            Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
//            isRegist = true;
            System.out.println("----注册返回信息:" + msg);
//            btn_left.setVisibility(View.GONE);
            user = DataContoler.parseLoginMsgAndSetUser(msg, user.getPass(), "");
            // bean=resultBean.getData();
            if (user != null) {

//                ImageLoader.getInstance().displayImage(user.getUserImg(), iv);
//                usrImg = user.getUserImg();
//                et_nicheng.setText(user.getNickName());
//                System.out.println("----注册后user返回信息:" + user.getEmail());
                //MenuLeftFragment.isLogin = true;
//                PreferenceUtils.setPrefBoolean(this, Constant.CC_IFLOGIN, true);


                //设置数据
                String data = DataContoler.getJsonData(msg);
                if (data != null)
                    PreferenceUtils.setPrefString(getActivity(),
                            Constant.User, data);//

//                setView(false, false, true);
//                setRightButton("跳过");

                MineFragment.user = user;
                DBTools.loginDb(getActivity(), user);

                Intent intent = new Intent();
                intent.setAction(Constant.LOGINMSG);
                getActivity().sendBroadcast(intent);

//                AppManager.getAppManager().finishActivity(this);
//                overridePendingTransition(R.anim.out_from_left, R.anim.out_from_right);
//                AppManager.getAppManager().finishActivity(
//                        LoginActivity.class);


                // intent.setAction(Constant.LOGINMSG);
                // sendBroadcast(intent);
            }

        } else {
            BaseBean baseBean = JsonTools.fromJson(msg, BaseBean.class);
            Toast.makeText(getActivity(), baseBean.getMsg(), Toast.LENGTH_LONG).show();

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




    private void loginSuc(String msg) {

        updataView(msg);
        // isLogin = true;
        PreferenceUtils
                .setPrefBoolean(getActivity(), Constant.CC_IFLOGIN, true);
        requesAward(Constant.RE_INVITE);
    }



    private void updataView(String msg) {

        // isLogin = true;
        PreferenceUtils
                .setPrefBoolean(getActivity(), Constant.CC_IFLOGIN, true);
        user = DataContoler.upDb(msg, user);
        setView(user);
        DBTools.loginDb(getActivity(), user);

        /** 发送更新首页广播 */
        Intent intent = new Intent();
        intent.setAction(Constant.DATA_UPDATA_HOME);
        intent.setAction(Constant.AULOGINMSG);
        getActivity().sendBroadcast(intent);
    }




    private void loginMethod() {
        System.out.println("###########关键位置！！！！！loginmethod");
        login_v = DataContoler.getLoginV(user.getUsername(), user.getPass(), passkey);
        System.out.println("###########"+login_v);
        String oauthid="";
        if (!StringUtil.isNull(login_v)) {
            MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(
                    getActivity(), HttpTools.TAG_AULOGIN, Constant.REQUEST_POST,
                    ParamsTools.login(UrlManager.LOGIN(), login_v, oauthid),
                    callBack, "正在登录...", false));




        }

    }




    @Override
    public void successCallBack(String resultTag, BaseBean baseBean,
                                String callBackMsg, boolean isShowDiolog) {
        // TODO Auto-generated method stub
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        // LogHelper.e("-----侧滑栏请求成功回调"+resultTag);
        System.out.println("successCallBack");
        successParse(callBackMsg, resultTag);
    }

    @Override
    public void failShowCallBack(String resultTag, BaseBean baseBean,
                                 String callBackMsg, boolean isShowDiolog) {
        // TODO Auto-generated method stub
        super.failShowCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        // LogHelper.e("-----侧滑栏请求成功回调11111");
        if (resultTag.equals(HttpTools.TAG_AULOGIN)) {
            System.out.println("#######自动登录失败000000!"+callBackMsg);
            ToastHelper.show(getActivity(), "自动登录失败");
            login();
        }
    }

    @Override
    public void failCallBack(Throwable arg0, String resultTag,
                             boolean isShowDiolog) {
        // TODO Auto-generated method stub
        super.failCallBack(arg0, resultTag, isShowDiolog);
        // LogHelper.e("-----侧滑栏请求失败回调");
        if (resultTag.equals(HttpTools.TAG_AULOGIN)) {
            System.out.println("######自动登录失败11111!");
            ToastHelper.show(getActivity(), "自动登录成功");
            login();
        }
    }





    /**
     * @Title: setView
     * @Description: TODO 更新显示数据
     * @param @param user 设定文件
     * @return void 返回类型
     * @throws
     */
    private void setView(User user) {
        System.out.println("#####更新侧滑栏显示数据");

        DecimalFormat df = new DecimalFormat("######0.00");

        if (!StringUtil.isNull(user.getUserImg()))
        {

            System.out.println("#####getUserImg"+user.getUserImg());
            ImageLoader.getInstance().displayImage(user.getUserImg(), iv_head);


        }
        else
            iv_head.setImageResource(R.drawable.circle_logo);

//        tv_note.setVisibility(View.GONE);      高伟豪 这里是个登录提示
//        tv_sign.setVisibility(View.VISIBLE);
//        rl_cash.setVisibility(View.VISIBLE);    高伟豪 穿币和零钱
        tv_nick.setText(user.getNickName());

        tv_ye.setText("零钱："+df.format(user.getAmounts() - user.getFrzAmounts()) + "");
        tv_cb.setText("穿币："+String.valueOf(user.getCcCoin() - user.getFrzCcCoin()));


//        mainActivity.setTopView(user);    高伟豪不知道有什么用。暂时关掉
    }




    private void loginOutUpView() {
        tv_nick.setText("点击登录");
        tv_ye.setText("");
        tv_cb.setText("");
    }



    /** 奖励请求 */
    private void requesAward(String tag) {
//        MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(
//                mainActivity, HttpTools.GET_AWARD_INFO, Constant.REQUEST_GET,
//                ParamsTools.getAward(UrlManager.GET_GETREWARD(), tag),
//        callBack, "", false));
    }



    @Override
    public void onStart() {
        super.onStart();


        System.out.println("#####重新进入minfragment onstart");
        //重新进入的时候刷新user data

        if (PreferenceUtils.getPrefBoolean(getActivity(), Constant.CC_IFLOGIN, false)==true)
        {


            System.out.println("######打开我的页面判断是否登录如果登陆了那么尝试重新获取我的数据");
            refreshMine();



        }


        final View.OnClickListener lsn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // TODO Auto-generated method stub
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                getActivity().startActivity(intent);
            }};
        im_setting.setOnClickListener(lsn);
        final View.OnClickListener login = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (PreferenceUtils.getPrefBoolean(getActivity(), Constant.CC_IFLOGIN, false)==false)
                {
                    System.out.println("#####未登录");
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    getActivity().startActivity(intent);
                }
                else
                {
                    System.out.println("#####已经登录等待新的页面");
                    Toast.makeText(getActivity(), "已经登录等待新的页面", Toast.LENGTH_SHORT).show();
                }


            }};
        bt_jumplogin.setOnClickListener(login);


        final View.OnClickListener tryy = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub



                ClassJumpTool.startToNextActivity(getActivity(), MyWebview.class, URL.UrlOrder);





            }};
        myOrder.setOnClickListener(this);


        final View.OnClickListener tryy2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ClassJumpTool.startToNextActivityForResult(getActivity(), MyWallet.class, 10);
            }};

        myWallet.setOnClickListener(tryy2);

        final View.OnClickListener tryy3 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (user==null) {
                    Toast.makeText(mainActivity, "您还未登录", Toast.LENGTH_SHORT).show();
                    return;
                }
                ClassJumpTool.startToNextActivityForResult(getActivity(),  MyInfo.class, 10);


            }};
        myInfo.setOnClickListener(tryy3);



        final View.OnClickListener tryy4 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                ClassJumpTool.startToNextActivityForResult(getActivity(),   MyBody.class, 10);




            }};
        myBody.setOnClickListener(tryy4);




        final View.OnClickListener tryy5 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (user==null) {
                    Toast.makeText(mainActivity, "您还未登录", Toast.LENGTH_SHORT).show();
                    return;
                }

                ClassJumpTool.startToNextActivityForResult(getActivity(),   ShareApp.class, 10);




            }};
        mf_invitefriend.setOnClickListener(tryy5);





        final View.OnClickListener tryy6 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                ClassJumpTool.startToNextActivityForResult(getActivity(),   AboutUs.class, 10);




            }};
        mf_aboutme.setOnClickListener(tryy6);    //mf_shaitu





        final View.OnClickListener tryy7 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                ClassJumpTool.startToNextActivityForResult(getActivity(),   MyShaitu.class, 10);




            }};
        mf_shaitu.setOnClickListener(tryy7);    //mf_shaitu   mf_saveitem



        final View.OnClickListener tryy8 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                ClassJumpTool.startToNextActivityForResult(getActivity(),   MySaveItem.class, 10);




            }};
        mf_saveitem.setOnClickListener(tryy8);    //mf_shaitu   mf_saveitem






    }






public  void refreshMine()
{

    //假如已经登录。并且重新进入。则尝试刷新我的页面信息。如果当前没有登录则刷新。如果登陆了获取我的页面信息

    MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(getActivity(),
            TAG_MINEFRESH, Constant.REQUEST_GET, new RequestParams(UrlManager
            .GET_MYPAGEDATA()), this, "获取我的页面信息", false));




}










    public void share()
    {
        String accid = sp.getString("accid", "");
        new ShareAction(getActivity())
                .setDisplayList(
                        SHARE_MEDIA.SINA,
                        SHARE_MEDIA.WEIXIN,
                        SHARE_MEDIA.WEIXIN_CIRCLE,
                        SHARE_MEDIA.WEIXIN_FAVORITE,
                        SHARE_MEDIA.SMS,
                        SHARE_MEDIA.MORE)
                .withTitle("给你推荐一个神奇的APP")
                .withText("可以把你爱的衣服都做给你穿")
                .withTargetUrl("http://android.myappcc.com/cc/user/FriendShare?id="+accid)
                .withMedia(new UMImage(getActivity(), "http://www.myappcc.com/main/img/index-log.png"))
                .setCallback(umShareListener)
                .open();
        System.out.println("ShareAction opened in mainactivity..  saveload..........");
    }

    public void goBack(){
        if(webView.canGoBack()){
        }
    }












    public void sessionweb(String x, String x1){
        final String xx=x;
        final String xx1=x1;
        webView.post(new Runnable() {
            @Override
            public void run() {
                //String call = "javascript:Compare(\"" + xx + "\")";
                String call = "javascript:Compare(\"" + xx + "\",\"" + xx1 + "\")";
                System.out.println(call);
                webView.loadUrl(call);
            }
        });
    }

    public static String getChannelName(Activity ctx) {
        if (ctx == null) {
            return null;
        }
        String channelName = null;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                //注意此处为ApplicationInfo 而不是 ActivityInfo,因为友盟设置的meta-data是在application标签中，而不是某activity标签中，所以用ApplicationInfo
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        channelName = applicationInfo.metaData.getString("");
                    }
                }

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return channelName;
    }

    public  void www( )
    {
       // webView.loadUrl(URL.UrlMine);
    }















    @Override
    public void addPageLoad() {
        System.out.println("调用minefragment");
        if(!TextUtils.isEmpty(result)) {
            System.out.println(result + "@@子类@@@@成功上传图片");
            //String str = "{\"Code\":1,\"Msg\":\"OK\",\"Data\":{\"ImageUrl\":\"http://f1.myappcc.com/zfs/7E0/1240/RIC/240174344351CABGTOTZGT.jpg\"}}";
            try {
                JSONObject jsonObject = new JSONObject(result);
                String s_data = jsonObject.getString("Data");
                //System.out.println(s_data);
                JSONObject jsonObject1 = new JSONObject(s_data);
                final String json_url = jsonObject1.getString("ImageUrl");
                System.out.println(json_url);
                String call = "javascript:funcphoto(\"" + json_url+ "\")";
                webView.post(new Runnable() {
                    @Override
                    public void run() {
                        String call = "javascript:funcphoto(\"" + json_url + "\")";
                        System.out.println(call);
                        webView.loadUrl(call);
                        System.out.println("牛逼啊牛逼");
                        try {
                            System.out.println("1");
                            Thread.sleep(300);
                            System.out.println("2");
                        } catch (InterruptedException e) {
                            System.out.println("个屁");
                        }
                        System.out.println("。。。。。。。");
                        System.out.println("fragment 必须用这个load");
                        //webView.reload();
                    }
                });
                //webView.loadUrl(call);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        System.out.println("!!!!!!!!!!走完页面加载程序");
    }















    public  void saveload()
    {
        webView.loadUrl("javascript:save()");
    }

    //gao 注册页面传递注册基本信息的js函数
    public void loadinformation(String validcode)
    {
        final String call = "javascript:loadDefine(\"" + URL.currentversion + "\",\"" + android.os.Build.BRAND + "\", \""+android.os.Build.MODEL+"\",\""+android.os.Build.VERSION.RELEASE+"\", \"" + getChannelName(getActivity()) + "\",\"" +validcode +"\")";
        System.out.println("load info");
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl(call);
            }
        });
    }


    //史力：接收相机或相册返回图片uri
    @SuppressWarnings("deprecation")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        //Uri imageUri = null;
        switch (requestCode) {
            //以下，调用摄像头发图
            case TAKE_PHOTO:// 摄像头
                System.out.println("***********进入TAKE_PHOTO选择");
                if (data != null)
                    imageUri = data.getData();
                System.out.println("imageUrl: " + imageUri);
                final String path = imageUri.toString().substring(7);
                System.out.println("*******imageUrl to path: " + path);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //调用接口传图
                        System.out.println("*******传入web的path：" + path);
                        result = ServerUtils.formUpload(com.tiemuyu.chuanchuan.activity.view.URL.UPLOAD_URLMoment, path);
                        System.out.println("*******result: "+result);
                        if(!TextUtils.isEmpty(result)){
                            System.out.println("******进入非空判断");
                            handler.sendEmptyMessage(1);
                            System.out.println(result);//成功
                        }else{
                            handler.sendEmptyMessage(2);//失败
                        }
                    }
                }).start();
                break;
            //史力：以下：当选择从相册发图时候执行
            case SELECT_FROM_ALBUM:
                System.out.println("***********进入SELECT_FROM_ALBUM选择");
                if (data == null) {
                    return;
                }
                imageUri = data.getData();
                System.out.println("old Uri: " + data.getData());
                //GetPathFromUri4kitkat.getPath方法是新版的uri与绝对路径的转换方法，适合不同安卓版本的uri
                final String newUri = GetPathFromUri4kitkat.getPath(getActivity(), data.getData());
                System.out.println("Uri after getPath: " + newUri);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //调用接口传图
                        System.out.println("*******传入web的path：" + newUri);
                        //根据funflag判断调用不同的url
                        if (funFlagStr.toUpperCase().equals("USERLOGO")) {
                            result = ServerUtils.formUpload(com.tiemuyu.chuanchuan.activity.view.URL.UPLOAD_URLUSERLOGO , newUri);
                        } else if (funFlagStr.toUpperCase().equals("REFUND")) {
                            result = ServerUtils.formUpload(com.tiemuyu.chuanchuan.activity.view.URL.UPLOAD_URLREFUND, newUri);
                        } else if (funFlagStr.toUpperCase().equals("USERIMG")) {
                            result = ServerUtils.formUpload(com.tiemuyu.chuanchuan.activity.view.URL.UPLOAD_URLUSERIMG, newUri);
                        } else if (funFlagStr.toUpperCase().equals("BODYPIC")) {
                            result = ServerUtils.formUpload(com.tiemuyu.chuanchuan.activity.view.URL.UPLOAD_URLBODYPIC, newUri);
                        } else {
                            result = ServerUtils.formUpload(com.tiemuyu.chuanchuan.activity.view.URL.UPLOAD_URLMoment, newUri);
                        }
                        System.out.println("*******result: "+result);
                        if(!TextUtils.isEmpty(result)){
                            System.out.println("******进入非空判断");
                            handler.sendEmptyMessage(1);
                            System.out.println(result);//成功
                        }else{
                            handler.sendEmptyMessage(2);//失败
                        }
                    }
                }).start();
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Toast.makeText(getActivity(), "图片上传成功", Toast.LENGTH_SHORT).show();
                    System.out.println("子类调用那个*******图片上传成功");
                    addPageLoad();
                    break;
                case 2:
                    Toast.makeText(getActivity(), "图片上传失败", Toast.LENGTH_SHORT).show();
                    System.out.println("*******图片上传失败");
                    break;
                default:
                    break;
            }
        }
    };

    private UMShareListener umShareListener = new UMShareListener() {

        @Override
        public void onResult(SHARE_MEDIA platform) {
            System.out.println("为什么啊为什么啊");
            com.umeng.socialize.utils.Log.d("plat", "platform" + platform);
            if(platform.name().equals("WEIXIN_FAVORITE")){
                Toast.makeText(getActivity(), platform + " 收藏成功啦",Toast.LENGTH_SHORT).show();
                System.out.println("收藏成功啦@@@@@@@@@@@@@@@@@@@@@@@@@");
            }else{
                Toast.makeText(getActivity(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
                System.out.println("分享成功啦@@@@@@@@@@@@@@@@@@");
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(), platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            System.out.println("分享失败啦@@@@@@@@@@@@@@@@@@");
            if(t!=null){
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(), platform + " 分享取消了", Toast.LENGTH_SHORT).show();
            System.out.println("分享取消了@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        }

    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order:
                ClassJumpTool.startToNextActivityForResult(getActivity(), UserOrderActivity.class, 10);
            break;
        }
    }
}
