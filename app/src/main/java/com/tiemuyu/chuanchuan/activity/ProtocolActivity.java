package com.tiemuyu.chuanchuan.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tiemuyu.chuanchuan.activity.fragment.MineFragment;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

public class ProtocolActivity extends BaseActivityG {

    private WebView webView;
    private TextView tv_head;
    private ImageView im_share;
    private String url;
    private String title;
    private String img_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocol);
        webView = (WebView) findViewById(R.id.web_view);
        tv_head = (TextView) findViewById(R.id.tv_head);
        im_share = (ImageView) findViewById(R.id.im_share);
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        img_url = getIntent().getStringExtra("img_url");
        Log.e("ProtocolActivity", "onCreate: "+url);
        int type = getIntent().getIntExtra("type", 0);
        if (type == 0) {
            im_share.setVisibility(View.GONE);
        }
        tv_head.setText(title);
        webView.loadUrl(url);
        //webView.getSettings().setJavaScriptEnabled(true);
        im_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });
        findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void share() {
        new ShareAction(this).setDisplayList(
                SHARE_MEDIA.SINA,
                SHARE_MEDIA.WEIXIN,
                SHARE_MEDIA.WEIXIN_CIRCLE,
                SHARE_MEDIA.WEIXIN_FAVORITE,
                SHARE_MEDIA.SMS,
                SHARE_MEDIA.MORE)
                .withTitle(title)
                .withText(title)
                .withTargetUrl(url)
                .withMedia(new UMImage(getApplicationContext(), img_url))
                .setCallback(umShareListener)
                .open();
        System.out.println("ShareAction opened in mainactivity..  saveload..........");
    }

    private UMShareListener umShareListener = new UMShareListener() {

        @Override
        public void onResult(SHARE_MEDIA platform) {
            com.umeng.socialize.utils.Log.d("plat", "platform" + platform);
            if (platform.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(getApplicationContext(), platform + "收藏成功啦", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getApplicationContext(), platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getApplicationContext(), platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }

    };
}
