package com.tiemuyu.chuanchuan.activity.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
import com.tiemuyu.chuanchuan.activity.FindTopicActivity;
import com.tiemuyu.chuanchuan.activity.FindWaterActivity;
import com.tiemuyu.chuanchuan.activity.MyApplication;
import com.tiemuyu.chuanchuan.activity.MyShaitu;
import com.tiemuyu.chuanchuan.activity.MyWebview;
import com.tiemuyu.chuanchuan.activity.ProtocolActivity;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.FindHeaderBean;
import com.tiemuyu.chuanchuan.activity.bean.FindWaterBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.view.URL;

import org.xutils.http.RequestParams;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lonze on 2016/8/23.
 */
public class FindFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener2<ListView>, View.OnClickListener {

    //没用
    boolean first = true;
    private View view;//fragment对应的主界面view
    private View headerView;//fagment的header对应的view变量
    private PullToRefreshListView pullToRefreshListView;
    private static final String TAG_FIND_HEADER = "TAG_FIND_HEADER";//sl:请求头部数据的标签
    private static final String TAG_FIND_WATER = "TAG_FIND_WATER";//sl:请求发现页面下方瀑布流标签
    private static final String TAG_GET_MORE_FIND = "TAG_GET_MORE_FIND";//sl:请求更多的瀑布流数据标签
    private FindHeaderBean findHeaderBean;
    private FindWaterBean findWaterBean;
    DisplayImageOptions options;//sl：显示图片所需变量
    protected ImageLoader imageLoader = ImageLoader.getInstance();//sl：显示图片所需的一个变量
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();//sl:
    FindAdapter findApdater;
    private int page_num = 1;

    //header的所有控件
    ImageView find_img1;
    ImageView find_img2;
    ImageView find_img3;
    ImageView find_img4;
    ImageView find_img5;
    ImageView find_img6;

    TextView find_txt1;
    TextView find_txt2;
    TextView find_txt3;
    TextView find_txt4;
    TextView find_txt5;
    TextView find_txt6;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        view = inflater.inflate(R.layout.discovery_fragment, null);//需要根据fragment的布局进行修改
        headerView = View.inflate(getActivity(), R.layout.find_header, null);

        //sl: 给options赋值，参考晒图做法
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.icon_morentupian2)
                .showImageForEmptyUri(R.drawable.icon_morentupian2)
                .showImageOnFail(R.drawable.icon_morentupian2)
                .cacheInMemory()
                .cacheOnDisc()
                .displayer(new RoundedBitmapDisplayer(20))
                .build();

        //给发现页面的header里面控件赋值
        find_img1 = (ImageView) headerView.findViewById(R.id.find_img1);
        find_img2 = (ImageView) headerView.findViewById(R.id.find_img2);
        find_img3 = (ImageView) headerView.findViewById(R.id.find_img3);
        find_img4 = (ImageView) headerView.findViewById(R.id.find_img4);
        find_img5 = (ImageView) headerView.findViewById(R.id.find_img5);
        find_img6 = (ImageView) headerView.findViewById(R.id.find_img6);

        find_txt1 = (TextView) headerView.findViewById(R.id.find_txt1);
        find_txt2 = (TextView) headerView.findViewById(R.id.find_txt2);
        find_txt3 = (TextView) headerView.findViewById(R.id.find_txt3);
        find_txt4 = (TextView) headerView.findViewById(R.id.find_txt4);
        find_txt5 = (TextView) headerView.findViewById(R.id.find_txt5);
        find_txt6 = (TextView) headerView.findViewById(R.id.find_txt6);


        initData();

        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.discover_lv);
        pullToRefreshListView.setAdapter(null);
        initIndicator(pullToRefreshListView);
        //sl: 给pulltorefreshlistview添加header
        ListView listView = pullToRefreshListView.getRefreshableView();
        listView.addHeaderView(headerView);
        pullToRefreshListView.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Bundle data;
        Intent intent;
        switch (v.getId()) {
            case R.id.find_img1://点击发现头部第一个图文框
            case R.id.find_txt1:
                if (findHeaderBean.getData().get(0).getStatusname().toString().equals("文章类型")) {//如果是文章列表
                    Log.e("message", "img1 and txt1 clicked!");
                    Log.e("tag", "list: " + findHeaderBean.getData().get(0).getStatusname().toString());

                    Log.e("tag", "water: " + findHeaderBean.getData().get(5).getStatusname().toString());
                    intent = new Intent(this.getActivity(), FindTopicActivity.class);
                    intent.putExtra("id",findHeaderBean.getData().get(0).getId());
                    startActivity(intent);
                } else {
                    Log.e("tag", "water: " + findHeaderBean.getData().get(0).getStatusname().toString());
                    intent = new Intent(getActivity(), FindWaterActivity.class);
                    intent.putExtra("id",findHeaderBean.getData().get(0).getId());
                    startActivity(intent);
                }
                break;
            case R.id.find_img2://第二个……
            case R.id.find_txt2:
                if (findHeaderBean.getData().get(1).getStatusname().toString().equals("文章类型")) {
                    Log.e("message", "img2 and txt2 clicked!");
                    Log.e("tag", "list: " + findHeaderBean.getData().get(1).getStatusname().toString());
                    Log.e("tag", "water: " + findHeaderBean.getData().get(5).getStatusname().toString());
                    intent = new Intent(this.getActivity(), FindTopicActivity.class);
                    intent.putExtra("id",findHeaderBean.getData().get(1).getId());
                    startActivity(intent);
                } else {
                    Log.e("tag", "water: " + findHeaderBean.getData().get(1).getStatusname().toString());
                    intent = new Intent(getActivity(), FindWaterActivity.class);
                    intent.putExtra("id",findHeaderBean.getData().get(1).getId());
                    startActivity(intent);
                }
                break;
            case R.id.find_img3://第3个……
            case R.id.find_txt3:
                if (findHeaderBean.getData().get(2).getStatusname().toString().equals("文章类型")) {
                    Log.e("message", "img3 and txt3 clicked!");
                    Log.e("tag", "list: " + findHeaderBean.getData().get(2).getStatusname().toString());
                    Log.e("tag", "water: " + findHeaderBean.getData().get(5).getStatusname().toString());
                    intent = new Intent(this.getActivity(), FindTopicActivity.class);
                    intent.putExtra("id",findHeaderBean.getData().get(2).getId());
                    startActivity(intent);
                } else {
                    Log.e("tag", "water: " + findHeaderBean.getData().get(2).getStatusname().toString());
                    intent = new Intent(getActivity(), FindWaterActivity.class);
                    intent.putExtra("id",findHeaderBean.getData().get(2).getId());
                    startActivity(intent);
                }
                break;
            case R.id.find_img4://第4个……
            case R.id.find_txt4:
                if (findHeaderBean.getData().get(3).getStatusname().toString().equals("文章类型")) {
                    Log.e("message", "img4 and txt4 clicked!");
                    Log.e("tag", "list: " + findHeaderBean.getData().get(3).getStatusname().toString());
                    Log.e("tag", "water: " + findHeaderBean.getData().get(5).getStatusname().toString());
                    intent = new Intent(this.getActivity(), FindTopicActivity.class);
                    intent.putExtra("id",findHeaderBean.getData().get(3).getId());
                    startActivity(intent);
                } else {
                    Log.e("tag", "water: " + findHeaderBean.getData().get(3).getStatusname().toString());
                    intent = new Intent(getActivity(), FindWaterActivity.class);
                    intent.putExtra("id",findHeaderBean.getData().get(3).getId());
                    startActivity(intent);
                }
                break;
            case R.id.find_img5://第5个……
            case R.id.find_txt5:
                if (findHeaderBean.getData().get(4).getStatusname().toString().equals("文章类型")) {
                    Log.e("message", "img5 and txt5 clicked!");
                    Log.e("tag", "list: " + findHeaderBean.getData().get(4).getStatusname().toString());
                    Log.e("tag", "water: " + findHeaderBean.getData().get(5).getStatusname().toString());
                    intent = new Intent(this.getActivity(), FindTopicActivity.class);
                    intent.putExtra("id",findHeaderBean.getData().get(4).getId());
                    startActivity(intent);
                } else {
                    Log.e("tag", "water: " + findHeaderBean.getData().get(4).getStatusname().toString());
                    intent = new Intent(getActivity(), FindWaterActivity.class);
                    intent.putExtra("id",findHeaderBean.getData().get(4).getId());
                    startActivity(intent);
                }
                break;
            case R.id.find_img6://第6个……
            case R.id.find_txt6:
                if (findHeaderBean.getData().get(5).getStatusname().toString().equals("文章类型")) {
                    Log.e("message", "img6 and txt6 clicked!");
                    Log.e("tag", "list: " + findHeaderBean.getData().get(5).getStatusname().toString());
                    Log.e("tag", "water: " + findHeaderBean.getData().get(5).getStatusname().toString());
                    intent = new Intent(this.getActivity(), FindTopicActivity.class);
                    intent.putExtra("id",findHeaderBean.getData().get(5).getId());
                    startActivity(intent);
                } else {
                    Log.e("tag", "water: " + findHeaderBean.getData().get(5).getStatusname().toString());
                    intent = new Intent(getActivity(), FindWaterActivity.class);
                    intent.putExtra("id",findHeaderBean.getData().get(5).getId());
                    startActivity(intent);
                }
                break;
        }
    }

    //sl: 发送网络请求
    private void initData() {
        MyApplication.poolManager.addAsyncTask(//史力：发现页面头部的图文请求
                new ThreadPoolTaskHttp(getActivity(),
                        TAG_FIND_HEADER,
                        Constant.REQUEST_GET,
                        new RequestParams(UrlManager.findHeader()),
                        this,
                        "获取发现页面头部",
                        false));

        MyApplication.poolManager.addAsyncTask(//史力：发现页面下方瀑布流请求
                new ThreadPoolTaskHttp(getActivity(),
                        TAG_FIND_WATER,
                        Constant.REQUEST_GET,
                        new RequestParams(UrlManager.findWater(1)),
                        this,
                        "获取发现页面瀑布流",
                        false));

        MyApplication.poolManager.addAsyncTask(//史力：高总测试
                new ThreadPoolTaskHttp(getActivity(),
                        "TEST_GWH",
                        Constant.REQUEST_GET,
                        new RequestParams(UrlManager.testGWH()),
                        this,
                        "高总测试",
                        false));
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        logetag("find onrefresh down called!");
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        logetag("find onrefresh up called!");
        page_num++;
        logetag("page number before refresh: " + page_num);
        MyApplication.poolManager.addAsyncTask(//史力：发现页面下方瀑布流请求
                new ThreadPoolTaskHttp(getActivity(),
                        TAG_GET_MORE_FIND,
                        Constant.REQUEST_GET,
                        new RequestParams(UrlManager.findWater(page_num)),
                        this,
                        "获取发现页面瀑布流",
                        false));
        new GetDataTask().execute();
    }

    //sl: 自定义的adapter
    class FindAdapter extends BaseAdapter {

         int pos;

        private class ViewHolder {
            private LinearLayout find_first_article_adapter;
            public TextView title_txt;
            public ImageView main_img;
            public TextView desp_txt;
            public TextView view_num;
        }

        public FindAdapter() {}

        @Override
        public int getCount() {
            return findWaterBean.getData().getList().size();
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
            pos = position;
            if (convertView == null) {
                view = getActivity().getLayoutInflater().inflate(R.layout.find_item_layout, parent, false);
                holder = new ViewHolder();
                holder.find_first_article_adapter = (LinearLayout) view.findViewById(R.id.find_first_article_adapter);
                holder.title_txt = (TextView) view.findViewById(R.id.find_item_ttl);
                holder.main_img = (ImageView) view.findViewById(R.id.find_main_pic);
                holder.desp_txt = (TextView) view.findViewById(R.id.desp_txt);
                holder.view_num = (TextView) view.findViewById(R.id.view_num_txt);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.title_txt.setText(findWaterBean.getData().getList().get(position).getName());
            if (position == 0)
                logetag("position 0 pic: " + findWaterBean.getData().getList().get(position).getImg());
            imageLoader.displayImage(findWaterBean.getData().getList().get(position).getImg(), holder.main_img, options, animateFirstListener);
            holder.desp_txt.setText(findWaterBean.getData().getList().get(position).getMiaoshu());
            holder.view_num.setText(findWaterBean.getData().getList().get(position).getLooksum() + "");
            final Intent intent = new Intent(getActivity(), MyWebview.class);
            intent.putExtra("Intent_Data_Packet", findWaterBean.getData().getList().get(pos).getUrl());
            intent.putExtra("title", findWaterBean.getData().getList().get(pos).getName());
            intent.putExtra("type", 1);
            intent.putExtra("img_url", findWaterBean.getData().getList().get(pos).getImg());
            //sl：每个adapter的点击事件
            holder.find_first_article_adapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("tag", "find fragment article clicked!");

                    startActivity(intent);
                }
            });
            return view;
        }
    }

    @Override
    public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
        super.failCallBack(arg0, resultTag, isShowDiolog);
        logetag("find header callback failed!" + resultTag);
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
        logetag("find header callback success!");
        logetag(callBackMsg);
        callBackAction(callBackMsg, resultTag);
    }

    @Override
    public void failShowCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.failShowCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void goBack(){}

    private void logetag(String outinfo) {
        Log.e("TAG", outinfo);
    }

    private void callBackAction(String callbackMsg, String resultTag) {
        if (resultTag.equals(TAG_FIND_HEADER)) {
            Gson gson = new Gson();
            findHeaderBean = gson.fromJson(callbackMsg, FindHeaderBean.class);

            //sl：给每个发现头控件加载图文
            imageLoader.displayImage(findHeaderBean.getData().get(0).getImg(), find_img1, options, animateFirstListener);
            find_txt1.setText(findHeaderBean.getData().get(0).getName());
            imageLoader.displayImage(findHeaderBean.getData().get(1).getImg(), find_img2, options, animateFirstListener);
            find_txt2.setText(findHeaderBean.getData().get(1).getName());
            imageLoader.displayImage(findHeaderBean.getData().get(2).getImg(), find_img3, options, animateFirstListener);
            find_txt3.setText(findHeaderBean.getData().get(2).getName());
            imageLoader.displayImage(findHeaderBean.getData().get(3).getImg(), find_img4, options, animateFirstListener);
            find_txt4.setText(findHeaderBean.getData().get(3).getName());
            imageLoader.displayImage(findHeaderBean.getData().get(4).getImg(), find_img5, options, animateFirstListener);
            find_txt5.setText(findHeaderBean.getData().get(4).getName());
            imageLoader.displayImage(findHeaderBean.getData().get(5).getImg(), find_img6, options, animateFirstListener);
            find_txt6.setText(findHeaderBean.getData().get(5).getName());
            //sl:给每个控件定义事件响应
            find_img1.setOnClickListener(this);
            find_img2.setOnClickListener(this);
            find_img3.setOnClickListener(this);
            find_img4.setOnClickListener(this);
            find_img5.setOnClickListener(this);
            find_img6.setOnClickListener(this);

            find_txt1.setOnClickListener(this);
            find_txt2.setOnClickListener(this);
            find_txt3.setOnClickListener(this);
            find_txt4.setOnClickListener(this);
            find_txt5.setOnClickListener(this);
            find_txt6.setOnClickListener(this);

        } else if (resultTag.equals(TAG_FIND_WATER)) {//sl: 如果是拉取瀑布流，执行此代码
            logetag("find get water success!");
            Gson gson = new Gson();
            findWaterBean = gson.fromJson(callbackMsg, FindWaterBean.class);
            logetag("find get water result:" + findWaterBean.getData().getList().get(0).getMiaoshu());//史力：测试通过
            findApdater = new FindAdapter();
            pullToRefreshListView.setAdapter(findApdater);
        } else if (resultTag.equals(TAG_GET_MORE_FIND)) {
            logetag("find more water call back success!");
            logetag("page num in callback: " + page_num);
            Gson gson = new Gson();
            FindWaterBean tmp = gson.fromJson(callbackMsg, FindWaterBean.class);
            for (FindWaterBean.DataBean.ListBean tmp_list_bean : tmp.getData().getList()) {
                findWaterBean.getData().getList().add(tmp_list_bean);
            }
            logetag("size of list after refresh: " + findWaterBean.getData().getList().size());
            findApdater.notifyDataSetChanged();
//            findWaterBean.getData().getList().add(tmp.getData().getList());
        } else if (resultTag.equals("TEST_GWH")) {
            logetag("test json: " + callbackMsg);
        }
        // TODO else if 其他判断
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

    private class GetDataTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            return null;
            //return "" + (mItemCount++);
        }

        @Override
        protected void onPostExecute(String result) {
            //mListItems.add(result);
            findApdater.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            pullToRefreshListView.onRefreshComplete();
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
}
