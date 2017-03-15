package com.tiemuyu.chuanchuan.activity;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import org.xutils.x;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.fm.openinstall.OpenInstall;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.msg.MessageNotifierCustomization;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.uinfo.UserInfoProvider;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

import com.tiemuyu.chuanchuan.activity.chat_tools.NimTaskExecutor;
import com.tiemuyu.chuanchuan.activity.chat_tools.activity.MessageActivity;
import com.tiemuyu.chuanchuan.activity.chat_tools.fragment.TextMessageActivity;
import com.tiemuyu.chuanchuan.activity.chat_tools.utils.StorageUtil;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolManager;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * describe :
 * Created by Pm on 2016/6/7.
 * PackageName com.guanjia800.clientApp.myapplication.
 * ProjectName HouseKeeper_android.
 */

public class MyApplication extends Application {
    public static MyApplication mInstance;
    DisplayImageOptions defaultOptions;
    private static Context context;
    public static ThreadPoolManager poolManager;//线程池






    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        context = this;
        //网络
        //图片
        ImageLoading();
        MyApplication.context = this;
        NIMClient.init(this, loginInfo(), options());
        initUIKit();
        requestQueue = Volley.newRequestQueue(this.getApplicationContext());
        UMShareAPI.get(this);
        // ImageLoading();
        OpenInstall.init(this, "x40wn7");
        OpenInstall.init(this);
        OpenInstall.setDebug(true);
        init();

    }



    private void init() {
        x.Ext.init(this);
//        x.Ext.setDebug(true);
//
//        CookieSyncManager.createInstance(this);
//        //LogHelper.e("---MyApplication-onCreate:");
//
//        // 注册App异常崩溃处理器
//        handlerException = HandlerException.getInstance();
//        handlerException.init(getApplicationContext());
//
//        //初始化imageloder
//        ImageLoaderConfig.initImageLoader(this,
//                Constant.BASE_IMAGE_IMAGELODE_CACHE);

//		UrlManager.BASEURL = PreferenceUtils.getPrefString(getApplicationContext(),
//				Constant.BASE_URL_NAME, UrlManager.DEFAULT_BASEURL);
//		UrlManager.DOMAIN = PreferenceUtils.getPrefString(getApplicationContext(),
//				Constant.BASE_DOMAIN_NAME, UrlManager.DEFAULT_DOMAIN);
//		UrlManager.HJ_NAME = PreferenceUtils.getPrefString(getApplicationContext(),
//				Constant.BASE_HJ_NAME, UrlManager.DEFAULT_HJ_NAME);
//
//		LogHelper.d("----2222>"+UrlManager.BASEURL);
//		LogHelper.d("----3333>"+PreferenceUtils.getPrefString(getApplicationContext(),
//				Constant.BASE_URL_NAME, "空地址"));
//        mChannel=getUMENG_CHANNEL();
//        getImSettingInfo();
//        ConnectionUtil.DbOffer(getApplicationContext(), new Runnable() {
//
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//                FileUtil.creatSDDir(Constant.BASE_PATH);
//                FileUtil.creatSDDir(Constant.BASE_IMAGE_CACHE);
//                FileUtil.creatSDDir(Constant.BASE_IMAGE_NEW);
//                FileUtil.creatSDDir(Constant.BASE_IMAGE_DOWNLOAD);
//                FileUtil.creatSDDir(Constant.BASE_IMAGE_U3D);
//
//                //LogHelper.d("----创建目录");
//            }
//        });

        /** 开启请求线程池 */
        poolManager=new ThreadPoolManager(ThreadPoolManager.TYPE_FIFO, 8);
        poolManager.start();
    }












    {
        // 史力：分别设置微信和微博分享的参数
        PlatformConfig.setWeixin("wxbe4f4acfeb2d68e6", "3e074e554e158e2147254046ee45eab0");
        PlatformConfig.setSinaWeibo("144500884", "0299f6ecae99654accf30de4b3c877ac");
    }

    public static MyApplication getInstace() {
        if (mInstance == null) {
            return new MyApplication();
        }
        return mInstance;
    }

    public static Context getContext() {
        return context;
    }
    /**
     * 图片加载
     */
    private void ImageLoading() {
        System.out.println("!!!!!!!!is ImageLoading executed?!!!!!!!");
        //设置缓存目录
        File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "imageloader/Cache");
        //图片缓存初始化
        defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                .defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .memoryCache(new WeakMemoryCache())
                .memoryCacheSize(2 * 1024 * 1024) //缓存到内存的最大数据
                .discCacheSize(50 * 1024 * 1024)
                .memoryCacheExtraOptions(480, 800)
                .discCacheFileCount(1000)//缓存到文件的最大数据
                .discCache(new UnlimitedDiscCache(cacheDir))
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config);
    }

    // 如果返回值为 null，则全部使用默认参数。
    private SDKOptions options() {
        SDKOptions options = new SDKOptions();

        // 如果将新消息通知提醒托管给 SDK 完成，需要添加以下配置。否则无需设置。
        StatusBarNotificationConfig config = new StatusBarNotificationConfig();
        config.notificationEntrance = MainActivity.class; // 点击通知栏跳转到该Activity
        config.notificationSmallIconId = R.mipmap.icon;
        // 呼吸灯配置
        config.ledARGB = Color.GREEN;
        config.ledOnMs = 1000;
        config.ledOffMs = 1500;
        config.ring = true;
        config.vibrate = false;
        // 通知铃声的uri字符串
        config.notificationSound = "android.resource://com.tiemuyu.chuanchuan.activity/raw/msg";
        options.statusBarNotificationConfig = config;

        // 配置保存图片，文件，log 等数据的目录
        // 如果 options 中没有设置这个值，SDK 会使用下面代码示例中的位置作为 SDK 的数据目录。
        // 该目录目前包含 log, file, image, audio, video, thumb 这6个目录。
        // 如果第三方 APP 需要缓存清理功能， 清理这个目录下面个子目录的内容即可。
        String sdkPath = Environment.getExternalStorageDirectory() + "/" + getPackageName() + "/nim";
        options.sdkStorageRootPath = sdkPath;

        // 配置是否需要预下载附件缩略图，默认为 true
        options.preloadAttach = true;

        // 配置附件缩略图的尺寸大小。表示向服务器请求缩略图文件的大小
        // 该值一般应根据屏幕尺寸来确定， 默认值为 Screen.width / 2
        options.thumbnailSize = 480 / 2;

        options.messageNotifierCustomization = messageNotifierCustomization;

        // 用户资料提供者, 目前主要用于提供用户资料，用于新消息通知栏中显示消息来源的头像和昵称
        options.userInfoProvider = new UserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String account) {
                return null;
            }

            @Override
            public int getDefaultIconResId() {
                return R.mipmap.ic_launcher;
            }

            @Override
            public Bitmap getTeamIcon(String tid) {
                return null;
            }

            @Override
            public Bitmap getAvatarForMessageNotifier(String account) {
                return null;
            }

            @Override
            public String getDisplayNameForMessageNotifier(String account, String sessionId,
                                                           SessionTypeEnum sessionType) {
                return null;
            }
        };
        return options;
    }

    private MessageNotifierCustomization messageNotifierCustomization = new MessageNotifierCustomization() {
        @Override
        public String makeNotifyContent(String nick, IMMessage message) {
            return null; // 采用SDK默认文案
        }

        @Override
        public String makeTicker(String nick, IMMessage message) {
            return null; // 采用SDK默认文案
        }
    };

    private void initUIKit() {
        StorageUtil.init(context, null);
    }

    public RequestQueue requestQueue;
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
        }
        return this.requestQueue;
    }

    // 如果已经存在用户登录信息，返回LoginInfo，否则返回null即可
    private LoginInfo loginInfo() {
        return null;
    }
}
