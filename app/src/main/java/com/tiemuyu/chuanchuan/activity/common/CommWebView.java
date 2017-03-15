//package com.tiemuyu.chuanchuan.activity.common;
//
//import android.os.Bundle;
//import android.view.KeyEvent;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//
//import com.tiemuyu.chuanchuan.activity.R;
//import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivity;
//
//public class CommWebView extends BaseActivity {
//    private WebView webView;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_comm_web_view);
//        goBack();
//        setTitle(getIntent().getStringExtra("title"));
//        initView();
//    }
//
//    private void initView() {
//        String url = getIntent().getStringExtra("url");
//        webView = (WebView) findViewById(R.id.web_view);
//        //启用支持JavaScript
//        webView.getSettings().setJavaScriptEnabled(true);
//        //启用支持DOM Storage
//        webView.getSettings().setDomStorageEnabled(true);
//        //加载web资源
//        webView.loadUrl(url);
//        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
//    }
//
//    //改写物理按键的返回的逻辑
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        // TODO Auto-generated method stub
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (webView.canGoBack()) {
//                webView.goBack();//返回上一页面
//                return true;
//            } else {
//                finish();
//            }
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//}
