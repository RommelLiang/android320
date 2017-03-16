package com.tiemuyu.chuanchuan.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tiemuyu.chuanchuan.activity.fragment.MineFragment;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.AppManager;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import org.xutils.view.annotation.ViewInject;

/**
 * Created by CC2.0 on 2017/1/23.
 */

public class MyInviteCode extends BaseActivityG {

    @ViewInject(R.id.myinvitecode_text)
    private TextView myinvitecode_text;

    private Button nextstep;
    private String accid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.invite_code_layout);
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        accid = getIntent().getStringExtra("accid");
        //  Constant.VERSION = Version.getAppVersionName(this);
        // _global = GlobalVariable.getInstance()
        initProcess();
        nextstep = (Button) findViewById(R.id.nextstep);
        nextstep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });
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
    protected void initAppAccess() {}

    protected void initData() {
//        // TODO Auto-generated method stub
//        c_type = getIntent().getStringExtra(ClassJumpTool.DATA_PACKET_NAME);
//        if (c_type != null)
//            coming_type = c_type;
//        isCollection = false;
    }

    protected void initUI() {
        myinvitecode_text=(TextView) findViewById(R.id.myinvitecode_text);
        myinvitecode_text.setText(MineFragment.user.getInviteCode());
    }

    protected void initListener() {
        // TODO Auto-generated method stub
//        bt_login.setOnClickListener(this);
//        bt_back.setOnClickListener(this);
//        //todo   添加 注册按钮和注册跳转。

    }
    public void share()
    {
        Log.e("accid", "share: "+accid );
        new ShareAction(this).setDisplayList(
                SHARE_MEDIA.SINA,
                SHARE_MEDIA.WEIXIN,
                SHARE_MEDIA.WEIXIN_CIRCLE,
                SHARE_MEDIA.WEIXIN_FAVORITE,
                SHARE_MEDIA.SMS,
                SHARE_MEDIA.MORE)
                .withTitle("给你推荐一个神奇的APP")
                .withText("可以把你爱的衣服都做给你穿")
                .withTargetUrl("http://android.myappcc.com/cc/user/FriendShare?id="+accid)
                .withMedia(new UMImage(getApplicationContext(), "http://www.myappcc.com/main/img/index-log.png"))
                .setCallback(umShareListener)
                .open();
        System.out.println("ShareAction opened in mainactivity..  saveload..........");
    }
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            System.out.println("为什么啊为什么啊");
            com.umeng.socialize.utils.Log.d("plat", "platform" + platform);
            if(platform.name().equals("WEIXIN_FAVORITE")){
                Toast.makeText(getApplicationContext(), platform + " 收藏成功啦",Toast.LENGTH_SHORT).show();
                System.out.println("收藏成功啦@@@@@@@@@@@@@@@@@@@@@@@@@");
            }else{
                Toast.makeText(getApplicationContext(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
                System.out.println("分享成功啦@@@@@@@@@@@@@@@@@@");
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getApplicationContext(), platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            System.out.println("分享失败啦@@@@@@@@@@@@@@@@@@");
            if(t!=null){
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getApplicationContext(), platform + " 分享取消了", Toast.LENGTH_SHORT).show();
            System.out.println("分享取消了@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        }

    };
}
