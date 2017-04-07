package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

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
import com.tiemuyu.chuanchuan.activity.adapter.FindSecondWaterAdapter;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.FindSecondWaterBean;
import com.tiemuyu.chuanchuan.activity.bean.WaterBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;

import org.xutils.http.RequestParams;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @项目名： 227androidpay-master
 * @包名： com.tiemuyu.chuanchuan.activity
 * @类描述：
 * @创建人： hr
 * @创建时间： 2017/3/1
 * @version：
 */

public class FindWaterActivity extends BaseActivityG implements View.OnClickListener, PullToRefreshBase.OnRefreshListener2<ListView> {

    private View headerview;
    private PullToRefreshListView pullToRefreshListView;
    private int btn_tag;
    private RelativeLayout rl_main_header;
    private LinearLayout main_header_goback;
    private TextView tv_main_header_title;
    private ImageView header_big_img;
    private TextView header_img_txt;
    private RelativeLayout rl_paixufangshi;
    private TextView tv_filter;
    private LinearLayout ll_sort_list;
    private TextView tv_sort_type;
    private ImageView iv_sort_arrow;
    private RelativeLayout rl_zonghepaixu;
    private RelativeLayout rl_gaodaodi;
    private RelativeLayout rl_didaogao;
    private HorizontalScrollView hsv_keys;
    LinearLayout ll_water_search_btn;
    TextView sch_keys[];
    int i;

    //发送请求api所需的四个变量
    private int topic_id;
    private static int watercount = 1;
    private int state = 0;
    private String key_word = "";

    private static final String FIND_WATER_LIST = "FIND_WATER_LIST";
    private static final String FIND_MORE_WATER_LIST = "FIND_MORE_WATER_LIST";

    FindSecondWaterAdapter findSecondWaterAdapter;//下方瀑布流的变量
    FindSecondWaterBean findSecondWaterBean;//储存数据的bean

    protected ImageLoader imageLoader = ImageLoader.getInstance();//sl：显示图片所需的一个变量
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();//sl:显示图片所需
    DisplayImageOptions options;//sl：显示图片所需变量
    private int id;
    private WaterBean.DataBeanX.TopicssBean topicss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn_tag = 0;
        setContentView(R.layout.layout1);
        headerview = View.inflate(this, R.layout.find_water_header, null);
        rl_main_header = (RelativeLayout) findViewById(R.id.rl_main_header);
        header_big_img = (ImageView) headerview.findViewById(R.id.header_big_img);
        header_img_txt = (TextView) headerview.findViewById(R.id.header_img_txt);
        main_header_goback = (LinearLayout) findViewById(R.id.main_header_goback);
        main_header_goback.setOnClickListener(this);
        tv_main_header_title = (TextView) findViewById(R.id.tv_main_header_title);
        rl_paixufangshi = (RelativeLayout) headerview.findViewById(R.id.rl_paixufangshi);
        rl_paixufangshi.setOnClickListener(this);
        tv_filter = (TextView) headerview.findViewById(R.id.tv_filter);
        tv_filter.setOnClickListener(this);
        ll_sort_list = (LinearLayout) headerview.findViewById(R.id.ll_sort_list);
        tv_sort_type = (TextView) headerview.findViewById(R.id.tv_sort_type);
        iv_sort_arrow = (ImageView) headerview.findViewById(R.id.iv_sort_arrow);
        rl_zonghepaixu = (RelativeLayout) headerview.findViewById(R.id.rl_zonghepaixu);
        rl_zonghepaixu.setOnClickListener(this);
        rl_gaodaodi = (RelativeLayout) headerview.findViewById(R.id.rl_gaodaodi);
        rl_gaodaodi.setOnClickListener(this);
        rl_didaogao = (RelativeLayout) headerview.findViewById(R.id.rl_didaogao);
        rl_didaogao.setOnClickListener(this);
        hsv_keys = (HorizontalScrollView) headerview.findViewById(R.id.hsv_keys);
        ll_water_search_btn = (LinearLayout) headerview.findViewById(R.id.ll_water_search_btn);

        //从上一个activity得到数据
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        Log.e("onCreate: ", id+"");
        getHead();
        // headerDataBean = (FindHeaderBean.DataBean) intent.getSerializableExtra("img1_info");

    }

    void initData(int topic_id, int state, int index, String key_word) {
        if (watercount == 1) {
            MyApplication.poolManager.addAsyncTask(
                    new ThreadPoolTaskHttp(this,
                            FIND_WATER_LIST,
                            Constant.REQUEST_GET,
                            new RequestParams(UrlManager.findSecondWater(topic_id, state, index, key_word)),
                            this,
                            "获取发现页面第一次加载的所有文章",
                            false));
        } else if (watercount > 1) {
            MyApplication.poolManager.addAsyncTask(
                    new ThreadPoolTaskHttp(this,
                            FIND_MORE_WATER_LIST,
                            Constant.REQUEST_GET,
                            new RequestParams(UrlManager.findSecondWater(topic_id, state, index, key_word)),
                            this,
                            "获取发现页面更多的产品",
                            false));
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.main_header_goback:
                finish();
                break;
            case R.id.rl_paixufangshi:
                Log.e("tag", "paixufangshi clicked!");
                if (btn_tag == 0) {
                    tv_sort_type.setTextColor(getResources().getColor(R.color.ColorLaunchBackground));
                    iv_sort_arrow.setBackgroundResource(R.drawable.find_water_down_arrow);
                    ll_sort_list.setVisibility(View.VISIBLE);
                    btn_tag = 1;
                } else if (btn_tag == 1) {
                    tv_sort_type.setTextColor(getResources().getColor(R.color.colorBlack));
                    iv_sort_arrow.setBackgroundResource(R.drawable.find_water_up_arrow);
                    ll_sort_list.setVisibility(View.GONE);
                    btn_tag = 0;
                } else if (btn_tag == 2) {
                    tv_sort_type.setTextColor(getResources().getColor(R.color.ColorLaunchBackground));
                    iv_sort_arrow.setBackgroundResource(R.drawable.find_water_down_arrow);
                    ll_sort_list.setVisibility(View.VISIBLE);
                    hsv_keys.setVisibility(View.GONE);
                    tv_filter.setTextColor(getResources().getColor(R.color.colorBlack));
                    btn_tag = 1;
                }
                break;
            case R.id.tv_filter:
                Log.e("tag", "filter clicked!");
                if (btn_tag == 0) {
                    btn_tag = 2;
                    ll_sort_list.setVisibility(View.GONE);
                    hsv_keys.setVisibility(View.VISIBLE);
                    tv_filter.setTextColor(getResources().getColor(R.color.ColorLaunchBackground));
                } else if (btn_tag == 1) {
                    btn_tag = 2;
                    tv_sort_type.setTextColor(getResources().getColor(R.color.colorBlack));
                    iv_sort_arrow.setBackgroundResource(R.drawable.find_water_up_arrow);
                    ll_sort_list.setVisibility(View.GONE);
                    hsv_keys.setVisibility(View.VISIBLE);
                    tv_filter.setTextColor(getResources().getColor(R.color.ColorLaunchBackground));
                } else if (btn_tag == 2) {
                    btn_tag = 0;
                    ll_sort_list.setVisibility(View.GONE);
                    hsv_keys.setVisibility(View.GONE);
                    tv_filter.setTextColor(getResources().getColor(R.color.colorBlack));
                }
                break;
            case R.id.rl_zonghepaixu:
                Log.e("tag", "zonghepaixu");
                ll_sort_list.setVisibility(View.GONE);
                tv_sort_type.setTextColor(getResources().getColor(R.color.colorBlack));
                iv_sort_arrow.setBackgroundResource(R.drawable.find_water_up_arrow);
                watercount = 1;
                initData(topic_id, 0, 1, key_word);
                break;
            case R.id.rl_gaodaodi:
                Log.e("tag", "gaodaodi");
                ll_sort_list.setVisibility(View.GONE);
                tv_sort_type.setTextColor(getResources().getColor(R.color.colorBlack));
                iv_sort_arrow.setBackgroundResource(R.drawable.find_water_up_arrow);
                watercount = 1;
                initData(topic_id, 1, 1, key_word);
                break;
            case R.id.rl_didaogao:
                Log.e("tag", "didaogao");
                ll_sort_list.setVisibility(View.GONE);
                tv_sort_type.setTextColor(getResources().getColor(R.color.colorBlack));
                iv_sort_arrow.setBackgroundResource(R.drawable.find_water_up_arrow);
                watercount = 1;
                initData(topic_id, 2, 1, key_word);
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void FindSecondWaterAction(String resulttag, String callbackmsg) {
        if (resulttag.equals(FIND_WATER_LIST)) {
            Gson gson = new Gson();
            findSecondWaterBean = gson.fromJson(callbackmsg, FindSecondWaterBean.class);
            Log.e("tag", "test of second water bean: " + findSecondWaterBean.getData().getData().get(0).getProductname());//test passed
            findSecondWaterAdapter = new FindSecondWaterAdapter(findSecondWaterBean, this);
            pullToRefreshListView.setAdapter(findSecondWaterAdapter);
        } else if (resulttag.equals(FIND_MORE_WATER_LIST)) {
            Gson gson = new Gson();
            FindSecondWaterBean findSecondWaterBean1 = gson.fromJson(callbackmsg, FindSecondWaterBean.class);
            for (int i = 0; i < findSecondWaterBean1.getData().getData().size(); i++) {
                findSecondWaterBean.getData().getData().add(findSecondWaterBean1.getData().getData().get(i));
            }
        } else if (resulttag.equals(TAG_GETHANDER)) {
            WaterBean waterBean = GsonUtils.fromData(callbackmsg, WaterBean.class);
            topicss = waterBean.getData().getTopicss();
            sch_keys = new TextView[topicss.getGuanJianList().size()];

            rl_main_header.setVisibility(View.VISIBLE);
            pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_grid);
            ListView listView = pullToRefreshListView.getRefreshableView();
            listView.addHeaderView(headerview, null, true);
            pullToRefreshListView.setAdapter(null);
            initIndicator(pullToRefreshListView);
            pullToRefreshListView.setOnRefreshListener(this);

            options = new DisplayImageOptions.Builder()
                    .showStubImage(R.drawable.icon_morentupian2)
                    .showImageForEmptyUri(R.drawable.icon_morentupian2)
                    .showImageOnFail(R.drawable.icon_morentupian2)
                    .cacheInMemory()
                    .cacheOnDisc()
                    .displayer(new RoundedBitmapDisplayer(0))
                    .build();

            tv_main_header_title.setText(topicss.getName().toString());
            imageLoader.displayImage(topicss.getBigimg(), header_big_img, options, animateFirstListener);
            header_img_txt.setText(topicss.getMiaoshu());
            ll_sort_list.setVisibility(View.GONE);
            hsv_keys.setVisibility(View.GONE);

            //用循环方式动态添加搜索关键词
            for (i = 0; i < topicss.getGuanJianList().size(); i++) {
                sch_keys[i] = new TextView(this);
                sch_keys[i].setId(1000 + i);
                sch_keys[i].setText(topicss.getGuanJianList().get(i));
                sch_keys[i].setLayoutParams(new TextSwitcher.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                sch_keys[i].setBackgroundResource(R.color.colorLightGrey);
                sch_keys[i].setPadding(20, 7, 20, 7);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);//定义一个LayoutParams
                layoutParams.setMargins(0, 0, 20, 0);//4个参数按顺序分别是左上右下
                sch_keys[i].setLayoutParams(layoutParams);
                ll_water_search_btn.addView(sch_keys[i]);
                sch_keys[i].setOnClickListener(new SearchKeyClickedListener(sch_keys[i], sch_keys[i].getText().toString()));
            }
            topic_id = topicss.getId();
            state = 0;
            watercount = 1;
            key_word = "";
            initData(topic_id, state, watercount, key_word);
        }
    }

    @Override
    public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
        super.failCallBack(arg0, resultTag, isShowDiolog);
        Log.e("tag", "second waterfall failed!");
    }

    @Override
    public void startCallBack(String resultTag, boolean isShowDiolog, String showTitle) {
        super.startCallBack(resultTag, isShowDiolog, showTitle);
    }

    @Override
    public void cancelCallBack(String resultTag) {
        super.cancelCallBack(resultTag);
    }

    @Override
    public void reLoginCallBack(String resultTag, boolean isShowDiolog) {
        super.reLoginCallBack(resultTag, isShowDiolog);
    }

    @Override
    public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        Log.e("tag", "second waterfall success: " + callBackMsg);
        FindSecondWaterAction(resultTag, callBackMsg);
    }

    @Override
    public void failShowCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.failShowCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {}

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        Log.e("tag", "pull up in second water!!");
        watercount++;
        initData(topic_id, state, watercount, key_word);
        new GetDataTask().execute();
    }

    //sl: 显示图片所需的一个静态类，来自晒图代码
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

    class SearchKeyClickedListener implements View.OnClickListener {
        private TextView tv;
        private String key_word;

        private SearchKeyClickedListener(TextView tv, String key_word) {
            this.tv = tv;
            this.key_word = key_word;
        }

        @Override
        public void onClick(View v) {
            watercount = 1;
            Log.e("tag", "key word: " + key_word);
            Log.e("tag", "key word in param: " + tv.getText().toString());
            hsv_keys.setVisibility(View.GONE);
            tv_filter.setTextColor(getResources().getColor(R.color.colorBlack));
            initData(topic_id, 0, 1, key_word);
            //search_input.setText(key_word);//当点击搜索框下面的文字时候，将上面的搜索框的文字设置成按钮里面的文字
        }
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

    //瀑布流所需，貌似无用，先不要删 狗鸡巴安卓操你妈逼 日你奶奶的草拟吗
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
            pullToRefreshListView.onRefreshComplete();
        }
    }

    //瀑布流所需
    private class GetDataTask extends AsyncTask<Void, Void, Void> {
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
            findSecondWaterAdapter.notifyDataSetChanged();
            pullToRefreshListView.onRefreshComplete();
        }
    }
    private String TAG_GETHANDER = "TAG_GETHANDER";
    private void getHead(){
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(this,
                        TAG_GETHANDER,
                        Constant.REQUEST_GET,
                        new RequestParams(UrlManager.findSecondWater(id,0,1,"")),
                        this,
                        "获取Hander",
                        false));
    }
}