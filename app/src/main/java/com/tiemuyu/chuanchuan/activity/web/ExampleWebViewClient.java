package com.tiemuyu.chuanchuan.activity.web;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tiemuyu.chuanchuan.activity.view.ImagePreviewActivity;
import com.tiemuyu.chuanchuan.activity.MainActivity;
import com.tiemuyu.chuanchuan.activity.InterfacePac.JsInterface;
import com.tiemuyu.chuanchuan.activity.util.ViewTools;

/**
 * Created by Lonze on 2016/8/23.
 */
public class ExampleWebViewClient extends WebViewClient {

    private Context context;
    private LinearLayout bottom;
    private RelativeLayout header;
    private ImageView im_setting;
    private LinearLayout search;
    private static ExampleWebViewClient client;
    private JsInterface JSInterface2 = new JsInterface();
    private String[] URL = {
            "http://test.myappcc.com/cc/home/index",
            "http://test.myappcc.com/cc/Login/Index",
            "http://test.myappcc.com/cc/Find/FindIndex",
            "http://test.myappcc.com/cc/user/mine"
    };

    private ExampleWebViewClient(Context context){
        this.context = context;
    }
    private ExampleWebViewClient(){
        super();
    }
    public static ExampleWebViewClient getInstance(Context context){
        if(client == null){
            client = new ExampleWebViewClient(context);
        }
        return client;
    }
    public static ExampleWebViewClient getInstance(){
        if(client == null){
            client = new ExampleWebViewClient();
        }
        return client;
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.proceed();
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (Build.VERSION.SDK_INT >= 19) {
            view.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        //view.getSettings().setBlockNetworkImage(false);
        // webView.addJavascriptInterface(new JavascriptInterface(), "Phoenix_Android");
        //view.getSettings().setJavaScriptEnabled(true);
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        JSInterface2.setWvClientClickListener(new webviewClick());
        System.out.println("**************加載完成"+url);
        MainActivity.isTopTask = ViewTools.isTopTask(URL,url);
        ViewTools.showSetting(URL,url,im_setting);
        System.out.println("**********isTopTask:::"+MainActivity.isTopTask);
        if(MainActivity.isTopTask){
            bottom.setVisibility(View.VISIBLE);

            System.out.println("******@@@url------"+url);
            if(url.equals(URL[0])){
                ViewTools.changeTitle(header,search,null);
            }
            else if(url.equals(URL[1])){
                ViewTools.changeTitle(header,search,"客服",false);
            }
            else if(url.equals(URL[2])){
                ViewTools.changeTitle(header,search,"发现",false);
            }
            else {
                ViewTools.changeTitle(header,search,"");
            }
        }else {
            bottom.setVisibility(View.GONE);
            ViewTools.changeTitle(header,search,"年后",true);
        }
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
        //view.loadUrl(url);
    }

    public void setHeader(RelativeLayout header,LinearLayout search,ImageView im_setting,LinearLayout bottom){
        this.header = header;
        this.search = search;
        this.im_setting = im_setting;
        this.bottom = bottom;
    }

    class webviewClick implements JsInterface.wvClientClickListener {

        @Override
        public void shareInAndroid(String title, String text, String image_url, String share_url, String type) {}

        @Override
        public void wvHasClickEnvent(String imgUrl) {
            // TODO Auto-generated method stub
            Toast.makeText(context, "图片的URL是：" + imgUrl, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, ImagePreviewActivity.class);
            intent.putExtra("imgUrl", imgUrl);
            context.startActivity(intent);

        }

        @Override
        public void testToast(String key){
            Toast.makeText(context, key, Toast.LENGTH_SHORT).show();
            System.out.println("##########################" + key);
        }

        public void AndroidToast(String toastText) {
            Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void photo(String funflag, int num, int type) {
        }

        @Override
        public void navigatorback(String key){}

        @Override
        public void navigatoropen(String key){
        }

        @Override
        public void ToastUsrPwd(String Usrname, String Password, String accid, String acctoken) {
        }

        @Override
        public void payfunction(String signString, long type ,String orderid) {
        }

        @Override
        public void headersettingtext(String text, String script){
        }
        @Override
        public void headerbackhide(){}

        @Override
        public void funcsetlogin(String userid, String accid, String acctoken){}
        @Override
        public void sessionset(String id,String session){
        }

        @Override
        public void sessionget(String id  ,String x1){
        }

 		@Override
        public void copyText(String text) {}

        @Override
        public void InforPass(String xx) {
        }

        @Override
        public void userimag(String path){

        }

    }
}
