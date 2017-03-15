package com.tiemuyu.chuanchuan.activity.InterfacePac;

import android.webkit.JavascriptInterface;
import android.widget.Toast;

import java.math.BigInteger;

/**
 * @项目名： android3.0
 * @包名： com.tiemuyu.chuanchuan.activity
 * @类描述：
 * @创建人： hr
 * @创建时间： 2016/8/18
 * @version：
 */
public class JsInterface {

    /*interface for javascript to invokes*/
    public interface wvClientClickListener {


        //以下接口方法用于调用大图显示
        public void wvHasClickEnvent(String x);

        //以下接口方法用于调用toast
        public void AndroidToast(String toastText);

        //以下接口方法用于调用照片上传
        public void photo(String funflag,int num,int type);

        //以下接口方法用于跳转页面
        public void navigatorback(String key);

        //以下接口方法用于打开页面
        public void navigatoropen(String key);

        //以下接口toast用户名密码
        public void ToastUsrPwd(String Usrname, String Password, String accid, String acctoken);

        //以下为单纯测试的接口
        public void testToast(String key);

        //以下为支付的接口
        public void payfunction(String signString, long type, String orderid);

        //以下为设置按钮的文字和执行的js脚本接口
        public void headersettingtext(String text, String script);

        //以下为设置回退按钮消失接口
        public void headerbackhide();

        //以下为设置登录的信息传递接口
        public void funcsetlogin(String userid, String accid, String acctoken);

        //以下为短期信息存储的接口
        public void sessionset(String id,String session);

        //以下为短期信息获取的接口
        public void sessionget(String id, String x1);
		
		//史力：以下接口为复制文字到剪贴板里面
        public void copyText(String text);

        //以下为注册数据传递接口
        public void InforPass(String validcode);

        //以下为im头像传递接口
        public void userimag(String path);

        //史力：以下接口用于社会化分享
        public void shareInAndroid(String title, String text, String image_url, String share_url, String type);

    }

    private wvClientClickListener wvEnventPro = null;

    public void setWvClientClickListener(wvClientClickListener listener) {
        wvEnventPro = listener;
    }

    //JSInterface2.ToastAgain 测试接口单纯做测试
    @JavascriptInterface
    public void ToastAgain(String toastText) {
        if(wvEnventPro != null)
            wvEnventPro.testToast(toastText);
    }

    //JSInterface2.javaFunction 调用大图显示
    @JavascriptInterface  //这个注解很重要
    public void javaFunction(String x) {
        if(wvEnventPro != null)
            wvEnventPro.wvHasClickEnvent(x);
    }

    //JSInterface2.ToastInJavascript 调用本机toast
    @JavascriptInterface
    public void ToastInJavascript(String toastText) {
        if(wvEnventPro != null)
            wvEnventPro.AndroidToast(toastText);
    }

    //JSInterface2.PhotoUpdate 图片上传接口
    //USERLOGO  refund userimg BODYPIC
    @JavascriptInterface
    public void PhotoUpdate(String funflag,int num,int type )
    {
        if(wvEnventPro != null)
            wvEnventPro.photo(funflag, num, type);
    }

    //JSInterface2.navigator_back 回退方法
    @JavascriptInterface
    public void navigator_back(String key)
    {
        if(wvEnventPro != null)
            wvEnventPro.navigatorback(key);
    }

    //JSInterface2.navigator_open 打开页面方法
    @JavascriptInterface
    public void navigator_open(String url)
    {
        if(wvEnventPro != null)
            wvEnventPro.navigatoropen(url);
    }

    //JSInterface2.ToastUsernamePassword 用户名和密码相关
    @JavascriptInterface
    public void ToastUsernamePassword(String logincache, String savestr, String accid, String acctoken) {
        if(wvEnventPro != null)
            wvEnventPro.ToastUsrPwd(logincache, savestr, accid, acctoken);
    }

    //JSInterface2.func_pay 调用支付
    @JavascriptInterface
    public void func_pay(String signString, long type ,String orderid) {
        //Toast.makeText(, "orderid  :"+orderid, Toast.LENGTH_SHORT).show();
        System.out.println("orderiddddddd+"+signString);
        if(wvEnventPro != null)
            wvEnventPro.payfunction(signString, 2, orderid);
    }

    //JSInterface2.header_setting_text("","") 设置按钮的文字和执行的js脚本
    @JavascriptInterface
    public void header_setting_text(String text, String script) {
        if(wvEnventPro != null)
            wvEnventPro.headersettingtext(text, script);
    }

    //JSInterface2.header_back_hide() 设置回退消失 干掉不用
    @JavascriptInterface
    public void header_back_hide() {

       // if(wvEnventPro != null)
            //wvEnventPro.headerbackhide();
    }

    //JSInterface2.func_setlogin(userid, accid, acctoken)  设置登录的额外信息im   干掉不用
    @JavascriptInterface
    public void func_setlogin(String userid, String accid, String acctoken ) {
        if(wvEnventPro != null)
            wvEnventPro.funcsetlogin(userid, accid, acctoken);
    }

    //JSInterface2.session_set()  设置短期保存的文本
    @JavascriptInterface
    public void session_set(String id,String session) {
        if(wvEnventPro != null)
            wvEnventPro.sessionset(id, session);
    }

    //JSInterface2.session_get()  获取短期保存的文本
    @JavascriptInterface
    public void session_get(String id, String x1) {
        if(wvEnventPro != null)
            wvEnventPro.sessionget(id, x1);
    }

    //JSInterface2.copy_text() 复制
	@JavascriptInterface
    public void copy_text(String text) {
        if (wvEnventPro != null)
            wvEnventPro.copyText(text);
    }

    //JSInterface2.InforPass() 传递基础数据
    @JavascriptInterface
    public void Infor_Pass(String validcode) {
        if (wvEnventPro != null)
            wvEnventPro.InforPass(validcode);
    }

    //JSInterface2.User_Imag() 传递im头像
    @JavascriptInterface
    public void User_Imag(String path) {
        if (wvEnventPro != null)
            wvEnventPro.userimag(path);
    }

    @JavascriptInterface
    public void ShareInAndroid(String title, String text, String image_url, String share_url, String type) {
        if (wvEnventPro != null)
            wvEnventPro.shareInAndroid(title, text, image_url, share_url, type);
    }

}
