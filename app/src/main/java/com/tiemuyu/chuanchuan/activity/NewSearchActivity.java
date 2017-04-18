package com.tiemuyu.chuanchuan.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.tiemuyu.chuanchuan.activity.adapter.SearchWaterAdapter;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.SearchResultBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;

import org.xutils.http.RequestParams;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @项目名： 227androidpay-master
 * @包名： com.tiemuyu.chuanchuan.activity
 * @类描述：
 * @创建人： hr
 * @创建时间： 2017/3/8
 * @version：
 */

public class NewSearchActivity extends BaseActivityG implements View.OnClickListener, PullToRefreshBase.OnRefreshListener2<ListView> {

    private LinearLayout btn_bk;
    private EditText et_search;
    private TextView tv_search_btn;
    private PullToRefreshListView pullToRefreshListView;
    private SearchWaterAdapter searchWaterAdapter;
    private RelativeLayout zonghepaixu;
    private RelativeLayout price_sort;
    private ImageView price_ord_arrow;
    private RelativeLayout renqi_sort;
    private ImageView renqi_ord_arrow;

    private int btn_state;//0-综合，1-价格从低到高，2-价格从高到低，3-人气从高到低，4-人气从低到高
    private static final String KEY_WORD_SEARCH = "KEY_WORD_SEARCH";
    private static final String MORE_SEARCH_RESULT = "MORE_SEARCH_RESULT";
    private int pageNum;
    private String ord_by;
    private String key_wd;

    private SearchResultBean searchResultBean; //储存搜索结果的bean

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch(v.getId()){
            case R.id.iv_search_bk:
                finish();
                break;
            case R.id.rl_zonghe:
                if (btn_state == 0) {
                    price_ord_arrow.setImageResource(R.drawable.search_mid);
                    renqi_ord_arrow.setImageResource(R.drawable.search_mid);
                    //todo 重新发请求拉数据
//                    pageNum = 1;
//                    ord_by = "price";
//                    initData(key_wd, pageNum, ord_by);
                    break;
                }
                else if (btn_state == 1 || btn_state == 2 || btn_state == 3 || btn_state == 4) {
                    btn_state = 0;
                    price_ord_arrow.setImageResource(R.drawable.search_mid);
                    renqi_ord_arrow.setImageResource(R.drawable.search_mid);
                    //todo 重新发请求拉数据
                    pageNum = 1;
                    ord_by = "price";
                    initData(key_wd, pageNum, ord_by);
                    break;
                }
            case R.id.rl_jiage:
                if (btn_state == 0) {
                    btn_state = 1;
                    price_ord_arrow.setImageResource(R.drawable.search_up);
                    //todo 重新发请求拉数据
                    pageNum = 1;
                    ord_by = "price";
                    initData(key_wd, pageNum, ord_by);
                    break;
                }
                if (btn_state == 1) {
                    btn_state = 2;
                    price_ord_arrow.setImageResource(R.drawable.search_down);
                    //todo 重新发请求拉数据
                    pageNum = 1;
                    ord_by = "pricedesc";
                    initData(key_wd, pageNum, ord_by);

                    break;
                }
                if (btn_state == 2) {
                    btn_state = 1;
                    price_ord_arrow.setImageResource(R.drawable.search_up);
                    //todo 重新发请求拉数据
                    pageNum = 1;
                    ord_by = "price";
                    initData(key_wd, pageNum, ord_by);
                    break;
                }
                if (btn_state == 3) {
                    btn_state = 1;
                    price_ord_arrow.setImageResource(R.drawable.search_down);
                    renqi_ord_arrow.setImageResource(R.drawable.search_mid);
                    //todo 重新发请求拉数据
                    pageNum = 1;
                    ord_by = "price";
                    initData(key_wd, pageNum, ord_by);
                    break;
                }
                if (btn_state == 4) {
                    btn_state = 1;
                    price_ord_arrow.setImageResource(R.drawable.search_down);
                    renqi_ord_arrow.setImageResource(R.drawable.search_mid);
                    //todo 重新发请求拉数据
                    pageNum = 1;
                    ord_by = "price";
                    initData(key_wd, pageNum, ord_by);
                    break;
                }
            case R.id.rl_renqi:
                if (btn_state == 0) {
                    btn_state = 3;
                    renqi_ord_arrow.setImageResource(R.drawable.search_down);
                    //todo 重新发请求拉数据
                    pageNum = 1;
                    ord_by = "renqi";
                    initData(key_wd, pageNum, ord_by);
                    break;
                }
                else if (btn_state == 1) {
                    btn_state = 3;
                    price_ord_arrow.setImageResource(R.drawable.search_mid);
                    renqi_ord_arrow.setImageResource(R.drawable.search_down);
                    //todo 重新发请求拉数据
                    pageNum = 1;
                    ord_by = "renqi";
                    initData(key_wd, pageNum, ord_by);
                    break;
                }
                else if (btn_state == 2) {
                    btn_state = 3;
                    price_ord_arrow.setImageResource(R.drawable.search_mid);
                    renqi_ord_arrow.setImageResource(R.drawable.search_down);
                    //todo 重新发请求拉数据
                    pageNum = 1;
                    ord_by = "renqi";
                    initData(key_wd, pageNum, ord_by);
                    break;
                }
                else if (btn_state == 3) {
                    btn_state = 4;
                    price_ord_arrow.setImageResource(R.drawable.search_mid);
                    renqi_ord_arrow.setImageResource(R.drawable.search_up);
                    //todo 重新发请求拉数据
                    pageNum = 1;
                    ord_by = "renqidesc";
                    initData(key_wd, pageNum, ord_by);
                    break;
                }
                else if (btn_state == 4) {
                    btn_state = 3;
                    price_ord_arrow.setImageResource(R.drawable.search_mid);
                    renqi_ord_arrow.setImageResource(R.drawable.search_down);
                    //todo 重新发请求拉数据
                    pageNum = 1;
                    ord_by = "renqi";
                    initData(key_wd, pageNum, ord_by);
                    break;
                }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_search_layout);
        btn_state = 0;
        pageNum = 1;
        btn_bk = (LinearLayout) findViewById(R.id.iv_search_bk);
        btn_bk.setOnClickListener(this);
        tv_search_btn = (TextView) findViewById(R.id.tv_search_btn);
        tv_search_btn.setOnClickListener(this);
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.search_resutl_list);
        initIndicator(pullToRefreshListView);
        zonghepaixu = (RelativeLayout) findViewById(R.id.rl_zonghe);
        zonghepaixu.setOnClickListener(this);
        price_sort = (RelativeLayout) findViewById(R.id.rl_jiage);
        price_sort.setOnClickListener(this);
        price_ord_arrow = (ImageView) findViewById(R.id.iv_jiagejiantou);
        renqi_sort = (RelativeLayout) findViewById(R.id.rl_renqi);
        renqi_sort.setOnClickListener(this);
        renqi_ord_arrow = (ImageView) findViewById(R.id.iv_renqijiantou);
        et_search = (EditText) findViewById(R.id.cet_search);
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Log.e("tag", "keyboard search: " + et_search.getText());
                    //todo 点击搜索之后的网络请求
                    String key = "";
                    try {
                        key = URLEncoder.encode(et_search.getText().toString(), "utf-8");//先把get请求中的汉字转码
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    btn_state = 0;
                    key_wd = key;
                    ord_by = "price";
                    initData(key, pageNum, ord_by);
                    return true;
                }
                return false;
            }
        });
        findViewById(R.id.tv_search_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void moreData(String key_word, int page, String order_by, String tag) {
        String url = UrlManager.keyWordSearch(key_word, page, order_by);
        MyApplication.poolManager.addAsyncTask(//史力：发现页面头部的图文请求
                new ThreadPoolTaskHttp(this,
                        tag,
                        Constant.REQUEST_GET,
                        new RequestParams(url),
                        this,
                        "获取搜索关键词的结果",
                        false));
    }

    void initData(String key_word, int page, String order_by) {
        String url = UrlManager.keyWordSearch(key_word, page, order_by);
        Log.e("tag", "request url: " + url);
        MyApplication.poolManager.addAsyncTask(//史力：发现页面头部的图文请求
                new ThreadPoolTaskHttp(this,
                        KEY_WORD_SEARCH,
                        Constant.REQUEST_GET,
                        new RequestParams(url),
                        this,
                        "获取搜索关键词的结果",
                        false));

        //测试用别的URL，成功
/*        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(this,
                        KEY_WORD_SEARCH,
                        Constant.REQUEST_GET,
//                        new RequestParams(UrlManager.Get_Water()),
//                        new RequestParams(UrlManager.keyWordSearch(key_word, page, order_by)),
                        new RequestParams("http://test.myappcc.com/api/apppi?getsearch&key=&page=1&orderby=price"),
                        this,
                        "获取最新报价",
                        false));*/
    }

    private void SearchAction(String callbackmsg, String resultTag) {
        if (resultTag.equals(KEY_WORD_SEARCH)) {
            Gson gson = new Gson();
            searchResultBean = gson.fromJson(callbackmsg, SearchResultBean.class);
            Log.e("tag", "data in json: " + searchResultBean.getData().getPageData().getRows().get(0).getProductName());//测试输出成功
            // todo 将得到的数据放到adapter里面显示出来
            searchWaterAdapter = new SearchWaterAdapter(searchResultBean, this);
            pullToRefreshListView.setAdapter(searchWaterAdapter);
            pullToRefreshListView.setOnRefreshListener(this);
        } else if (resultTag.equals(MORE_SEARCH_RESULT)) {
            SearchResultBean tmp_bean;//临时的bean用于储存新拉到的数据
            Gson gson = new Gson();
            tmp_bean = gson.fromJson(callbackmsg, SearchResultBean.class);
            for (int i = 0; i < tmp_bean.getData().getPageData().getRows().size(); i++) {
                searchResultBean.getData().getPageData().getRows().add(tmp_bean.getData().getPageData().getRows().get(i));
            }
        }
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        Log.e("tag", "pull up in second water!!");
        pageNum++;
        moreData(key_wd, pageNum, ord_by, MORE_SEARCH_RESULT);
        new GetDataTask().execute();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {}

    @Override
    public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
        super.failCallBack(arg0, resultTag, isShowDiolog);
        Log.e("tag", "search result callback fail!");
        Log.e("tag", arg0.toString());
        Log.e("tag", "result tag: " + resultTag);
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
        Log.e("tag", "search result callback succeed!");
        Log.e("tag", "callback msg: " + callBackMsg);
        SearchAction(callBackMsg, resultTag);
    }

    @Override
    public void failShowCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.failShowCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
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
            searchWaterAdapter.notifyDataSetChanged();
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
