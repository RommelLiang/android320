package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.NewShaituBean;
import com.tiemuyu.chuanchuan.activity.bean.ShaituWaterBean;
import com.tiemuyu.chuanchuan.activity.bean.ZhuantiWaterBean;
import com.tiemuyu.chuanchuan.activity.bean.ZhuantiWaterExtBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.AppManager;
import com.tiemuyu.chuanchuan.activity.util.DataContoler;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.util.ToastHelper;

import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by CC2.0 on 2017/2/11.
 */

public class MyShaitu extends BaseActivityG {

    //类比本题目做我的收藏

    //=============L1瀑布流相关变量================
    private LinkedList<String> mListItems;//
    private PullToRefreshGridView mPullRefreshListView;//
    private List<String> images;
    private int mItemCount = 12;
    private LinearLayout shaitu_back;

    protected ImageLoader imageLoader = ImageLoader.getInstance();

    DisplayImageOptions options;
    private String TAG_GetShaitu = "TAG_GetShaitu";   //获取初始瀑布流
    private String TAG_GetMoreShaitu = "TAG_GetMoreShaitu";   //获取更多瀑布流
    List<List<ShaituWaterBean>> waterfall_listwhole = new ArrayList<>();
    private ZhuantiWaterExtBean zhuantiinfo = new ZhuantiWaterExtBean();
    private static int watercount;

    private WaterAdapter wateradaper;

    //sl: 新的晒图bean
    NewShaituBean newShaituBean;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_shaitu);
        pd = new ProgressDialog(this);//加载的ProgressDialog
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);//选择加载风格 这里是圆圈 STYLE_HORIZONTAL 是水平进度条
        pd.setMessage("加载中....");
        pd.show();
        findViewById(R.id.MyShaituGoBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
                .displayer(new RoundedBitmapDisplayer(0))
                .build();
        shaitu_back = (LinearLayout) findViewById(R.id.MyShaituGoBack);
        shaitu_back.setOnClickListener(this);
        images = new ArrayList<>();
        //不需要获取数据
        //        final String  information = getIntent().getStringExtra("Intent_Data_Packet");//.getStringExtra("et1");
        //        ZhuantiId=information;
        mPullRefreshListView = (PullToRefreshGridView) findViewById(R.id.pull_refresh_grid);
        initZuixinbaojiaDatas();//给瀑布流初始化数据
        initIndicator();//初始化indicator

        initProcess();
        pd.dismiss();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.MyShaituGoBack:
                finish();
                break;
        }
    }

    /**
     * 加载的流程
     */
    protected void initProcess() {
        initData();

        initAppAccess();//sl: 以下三个都没用
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
        //todo   添加 注册按钮和注册跳转。
    }

    protected void initData() {
        // TODO Auto-generated method stub
        initHttpFirstData();
    }

    protected void initUI() {
        // TODO Auto-generated method stub
    }

    private void initHttpFirstData() {
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(this,
                        TAG_GetShaitu,
                        Constant.REQUEST_GET,
                        new RequestParams(
                                UrlManager.Get_Shaitu()),
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

//    private  void addupWater()
//    {
//        System.out.println("####Add_Water"+watercount);
//        MyApplication.poolManager.addAsyncTask(
//                new ThreadPoolTaskHttp(this,
//                        TAG_GetMoreZhuanti,
//                        Constant.REQUEST_GET,
//                        new RequestParams(UrlManager.Get_ZhuantiMoreWater()+watercount+"&id="+ZhuantiId),
//                        this,
//                        "获取最新报价",
//                        false));
//    }

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
//            addupWater();
            //      mListItems.add("" + mItemCount++);
            wateradaper.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            mPullRefreshListView.onRefreshComplete();
        }
    }

    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

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
        NewShaituBean newShaituBean;

        private class ViewHolder {
            public TextView text;
            public ImageView shaitu_usr_image;
            public ImageView main_pic;
            public TextView price;
            private LinearLayout ll_item;
        }

        public WaterAdapter(NewShaituBean newShaituBean) {
            this.newShaituBean = newShaituBean;
        }

        @Override
        public int getCount() {
            //return images.size();//imageUrls.length;
            return newShaituBean.getData().getPagedata().getRows().size();
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
            final ViewHolder holder;
            if (convertView == null) {
                view = getLayoutInflater().inflate(R.layout.shaitu_item_layout, parent, false);
                holder = new ViewHolder();
                holder.main_pic = (ImageView) view.findViewById(R.id.shaitu_main_pic);
                holder.text = (TextView) view.findViewById(R.id.shaitu_usr_name);
                holder.price = (TextView) view.findViewById(R.id.chakandingzhi_btn);
                holder.shaitu_usr_image = (ImageView) view.findViewById(R.id.shaitu_usr_img);
                holder.ll_item = (LinearLayout) view.findViewById(R.id.ll_item);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            //这两个不需要
            //holder.text.setText(waterfall_listwhole.get(position/10).get(position%10).getProductId());
            //holder.price.setText(waterfall_listwhole.get(position/10).get(position%10).getProductId());
            //imageLoader.displayImage(images.get(position), holder.shaitu_usr_image, options, animateFirstListener);

            //sl:用新的bean来显示瀑布流图片和文字
            Log.e("isIsPraise", "getView: "+newShaituBean.getData().getPagedata().getRows().get(position).isIsPraise());
            if (newShaituBean.getData().getPagedata().getRows().get(position).getIsOffer() !=1) {
                holder.price.setVisibility(View.GONE);
            } else {
                holder.price.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent2 = new Intent(MyShaitu.this, ClothesDetailsActivity.class);
                        intent2.putExtra("productid",newShaituBean.getData().getPagedata().getRows().get(position).getProductId());
                        startActivity(intent2);
                    }
                });
            }
            holder.text.setText(newShaituBean.getData().getUsers().get(0).getNickName());
            holder.price.setText("查看定制");
            holder.ll_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MyShaitu.this, MyShaiTuDetailActivity.class);
                    intent.putExtra("data", newShaituBean.getData().getPagedata().getRows().get(position));
                    startActivity(intent);

                }
            });
            imageLoader.displayImage(newShaituBean.getData().getUsers().get(0).getUserImg(), holder.shaitu_usr_image, options, animateFirstListener);
            imageLoader.displayImage(newShaituBean.getData().getPagedata().getRows().get(position).getImage1(), holder.main_pic, options, animateFirstListener);
            return view;
        }
    }

    /********************************
     * 高伟豪添加
     ********************************/
    public void ShaituAction(String msg, String resultTag) {
        if (resultTag.equals(TAG_GetShaitu)) {
            Log.e("TAG", msg);
            //text_json.setText(msg);
            //TODO  数据解析

            //gwh的旧方法：
//            List<ShaituWaterBean> temp_shaitu_list;
//            temp_shaitu_list =  DataContoler.parseShaituWaterDetail(msg) ;// DataController.parseZhuantiWaterfallDetail(msg);
//            waterfall_listwhole.add(temp_shaitu_list);
//            initNewOfferDatas(temp_shaitu_list);
////            zhuantiinfo=DataContoler.parseZhuantiWaterDetail(msg);//DataController.parseZhuantiWaterDetail(msg);
//            watercount=2;

            //sl:新修改
            Gson gson = new Gson();
            NewShaituBean newShaituBean = gson.fromJson(msg, NewShaituBean.class);
            Log.e("TEST", "ContentBrief: " + newShaituBean.getData().getPagedata().getRows().get(0).getContentBrief());
            initNewOfferDatas(newShaituBean);
            mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {

                @Override
                public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {

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
                public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                    Log.e("TAG", "onPullUpToRefresh"); // Do work to refresh the list here.
                    System.out.println("######mPullRefreshListView  高伟豪");
                    new GetDataTask().execute();
                }
            });
        } else if (resultTag.equals(TAG_GetMoreShaitu)) {
            //todo 数据解析
            //后台都没做方法
            System.out.println("########waterfall msg is: " + msg + "; " + resultTag);//打印信息验证是否正确
            watercount++;
        }
    }

    //最新报价瀑布流初始化数据
    private void initNewOfferDatas(NewShaituBean newShaituBean) {
        //gwh的旧方法：
//        System.out.println("size of water fall is " + waterfall_list_in.size());
//        for (int i = 0; i < waterfall_list_in.size(); i++) {

////            imageUrls[i]=waterfall_list_in.get(i).getImg_url();
//            System.out.println(waterfall_list_in.get(i).getImage1());
//            images.add(waterfall_list_in.get(i).getImage1());
//        }

        wateradaper = new WaterAdapter(newShaituBean);
        //高伟豪 每次的瀑布流下拉都是调用数据生成然后set一次
        mPullRefreshListView.setAdapter(wateradaper);
//        new WaterAdapter();
        mPullRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent2 = new Intent(MyShaitu.this, ClothesDetailsActivity.class);
                Log.e("商品", "onItemClick: " + waterfall_listwhole.get(position / 10).get(position % 10).getProductId());
                intent2.putExtra("productid", Integer.parseInt(waterfall_listwhole.get(position / 10).get(position % 10).getProductId()));
                startActivity(intent2);
            }
        });
    }

    /********************************
     * 添加的接口
     ********************************/
    @Override
    public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        System.out.println("http succeed!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        ShaituAction(callBackMsg, resultTag);
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
    /***************************************************************/
}