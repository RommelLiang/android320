package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.FindHeaderBean;
import com.tiemuyu.chuanchuan.activity.bean.FindTopicBean;
import com.tiemuyu.chuanchuan.activity.bean.TopicHeander;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;

import org.xutils.http.RequestParams;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @项目名： 227androidpay-master
 * @包名： com.tiemuyu.chuanchuan.activity
 * @类描述：
 * @创建人： 史力
 * @创建时间： 2017/2/27
 * @version：
 */

public class FindTopicActivity extends BaseActivityG implements View.OnClickListener {

    TextView find_topic_header_title;
    LinearLayout find_topic_header_goback;
    private View headerview;
    private ImageView header_big_img;
    private TextView header_img_txt;
    private EditText search_input;
    private TextView search_btn;
    private LinearLayout ll_search_btns;
    TextView sch_keys[];
    PullToRefreshListView pullToRefreshListView;

    //FindHeaderBean.DataBean headerDataBean;
    FindTopicBean findTopicBean;
    FindTopicAdapter findTopicAdapter;

    protected ImageLoader imageLoader = ImageLoader.getInstance();//sl：显示图片所需的一个变量
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();//sl:显示图片所需
    DisplayImageOptions options;//sl：显示图片所需变量
    private int id;
    int i = 0;
    private static final String SEARCH_ARTICLE_LIST = "SEARCH_ARTICLE_LIST";
    private TopicHeander.DataBean.TopicssBean topicss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //史力：从上一个activity接收数据
        Intent intent = getIntent();
        //headerDataBean = (FindHeaderBean.DataBean) intent.getSerializableExtra("img1_info");
        id = intent.getIntExtra("id",0);

        setContentView(R.layout.find_topic_layout);

        //给控件赋值
        find_topic_header_goback = (LinearLayout) findViewById(R.id.header_find_goback);
        find_topic_header_title = (TextView) findViewById(R.id.tv_find_topic_title);
        headerview = View.inflate(this, R.layout.find_topic_header, null);
        header_big_img = (ImageView) headerview.findViewById(R.id.header_big_img);
        header_img_txt = (TextView) headerview.findViewById(R.id.header_img_txt);
        search_input = (EditText) headerview.findViewById(R.id.find_topic_header_serach_txt);
        search_btn = (TextView) headerview.findViewById(R.id.search_btn);
        ll_search_btns = (LinearLayout) headerview.findViewById(R.id.ll_search_btns);
        search_btn.setOnClickListener(this);
        find_topic_header_goback.setOnClickListener(this);
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.lv_find_topic);
        getHead();

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch(v.getId()) {
            case R.id.header_find_goback:
                finish();
                break;
            case R.id.search_btn:
                Log.e("tag", "search something here...");
                if (search_input.getText().toString().equals("")) {
                    Toast.makeText(this, "请输入搜索内容", Toast.LENGTH_SHORT).show();
                    Log.e("tag", "search nothing!!!");
                }
                Log.e("tag", "search: " + search_input.getText().toString());
                initData(search_input.getText().toString(), topicss.getId());
                break;
        }
    }

    private void initData(String key_word, int topic_id) {
        Log.e("tag", "url: " + UrlManager.getSearchArticles(key_word, topic_id));
        try {
            String word = URLEncoder.encode(key_word, "UTF-8");
            Log.e("word", "initData: "+word );
            String url = UrlManager.getSearchArticles(word, topic_id);
            url = url.replaceAll(" ", "");
            Log.e("tag", url);
            MyApplication.poolManager.addAsyncTask(//史力：发现页面头部的图文请求
                    new ThreadPoolTaskHttp(this,
                            SEARCH_ARTICLE_LIST,
                            Constant.REQUEST_GET,
                            new RequestParams(url),
                            this,
                            "获取发现页面第一次加载的所有文章",
                            false));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e("Exception", "initData: " + e.getLocalizedMessage());
        }

    }

    private void FindTopicAction(String resultTag, String callbackMessage) {
        if (resultTag.equals(SEARCH_ARTICLE_LIST)) {
            Gson gson  = new Gson();
            Log.e("FindTopicAction: ", callbackMessage);
            findTopicBean = gson.fromJson(callbackMessage, FindTopicBean.class);
            Log.e("tag", "In callback function: " + findTopicBean.getData().get(0).getMiaoshu());
            findTopicAdapter = new FindTopicAdapter();
            pullToRefreshListView.setAdapter(findTopicAdapter);
        } else if (resultTag.equals(TAG_GETHANDER)) {
            TopicHeander topicHeander = GsonUtils.fromData(callbackMessage, TopicHeander.class);
            topicss = topicHeander.getData().getTopicss();
            sch_keys = new TextView[topicss.getGuanJianList().size()];
            search_btn.setOnClickListener(this);

            find_topic_header_title.setText(topicss.getName());
            ListView listView = pullToRefreshListView.getRefreshableView();
            listView.addHeaderView(headerview, null, true);
            pullToRefreshListView.setAdapter(null);
            //sl: 给options赋值，参考晒图做法
            options = new DisplayImageOptions.Builder()
                    .showStubImage(R.drawable.icon_morentupian2)
                    .showImageForEmptyUri(R.drawable.icon_morentupian2)
                    .showImageOnFail(R.drawable.icon_morentupian2)
                    .cacheInMemory()
                    .cacheOnDisc()
                    .displayer(new RoundedBitmapDisplayer(0))
                    .build();
            imageLoader.displayImage(topicss.getBigimg(), header_big_img, options, animateFirstListener);
            header_img_txt.setText(topicss.getMiaoshu());
            //用循环动态添加按钮
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
                ll_search_btns.addView(sch_keys[i]);
                sch_keys[i].setOnClickListener(new SearchKeyClickedListener(sch_keys[i], sch_keys[i].getText().toString()));
            }
            initData("", topicss.getId());
        }
    }

    //sl：网络请求所需实现的接口
    @Override
    public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
        super.failCallBack(arg0, resultTag, isShowDiolog);
        Log.e(resultTag, "failed: " + arg0.getLocalizedMessage());
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
        Log.e("tag", resultTag + "; " + callBackMsg);
        FindTopicAction(resultTag, callBackMsg);
    }

    @Override
    public void failShowCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.failShowCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
    }
    //以上为网络请求所需实现的接口

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
            Log.e("tag", "btn id: " + tv.getId());
            //search_input.setText(key_word);//当点击搜索框下面的文字时候，将上面的搜索框的文字设置成按钮里面的文字
            initData(key_word,topicss.getId());
        }
    }

    class FindTopicAdapter extends BaseAdapter {

//        Intent intent;

        class ViewHolder{
            LinearLayout ll_find_topic_adapter;
            ImageView big_img;
            TextView art_title;
            TextView art_des;
            TextView view_count;
        }

        @Override
        public int getCount() {
            return findTopicBean.getData().size();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            final ViewHolder holder;
            if (convertView == null) {
                view = getLayoutInflater().inflate(R.layout.find_search_article_item, parent, false);
                holder = new ViewHolder();
                holder.ll_find_topic_adapter = (LinearLayout) view.findViewById(R.id.ll_find_topic_adapter);
                holder.big_img = (ImageView) view.findViewById(R.id.search_article_img);
                holder.art_title = (TextView) view.findViewById(R.id.find_search_title);
                holder.art_des = (TextView) view.findViewById(R.id.art_description);
                holder.view_count = (TextView) view.findViewById(R.id.search_article_view_count);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            imageLoader.displayImage(findTopicBean.getData().get(position).getImg(), holder.big_img, options, animateFirstListener);
            holder.art_title.setText(findTopicBean.getData().get(position).getName());
            holder.art_des.setText(findTopicBean.getData().get(position).getMiaoshu());
            holder.view_count.setText(findTopicBean.getData().get(position).getLooksum() + "");
            //给intent提供设置
            final int pos = position;
            holder.ll_find_topic_adapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(FindTopicActivity.this, MyWebview.class);
                    Log.e("tag", findTopicBean.getData().get(pos).getUrl());
                    intent.putExtra("Intent_Data_Packet", findTopicBean.getData().get(pos).getUrl());
                    Log.e("tag", findTopicBean.getData().get(pos).getName());
                    intent.putExtra("title", findTopicBean.getData().get(pos).getName());
                    intent.putExtra("type", 1);
                    Log.e("tag", findTopicBean.getData().get(pos).getImg());
                    intent.putExtra("img_url", findTopicBean.getData().get(pos).getImg());
                    startActivity(intent);
                }
            });
            return view;
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