package com.tiemuyu.chuanchuan.activity.http;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.util.JsonTools;
import com.tiemuyu.chuanchuan.activity.util.LogHelper;
import com.tiemuyu.chuanchuan.activity.util.PreferenceUtils;
import com.tiemuyu.chuanchuan.activity.util.StringUtil;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.http.cookie.DbCookieStore;
import org.xutils.x;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;

//import com.lidroid.xutils.http.RequestParams;
//import com.lidroid.xutils.http.client.HttpRequest;
//import org.xutils.common.Callback;
//import org.xutils.http.HttpMethod;
//import org.xutils.http.RequestParams;
//import org.xutils.http.request.HttpRequest;

/**
 * Created by CC2.0 on 2017/1/6.
 */


@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class HttpTools {
    public static String TAG_AULOGIN = "TAG_AULOGIN";// 自动登录
    public static String TAG_GETPASSKEY = "TAG_GETPASSKEY";// 获取passkey
    public static String TAG_THIRD_LOGIN = "TAG_THIRD_LOGIN";// 第三方登录
    public static String TAG_UP_ACCOUNT = "TAG_UP_ACCOUNT";// 获取资金信息
    public static String GET_AWARD_INFO = "GET_AWARD_INFO";// 获取邀请信息
    public static String TAG_CHECKAPP = "TAG_CHECKAPP";// 检测更新
    public static String TAG_AWARD_YXINFO = "TAG_AWARD_YXINFO";// 获取奖励信息
    public static String TAG_REGIST_AWARD_INFO = "TAG_REGIST_AWARD_INFO";// 注册奖励
    public static String TAG_APPACCESS = "TAG_APPACCESS";// 访问记录
    public static String TAG_APPEX = "TAG_APPEX";// 异常记录
    public static String TAG_IM_USET = "TAG_IM_USET";// IM用户昵称、头像
    public static String TAG_IM_SLONGTEXT="TAG_IM_SLONGTEXT";//发送IM长消息
    public static String TAG_IM_RLONGTEXT="TAG_IM_RLONGTEXT";//拉取IM长消息
    public static String TAG_IM_GROUP="TAG_IM_GROUP";//拉取众筹群组列表
    public static String TAG_U3D_UPIMG="TAG_U3D_UPIMG";//上传3D头像
    public static String TAG_U3D_CREATHEAD="TAG_U3D_CREATHEAD";//生成3D头像
    public static String TAG_TRY="TAG_TRY";//随处调用的测试tag
    public static String TAG_SET_BODY_DATA="TAG_SET_BODY_DATA";//设置身体数据的标签




    public static String TAG_TOAST_UserMine="TAG_TOAST_UserMine";


    private final String COOKIE="Cookie";
    private final String UA="User-Agent";

    private int action;// 请求类型 1 get , 0 post;
    private String title;//
    private Context context;
    private String resultTag;// 请求标识
    private HttpMethod method;//
    private RequestParams params;// 请求参数
    private boolean isShowDialog;// 是否显示dialog
    private ThreadPoolTaskHttp.HttpCallBack callBack;// 请求回调

    public HttpTools(Context context, ThreadPoolTaskHttp.HttpCallBack callBack, String resultTag,
                     RequestParams params, int action, String title, boolean isShowDialog) {
        this.callBack = callBack;
        this.action = action;
        this.params = params;
        this.context = context;
        this.resultTag = resultTag;
        this.isShowDialog = isShowDialog;
        this.title = title;

        setHttpUtils();
        httpSend();
    }

    private void setHttpUtils() {

        Log.e("setHttpUtils: "+resultTag,!StringUtil.isNull(PreferenceUtils.getPrefString(context, Constant.CC_COOKIE, "")) +"" );
        // 设置cookie
        if (!StringUtil.isNull(PreferenceUtils.getPrefString(context, Constant.CC_COOKIE, ""))) {
            LogHelper.e("----线程池请求的cookie:"
                    + PreferenceUtils.getPrefString(context, Constant.CC_COOKIE, ""));

            System.out.println("----线程池请求的cookie:"
                    + PreferenceUtils.getPrefString(context, Constant.CC_COOKIE, ""));
            Log.e( "Constant.CC_COOKIE", PreferenceUtils.getPrefString(context, Constant.CC_COOKIE, ""));
            params.addHeader(COOKIE,
                    PreferenceUtils.getPrefString(context, Constant.CC_COOKIE, ""));

        }
        // 设置UA
        if (!StringUtil.isNull(PreferenceUtils.getPrefString(context, Constant.CC_UA, ""))) {
            LogHelper.e("---线程池请求的UA:" +
                    PreferenceUtils.getPrefString(context, Constant.CC_UA, ""));
            System.out.println("---线程池请求的UA:" +
                    PreferenceUtils.getPrefString(context, Constant.CC_UA, ""));
            Log.e( "Constant.CC_UA ", PreferenceUtils.getPrefString(context,  Constant.CC_UA, ""));
            params.addHeader(UA, PreferenceUtils.getPrefString(context, Constant.CC_UA, ""));
        }

        if (action == 1)
            method = HttpMethod.GET;
        else
            method = HttpMethod.POST;
        callBack.startCallBack(resultTag, isShowDialog, title);
    }

    private void httpSend() {
     //   LogHelper.d("-----请求的地址:"+params.getUri());
        Log.e("测试发图:","发布完成"+resultTag );
        HashMap<String, String> headers = params.getHeaders();
        if (headers != null) {
            for ( HashMap.Entry<String, String> entry : headers.entrySet() ) {
                Log.e(resultTag, "Key = " + entry.getKey() + ", Value = " + entry.getValue());

            }
        }
        x.http().request(method, params, new Callback.CommonCallback<String>() {

            @Override
            public void onCancelled(CancelledException arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onError(Throwable arg0, boolean arg1) {
                // TODO Auto-generated method stub
                callBack.failCallBack(arg0, resultTag, isShowDialog);
                LogHelper.d("-----请求失败:"+resultTag);
            }

            @Override
            public void onFinished() {
                // TODO Auto-generated method stub
            }

            @Override
            public void onSuccess(String re) {
                // TODO Auto-generated method stub
                Log.e("测试发图onSuccess:","发布完成"+resultTag );
                String cookie = getCookie();
                // LogHelper.d("-------请求成功，tag>"+resultTag+",getCookie():"+cookie);
                BaseBean baseBean = JsonTools.fromJson(re, BaseBean.class);
                Log.e("测试发图onSuccess: ",resultTag+"::"+ re);
                int code = baseBean.getCode();
                System.out.println("#####resultTag,"+resultTag+",code"+code);

                if(!StringUtil.isNull(cookie)){
                    PreferenceUtils.setPrefString(context, Constant.CC_COOKIE, cookie);
                }


                if (code == 1) {
                    callBack.successCallBack(resultTag, baseBean, re, isShowDialog);
                    System.out.println("#####http请求维护code==1-"+resultTag+"返回参数："+re);
                }
                else if (code == -2)
                {
                    System.out.println("#####http请求维护code==-2-"+resultTag);
                    callBack.reLoginCallBack(resultTag, isShowDialog);

                }
                else
                    callBack.failShowCallBack(resultTag, baseBean, re,isShowDialog );

                System.out.println("#####http请求维护进入onSuccess"+resultTag+"返回参数："+re);
            }
        });
    }

    @SuppressLint("NewApi")
    protected String getCookie() {
        List<HttpCookie> cookies = DbCookieStore.INSTANCE.getCookies();
        LogHelper.e("------cookies:" + cookies.size());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < cookies.size(); i++) {

            HttpCookie cookie = cookies.get(i);
            String cookieName = cookie.getName();
            String cookieValue = cookie.getValue();

            if (!TextUtils.isEmpty(cookieName)) {
                sb.append(cookieName + "=");
                if (i == (cookies.size() - 1))
                    sb.append(cookieValue);
                else
                    sb.append(cookieValue + ";");
            }
        }

        System.out.println("-----get cookie：" + sb.toString()+",标识:"+resultTag);
        LogHelper.e("-----get cookie：" + sb.toString()+",标识:"+resultTag);

        return sb.toString();
    }

}
