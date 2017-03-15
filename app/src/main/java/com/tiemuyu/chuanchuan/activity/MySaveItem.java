package com.tiemuyu.chuanchuan.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.tiemuyu.chuanchuan.activity.bean.FavBean;
import com.tiemuyu.chuanchuan.activity.bean.SaveItemBean;
import com.tiemuyu.chuanchuan.activity.bean.ZhuantiWaterExtBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.AppManager;
import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by CC2.0 on 2017/2/13.
 */

public class MySaveItem extends BaseActivityG {

    //类比本题目做我的收藏

    //=============L1瀑布流相关变量================
    private LinkedList<String> mListItems;//
    private PullToRefreshGridView mPullRefreshListView;//
    private List<String> images;
    private int mItemCount = 12;

    LinearLayout fav_goback;
    private WaterAdapter waterAdapter;

    protected ImageLoader imageLoader = ImageLoader.getInstance();

    DisplayImageOptions options;

    private  String TAG_GetSaveItem="TAG_GetSaveItem";   //获取初始瀑布流
    private  String TAG_GetMoreSaveItem="TAG_GetMoreSaveItem";   //获取更多瀑布流

    List<List<SaveItemBean>> waterfall_listwhole=new ArrayList<>();

    private ZhuantiWaterExtBean zhuantiinfo=new ZhuantiWaterExtBean();

    ProgressDialog pd;

    private  static int watercount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_saveitem);
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        pd = new ProgressDialog(this);//加载的ProgressDialog
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);//选择加载风格 这里是圆圈 STYLE_HORIZONTAL 是水平进度条
        pd.setMessage("加载中....");
        pd.show();
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
        images=new ArrayList<String>();
        fav_goback = (LinearLayout) findViewById(R.id.MyShoucangBack);
        fav_goback.setOnClickListener(this);
//不需要获取数据
//        final String  information = getIntent().getStringExtra("Intent_Data_Packet");//.getStringExtra("et1");
//        ZhuantiId=information;
        mPullRefreshListView = (PullToRefreshGridView) findViewById(R.id.fav_listview);
        initZuixinbaojiaDatas();//给瀑布流初始化数据
        initIndicator();//初始化indicator

        initProcess();
        pd.dismiss();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.MyShoucangBack:
                finish();
                break;
        }
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

    protected void initListener() {
        // TODO Auto-generated method stub
        //todo   添加 注册按钮和注册跳转。
    }

    protected void initData() {
//        // TODO Auto-generated method stub
        initHttpFirstData();
    }

    protected void initUI() {
        // TODO Auto-generated method stub
    }

    private void initHttpFirstData() {
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(this,
                        TAG_GetSaveItem,
                        Constant.REQUEST_GET,
                        new RequestParams(
                                UrlManager.Get_SaveItem()),
                        this,
                        "获取我的收藏",
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
            waterAdapter.notifyDataSetChanged();
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
            } catch (InterruptedException e) {
                // TODO: 2017/1/24
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            addupWater();
            waterAdapter.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            mPullRefreshListView.onRefreshComplete();
        }
    }

    private  void addupWater() {
        System.out.println("####Add_Water"+watercount);
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(this,
                        TAG_GetMoreSaveItem,
                        Constant.REQUEST_GET,
                        new RequestParams(UrlManager.Get_MoreSaveItem()+watercount+"&pagesize=10"),
                        this,
                        "获取最新报价",
                        false));
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
        FavBean favBean;

        private class ViewHolder {
            public TextView fav_name;
            public ImageView fav_img;
            public TextView fav_price;
            public TextView fav_time;
            public RelativeLayout rl_item;
            public Button dlt_fav;
        }

        public WaterAdapter(FavBean favBean) {
            this.favBean = favBean;
            Log.e("TAG", "favourite constructor executed!");
            Log.e("TAG", "in fav adapter constructor: " + favBean.getData().get(0).getProductName());
            Log.e("TAG", "size of the fav lv: " + favBean.getData().size());
        }

        @Override
        public int getCount() {
            Log.e("TAG", "size of the fav lv: " + favBean.getData().size());
            return favBean.getData().size();//imageUrls.length;
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
        public View getView(final int position, View convertView, final ViewGroup parent) {
            View view = convertView;
            final ViewHolder holder;
            if (convertView == null) {
                view = getLayoutInflater().inflate(R.layout.fav_item_layout, parent, false);
                holder = new ViewHolder();
                holder.fav_name = (TextView) view.findViewById(R.id.fav_name);
                holder.fav_price=(TextView) view.findViewById(R.id.fav_price);
                holder.fav_time = (TextView) view.findViewById(R.id.fav_time);
                holder.fav_img = (ImageView) view.findViewById(R.id.fav_pic);
                holder.rl_item = (RelativeLayout) view.findViewById(R.id.rl_item);
                holder.dlt_fav = (Button) view.findViewById(R.id.dlt_fav);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.fav_name.setText(favBean.getData().get(position).getProductName());
            holder.fav_price.setText("￥" + Double.toString(favBean.getData().get(position).getPrice()));
            holder.fav_time.setText(favBean.getData().get(position).getCollectTime());
            holder.rl_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent2 = new Intent(MySaveItem.this, ClothesDetailsActivity.class);
                    intent2.putExtra("productid",favBean.getData().get(position).getProductId());
                    startActivity(intent2);
                }
            });
            holder.dlt_fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delToFav(favBean.getData().get(position).getProductId());
                    favBean.getData().remove(position);
                    waterAdapter.notifyDataSetChanged();
                }
            });
            Log.e("TAG", position + "'s pic: " + favBean.getData().get(position).getProductName());
            imageLoader.displayImage(favBean.getData().get(position).getMainImage(), holder.fav_img, options, animateFirstListener);
            return view;
        }
    }

    /********************************高伟豪添加********************************/
    public void ShaituAction(String msg, String resultTag) {
        if (resultTag.equals(TAG_GetSaveItem)) {
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
            Log.e("TAG", msg);
            //TODO  数据解析
            //gwh旧方法：
//            List<SaveItemBean> temp_shaitu_list;
//            temp_shaitu_list =  DataContoler.parseSaveItemDetail(msg) ;// DataController.parseZhuantiWaterfallDetail(msg);
//            waterfall_listwhole.add(temp_shaitu_list);
//            initNewOfferDatas(temp_shaitu_list);
//            watercount=2;

            //sl: 用新方法解析json
            Gson gson = new Gson();
            FavBean favBean = gson.fromJson(msg, FavBean.class);
            Log.e("TAG", favBean.getData().get(0).getProductName());//sl: to test the json, test success!
            initNewOfferDatas(favBean);
        } else if (resultTag.equals(TAG_GetMoreSaveItem)) {
            //todo 数据解析
            //后台都没做方法
//            List<SaveItemBean> temp_shaitu_list=;
//            temp_shaitu_list =  DataContoler.parseSaveItemDetail(msg) ;// DataController.parseZhuantiWaterfallDetail(msg);
//            waterfall_listwhole.add(temp_shaitu_list);
//            initNewOfferDatas(temp_shaitu_list);
            System.out.println("########waterfall msg is: " + msg + "; " + resultTag);//打印信息验证是否正确
            watercount++;
        } else if (resultTag.equals("TAG_POST_Delfav")) {
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        }
    }

    //最新报价瀑布流初始化数据
    private void initNewOfferDatas(FavBean favBean) {
        //gwh的旧方法，弃用：
//        System.out.println("size of water fall is " + waterfall_list_in.size());
//        for (int i = 0; i < waterfall_list_in.size(); i++) {
////            imageUrls[i]=waterfall_list_in.get(i).getImg_url();
//            System.out.println(waterfall_list_in.get(i).getMainImage());
//            images.add(waterfall_list_in.get(i).getMainImage());
//        }

        waterAdapter = new WaterAdapter(favBean);
        //高伟豪 每次的瀑布流下拉都是调用数据生成然后set一次
        mPullRefreshListView.setAdapter(waterAdapter);
//        new WaterAdapter()
        mPullRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MySaveItem.this, "哈哈", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(MySaveItem.this, ClothesDetailsActivity.class);
                Log.e("商品", "onItemClick: " + waterfall_listwhole.get(position/10).get(position%10).getProductId());
                intent2.putExtra("productid",Integer.parseInt(waterfall_listwhole.get(position/10).get(position%10).getProductId()));
                startActivity(intent2);
            }
        });
    }
    private String TAG_POST_Delfav = "TAG_POST_Delfav";
    private void delToFav(int id) {
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(MySaveItem.this,
                        TAG_POST_Delfav,
                        Constant.REQUEST_POST,
                        ParamsTools.delFavorites(
                                UrlManager.Delfav(),id+""),
                        MySaveItem.this, "取消收藏",
                        false));
    }
    /********************************添加的接口********************************/
    @Override
    public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        System.out.println("http succeed!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        ShaituAction(callBackMsg, resultTag);
    }

    //		public void loadingCallBack(long total, long current, boolean isUploading,
//				String resultTag);/** 加载中 */
    public void failCallBack(Throwable arg0, String resultTag,boolean isShowDiolog) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!failed callback!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }/**请求失败*/

//    public void startCallBack(String resultTag,boolean isShowDiolog,String showTitle){} /** 开始*/
//    public void cancelCallBack(String resultTag){}/**取消*/
    public void failShowCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!succeed but code != 1 !!!!!!!!!!!!!!!!!!!!!!!!!");
    }/** 请求成功，但code!=1 */

    public void reLoginCallBack(String resultTag,boolean isShowDiolog){
        System.out.println("!!!!!!!!!!!!!!request succeed! but need relogin!!!!!!!!!!!!!!!!!");
    }/** 请求成功,但要重新登录 */
    /****************************************************************/


}