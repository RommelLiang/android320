package com.tiemuyu.chuanchuan.activity.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.auth.AuthService;
import com.tiemuyu.chuanchuan.activity.MainActivity;
import com.tiemuyu.chuanchuan.activity.R;
import com.umeng.analytics.MobclickAgent;

/**
 * 功能描述：引导页
 * Created by benny on 2016/5/7.
 * ProjectName：mlzMall.
 */
public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    //引导图片资源
    private int[] pics;
    private List<ImageView> imageList; // viewpager的数据
    private LinearLayout llPointGroup; // 点的组
    private View mSelectPointView; // 选中的点view对象
    private int basicWidth; // 点之间的宽度
    private Button btnStart; // 开始体验按钮
    private Button btnBypass;  // Bypass Button
    SharedPreferences setting;
    //史力：以下常量在clearWebViewCache中会被用到
    private static final String APP_CACAHE_DIRNAME = "/webcache";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.guide_layout);
        // 史力：以下读取UserInfo里面的信息
        setting = getSharedPreferences("UserInfo", Activity.MODE_PRIVATE);
        // 史力：以下为测试，仅仅把sp清空，看看cache能不能被清空、app能不能恢复到登陆前的状态
//        SharedPreferences.Editor editor = setting.edit();
//        editor.remove("accid");
//        editor.commit();
//        System.out.println("!!!!!!!!!accid after removed: " + setting.getString("accid", ""));
        // 史力：先把GuideActivity里面的accid和acctoken打印出来
        System.out.println("accid in GuideActivity: " + setting.getString("accid", ""));
        System.out.println("acctoken in GuideActivity: " + setting.getString("acctoken", ""));
        System.out.println("!!!!!!!!!!!!!!! brand is " + Build.BRAND);
        // 史力 高伟豪：判断如果logincache为空，那么清除所有的缓存和sp
        if (setting.getString("logincache", "").equals("")){
            System.out.println("!!!!!!!!!!!!!开始进行cache clear!!!!!!!!!!!!!");
            clearcache();
            //NIMClient.getService(AuthService.class).logout();// 高伟豪：注释掉该句话
        }
        setting = getSharedPreferences("aaaa", 0);
        Boolean user_first = setting.getBoolean("FIRST",true);
        if(user_first) {//第一次
            setting.edit().putBoolean("FIRST", false).commit();
            //Toast.makeText(GuideActivity.this, "第一次", Toast.LENGTH_LONG).show();
            pics = new int[]{
                    R.drawable.ic_launcher,
                    R.drawable.bg1new,
                    R.drawable.bg2new,
                    R.drawable.bg3new,
                    R.drawable.bg4new,
                    R.drawable.bg5new};
        }else{
            //Toast.makeText(GuideActivity.this, "不是第一次", Toast.LENGTH_LONG).show();
            pics = new int[] {R.drawable.ic_launcher, R.drawable.launch2};//史力：注意此处必须要放两张图片以上
        }
        initView();
    }


    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }



    /**
     * 初始化控件
     */
    private void initView() {
        ViewPager mViewPager = (ViewPager) findViewById(R.id.vp_guide);
        btnStart = (Button) findViewById(R.id.btn_guide_start_experience);
        btnBypass = (Button) findViewById(R.id.btn_bypass);
        llPointGroup = (LinearLayout) findViewById(R.id.ll_guide_point_group);
        mSelectPointView = findViewById(R.id.select_point);

        initData();
        GuideAdapter mAdapter = new GuideAdapter(GuideActivity.this,imageList);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(this);
        btnStart.setOnClickListener(this);
        btnBypass.setOnClickListener(this);
        // view绘制流程: measure -> layout -> draw
        // 监听mSelectPointView控件layout
        // 获得视图树的观察者, 监听全部布局的回调
        mSelectPointView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // 只执行一次, 把当前的事件从视图树的观察者中移除掉
                mSelectPointView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                // 取出两个点之间的宽度
                basicWidth = llPointGroup.getChildAt(1).getLeft() - llPointGroup.getChildAt(0).getLeft();
                //System.out.println("点之间的宽度: " + basicWidth);
            }
        });
        // Show bypass button
        btnBypass.setVisibility(View.GONE);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        // ctrl + 2 松手停顿 L
        imageList = new ArrayList<ImageView>();
        ImageView iv;
        View view;
        LinearLayout.LayoutParams params;
        for (int i = 0; i < pics.length; i++) {
            iv = new ImageView(this);
            iv.setBackgroundResource(pics[i]);
            imageList.add(iv);
            // 根据图片的个数, 每循环一次向LinearLayout中添加一个点
            view = new View(this);
            view.setBackgroundResource(R.drawable.point_select);
            params = new LinearLayout.LayoutParams(10, 10);
            params.rightMargin = 5;
            params.topMargin = 5;
            params.bottomMargin = 5;
            if (i != 0) {
                params.leftMargin = 20;
            } else
                params.leftMargin = 5;
            view.setLayoutParams(params);
            llPointGroup.addView(view);
        }
    }

    /**
     * 当页面正在滚动时
     * position 当前选中的是哪个页面
     * positionOffset 比例
     * positionOffsetPixels 偏移像素
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int leftMargin = (int) (basicWidth * (position + positionOffset));
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mSelectPointView.getLayoutParams();
        params.leftMargin = leftMargin;
        mSelectPointView.setLayoutParams(params);
    }

    /**
     * 当页面被选中
     */
    @Override
    public void onPageSelected(int position) {
////        注释掉了“立即体验”按钮
//        if (position == imageList.size() - 1) {
//            btnStart.setVisibility(View.VISIBLE);
//        } else {
//            btnStart.setVisibility(View.GONE);
//        }
        //史力：以下判断如果是第一次打开程序且不是最后一张图片，则隐藏取消按钮
        if (position == imageList.size() - 1)
            btnBypass.setVisibility(View.VISIBLE);
        else
            btnBypass.setVisibility(View.GONE);
    }

    /**
     * 当页面滚动状态改变
     */
    @Override
    public void onPageScrollStateChanged(int state) {
        System.out.println("onPageScrollStateChanged() state: " + state);
    }

    @Override
    public void onClick(View v) {
        // 打开主页面
        startActivity(new Intent(this, MainActivity.class));
        GuideActivity.this.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        finish();
    }

    // 以下函数用于清除cache
    public void clearcache()
    {
        clearCacheFolder(this.getCacheDir(), System.currentTimeMillis());
        clearCookies(this);
        clearWebViewCache();
        SharedPreferences sp = getSharedPreferences("UserInfo", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("logincache");
        editor.remove("accid");
        editor.remove("acctoken");
        editor.commit();
        System.out.println("中间setting");
    }

    // 以下函数用于清除缓存文件路径
    private int clearCacheFolder(File dir, long numDays) {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@进入clearcache");
        int deletedFiles = 0;
        if (dir!= null && dir.isDirectory()) {
            try {
                for (File child:dir.listFiles()) {
                    if (child.isDirectory()) {
                        deletedFiles += clearCacheFolder(child, numDays);
                    }
                    if (child.lastModified() < numDays) {
                        if (child.delete()) {
                            deletedFiles++;
                        }
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return deletedFiles;
    }

    // 以下函数用于清楚coockie
    public static void clearCookies(Context context) {
        // Edge case: an illegal state exception is thrown if an instance of
        // CookieSyncManager has not be created.  CookieSyncManager is normally
        // created by a WebKit view, but this might happen if you start the
        // app, restore saved state, and click logout before running a UI
        // dialog in a WebView -- in which case the app crashes
        //@SuppressWarnings("unused")
//        CookieSyncManager cookieSyncMngr =
//                CookieSyncManager.createInstance(context);
//        CookieManager cookieManager = CookieManager.getInstance();
//        cookieManager.removeAllCookie();
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }

    /**
     * 清除WebView缓存
     */
    public void clearWebViewCache(){
        //清理Webview缓存数据库
        try {
            deleteDatabase("webview.db");
            deleteDatabase("webviewCache.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //WebView 缓存文件
        File appCacheDir = new File(getFilesDir().getAbsolutePath()+APP_CACAHE_DIRNAME);
        // Log.e(TAG, "appCacheDir path="+appCacheDir.getAbsolutePath());
        File webviewCacheDir = new File(getCacheDir().getAbsolutePath()+"/webviewCache");
        //Log.e(TAG, "webviewCacheDir path="+webviewCacheDir.getAbsolutePath());
        //删除webview 缓存目录
        if(webviewCacheDir.exists()){
            deleteFile(webviewCacheDir);
        }
        //删除webview 缓存 缓存目录
        if(appCacheDir.exists()){
            deleteFile(appCacheDir);
        }
    }

    /**
     * 递归删除 文件/文件夹
     *
     * @param file
     */
    public void deleteFile(File file) {
        // Log.i(TAG, "delete file path=" + file.getAbsolutePath());
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            file.delete();
        } else {
            //Log.e(TAG, "delete file no exists " + file.getAbsolutePath());
        }
    }

}