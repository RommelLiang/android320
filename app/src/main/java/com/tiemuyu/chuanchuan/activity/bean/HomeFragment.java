package com.tiemuyu.chuanchuan.activity.bean;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.squareup.picasso.Picasso;
import com.tiemuyu.chuanchuan.activity.MyApplication;
import com.tiemuyu.chuanchuan.activity.ProtocolActivity;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.adapter.DingZhiChengPinAdapter;
import com.tiemuyu.chuanchuan.activity.adapter.DzcpAdapter;
import com.tiemuyu.chuanchuan.activity.adapter.WaterAdapter;
import com.tiemuyu.chuanchuan.activity.adapter.ZhuanTiWaterAdapter;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.fragment.BaseFragment;
import com.tiemuyu.chuanchuan.activity.util.GlideImageLoader;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.PreferenceUtils;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.youth.banner.listener.OnBannerClickListener;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lonze on 2016/8/23.
 */
public class HomeFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener2<ListView> {


    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;
    private static int watercount = 1;
    private static int qunzhuangCount = 1;
    private static int waiTaoCount = 1;
    private static int shangYICount = 1;
    private static int kuZhuangYICount = 1;
    private static int taoZhuangCount = 1;
    private static int dingzhichengpin = 1;


    private SharedPreferences sp;
    boolean first = true;
    private View view, view1, view2, view3, view4, view5, view6, view7;//三个滑动页面
    private List<View> viewList;//view数组
    private ViewPager viewPager;// 对应的viewPager
    private List<String> titleList;
    private PagerTabStrip pagerTabStrip;
    private View headerView;


    //=============L1瀑布流相关变量================
    private LinkedList<String> mListItems;//旧版弃用
    private PullToRefreshListView mPullRefreshListView;//旧版弃用
    private PullToRefreshListView mPullRefreshListView3;//旧版弃用
    private PullToRefreshListView mPullRefreshListView4;//旧版弃用
    private PullToRefreshListView mPullRefreshListView5;//旧版弃用
    private PullToRefreshListView mPullRefreshListView6;//旧版弃用
    private PullToRefreshListView mPullRefreshListView7;//旧版弃用
    private WaterAdapter wateradaper;
    private ZhuanTiWaterAdapter zhuanTiWaterAdapter3;
    private ZhuanTiWaterAdapter zhuanTiWaterAdapter4;
    private ZhuanTiWaterAdapter zhuanTiWaterAdapter5;
    private ZhuanTiWaterAdapter zhuanTiWaterAdapter6;
    private ZhuanTiWaterAdapter zhuanTiWaterAdapter7;
    private int mItemCount = 12;
    //=============================================

    //=============定制成品所需变量================
    private ListView lv_dzcp;
    private DzcpAdapter dzcpAdapter;
    //=============================================

    //===============GWH==================
    private ProgressDialog pd;
    private static final String TAG_GETBANNER = "TAG_GETBANNER";
    private static final String TAG_GETDINGZHICHENGPIN = "TAG_GETDINGZHICHENGPIN";
    private static final String TAG_ADDDINGZHICHENGPIN = "TAG_ADDDINGZHICHENGPIN";
    private static final String TAG_GETZHUANTI_QUN_ZHUANG = "TAG_GETZHUANTI_QUN_ZHUANG";
    private static final String TAG_ADDZHUANTI_QUN_ZHUANG = "TAG_ADDZHUANTI_QUN_ZHUANG";
    private static final String TAG_GETZHUANTI_WAI_TAO = "TAG_GETZHUANTI_WAI_TAO";
    private static final String TAG_ADDZHUANTI_WAI_TAO = "TAG_ADDZHUANTI_WAI_TAO";
    private static final String TAG_GETZHUANTI_SHANG_YI = "TAG_GETZHUANTI_SHANG_YI";
    private static final String TAG_ADDZHUANTI_SHANG_YI = "TAG_ADDZHUANTI_SHANG_YI";
    private static final String TAG_GETZHUANTI_KU_ZI = "TAG_GETZHUANTI_KU_ZI";
    private static final String TAG_ADDZHUANTI_KU_ZI = "TAG_ADDZHUANTI_KU_ZI";
    private static final String TAG_GETZHUANTI_TAO_ZHUANG = "TAG_GETZHUANTI_TAO_ZHUANG";
    private static final String TAG_ADDZHUANTI_TAO_ZHUANG = "TAG_ADDZHUANTI_TAO_ZHUANG";
    List<Banner> resbanner;
    /***
     * 这里的问题是banner下面的点要动态。
     **/
    private static final String TAG_GETWATER = "TAG_GETWATER";//获取瀑布流
    private static final String TAG_UPDATA_WATER = "TAG_GETWATER";//获取瀑布流

    private static final String TAG_GETADDWATER = "TAG_GETADDWATER";//获取瀑布流


    private View header;
    private com.youth.banner.Banner headerBanner;
    private ListView refreshableView;
    private ExpandableListView refreshableView2;
    private ListView refreshableView3;
    private ListView refreshableView4;
    private ListView refreshableView5;
    private ListView refreshableView6;
    private ListView refreshableView7;
    private View headerImage1, headerImage2, headerImage3, headerImage4, headerImage5;
    private ImageView image1, image2, image3, image4, image5;
    private TextView tv_miaoshu1, tv_miaoshu2, tv_miaoshu3, tv_miaoshu4, tv_miaoshu5;
    private ZhuanTiMessage QunZhuanagMessage;
    private Gson gson = new Gson();
    private ZhuanTiMessage waiTao;
    private ZhuanTiMessage shangyi;
    private ZhuanTiMessage kulei;
    private ZhuanTiMessage taozhuang;
    private List<ZhuanTiMessage.DataBean.ListBean> qunZhuangList;
    private List<ZhuanTiMessage.DataBean.ListBean> waiTaolist;
    private List<ZhuanTiMessage.DataBean.ListBean> shangYilist;
    private List<ZhuanTiMessage.DataBean.ListBean> kuLeilist;
    private List<ZhuanTiMessage.DataBean.ListBean> taoZhuanglist;
    private LastPrice lastPrice;
    private List<LastPrice.DataBean.RowsBean> rows;
    private HomeBanner homeBanner;
    private List<HomeBanner.DataBean> homeBannerData;
    private ArrayList<String> imagesList;
    private PullToRefreshListView mPullRefreshListView2;
    private View headerView2;
    private com.youth.banner.Banner headerBanner2;
    private DIngzhi dIngzhi;
    private List<DIngzhi.DataBean> dIngzhiData;
    private DingZhiChengPinAdapter dingZhiChengPinAdapter;

    //=====================================


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);


        initHttpBannerData();
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.icon_morentupian2)
                .showImageForEmptyUri(R.drawable.icon_morentupian2)
                .showImageOnFail(R.drawable.icon_morentupian2)
                .cacheInMemory()
                .cacheOnDisc()
                .displayer(new RoundedBitmapDisplayer(20))
                .build();

        initVIew(inflater);
        return view;
    }

    private void initVIew(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.home_fragment, null);//需要根据fragment的布局进行修改
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        view1 = inflater.inflate(R.layout.layout1, null);
        view2 = inflater.inflate(R.layout.layout1, null);
        view3 = inflater.inflate(R.layout.layout1, null);
        view4 = inflater.inflate(R.layout.layout1, null);
        view5 = inflater.inflate(R.layout.layout1, null);
        view6 = inflater.inflate(R.layout.layout1, null);
        view7 = inflater.inflate(R.layout.layout1, null);

        viewList = new ArrayList<>();//将要分页显示的View装入数组中

        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);
        viewList.add(view5);
        viewList.add(view6);
        viewList.add(view7);
        titleList = new ArrayList<>();//每个页面的Title数据
        titleList.add("最新报价");
        titleList.add("定制成品");
        titleList.add("裙装");
        titleList.add("外套");
        titleList.add("上衣");
        titleList.add("裤装");
        titleList.add("套装");
        pagerTabStrip = (PagerTabStrip) view.findViewById(R.id.pagertab);//获得一个pagerTabStrip的实例
        pagerTabStrip.setTabIndicatorColorResource(R.color.ColorSplashBackground);//设置选项卡菜单文字颜色
        pagerTabStrip.setDrawFullUnderline(false);//设置不显示选项卡最下面一条贯穿始终的实线


        //===================瀑布流相关=================
        mPullRefreshListView = (PullToRefreshListView) view1.findViewById(R.id.pull_refresh_grid);
        mPullRefreshListView2 = (PullToRefreshListView) view2.findViewById(R.id.pull_refresh_grid);
        mPullRefreshListView3 = (PullToRefreshListView) view3.findViewById(R.id.pull_refresh_grid);
        mPullRefreshListView4 = (PullToRefreshListView) view4.findViewById(R.id.pull_refresh_grid);
        mPullRefreshListView5 = (PullToRefreshListView) view5.findViewById(R.id.pull_refresh_grid);
        mPullRefreshListView6 = (PullToRefreshListView) view6.findViewById(R.id.pull_refresh_grid);
        mPullRefreshListView7 = (PullToRefreshListView) view7.findViewById(R.id.pull_refresh_grid);
        initIndicator(mPullRefreshListView);//初始化indicator
        initIndicator(mPullRefreshListView2);//初始化indicator
        initIndicator(mPullRefreshListView3);//初始化indicator
        initIndicator(mPullRefreshListView4);//初始化indicator
        initIndicator(mPullRefreshListView5);//初始化indicator
        initIndicator(mPullRefreshListView6);//初始化indicator
        initIndicator(mPullRefreshListView7);//初始化indicator
        System.out.println("next step????????????");
        viewPager.setAdapter(pagerAdapter);
    }
//==============================================

    PagerAdapter pagerAdapter = new PagerAdapter() {

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return viewList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // TODO Auto-generated method stub
            container.removeView(viewList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // TODO Auto-generated method stub
            container.addView(viewList.get(position));

            return viewList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            pagerTabStrip.setTextColor(getResources().getColor(R.color.ColorSplashBackground));
            return titleList.get(position);
        }
    };

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        Log.e("TAG", "onPullDownToRefresh"); // Do work to
        if (refreshView == mPullRefreshListView) {
            getLastPrice(TAG_UPDATA_WATER);
        }
        //最新定制
        if (refreshView == mPullRefreshListView2) {

        }
        //裙装专题
        if (refreshView == mPullRefreshListView3) {
            Log.e("mPullRefreshListView3", "onPullDownToRefresh: ");
            getZhuanTi(20, 1, TAG_GETZHUANTI_QUN_ZHUANG);
        }
        if (refreshView == mPullRefreshListView4) {
            //lws:获取外套专题
            getZhuanTi(19, 1, TAG_GETZHUANTI_WAI_TAO);
        }
        if (refreshView == mPullRefreshListView5) {
            //lws:获上衣装专题
            getZhuanTi(22, 1, TAG_GETZHUANTI_SHANG_YI);
        }
        if (refreshView == mPullRefreshListView6) {
            //lws:获取裤装专题
            getZhuanTi(21, 1, TAG_GETZHUANTI_KU_ZI);
        }
        if (refreshView == mPullRefreshListView7) {
            //lws:获取套装专题
            getZhuanTi(23, 1, TAG_GETZHUANTI_TAO_ZHUANG);
        }
        new GetDataTaskDown().execute();
    }

    private void getZhuanTi(int id, int count, String TAG) {
        //lws:获取裙装专题
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(getActivity(),
                        TAG,
                        Constant.REQUEST_GET,
                        new RequestParams(
                                UrlManager.Get_ZhuanTi() + count + "&id=" + id),
                        this,
                        "获取裙装",
                        false));
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        Log.e("TAG", "onPullUpToRefresh"); // Do work to refresh the list here.

        System.out.println("######mPullRefreshListView  高伟豪");
        if (refreshView == mPullRefreshListView) {
            watercount++;
            addupWater();
        }
        //最新定制
        if (refreshView == mPullRefreshListView2) {

        }
        if (refreshView == mPullRefreshListView3) {
            Log.e("mPullRefreshListView3", "onPullUpToRefresh: ");
            //lws:获取裙装专题
            qunzhuangCount++;
            getZhuanTi(20, qunzhuangCount, TAG_ADDZHUANTI_QUN_ZHUANG);
        }
        if (refreshView == mPullRefreshListView4) {
            //lws:获取外套专题
            waiTaoCount++;
            getZhuanTi(19, waiTaoCount, TAG_ADDZHUANTI_WAI_TAO);
        }
        if (refreshView == mPullRefreshListView5) {
            //lws:获上衣装专题
            shangYICount++;
            getZhuanTi(22, shangYICount, TAG_ADDZHUANTI_SHANG_YI);
        }
        if (refreshView == mPullRefreshListView6) {
            //lws:获取裤装专题
            kuZhuangYICount++;
            getZhuanTi(21, kuZhuangYICount, TAG_ADDZHUANTI_KU_ZI);
        }
        if (refreshView == mPullRefreshListView7) {
            //lws:获取套装专题
            taoZhuangCount++;
            getZhuanTi(23, taoZhuangCount, TAG_ADDZHUANTI_TAO_ZHUANG);
        }
        new GetDataTask().execute();
    }


    private void setView() {
        PreferenceUtils.setPrefBoolean(getActivity(), "firstload", true);
        /*initBannerView(view1);//因为banner在view1里面，所以要用view1作为参数传递到initView里面
        initBannerView2(view2);*/
        pd.dismiss();
    }


    private void initHttpBannerData() {
        sp = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        //史力：以下是键盘上推
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        init();
        pd.show();
        getBanner();

        getDINGzhi(TAG_GETDINGZHICHENGPIN,dingzhichengpin);

        //lws:获取裙装专题
        getZhuanTi(20, qunzhuangCount, TAG_GETZHUANTI_QUN_ZHUANG);
        //lws:获取外套专题
        getZhuanTi(19, waiTaoCount, TAG_GETZHUANTI_WAI_TAO);
        //lws:获取上衣专题
        getZhuanTi(22, shangYICount, TAG_GETZHUANTI_SHANG_YI);
        //lws:获取裤类
        getZhuanTi(21, kuZhuangYICount, TAG_GETZHUANTI_KU_ZI);
        //lws:获取套装
        getZhuanTi(23, taoZhuangCount, TAG_GETZHUANTI_TAO_ZHUANG);
    }

    private void getDINGzhi(String tag, int page) {
        Log.e("getDINGzhi: " , UrlManager.Getdingzhilist(String.valueOf(page)));
        //获取定制成品
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(getActivity(),
                        tag,
                        Constant.REQUEST_GET,
                        new RequestParams(
                                UrlManager.Getdingzhilist(String.valueOf(page))),
                        this,
                        "获取定制成品",
                        false));
    }

    private void getBanner() {
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(getActivity(),
                        TAG_GETBANNER,
                        Constant.REQUEST_GET,
                        new RequestParams(
                                UrlManager.Get_Banner()),
                        this,
                        "获取banner",
                        false));
    }


    private void init() {
        pd = new ProgressDialog(getActivity());//加载的ProgressDialog
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);//选择加载风格 这里是圆圈 STYLE_HORIZONTAL 是水平进度条
        pd.setMessage("加载中....");
    }




    //轮播图所需的图片、文字、URL等



    @Override
    public void onStart() {
        super.onStart();
        System.out.println("HomeFragment onstart!");
        if (first) {
            System.out.println("HomeFragment first!");
            //webView.loadUrl(URL.UrlHome);
            first = false;
        }
        System.out.println("HomeFragment out!");
    }

    @Override
    public void onResume() {
        super.onResume();
        //开始轮播
        /*startBanner();
        startBanner2();*/
    }

    @Override
    public void onStop() {
        super.onStop();
        //暂停轮播
        /*handler.removeCallbacks(runnable);
        handler.removeCallbacks(runnable2);*/
    }

    public void saveload() {
//        webView.loadUrl("javascript:save()");
    }

    public void goBack() {
//        if (webView.canGoBack() && webView.isClickable()) {
//            webView.goBack();
//        }
    }

    //为瀑布流下拉定义外观
    private void initIndicator(PullToRefreshListView mp) {
        ILoadingLayout startLabels = mp.getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
        startLabels.setRefreshingLabel("刷新中...");// 刷新时
        startLabels.setReleaseLabel("松开即可刷新...");// 下来达到一定距离时，显示的提示

        ILoadingLayout endLabels = mp.getLoadingLayoutProxy(false, true);
        endLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
        endLabels.setRefreshingLabel("正在刷新...");// 刷新时
        endLabels.setReleaseLabel("松开即可刷新...");// 下来达到一定距离时，显示的提示
    }


    //瀑布流所需
    private class GetDataTaskDown extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO: 2017/1/24
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mPullRefreshListView.onRefreshComplete();
            mPullRefreshListView3.onRefreshComplete();
            mPullRefreshListView4.onRefreshComplete();
            mPullRefreshListView5.onRefreshComplete();
            mPullRefreshListView6.onRefreshComplete();
            mPullRefreshListView7.onRefreshComplete();
        }
    }


    //瀑布流所需
    private class GetDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);

//                for (int i = 0; i < 4; i++) {
//                    mListItems.add("￥" + mItemCount++);
//                }
            } catch (InterruptedException e) {
                // TODO: 2017/1/24
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.e("GetDataTask", "onPostExecute: " + result);
            wateradaper.notifyDataSetChanged();
            zhuanTiWaterAdapter3.notifyDataSetChanged();
            zhuanTiWaterAdapter4.notifyDataSetChanged();
            zhuanTiWaterAdapter5.notifyDataSetChanged();
            zhuanTiWaterAdapter6.notifyDataSetChanged();
            zhuanTiWaterAdapter7.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            mPullRefreshListView.onRefreshComplete();
            mPullRefreshListView3.onRefreshComplete();
            mPullRefreshListView4.onRefreshComplete();
            mPullRefreshListView5.onRefreshComplete();
            mPullRefreshListView6.onRefreshComplete();
            mPullRefreshListView7.onRefreshComplete();
        }
    }

    /********************************
     * 高伟豪添加
     ********************************/
    public void HomeAction(String msg, String resultTag) {
        if (resultTag.equals(TAG_GETBANNER)) {
            Log.e("TAG_GETBANNER", "HomeAction: "+msg);
            homeBanner = GsonUtils.fromData(msg, HomeBanner.class);
            homeBannerData = homeBanner.getData();
            setView();
            getLastPrice(TAG_GETWATER);


        } else if (resultTag.equals(TAG_GETWATER)) {//史力：如果是瀑布流则执行下面语句
            lastPrice = GsonUtils.fromData(msg, LastPrice.class);
            rows = lastPrice.getData().getRows();
            wateradaper = new WaterAdapter(rows, getActivity());
            mPullRefreshListView.setAdapter(wateradaper);
            refreshableView = mPullRefreshListView.getRefreshableView();
            mPullRefreshListView.setOnRefreshListener(this);
            imagesList = new ArrayList<>();
            for (int i = 0; i < homeBannerData.size(); i++) {
                imagesList.add(homeBannerData.get(i).getImgUrl());
            }
            int headerViewsCount = refreshableView.getHeaderViewsCount();

            if (headerViewsCount == 0) {
                headerView = View.inflate(getActivity(), R.layout.header_banner, null);
                headerBanner = (com.youth.banner.Banner) headerView.findViewById(R.id.banner);
                headerBanner.setImageLoader(new GlideImageLoader())
                        .setImages(imagesList).start();
                refreshableView.addHeaderView(headerView);
            }
            headerBanner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Intent intent = new Intent(getActivity(),ProtocolActivity.class);
                    intent.putExtra("url",homeBannerData.get(position-1).getLinkUrl());
                    intent.putExtra("title",homeBannerData.get(position-1).getTitle());
                    startActivity(intent);
                }
            });

        } else if(resultTag.equals(TAG_GETDINGZHICHENGPIN)) {
            Log.e("TAG_GETDINGZHICHENGPIN", "HomeAction: "+ msg );
            //定制成品
            dIngzhi = GsonUtils.fromData(msg, DIngzhi.class);
            dIngzhiData = dIngzhi.getData();
            dingZhiChengPinAdapter = new DingZhiChengPinAdapter(dIngzhiData,getActivity());
            refreshableView2 = (ExpandableListView) mPullRefreshListView2.getRefreshableView();
            refreshableView2.setGroupIndicator(null);
            refreshableView2.setAdapter(dingZhiChengPinAdapter);
            int headerViewsCount2 = refreshableView2.getHeaderViewsCount();
            if (headerViewsCount2 == 0) {
                headerView2 = View.inflate(getActivity(), R.layout.header_banner, null);
                headerBanner2 = (com.youth.banner.Banner) headerView2.findViewById(R.id.banner);
                headerBanner2.setImageLoader(new GlideImageLoader())
                        .setImages(imagesList).start();
                refreshableView2.addHeaderView(headerView2);
            }
            headerBanner2.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Intent intent = new Intent(getActivity(),ProtocolActivity.class);
                    intent.putExtra("url",homeBannerData.get(position-1).getLinkUrl());
                    intent.putExtra("title",homeBannerData.get(position-1).getTitle());
                    startActivity(intent);
                }
            });
        }else if (resultTag.equals(TAG_GETADDWATER)) {
            rows.addAll(GsonUtils.fromData(msg,LastPrice.class).getData().getRows());
            wateradaper.notifyDataSetChanged();
        }  else if (resultTag.equals(TAG_GETZHUANTI_QUN_ZHUANG)) {//梁文硕：裙装专题
            QunZhuanagMessage = gson.fromJson(msg, ZhuanTiMessage.class);
            qunZhuangList = QunZhuanagMessage.getData().getList();
            zhuanTiWaterAdapter3 = new ZhuanTiWaterAdapter(qunZhuangList, getActivity());
            mPullRefreshListView3.setAdapter(zhuanTiWaterAdapter3);
            refreshableView3 = mPullRefreshListView3.getRefreshableView();
            mPullRefreshListView3.setOnRefreshListener(this);
            int headerViewsCount3 = refreshableView3.getHeaderViewsCount();
            if (headerViewsCount3 == 0) {
                headerImage1 = View.inflate(getActivity(), R.layout.header_image, null);
                image1 = (ImageView) headerImage1.findViewById(R.id.header_image);
                tv_miaoshu1 = (TextView) headerImage1.findViewById(R.id.tv_miaoshu);
                Picasso.with(getActivity()).load(QunZhuanagMessage.getData().getTopics().getBigimg()).into(image1);
                tv_miaoshu1.setText(QunZhuanagMessage.getData().getTopics().getMiaoshu());
                refreshableView3.addHeaderView(headerImage1);
            }
        } else if (resultTag.equals(TAG_ADDZHUANTI_QUN_ZHUANG)) {
            qunZhuangList.addAll(gson.fromJson(msg, ZhuanTiMessage.class).getData().getList());
            zhuanTiWaterAdapter3.notifyDataSetChanged();
        } else if (resultTag.equals(TAG_GETZHUANTI_WAI_TAO)) {//梁文硕：外套专题
            waiTao = gson.fromJson(msg, ZhuanTiMessage.class);
            waiTaolist = waiTao.getData().getList();
            zhuanTiWaterAdapter4 = new ZhuanTiWaterAdapter(waiTaolist, getActivity());
            mPullRefreshListView4.setAdapter(zhuanTiWaterAdapter4);
            refreshableView4 = mPullRefreshListView4.getRefreshableView();
            mPullRefreshListView4.setOnRefreshListener(this);
            int headerViewsCount4 = refreshableView4.getHeaderViewsCount();
            if (headerViewsCount4 == 0) {
                headerImage2 = View.inflate(getActivity(), R.layout.header_image, null);
                image2 = (ImageView) headerImage2.findViewById(R.id.header_image);
                tv_miaoshu2 = (TextView) headerImage2.findViewById(R.id.tv_miaoshu);
                Picasso.with(getActivity()).load(waiTao.getData().getTopics().getBigimg()).into(image2);
                tv_miaoshu2.setText(waiTao.getData().getTopics().getMiaoshu());
                refreshableView4.addHeaderView(headerImage2);
            }
        } else if (resultTag.equals(TAG_ADDZHUANTI_WAI_TAO)) {
            waiTaolist.addAll(gson.fromJson(msg, ZhuanTiMessage.class).getData().getList());
            zhuanTiWaterAdapter4.notifyDataSetChanged();
        } else if (resultTag.equals(TAG_GETZHUANTI_SHANG_YI)) {//梁文硕：上衣专题
            shangyi = gson.fromJson(msg, ZhuanTiMessage.class);
            shangYilist = shangyi.getData().getList();
            zhuanTiWaterAdapter5 = new ZhuanTiWaterAdapter(shangYilist, getActivity());
            mPullRefreshListView5.setAdapter(zhuanTiWaterAdapter5);
            refreshableView5 = mPullRefreshListView5.getRefreshableView();
            mPullRefreshListView5.setOnRefreshListener(this);
            int headerViewsCount5 = refreshableView5.getHeaderViewsCount();
            if (headerViewsCount5 == 0) {
                headerImage3 = View.inflate(getActivity(), R.layout.header_image, null);
                image3 = (ImageView) headerImage3.findViewById(R.id.header_image);
                tv_miaoshu3 = (TextView) headerImage3.findViewById(R.id.tv_miaoshu);
                Picasso.with(getActivity()).load(shangyi.getData().getTopics().getBigimg()).into(image3);
                tv_miaoshu3.setText(shangyi.getData().getTopics().getMiaoshu());
                Log.e("refreshableView5", "setBanner: " + "refreshableView5");
                refreshableView5.addHeaderView(headerImage3);
            }
        } else if (resultTag.equals(TAG_ADDZHUANTI_SHANG_YI)) {
            shangYilist.addAll(gson.fromJson(msg, ZhuanTiMessage.class).getData().getList());
            zhuanTiWaterAdapter5.notifyDataSetChanged();
        } else if (resultTag.equals(TAG_GETZHUANTI_KU_ZI)) {//梁文硕：裤类专题
            kulei = gson.fromJson(msg, ZhuanTiMessage.class);
            kuLeilist = kulei.getData().getList();
            zhuanTiWaterAdapter6 = new ZhuanTiWaterAdapter(kuLeilist, getActivity());
            mPullRefreshListView6.setAdapter(zhuanTiWaterAdapter6);
            refreshableView6 = mPullRefreshListView6.getRefreshableView();
            mPullRefreshListView6.setOnRefreshListener(this);
            int headerViewsCount6 = refreshableView6.getHeaderViewsCount();
            if (headerViewsCount6 == 0) {
                headerImage4 = View.inflate(getActivity(), R.layout.header_image, null);
                image4 = (ImageView) headerImage4.findViewById(R.id.header_image);
                tv_miaoshu4 = (TextView) headerImage4.findViewById(R.id.tv_miaoshu);
                Picasso.with(getActivity()).load(kulei.getData().getTopics().getBigimg()).into(image4);
                tv_miaoshu4.setText(kulei.getData().getTopics().getMiaoshu());
                Log.e("refreshableView6", "setBanner: " + "refreshableView6");
                refreshableView6.addHeaderView(headerImage4);
            }
        } else if (resultTag.equals(TAG_ADDZHUANTI_KU_ZI)) {
            kuLeilist.addAll(gson.fromJson(msg, ZhuanTiMessage.class).getData().getList());
            zhuanTiWaterAdapter6.notifyDataSetChanged();
        } else if (resultTag.equals(TAG_GETZHUANTI_TAO_ZHUANG)) {//梁文硕：套装专题
            taozhuang = gson.fromJson(msg, ZhuanTiMessage.class);
            taoZhuanglist = taozhuang.getData().getList();
            zhuanTiWaterAdapter7 = new ZhuanTiWaterAdapter(taoZhuanglist, getActivity());
            mPullRefreshListView7.setAdapter(zhuanTiWaterAdapter7);
            refreshableView7 = mPullRefreshListView7.getRefreshableView();
            mPullRefreshListView7.setOnRefreshListener(this);
            int headerViewsCount7 = refreshableView7.getHeaderViewsCount();
            if (headerViewsCount7 == 0) {
                headerImage5 = View.inflate(getActivity(), R.layout.header_image, null);
                image5 = (ImageView) headerImage5.findViewById(R.id.header_image);
                tv_miaoshu5 = (TextView) headerImage5.findViewById(R.id.tv_miaoshu);
                Picasso.with(getActivity()).load(taozhuang.getData().getTopics().getBigimg()).into(image5);
                tv_miaoshu5.setText(taozhuang.getData().getTopics().getMiaoshu());
                Log.e("refreshableView7", "setBanner: " + taozhuang.getData().getTopics().getMiaoshu());
                refreshableView7.addHeaderView(headerImage5);
            }
        } else if (resultTag.equals(TAG_ADDZHUANTI_TAO_ZHUANG)) {
            taoZhuanglist.addAll(gson.fromJson(msg, ZhuanTiMessage.class).getData().getList());
            zhuanTiWaterAdapter7.notifyDataSetChanged();
        }
    }

    private void getLastPrice(String tag) {
        /**********************叠加的方法做数据加载*******高伟豪：下面三句话是做get请求的：************/
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(getActivity(),
                        tag,
                        Constant.REQUEST_GET,
                        new RequestParams(UrlManager.Get_Water()),
                        this,
                        "获取最新报价",
                        false));
    }


    private void addupWater() {
        System.out.println("####Add_Water" + watercount);
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(getActivity(),
                        TAG_GETADDWATER,
                        Constant.REQUEST_GET,
                        new RequestParams(UrlManager.Add_Water() + watercount + "&pageSize=10"),
                        this,
                        "获取最新报价",
                        false));
    }
    /********************************
     * 添加的接口
     ********************************/
    @Override
    public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        System.out.println("http succeed!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        HomeAction(callBackMsg, resultTag);
    }

    //		public void loadingCallBack(long total, long current, boolean isUploading,
//				String resultTag);/** 加载中 */
    public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!failed callback!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Log.e("resultTag" + resultTag, "failCallBack: " + arg0.getLocalizedMessage() );
    }

    /**
     * 请求失败
     */
    public void failShowCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!succeed but code != 1 !!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    /**
     * 请求成功，但code!=1
     */

    public void reLoginCallBack(String resultTag, boolean isShowDiolog) {
        System.out.println("!!!!!!!!!!!!!!request succeed! but need relogin!!!!!!!!!!!!!!!!!");
    }/** 请求成功,但要重新登录 */
    /****************************************************************/

}