package com.tiemuyu.chuanchuan.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.tiemuyu.chuanchuan.activity.adapter.LingAdapter;
import com.tiemuyu.chuanchuan.activity.adapter.ViewPagerAdapter;
import com.tiemuyu.chuanchuan.activity.adapter.ZiChanAdapter;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.CoinBean;
import com.tiemuyu.chuanchuan.activity.bean.LingBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.proxy.LoadingProxy;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;

import org.xutils.http.RequestParams;

import java.util.ArrayList;

public class ZiChanMingXiActivity extends BaseActivityG {

    private ViewPager vp;
    private RelativeLayout findLl;
    private int one;//单个水平动画位移
    private int width;
    private int dx;// 动画图片偏移量
    private int x = 0;
    private ViewPagerAdapter pageAdapter;
    private int currIndex = 0;// 当前页卡编号
    private TextView tv_left,tv_right;
    private final String GET_COIN = "GET_COIN";
    private final String GET_LING = "GET_LING";
    private CoinBean coinBean;
    private PullToRefreshListView pull_refresh_grid_one;
    private PullToRefreshListView pull_refresh_grid_two;
    private LingBean lingBean;
    private LoadingProxy mInstance;
    private int chuan = 1;
    private int ling = 1;
    private ZiChanAdapter mZiChanAdapter;
    private LingAdapter mLingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zi_chan_ming_xi);
        findLl = (RelativeLayout) findViewById(R.id.find_ll);
        vp = (ViewPager) findViewById(R.id.vp);
        tv_left = (TextView) findViewById(R.id.tv_left);
        tv_right = (TextView) findViewById(R.id.tv_right);
        setStripMove();
        mInstance = LoadingProxy.getInstance(this);
        ArrayList<View> views = new ArrayList();
        View one = View.inflate(this, R.layout.zi_chan_layout, null);
        pull_refresh_grid_one = (PullToRefreshListView) one.findViewById(R.id.pull_refresh_grid);
        View two = View.inflate(this, R.layout.zi_chan_layout, null);
        pull_refresh_grid_two = (PullToRefreshListView) two.findViewById(R.id.pull_refresh_grid);
        views.add(one);
        views.add(two);
        pageAdapter = new ViewPagerAdapter(views);
        vp.setAdapter(pageAdapter);
        vp.setOnPageChangeListener(listener);
        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(0);
            }
        });
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(1);
            }
        });
        mInstance.show();
        //穿币详情
        getMessage(GET_COIN,1,chuan);
        //零钱明细
        getMessage(GET_LING,2,ling);
        findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void setStripMove() {

        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) findLl.getLayoutParams();
        linearParams.width = width / 2;
        Display currDisplay = this.getWindowManager().getDefaultDisplay();//获取屏幕当前分辨率
        int displayWidth = currDisplay.getWidth();
        one = displayWidth / 2; //设置水平动画平移大小
    }
    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Animation animation = null;
            if (position > currIndex) {
                dx = x + one;
            } else {
                dx = x - one;
            }
            switch (position) {
                case 0:
                    animation = new TranslateAnimation(x, dx, 0, 0);
                    break;
                case 1:
                    animation = new TranslateAnimation(x, dx, 0, 0);
                    break;
            }
            if (position > currIndex) {
                x += one;
            } else {
                x -= one;
            }
            currIndex = position;
            animation.setDuration(100);
            animation.setFillAfter(true);//
            findLl.startAnimation(animation);
        }
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    @Override
    public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        Log.e("successCallBack: ", callBackMsg);
        if (resultTag.equals(GET_COIN)) {
            if (coinBean == null) {
                coinBean = GsonUtils.fromData(callBackMsg, CoinBean.class);
                mZiChanAdapter = new ZiChanAdapter(ZiChanMingXiActivity.this, coinBean.getData().getRows());
                pull_refresh_grid_one.setAdapter(mZiChanAdapter);
                pull_refresh_grid_one.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                    @Override
                    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                        chuan = 1;
                        getMessage(GET_COIN,1,chuan);
                    }

                    @Override
                    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                        chuan++;
                        getMessage(GET_COIN,1,chuan);
                    }
                });
            } else {
                pull_refresh_grid_one.onRefreshComplete();
                if (chuan == 1) {
                    coinBean = GsonUtils.fromData(callBackMsg, CoinBean.class);
                } else {
                    coinBean.getData().getRows().addAll(GsonUtils.fromData(callBackMsg, CoinBean.class).getData().getRows());
                }
                mZiChanAdapter.notifyDataSetChanged();
            }

        } else if (resultTag.equals(GET_LING)) {
            if (lingBean == null) {
                lingBean = GsonUtils.fromData(callBackMsg, LingBean.class);
                mLingAdapter = new LingAdapter(ZiChanMingXiActivity.this, lingBean.getData().getRows());
                pull_refresh_grid_two.setAdapter(mLingAdapter);
                pull_refresh_grid_two.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                    @Override
                    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                        ling = 1;
                        getMessage(GET_LING,2,ling);
                    }

                    @Override
                    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                        ling++;
                        getMessage(GET_LING,2,ling);
                    }
                });
            } else {
                pull_refresh_grid_two.onRefreshComplete();
                if (chuan == 1) {
                    lingBean = GsonUtils.fromData(callBackMsg, LingBean.class);
                } else {
                    lingBean.getData().getRows().addAll(GsonUtils.fromData(callBackMsg, LingBean.class).getData().getRows());
                }
                mLingAdapter.notifyDataSetChanged();
            }
            Log.e( "GET_LING ",callBackMsg );
            mInstance.dismiss();

        }
    }

    @Override
    public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
        super.failCallBack(arg0, resultTag, isShowDiolog);
        Log.e("failCallBack: ", arg0.getLocalizedMessage());
    }

    private void getMessage(String tag,int id,int page){
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(this,
                        tag,
                        Constant.REQUEST_GET,
                        new RequestParams(
                                UrlManager.GetTradelist(page,id)),
                        this,
                        "获取产品信息",
                        false));
    }
}
