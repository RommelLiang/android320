package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
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
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.util.ToastHelper;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by CC2.0 on 2017/2/13.
 */

public class MySaveItem extends BaseActivityG  implements PullToRefreshBase.OnRefreshListener2<ListView>, ThreadPoolTaskHttp.HttpCallBack{

    //类比本题目做我的收藏

    //=============L1瀑布流相关变量================
    private LinkedList<String> mListItems;//
    private PullToRefreshListView mPullRefreshListView;//
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

    private  static int watercount;
    private FavBean mFavBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_saveitem);
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        mInstance.show();
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
        images=new ArrayList<String>();
        fav_goback = (LinearLayout) findViewById(R.id.MyShoucangBack);
        fav_goback.setOnClickListener(this);
        mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.fav_listview);
        initZuixinbaojiaDatas();//给瀑布流初始化数据
        initIndicator();//初始化indicator

        initProcess();
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
                    Intent intent2 = new Intent(MySaveItem.this, DingzhiDetailsActivity.class);
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

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        initHttpFirstData();
        watercount = 1;
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        if (mFavBean.getData().size() == 0){
            mPullRefreshListView.onRefreshComplete();
            return;
        }
        watercount++;
        addupWater();
    }
    public void ShaituAction(String msg, String resultTag) {
        if (resultTag.equals(TAG_GetSaveItem)) {
            Gson gson = new Gson();
            if (mFavBean != null) {
                mPullRefreshListView.onRefreshComplete();
            }
            mFavBean = gson.fromJson(msg, FavBean.class);
            mInstance.dismiss();
            mPullRefreshListView.setOnRefreshListener(this);
            if (mFavBean.getData().size() ==0) {
                ToastHelper.show(MySaveItem.this,"你还没有收藏任何商品");
                return;
            }
            Log.e("TAG", mFavBean.getData().get(0).getProductName());//sl: to test the json, test success!
            waterAdapter = new WaterAdapter(mFavBean);
            mPullRefreshListView.setAdapter(waterAdapter);
        } else if (resultTag.equals(TAG_GetMoreSaveItem)) {
            mFavBean.getData().addAll(GsonUtils.fromData(msg, FavBean.class).getData());
            waterAdapter.notifyDataSetChanged();
            mPullRefreshListView.onRefreshComplete();

        } else if (resultTag.equals("TAG_POST_Delfav")) {
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        }
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