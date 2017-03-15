package com.tiemuyu.chuanchuan.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.tiemuyu.chuanchuan.activity.adapter.IDataChanger;
import com.tiemuyu.chuanchuan.activity.adapter.OrderAdapter;
import com.tiemuyu.chuanchuan.activity.adapter.ViewPagerAdapter;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.OrderBean;
import com.tiemuyu.chuanchuan.activity.bean.User;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.LogUtil;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

public class UserOrderActivity extends BaseActivityG implements IDataChanger{
    private TextView tv_all, tv_pay, tv_shouhuo, tv_tuihuo;
    private ViewPager viewPager;
    private RelativeLayout findLl;
    private int dx;// 动画图片偏移量
    private int x = 0;
    private int currIndex = 0;// 当前页卡编号
    private int one;
    private View view, view1, view2, view3;
    private List<View> views;
    private ViewPagerAdapter pageAdapter;
    private final String TAG_GET_ORDER = "TAG_GET_ORDER";
    private final String TAG_ADD_ORDER = "TAG_ADD_ORDER";
    private ImageView home_back, im_no_order, im_no_order1, im_no_order2, im_no_order3;
    private ListView pullToRefreshListView, pullToRefreshListView1, pullToRefreshListView2, pullToRefreshListView3;
    private int page = 1;
    private OrderBean orderBeanAll;
    private OrderAdapter orderAdapterAll, orderAdapterAll2, orderAdapterAll3, orderAdapterAll4;
    private List<OrderBean.DataBean.RowsBean> rowsAll;//所有
    private List<OrderBean.DataBean.RowsBean> rowsWaitePay;//所有
    private List<OrderBean.DataBean.RowsBean> rowsPay;//已支付
    private List<OrderBean.DataBean.RowsBean> rowsGet;//交易完成
    private int code = 0;
    private int status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order);
        intiView();
        getOrder(0);
        findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getOrder(int status) {
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(this,
                        TAG_GET_ORDER,
                        Constant.REQUEST_GET,
                        new RequestParams(
                                UrlManager.GetOederlist(status, page)),
                        this,
                        "获取订单信息",
                        false));
    }

    private void intiView() {
        tv_all = (TextView) findViewById(R.id.tv_all);
        tv_pay = (TextView) findViewById(R.id.tv_pay);
        tv_shouhuo = (TextView) findViewById(R.id.tv_shouhuo);
        tv_tuihuo = (TextView) findViewById(R.id.tv_tuihuo);
        viewPager = (ViewPager) findViewById(R.id.vp_order);
        findLl = (RelativeLayout) findViewById(R.id.find_ll);
        home_back = (ImageView) findViewById(R.id.im_back);

        view = View.inflate(this, R.layout.order_layout, null);
        pullToRefreshListView = (ListView) view.findViewById(R.id.pull_refresh_grid);
        im_no_order = (ImageView) view.findViewById(R.id.im_no_order);

        view1 = View.inflate(this, R.layout.order_layout, null);
        pullToRefreshListView1 = (ListView) view1.findViewById(R.id.pull_refresh_grid);
        im_no_order1 = (ImageView) view1.findViewById(R.id.im_no_order);

        view2 = View.inflate(this, R.layout.order_layout, null);
        pullToRefreshListView2 = (ListView) view2.findViewById(R.id.pull_refresh_grid);
        im_no_order2 = (ImageView) view2.findViewById(R.id.im_no_order);

        view3 = View.inflate(this, R.layout.order_layout, null);
        pullToRefreshListView3 = (ListView) view3.findViewById(R.id.pull_refresh_grid);
        im_no_order3 = (ImageView) view3.findViewById(R.id.im_no_order);
        views = new ArrayList<>();
        views.add(view);
        views.add(view1);
        views.add(view2);
        views.add(view3);
        pageAdapter = new ViewPagerAdapter(views);
        viewPager.setAdapter(pageAdapter);
        viewPager.setOnPageChangeListener(listener);
        tv_all.setOnClickListener(this);
        tv_pay.setOnClickListener(this);
        tv_shouhuo.setOnClickListener(this);
        tv_tuihuo.setOnClickListener(this);
        home_back.setOnClickListener(this);
        setStripMove();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_all:
                viewPager.setCurrentItem(0);
                Toast.makeText(this, "0", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_pay:
                viewPager.setCurrentItem(1);
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_shouhuo:
                viewPager.setCurrentItem(2);
                Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_tuihuo:
                viewPager.setCurrentItem(3);
                Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.im_back:
                finish();
                break;
        }
    }

    private void setStripMove() {
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) findLl.getLayoutParams();
        linearParams.width = width / 4;
        Display currDisplay = this.getWindowManager().getDefaultDisplay();//获取屏幕当前分辨率
        int displayWidth = currDisplay.getWidth();
        one = displayWidth / 4; //设置水平动画平移大小
    }

    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Animation animation = null;
            if (position - currIndex == 1) {
                dx = x + one;
            } else if (position - currIndex == 2) {
                dx = x + one * 2;
            } else if (position - currIndex == 3) {
                dx = x + one * 3;
            } else if (position - currIndex == -1) {
                dx = x - one;
            } else if (position - currIndex == -2) {
                dx = x - one * 2;
            } else if (position - currIndex == -3) {
                dx = x - one * 3;
            }
            switch (position) {
                case 0:
                    animation = new TranslateAnimation(x, dx, 0, 0);
                    break;
                case 1:
                    animation = new TranslateAnimation(x, dx, 0, 0);
                    break;
                case 2:
                    animation = new TranslateAnimation(x, dx, 0, 0);
                    break;
                case 3:
                    animation = new TranslateAnimation(x, dx, 0, 0);
                    break;
            }
            if (position - currIndex == 1) {
                x += one;
            } else if (position - currIndex == 2) {
                x = x + one * 2;
            } else if (position - currIndex == 3) {
                x = x + one * 3;
            } else if (position - currIndex == -1) {
                x = x - one;
            } else if (position - currIndex == -2) {
                x = x - one * 2;
            } else if (position - currIndex == -3) {
                x = x - one * 3;
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
        LogUtil.LogRommel(callBackMsg);
        if (resultTag.equals(TAG_GET_ORDER)) {
            orderBeanAll = GsonUtils.fromData(callBackMsg, OrderBean.class);
            rowsAll = orderBeanAll.getData().getRows();
            setView();
        }
    }

    private void setView() {
        orderAdapterAll = new OrderAdapter(rowsAll, UserOrderActivity.this,UserOrderActivity.this);
        pullToRefreshListView.setAdapter(orderAdapterAll);
        im_no_order.setVisibility(View.GONE);
        rowsPay = new ArrayList<>();
        rowsWaitePay = new ArrayList<>();
        rowsGet = new ArrayList<>();
        for (int i = 0; i < rowsAll.size(); i++) {
            Log.e("Status", "successCallBack: " + rowsAll.get(i).getStatusClientName());
            Log.e("Status", "successCallBack: " + rowsAll.get(i).getStatus() + ":" + rowsAll.size());
            if (rowsAll.get(i).getStatus() == 11) {
                //已下单
                rowsWaitePay.add(rowsAll.get(i));
            } else if (rowsAll.get(i).getStatus() == 21) {
                //订单已发货
                rowsPay.add(rowsAll.get(i));
            } else if (rowsAll.get(i).getStatus() == 12) {
                //已付款
                rowsPay.add(rowsAll.get(i));
            } else if (rowsAll.get(i).getStatus() == 31) {
                //退款退货
                rowsGet.add(rowsAll.get(i));
            } else if (rowsAll.get(i).getStatus() == 92) {
                //交易关闭
            } else if (rowsAll.get(i).getStatus() == 91) {
                //已退款，交易关闭
                rowsGet.add(rowsAll.get(i));
            } else {
                //交易完成
                rowsGet.add(rowsAll.get(i));
            }

        }
        Log.e("rowsWaitePay", "setView: " + rowsPay.size());
        if (rowsWaitePay.size() != 0) {
            orderAdapterAll2 = new OrderAdapter(rowsWaitePay, UserOrderActivity.this,UserOrderActivity.this);
            pullToRefreshListView1.setAdapter(orderAdapterAll2);
            im_no_order1.setVisibility(View.GONE);
        }
        if (rowsPay.size() != 0) {
            orderAdapterAll3 = new OrderAdapter(rowsPay, UserOrderActivity.this,UserOrderActivity.this);
            pullToRefreshListView2.setAdapter(orderAdapterAll3);
            im_no_order2.setVisibility(View.GONE);
        }
        if (rowsGet.size() != 0) {
            orderAdapterAll4 = new OrderAdapter(rowsGet, UserOrderActivity.this,UserOrderActivity.this);
            pullToRefreshListView3.setAdapter(orderAdapterAll4);
            im_no_order3.setVisibility(View.GONE);
        }
        /*pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(UserOrderActivity.this, "position", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UserOrderActivity.this, OrderActivity.class);
                intent.putExtra("item", rowsAll.get(position));
                startActivity(intent);
            }
        });
        pullToRefreshListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(UserOrderActivity.this, OrderActivity.class);
                intent.putExtra("item", rowsWaitePay.get(position));
                startActivity(intent);
            }
        });
        pullToRefreshListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(UserOrderActivity.this, OrderActivity.class);
                intent.putExtra("item", rowsPay.get(position));
                startActivity(intent);
            }
        });
        pullToRefreshListView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(UserOrderActivity.this, OrderActivity.class);
                intent.putExtra("item", rowsBack.get(position));
                startActivity(intent);
            }
        });*/
    }

    @Override
    public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
        super.failCallBack(arg0, resultTag, isShowDiolog);
        Log.e(TAG_GET_ORDER, "failCallBack: " + arg0.getLocalizedMessage());
    }


    @Override
    public void dtaChange(int p) {
        rowsAll.remove(p);
        for (int i = 0; i < rowsAll.size(); i++) {
            Log.e("Status", "successCallBack: " + rowsAll.get(i).getStatusClientName());
            Log.e("Status", "successCallBack: " + rowsAll.get(i).getStatus() + ":" + rowsAll.size());
            if (rowsAll.get(i).getStatus() == 11) {
                //已下单
                rowsWaitePay.add(rowsAll.get(i));
            } else if (rowsAll.get(i).getStatus() == 21) {
                //订单已发货
            } else if (rowsAll.get(i).getStatus() == 12) {
                //已付款
                rowsPay.add(rowsAll.get(i));
            } else if (rowsAll.get(i).getStatus() == 31) {
                //退款处理中
                rowsGet.add(rowsAll.get(i));
            } else if (rowsAll.get(i).getStatus() == 92) {
                //交易关闭
            } else if (rowsAll.get(i).getStatus() == 91) {
                //已退款，交易关闭
            } else {
                //交易完成
                rowsGet.add(rowsAll.get(i));
            }
        }
        orderAdapterAll.notifyDataSetChanged();
        orderAdapterAll2.notifyDataSetChanged();
        orderAdapterAll3.notifyDataSetChanged();
        orderAdapterAll4.notifyDataSetChanged();
    }
}
