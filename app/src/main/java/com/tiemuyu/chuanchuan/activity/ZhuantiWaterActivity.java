package com.tiemuyu.chuanchuan.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.tiemuyu.chuanchuan.activity.bean.BannerDataBean;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.L1WaterItemBean;
import com.tiemuyu.chuanchuan.activity.bean.ZhuantiWaterBean;
import com.tiemuyu.chuanchuan.activity.bean.ZhuantiWaterExtBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.fragment.HomeFragment;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.AppManager;
import com.tiemuyu.chuanchuan.activity.util.ClassJumpTool;
import com.tiemuyu.chuanchuan.activity.util.DataContoler;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.util.ToastHelper;
import com.tiemuyu.chuanchuan.activity.view.GridViewWithHeaderAndFooter;
import com.tiemuyu.chuanchuan.activity.view.URL;

import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.tiemuyu.chuanchuan.activity.fragment.MineFragment.user;

/**
 * Created by CC2.0 on 2017/2/10.
 */

public class ZhuantiWaterActivity extends BaseActivityG {


    @ViewInject(R.id.zhuanti_image)
    private ImageView zhuanti_image;  //专题头部image
    private TextView zhuanti_miaoshu;


    //=============L1瀑布流相关变量================
    private LinkedList<String> mListItems;//
    private PullToRefreshListView mPullRefreshListView;//
    private List<String> images;
    private int mItemCount = 12;


    protected ImageLoader imageLoader = ImageLoader.getInstance();

    DisplayImageOptions options;

    private String ZhuantiId;

    private String TAG_GetZhuanti = "TAG_GetZhuanti";   //获取初始瀑布流

    private String TAG_GetMoreZhuanti = "TAG_GetMoreZhuanti";   //获取更多瀑布流

    List<List<ZhuantiWaterBean>> waterfall_listwhole = new ArrayList<List<ZhuantiWaterBean>>();


    private ZhuantiWaterExtBean zhuantiinfo = new ZhuantiWaterExtBean();


    private static int watercount;

    private WaterAdapter wateradaper = new WaterAdapter();
    private int width;
    private View headerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_zhuantiwater);

        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        //  Constant.VERSION = Version.getAppVersionName(this);
        // _global = GlobalVariable.getInstance();


        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.icon_morentupian2)
                .showImageForEmptyUri(R.drawable.icon_morentupian2)
                .showImageOnFail(R.drawable.icon_morentupian2)
                .cacheInMemory()
                .cacheOnDisc()
                .displayer(new RoundedBitmapDisplayer(20))
                .build();

        headerView = View.inflate(ZhuantiWaterActivity.this, R.layout.zhuanti_water_header, null);
        images = new ArrayList<String>();

        zhuanti_miaoshu = (TextView) headerView.findViewById(R.id.zhuanti_miaoshu);
        final String information = getIntent().getStringExtra("Intent_Data_Packet");//.getStringExtra("et1");

        ZhuantiId = information;

        mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_grid);
        ListView refreshableView = mPullRefreshListView.getRefreshableView();
        refreshableView.addHeaderView(headerView);
        initZuixinbaojiaDatas();//给瀑布流初始化数据
        initIndicator();//初始化indicator


        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {


                String label = DateUtils.formatDateTime(
                        getApplicationContext(),
                        System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME
                                | DateUtils.FORMAT_SHOW_DATE
                                | DateUtils.FORMAT_ABBREV_ALL);
                // Update the LastUpdatedLabel
                refreshView.getLoadingLayoutProxy()
                        .setLastUpdatedLabel(label);

//                addupWater();

                new GetDataTaskDown().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "onPullUpToRefresh"); // Do work to refresh the list here.

                System.out.println("######mPullRefreshListView  高伟豪");


                new GetDataTask().execute();
            }
        });

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

    protected void initListener() {
        // TODO Auto-generated method stub
//        //todo   添加 注册按钮和注册跳转。
//


    }

    protected void initData() {
//        // TODO Auto-generated method stub

        initHttpBannerData();


    }

    protected void initUI() {
        // TODO Auto-generated method stub


        zhuanti_image = (ImageView) headerView.findViewById(R.id.zhuanti_image);


    }


    private void initHttpBannerData() {
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(this,
                        TAG_GetZhuanti,
                        Constant.REQUEST_GET,
                        new RequestParams(
                                UrlManager.Get_ZhuantiWater()),
                        this,
                        "获取water",
                        false));
    }


    //最新报价瀑布流初始化数据
    private void initZuixinbaojiaDatas() {
        mListItems = new LinkedList<String>();
        for (int i = 0; i < mItemCount; i++) {
            mListItems.add("￥" + i);
        }
    }

    //为瀑布流下拉定义外观
    private void initIndicator() {
        ILoadingLayout startLabels = mPullRefreshListView.getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
        startLabels.setRefreshingLabel("刷新中...");// 刷新时
        startLabels.setReleaseLabel("松开即可刷新...");// 下来达到一定距离时，显示的提示

        ILoadingLayout endLabels = mPullRefreshListView.getLoadingLayoutProxy(false, true);
        endLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
        endLabels.setRefreshingLabel("正在刷新...");// 刷新时
        endLabels.setReleaseLabel("松开即可刷新...");// 下来达到一定距离时，显示的提示
    }


    private void addupWater() {

        System.out.println("####Add_Water" + watercount);

        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(this,
                        TAG_GetMoreZhuanti,
                        Constant.REQUEST_GET,
                        new RequestParams(UrlManager.Get_ZhuantiMoreWater() + watercount + "&id=" + ZhuantiId),
                        this,
                        "获取最新报价",
                        false));

    }


    //瀑布流所需
    private class GetDataTaskDown extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(200);


//                addupWater();

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
//            addupWater();

            //      mListItems.add("" + mItemCount++);
            wateradaper.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            mPullRefreshListView.onRefreshComplete();
        }
    }


    //瀑布流所需
    private class GetDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);


//                addupWater();

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
            addupWater();

            //      mListItems.add("" + mItemCount++);
            wateradaper.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            mPullRefreshListView.onRefreshComplete();
        }
    }


    public static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }


    class WaterAdapter extends BaseAdapter {

        private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

        private class ViewHolder {
            public TextView text_one;
            public ImageView image_one;
            public TextView price_one;
            public TextView text;
            public ImageView image;
            public TextView price;
            public LinearLayout left;
            public LinearLayout right;
        }

        @Override
        public int getCount() {
            return images.size() / 2;//imageUrls.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = convertView;
            final ZhuantiWaterActivity.WaterAdapter.ViewHolder holder;
            if (convertView == null) {
                holder = new WaterAdapter.ViewHolder();
                view = getLayoutInflater().inflate(R.layout.zhuan_ti_wather, parent, false);

                holder.text = (TextView) view.findViewById(R.id.new_price_pro_name);
                holder.price = (TextView) view.findViewById(R.id.new_price_txt);
                holder.image = (ImageView) view.findViewById(R.id.new_price_pic);
                holder.text_one = (TextView) view.findViewById(R.id.new_price_pro_name_one);
                holder.price_one = (TextView) view.findViewById(R.id.new_price_txt_one);
                holder.image_one = (ImageView) view.findViewById(R.id.new_price_pic_one);
                holder.left = (LinearLayout) view.findViewById(R.id.listview_item_left);
                holder.right = (LinearLayout) view.findViewById(R.id.listview_item_right);
                view.setTag(holder);

            } else {
                holder = (WaterAdapter.ViewHolder) view.getTag();
            }
            holder.text.setText(waterfall_listwhole.get(((position + 1) * 2 - 1) / 10).get(((position + 1) * 2 - 1) % 10).getProductname());
            holder.price.setText(waterfall_listwhole.get(((position + 1) * 2 - 1) / 10).get(((position + 1) * 2 - 1) % 10).getPrice());
            imageLoader.displayImage(images.get(((position + 1) * 2 - 1)), holder.image, options, animateFirstListener);

            holder.text_one.setText(waterfall_listwhole.get(((position + 1) * 2 - 2) / 10).get(((position + 1) * 2 - 2) % 10).getProductname());
            holder.price_one.setText(waterfall_listwhole.get(((position + 1) * 2 - 2) / 10).get(((position + 1) * 2 - 2) % 10).getPrice());
            imageLoader.displayImage(images.get(((position + 1) * 2 - 2)), holder.image_one, options, animateFirstListener);
            holder.left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastHelper.show(getApplicationContext(), waterfall_listwhole.get(((position + 1) * 2 - 2) / 10).get(((position + 1) * 2 - 2) % 10).getProductid());

                    Log.e("产品ID",":" + waterfall_listwhole.get(((position + 1) * 2 - 2) / 10).get(((position + 1) * 2 - 2) % 10).getProductid());
                    jump();
                }
            });
            holder.right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastHelper.show(getApplicationContext(), waterfall_listwhole.get(((position + 1) * 2 - 1) / 10).get(((position + 1) * 2 - 1) % 10).getProductid());

                    Log.e("产品ID",":" + waterfall_listwhole.get(((position + 1) * 2 - 1) / 10).get(((position + 1) * 2 - 1) % 10).getProductid());
                    jump();
                }
            });
            return view;
        }

    }


    /********************************
     * 高伟豪添加
     ********************************/
    public void ZhuantiAction(String msg, String resultTag) {
        if (resultTag.equals(TAG_GetZhuanti)) {
            System.out.println("ZHUANTI message is: " + msg + "; " + resultTag);
            //TODO  数据解析

            List<ZhuantiWaterBean> temp_waterfall_list = new ArrayList<ZhuantiWaterBean>();


            temp_waterfall_list = DataContoler.parseZhuantiWaterfallDetail(msg);// DataController.parseZhuantiWaterfallDetail(msg);
            waterfall_listwhole.add(temp_waterfall_list);

            initNewOfferDatas(temp_waterfall_list);
            Log.e("msg;", temp_waterfall_list.get(0).toString());

            zhuantiinfo = DataContoler.parseZhuantiWaterDetail(msg);               //DataController.parseZhuantiWaterDetail(msg);

            watercount = 2;
            ImageLoader.getInstance().displayImage(zhuantiinfo.getBigimg(), zhuanti_image);
            zhuanti_miaoshu.setText(zhuantiinfo.getMiaoshu());


        } else if (resultTag.equals(TAG_GetMoreZhuanti)) {
            //todo 数据解析

            System.out.println("########waterfall msg is: " + msg + "; " + resultTag);//打印信息验证是否正确

            List<ZhuantiWaterBean> temp_waterfall_list = new ArrayList<ZhuantiWaterBean>();


            temp_waterfall_list = DataContoler.parseZhuantiWaterfallDetail(msg);                 //DataController.parseZhuantiWaterfallDetail(msg);
            waterfall_listwhole.add(temp_waterfall_list);

            initNewOfferDatas(temp_waterfall_list);
            watercount++;


        }


    }


    //最新报价瀑布流初始化数据
    private void initNewOfferDatas(List<ZhuantiWaterBean> waterfall_list_in) {


        System.out.println("size of water fall is " + waterfall_list_in.size());
        for (int i = 0; i < waterfall_list_in.size(); i++) {


//            imageUrls[i]=waterfall_list_in.get(i).getImg_url();
            System.out.println(waterfall_list_in.get(i).getProductmainpic());
            images.add(waterfall_list_in.get(i).getProductmainpic());


        }


        //高伟豪 每次的瀑布流下拉都是调用数据生成然后set一次

        mPullRefreshListView.setAdapter(wateradaper);
//        new WaterAdapter()
       /* mPullRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                ToastHelper.show(getApplicationContext(), waterfall_listwhole.get(position / 10).get(position % 10).getProductid());


                jump();


//                ClassJumpTool.startToNextActivity(this, MyWebview.class, URL.UrlAssetDetail);


//                ToastHelper.show(getApplicationContext(),   id+"");
                System.out.println(id + "  " + waterfall_listwhole.get(position / 10).get(position % 10).getProductid());
//                startImagePagerActivity(position);
            }
        });*/


    }


    public void jump() {

        String temp = "7";
        ClassJumpTool.startToNextActivity(this, PaySelect.class, temp);


    }


    /********************************
     * 添加的接口
     ********************************/
    @Override
    public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        System.out.println("http succeed!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        ZhuantiAction(callBackMsg, resultTag);
    }

    //		public void loadingCallBack(long total, long current, boolean isUploading,
//				String resultTag);/** 加载中 */
    public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!failed callback!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    /**
     * 请求失败
     */

//    public void startCallBack(String resultTag,boolean isShowDiolog,String showTitle){} /** 开始*/
//    public void cancelCallBack(String resultTag){}/**取消*/
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
