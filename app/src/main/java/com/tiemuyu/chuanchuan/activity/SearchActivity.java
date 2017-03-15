package com.tiemuyu.chuanchuan.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tiemuyu.chuanchuan.activity.InterfacePac.JsInterface;
import com.tiemuyu.chuanchuan.activity.fragment.MineFragment;
import com.tiemuyu.chuanchuan.activity.view.ImagePreviewActivity;
import com.tiemuyu.chuanchuan.activity.view.URL;

import com.tiemuyu.chuanchuan.activity.util.NetworkUtil;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2016/8/4.
 * 用于聊天的窗口
 */
public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    //10-26 gao更改了这里

    private EditText ed_search;
    private WebView search_webView;
    private ImageView goBack;
    private TextView search, tv_title;
    private RelativeLayout ly_search;
    //必不可少
    private JsInterface JSInterface2 = new JsInterface();
    private String orderxx;
    public String lasturl;
    private ProgressDialog pd;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ly_search = (RelativeLayout) findViewById(R.id.ly_search);
        ed_search = (EditText) findViewById(R.id.ed_search);
        search = (TextView) findViewById(R.id.search_btn);
        search_webView = (WebView) findViewById(R.id.search_webView);
        tv_title = (TextView) findViewById(R.id.tv_title);
        search_webView.getSettings().setBlockNetworkImage(false);
        search_webView.getSettings().setJavaScriptEnabled(true);
        if (NetworkUtil.isConnected(this))
            search_webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        else
            search_webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        search_webView.getSettings().setDomStorageEnabled(true);
        search_webView.getSettings().setDatabaseEnabled(true);
        search_webView.getSettings().setAppCacheEnabled(true);

        search_webView.setWebViewClient(new HttpWebViewClient());
        goBack = (ImageView) findViewById(R.id.goBack);
        search.setOnClickListener(this);
        goBack.setOnClickListener(this);
        //这句必不可少
        search_webView.addJavascriptInterface(JSInterface2, "JSInterface2");
        sp = this.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        ed_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //TODO 这里做"回车"响应处理
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        search();

                        break;
                }
                return false;
            }
        });
        init();
    }

    private void init(){
        pd = new ProgressDialog(this);//加载的ProgressDialog
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);//选择加载风格 这里是圆圈 STYLE_HORIZONTAL 是水平进度条
        pd.setMessage("加载中....");
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goBack:
                if (search_webView.canGoBack() && search_webView.isClickable()) {
                    search_webView.goBack();
                } else
                    finish();
                break;
            case R.id.search:
                search();

                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (search_webView.canGoBack() && search_webView.isClickable()) {
                search_webView.goBack();
                return true;
            }else
                finish();
        }
        return false;


    }

    class HttpWebViewClient extends WebViewClient {

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            pd.show();

        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            JSInterface2.setWvClientClickListener(new webviewClick());

            if (url.contains("squareshow")) {
                tv_title.setVisibility(View.GONE);
                search.setVisibility(View.VISIBLE);
                ly_search.setVisibility(View.VISIBLE);
            } else {
                search.setVisibility(View.GONE);
                ly_search.setVisibility(View.GONE);
                tv_title.setVisibility(View.VISIBLE);
            }
            super.onPageFinished(view, url);
            pd.dismiss();//对话框消失 显示页面

        }
    }

    //重新搞一下。不用post了。直接搞了在app解析了然后判断。
    private void pay(final String payInfo, String orderid) {
        orderxx = orderid;
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(SearchActivity.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);
                //把result 按照；分开然后变成
                String[] resultlist = result.split(";");
                System.out.println("==============resultlist=============================="+resultlist[2]);
                String[] xxx=resultlist[0].split("=");
                String xxxafter=xxx[1].substring(1, xxx[1].length()-1);
                System.out.println("==============================xxxafter=============="+xxxafter);
                Message msg = new Message();
                if (xxxafter.equals("9000"))
                {
                    msg.what =1;
                }
                else          if(xxxafter.equals("4000")||xxxafter.equals("6002"))       msg.what =2;
                else msg.what=3;
                msg.obj =result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {
                    Toast.makeText(getApplicationContext(), "支付成功!", Toast.LENGTH_SHORT).show();
                    System.out.println("android.myappcc.com/cc/customize/buyerbodydata?id="+orderxx);
                    search_webView.post(new Runnable() {
                        @Override
                        public void run() {
                            search_webView.loadUrl("http://android.myappcc.com/cc/customize/buyerbodydata?id="+orderxx);
                        }
                    });
                    break;
                }
                case 2: {
                    Toast.makeText(getApplicationContext(), "支付错误请重试!", Toast.LENGTH_SHORT).show();
                    break;
                }
                case 3: {
                    Toast.makeText(getApplicationContext(), "支付退出请重试!", Toast.LENGTH_SHORT).show();
                    break;
                }
                default:
                    break;
            }
        }
    };

    class webviewClick implements JsInterface.wvClientClickListener {
        /*以下函数用来调用ImagePreviewActivity从而显示图片的大图*/
        @Override
        public void wvHasClickEnvent(String imgUrl) {
            // TODO Auto-generated method stub
            //Toast.makeText(getActivity(), "图片的URL是：" + imgUrl, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), ImagePreviewActivity.class);
            intent.putExtra("imgUrl", imgUrl);
            startActivityForResult(intent, 100);
        }

        //接受一个字符串参数toastText然后toast到手机屏幕上
        @Override
        public void AndroidToast(String toastText) {
            //Toast.makeText(getActivity(), toastText, Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_SHORT).show();
            System.out.println("AndroidToast##########################" + toastText);
        }

        @Override
        public void testToast(String key) {
            Toast.makeText(getApplicationContext(), key, Toast.LENGTH_SHORT).show();
            System.out.println("TEST##########################" + key);
        }

        //拍照相关
        @Override
        public void photo(String funflag, int num, int type) {
        }

        @Override
        public void navigatorback(String key) {
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^进入navigatorback" + key);
            if(key.equals("finish"))
            {
                System.out.println("one");
                search_webView.post(new Runnable() {
                    @Override
                    public void run() {
                        MineFragment.isFirst = true;
                        //search_webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                        search_webView.loadUrl(URL.UrlMine);
                    }
                });
            }
            else {
                System.out.println("two");
                search_webView.post(new Runnable() {
                    @Override
                    public void run() {
                        MineFragment.isFirst = true;
                        //search_webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                        search_webView.goBack();
                    }
                });
            }
        }

        @Override
        public void navigatoropen(String url) {
            //判断是否登陆
            System.out.println("**************进入navigatoropen" + url);
            System.out.println("**************调用" + url);
            System.out.println("**************当前url:" + lasturl);
            if (url.substring(0, 1).equals("/")) {
                final String newurl1 = URL.BASE + url;
                System.out.println("分支一**************含有/则Base相加" + newurl1);
                search_webView.post(new Runnable() {
                    @Override
                    public void run() {
                        search_webView.loadUrl(newurl1);
                    }
                });
            } else {
                System.out.println("分支二**************不含/则现有相加" + url);
                //http://test.myappcc.com/cc/user/mine
                String[] temp = null;
                temp = lasturl.split("/");
                System.out.println("分支二**************不含/则现有相加" + temp[0] + "temp0" + temp[1] + "temp1" + temp[2] + "temp2" + temp[3] + "temp3");
                final String newurl2 = temp[0] + "/" + temp[1] + "/" + temp[2] + "/" + temp[3] + "/" + temp[4] + "/" + url;
                System.out.println("分支二**************不含/则现有相加" + newurl2);
                search_webView.post(new Runnable() {
                    @Override
                    public void run() {
                        search_webView.loadUrl(newurl2);
                    }
                });
            }
        }

        /// <summary>
        /// 支付
        /// </summary>
        /// <param name="signString"></param>对应了order string
        /// <param name="type"></param>默认为2 为阿里支付
        /// <param name="scheme"></param>
        /// <param name="completeHandler"></param>
        @Override
        public void payfunction(String signString, long type, String orderid) {
            System.out.println("**********************payfunction orderid" + orderid);
            //String scheme = "cc2";//无用
            final String payinfo = signString;
            pay(payinfo, orderid);
        }

        @Override
        public void shareInAndroid(String title, String text, String image_url, String share_url, String type) {}

        @Override
        public void ToastUsrPwd(String logincache, String savestr, String accid, String acctoken) {//再加上acc
            System.out.println(sp.getString(logincache, ""));
            System.out.println("accid right after ToastUsrPwd:" + accid);
            System.out.println("acctoken right after ToastUsrPwd: " + acctoken);

            if(accid.equals("wrong")||accid.equals("noneed")) {System.out.println("777");}
            else {
                System.out.println("7997");
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(logincache, savestr);      //logincache字符串对应了savestr这个键值对，都存储在了UserInfo.xml这个文件里面
                editor.commit();
                //史力：以下用来检测logincache键值对、accid、acctoken的值,如果accid和acctoken都为空，则都写死成gao的accid和acctoken
                if (accid.equals("") && acctoken.equals("")) {
                    System.out.println("accid and acctoken are empty! ");
                    return;
                }
                //史力：测试accid和acctoken是否取出
                System.out.println(sp.getString(logincache, ""));
                System.out.println("accid:" + accid);
                System.out.println("acctoken: " + acctoken);
                //史力：将accid和acctoken都写入到sp中
                editor.putString("accid", accid).commit();
                editor.putString("acctoken", acctoken).commit();
                //史力：通过println测试accid和acctoken是否被成功地写入到了sp中去
                System.out.println("accid in SP: " + sp.getString("accid", ""));
                System.out.println("acctoken in SP: " + sp.getString("acctoken", ""));
                System.out.println("username after editor.putString: " + sp.getString(logincache, ""));
            }
        }

        @Override
        public void headersettingtext(String text, String script) {}

        //无用，不可删
        @Override
        public void headerbackhide(){}

        //无用，不可删
        @Override
        public void funcsetlogin(String userid, String accid, String acctoken){}

        @Override
        public void sessionset(String id,String session){}

        @Override
        public void sessionget(String id  ,String x1){}

        @Override
        public void copyText(String text) {}

        @Override
        public void InforPass(String validcode) {}

        @Override
        public void userimag(String path){}

    }

    private void search() {

        if ("".equals(ed_search.getText().toString().trim())) {
            Toast.makeText(SearchActivity.this, "不能发送空字符串", Toast.LENGTH_LONG).show();
            return;
        }
        String key = ed_search.getText().toString().trim();
        String UrlSearch = URL.UrlHomeSearch(key);
        search_webView.loadUrl(UrlSearch);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(ed_search.getWindowToken(), 0);
    }
}




