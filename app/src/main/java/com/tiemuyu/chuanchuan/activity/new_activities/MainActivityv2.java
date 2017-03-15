package com.tiemuyu.chuanchuan.activity.new_activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;



        //.lidroid.xutils.BitmapUtils;


import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.adapter.BannerPagerAdapter;
import com.tiemuyu.chuanchuan.activity.adapter.HorizontalScrollViewAdapter;
//import com.tiemuyu.chuanchuan.activity.common.CommWebView;
import com.tiemuyu.chuanchuan.activity.entity.BannerInfo;
import com.tiemuyu.chuanchuan.activity.view.MyHorizontalScrollView;

import org.xutils.x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @项目名： android1128
 * @包名： com.tiemuyu.chuanchuan.activity.new_activities
 * @类描述：
 * @创建人： hr
 * @创建时间： 2016/12/21
 * @version：
 */

public class MainActivityv2 extends Activity {

    //以下两个为下拉刷新所需
    TextView tv;
    SwipeRefreshLayout swipeRefreshLayout;

    //以下为轮播器所需
    private ViewPager vpBanner;
    private RadioGroup rgBanner;
    private Handler handler;
    private Runnable runnable;
    private BannerPagerAdapter bannerPagerAdapter;
    private ArrayList<BannerInfo> alBannerInfo;
    private int page = 1, positionNow;
    private static final int SIZE = 4;//轮播图数量
    private static final int DELAY_TIME = 3000;//轮播图片切换间隔时间（单位毫秒）

    //以下是图片滚动所需：
    private MyHorizontalScrollView mHorizontalScrollView;
    private HorizontalScrollViewAdapter mAdapter;
    private List<Integer> mDatas = new ArrayList<Integer>(Arrays.asList(
            R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d,
            R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h,
            R.drawable.l));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_mainv2);
        //以下是下拉刷新
        //tv = (TextView)findViewById(R.id.scrollTextView1);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light
        );
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                //tv.setText("正在刷新");
                // TODO Auto-generated method stub
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        //tv.setText("刷新完成");
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 6000);
            }
        });
        //以下是图片左右滚动所需：
        mHorizontalScrollView = (MyHorizontalScrollView) findViewById(R.id.id_horizontalScrollView);
//        mAdapter = new HorizontalScrollViewAdapter(this, mDatas);
        //添加滚动回调
        mHorizontalScrollView.setCurrentImageChangeListener(new MyHorizontalScrollView.CurrentImageChangeListener()
        {
            @Override
            public void onCurrentImgChanged(int position, View viewIndicator)
            {
                viewIndicator.setBackgroundColor(Color
                        .parseColor("#AA024DA4"));
            }
        });
        //添加点击回调
        mHorizontalScrollView.setOnItemClickListener(new MyHorizontalScrollView.OnItemClickListener()
        {

            @Override
            public void onClick(View view, int position)
            {
                view.setBackgroundColor(Color.parseColor("#AA024DA4"));
            }
        });
        //设置适配器
        mHorizontalScrollView.initDatas(mAdapter);
        //以上是下拉刷新
        setTitle("BannerDemo");
        initView();
    }

    private void initView(){
        initData();
        vpBanner=(ViewPager) findViewById(R.id.vp_banner);
        rgBanner=(RadioGroup) findViewById(R.id.radio_group);
        //BitmapUtils bitmapUtils=new BitmapUtils(this);
        ArrayList<View> alView=new ArrayList<>();
        ImageView[] imageViews=new ImageView[SIZE + 2];
        for(int i = 0;i<SIZE + 2;i++) {
            imageViews[i]=new ImageView(this);
            imageViews[i].setScaleType(ImageView.ScaleType.FIT_XY);
            if(i==0){
                x.image().bind(imageViews[i],alBannerInfo.get(SIZE-1).url_pic);

//                bitmapUtils.display(imageViews[i],alBannerInfo.get(SIZE-1).url_pic);
            }else if (i==SIZE+1) {
                x.image().bind(imageViews[i],alBannerInfo.get(0).url_pic);

//                bitmapUtils.display(imageViews[i],alBannerInfo.get(0).url_pic);
            }else if (i>0&&i<=SIZE) {
                x.image().bind(imageViews[i],alBannerInfo.get(i-1).url_pic);

//                bitmapUtils.display(imageViews[i],alBannerInfo.get(i-1).url_pic);
                imageViews[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent(MainActivityv2.this, CommWebView.class);
//                        intent.putExtra("title",alBannerInfo.get(positionNow).title);
//                        intent.putExtra("url",alBannerInfo.get(positionNow).url_content);
//                        startActivity(intent);
                    }
                });
            }
            alView.add(imageViews[i]);
        }

        bannerPagerAdapter=new BannerPagerAdapter(alView);
        //ViewPager设置适配器
        vpBanner.setAdapter(bannerPagerAdapter);
        //ViewPager显示第一个Fragment
        vpBanner.setCurrentItem(1);
        //ViewPager页面切换监听
        vpBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == SIZE+1) {
                    vpBanner.setCurrentItem(1, false);
                } else if (position == 0 && positionOffsetPixels == 0) {
                    vpBanner.setCurrentItem(SIZE, false);
                }
            }

            @Override
            public void onPageSelected(int position) {
                positionNow=position-1;
                switch (position) {
                    case 1:
                        rgBanner.check(R.id.rb_one);
                        page = 1;
                        break;
                    case 2:
                        rgBanner.check(R.id.rb_two);
                        page = 2;
                        break;
                    case 3:
                        rgBanner.check(R.id.rb_three);
                        page = 3;
                        break;
                    case 4:
                        rgBanner.check(R.id.rb_four);
                        page = 4;
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData(){
        alBannerInfo = new ArrayList<>();
        alBannerInfo.add(new BannerInfo("世界“造型最酷”海岛",
                "https://ss1.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/super/whfpf%3D425%2C260%2C50/sign=782de3003fdbb6fd250eb6666f199f2a/9c16fdfaaf51f3de9d78ee9f92eef01f3b297906.jpg",
                "http://bbs.miercn.com/bd/201511/thread_574672_1.html?source=bdxsy"));
        alBannerInfo.add(new BannerInfo("4岁双胞胎女孩潮气十足爆红网络",
                "https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/super/whfpf%3D425%2C260%2C50/sign=89a91a31848ba61edfbb9b6f2709a338/b3119313b07eca805da4251c972397dda04483a3.jpg",
                "http://china.ynet.com/3.1/1511/05/10506176.html?source=bdxsy"));
        alBannerInfo.add(new BannerInfo("“澳洲树蛙”头顶小蜗牛似法式贝雷帽",
                "https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/super/whfpf%3D425%2C260%2C50/sign=d06c29404110b912bf94a5bea5c0c831/00e93901213fb80e66910f2c30d12f2eb838948c.jpg",
                "http://china.ynet.com/3.1/1511/05/10507328.html?source=bdxsy"));
        alBannerInfo.add(new BannerInfo("南非插画师绘掌上3D世界 栩栩如生",
                "https://ss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/super/whfpf%3D425%2C260%2C50/sign=9898e7dc793e6709be5516bf5dfaab0f/d1a20cf431adcbef491765fdaaaf2edda3cc9f2d.jpg",
                "http://china.ynet.com/3.1/1511/05/10506306.html?source=bdxsy"));
    }

    @Override
    protected void onResume(){
        super.onResume();
        //开始轮播
        startBanner();
    }

    private void startBanner(){
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(runnable, DELAY_TIME);
                vpBanner.setCurrentItem(page, true);
                if(page==SIZE+1) {
                    page=0;
                }
                page++;
            }
        };
        handler.postDelayed(runnable, DELAY_TIME);
    }

    @Override
    protected void onStop(){
        super.onStop();
        //暂停轮播
        handler.removeCallbacks(runnable);
    }

}






















//package com.tiemuyu.chuanchuan.activity.new_activities;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.v4.view.ViewPager;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//
//import com.lidroid.xutils.BitmapUtils;
//import com.tiemuyu.chuanchuan.activity.R;
//import com.tiemuyu.chuanchuan.activity.adapter.BannerPagerAdapter;
//import com.tiemuyu.chuanchuan.activity.adapter.HorizontalScrollViewAdapter;
//import com.tiemuyu.chuanchuan.activity.common.CommWebView;
//import com.tiemuyu.chuanchuan.activity.entity.BannerInfo;
//import com.tiemuyu.chuanchuan.activity.view.MyHorizontalScrollView;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @项目名： android1128
// * @包名： com.tiemuyu.chuanchuan.activity.new_activities
// * @类描述：
// * @创建人： hr
// * @创建时间： 2016/12/21
// * @version：
// */
//
//public class MainActivityv2 extends Activity {
//
//    //以下两个为下拉刷新所需
//    TextView tv;
//    SwipeRefreshLayout swipeRefreshLayout;
//
//    //以下为轮播器所需
//    private ViewPager vpBanner;
//    private RadioGroup rgBanner;
//    private Handler handler;
//    private Runnable runnable;
//    private BannerPagerAdapter bannerPagerAdapter;
//    private ArrayList<BannerInfo> alBannerInfo;
//    private int page = 1, positionNow;
//    private static final int SIZE = 4;//轮播图数量
//    private static final int DELAY_TIME = 3000;//轮播图片切换间隔时间（单位毫秒）
//
//    //以下是图片滚动所需：
//    private MyHorizontalScrollView mHorizontalScrollView;
//    private HorizontalScrollViewAdapter mAdapter;
//    private List<Integer> mDatas = new ArrayList<Integer>(Arrays.asList(
//            R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d,
//            R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h,
//            R.drawable.l));
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        this.setContentView(R.layout.activity_mainv2);
//        //以下是下拉刷新
//        //tv = (TextView)findViewById(R.id.scrollTextView1);
//        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container);
//        swipeRefreshLayout.setColorSchemeResources(
//                android.R.color.holo_blue_light,
//                android.R.color.holo_red_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_green_light
//        );
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//
//            @Override
//            public void onRefresh() {
//                //tv.setText("正在刷新");
//                // TODO Auto-generated method stub
//                new Handler().postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        // TODO Auto-generated method stub
//                        //tv.setText("刷新完成");
//                        swipeRefreshLayout.setRefreshing(false);
//                    }
//                }, 6000);
//            }
//        });
//
//        //以下是图片左右滚动所需：
//        mHorizontalScrollView = (MyHorizontalScrollView) findViewById(R.id.id_horizontalScrollView);
//        mAdapter = new HorizontalScrollViewAdapter(this, mDatas);
//        //添加滚动回调
//        mHorizontalScrollView.setCurrentImageChangeListener(new MyHorizontalScrollView.CurrentImageChangeListener()
//        {
//            @Override
//            public void onCurrentImgChanged(int position, View viewIndicator)
//            {
//                viewIndicator.setBackgroundColor(Color
//                        .parseColor("#AA024DA4"));
//            }
//        });
//        //添加点击回调
//        mHorizontalScrollView.setOnItemClickListener(new MyHorizontalScrollView.OnItemClickListener()
//        {
//
//            @Override
//            public void onClick(View view, int position)
//            {
//                view.setBackgroundColor(Color.parseColor("#AA024DA4"));
//            }
//        });
//
//        //设置适配器
//        mHorizontalScrollView.initDatas(mAdapter);
//
//        //以上是下拉刷新
//        setTitle("BannerDemo");
//        initView();
//    }
//
//    private void initView(){
//        initData();
//        vpBanner=(ViewPager) findViewById(R.id.vp_banner);
//        rgBanner=(RadioGroup) findViewById(R.id.radio_group);
//        BitmapUtils bitmapUtils=new BitmapUtils(this);
//        ArrayList<View> alView=new ArrayList<>();
//        ImageView[] imageViews=new ImageView[SIZE + 2];
//        for(int i = 0;i<SIZE + 2;i++) {
//            imageViews[i]=new ImageView(this);
//            imageViews[i].setScaleType(ImageView.ScaleType.FIT_XY);
//            if(i==0){
//                bitmapUtils.display(imageViews[i],alBannerInfo.get(SIZE-1).url_pic);
//            }else if (i==SIZE+1) {
//                bitmapUtils.display(imageViews[i],alBannerInfo.get(0).url_pic);
//            }else if (i>0&&i<=SIZE) {
//                bitmapUtils.display(imageViews[i],alBannerInfo.get(i-1).url_pic);
//                imageViews[i].setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(MainActivityv2.this, CommWebView.class);
//                        intent.putExtra("title",alBannerInfo.get(positionNow).title);
//                        intent.putExtra("url",alBannerInfo.get(positionNow).url_content);
//                        startActivity(intent);
//                    }
//                });
//            }
//            alView.add(imageViews[i]);
//        }
//
//        bannerPagerAdapter=new BannerPagerAdapter(alView);
//        //ViewPager设置适配器
//        vpBanner.setAdapter(bannerPagerAdapter);
//        //ViewPager显示第一个Fragment
//        vpBanner.setCurrentItem(1);
//        //ViewPager页面切换监听
//        vpBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if (position == SIZE+1) {
//                    vpBanner.setCurrentItem(1, false);
//                } else if (position == 0 && positionOffsetPixels == 0) {
//                    vpBanner.setCurrentItem(SIZE, false);
//                }
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                positionNow=position-1;
//                switch (position) {
//                    case 1:
//                        rgBanner.check(R.id.rb_one);
//                        page = 1;
//                        break;
//                    case 2:
//                        rgBanner.check(R.id.rb_two);
//                        page = 2;
//                        break;
//                    case 3:
//                        rgBanner.check(R.id.rb_three);
//                        page = 3;
//                        break;
//                    case 4:
//                        rgBanner.check(R.id.rb_four);
//                        page = 4;
//                        break;
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//    }
//
//    private void initData(){
//        alBannerInfo = new ArrayList<>();
//        alBannerInfo.add(new BannerInfo("世界“造型最酷”海岛",
//                "https://ss1.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/super/whfpf%3D425%2C260%2C50/sign=782de3003fdbb6fd250eb6666f199f2a/9c16fdfaaf51f3de9d78ee9f92eef01f3b297906.jpg",
//                "http://bbs.miercn.com/bd/201511/thread_574672_1.html?source=bdxsy"));
//        alBannerInfo.add(new BannerInfo("4岁双胞胎女孩潮气十足爆红网络",
//                "https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/super/whfpf%3D425%2C260%2C50/sign=89a91a31848ba61edfbb9b6f2709a338/b3119313b07eca805da4251c972397dda04483a3.jpg",
//                "http://china.ynet.com/3.1/1511/05/10506176.html?source=bdxsy"));
//        alBannerInfo.add(new BannerInfo("“澳洲树蛙”头顶小蜗牛似法式贝雷帽",
//                "https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/super/whfpf%3D425%2C260%2C50/sign=d06c29404110b912bf94a5bea5c0c831/00e93901213fb80e66910f2c30d12f2eb838948c.jpg",
//                "http://china.ynet.com/3.1/1511/05/10507328.html?source=bdxsy"));
//        alBannerInfo.add(new BannerInfo("南非插画师绘掌上3D世界 栩栩如生",
//                "https://ss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/super/whfpf%3D425%2C260%2C50/sign=9898e7dc793e6709be5516bf5dfaab0f/d1a20cf431adcbef491765fdaaaf2edda3cc9f2d.jpg",
//                "http://china.ynet.com/3.1/1511/05/10506306.html?source=bdxsy"));
//    }
//
//    @Override
//    protected void onResume(){
//        super.onResume();
//        //开始轮播
//        startBanner();
//    }
//
//    private void startBanner(){
//        handler=new Handler();
//        runnable=new Runnable() {
//            @Override
//            public void run() {
//                handler.postDelayed(runnable, DELAY_TIME);
//                vpBanner.setCurrentItem(page, true);
//                if(page==SIZE+1){
//                    page=0;
//                }
//                page++;
//            }
//        };
//        handler.postDelayed(runnable, DELAY_TIME);
//    }
//
//    @Override
//    protected void onStop(){
//        super.onStop();
//        //暂停轮播
//        handler.removeCallbacks(runnable);
//    }
//
//}
