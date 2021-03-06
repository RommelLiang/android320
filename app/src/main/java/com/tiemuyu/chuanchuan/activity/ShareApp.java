package com.tiemuyu.chuanchuan.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tiemuyu.chuanchuan.activity.bean.User;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.fragment.MineFragment;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.AppManager;
import com.tiemuyu.chuanchuan.activity.util.Utility;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import org.xutils.view.annotation.ViewInject;

/**
 * Created by Weihao Gao on 2017/2/4.
 */

public class ShareApp extends BaseActivityG {



    @ViewInject(R.id.shareapp_share)
    private Button shareapp_share;// 登录
    private ImageView goback;
    private User user;
    private TextView cc_coin_txt,cc_coin_val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shareapp);
        goback = (ImageView) findViewById(R.id.im_back);
        goback.setOnClickListener(this);

        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        //  Constant.VERSION = Version.getAppVersionName(this);
        // _global = GlobalVariable.getInstance();

//        init();
//        pd.show();
//
        user = DBTools.getUser();
        //user = MineFragment.user;
        Log.e("user", "onCreate: "+user.getNickName());
        cc_coin_txt = (TextView) findViewById(R.id.cc_coin_txt);
        cc_coin_val = (TextView) findViewById(R.id.cc_coin_val);
        cc_coin_txt.setText((user.getCcCoin())+"");
        cc_coin_val.setText((user.getAmounts() - user.getFrzAmounts())+"");
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

    protected void initData() {
//        // TODO Auto-generated method stub

    }

    protected void initUI() {


        shareapp_share=(Button) findViewById(R.id.shareapp_share);




    }

    protected void initListener() {
        // TODO Auto-generated method stub
        //todo   添加 注册按钮和注册跳转。
        shareapp_share.setOnClickListener(this);



    }



    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.shareapp_share:
                if (Utility.isFastDoubleClick()) // 连续点击
                    return;

                //todo 调用分享
                System.out.println("#####调用分享按钮");
                share();
                break;
            case R.id.im_back:
                finish();
                break;
        }
    }

    public void share()
    {
        String accid = MineFragment.user.getAccid();
        Log.e("accid", "share: " + accid );
        new ShareAction(this).setDisplayList(
                        SHARE_MEDIA.SINA,
                        SHARE_MEDIA.WEIXIN,
                        SHARE_MEDIA.WEIXIN_CIRCLE,
                        SHARE_MEDIA.WEIXIN_FAVORITE,
                        SHARE_MEDIA.SMS,
                        SHARE_MEDIA.MORE)
                .withTitle("买不到的衣服，做一件")
                .withText("60万时尚达人的小秘密！按图片做出任何衣服，更高性价比，更合意。")
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
                Toast.makeText(getApplicationContext(),  " 收藏成功啦",Toast.LENGTH_SHORT).show();
                System.out.println("收藏成功啦@@@@@@@@@@@@@@@@@@@@@@@@@");
            }else{
                Toast.makeText(getApplicationContext(), " 分享成功啦", Toast.LENGTH_SHORT).show();
                System.out.println("分享成功啦@@@@@@@@@@@@@@@@@@");
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getApplicationContext(), " 分享失败啦", Toast.LENGTH_SHORT).show();
            System.out.println("分享失败啦@@@@@@@@@@@@@@@@@@");
            if(t!=null){
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getApplicationContext(),  " 分享取消了", Toast.LENGTH_SHORT).show();
            System.out.println("分享取消了@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        }

    };
}
