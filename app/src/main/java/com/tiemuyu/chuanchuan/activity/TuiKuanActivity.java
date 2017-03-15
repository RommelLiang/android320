package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tiemuyu.chuanchuan.activity.adapter.TuiKuanJinDuAdapter;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.Order;
import com.tiemuyu.chuanchuan.activity.bean.OrderBean;
import com.tiemuyu.chuanchuan.activity.bean.TiuKuanMessage;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.view.MyListView;

import org.xutils.http.RequestParams;

public class TuiKuanActivity extends BaseActivityG {
    private Intent intent;
    private ImageView iv_order_image;
    private TextView tv_order_type,tv_orderNumber,tv_miaoshu,tv_price,tv_time,tv_chuan_pay,tv_all,
            tv_conut,tv_cash;
    private MyListView lv_jindu;
    private OrderBean.DataBean.RowsBean rowsBean;
    private String TAG_cancelOrder = "TAG_cancelOrder";
    private TiuKuanMessage tiuKuanMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tui_kuan);
        intent = getIntent();

        rowsBean = (OrderBean.DataBean.RowsBean) intent.getSerializableExtra("order");
        refund(rowsBean.getItems().get(0).getOrderId());
        initView();
        Picasso.with(this)
                .load(rowsBean.getItems().get(0).getMainImage())
                .resize(100, 100).into(iv_order_image);
        findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initView() {
        iv_order_image = (ImageView) findViewById(R.id.iv_order_image);
        tv_order_type = (TextView) findViewById(R.id.tv_order_type);
        tv_orderNumber = (TextView) findViewById(R.id.tv_orderNumber);
        tv_miaoshu = (TextView) findViewById(R.id.tv_miaoshu);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_chuan_pay = (TextView) findViewById(R.id.tv_chuan_pay);
        tv_cash = (TextView) findViewById(R.id.tv_cash);
        tv_all = (TextView) findViewById(R.id.tv_all);
        tv_conut = (TextView) findViewById(R.id.tv_conut);
        lv_jindu = (MyListView) findViewById(R.id.lv_jindu);
        tv_order_type.setText(rowsBean.getStatusAdminName());
        tv_orderNumber.setText(rowsBean.getOrderNo());
        tv_miaoshu.setText(rowsBean.getOrderSubject());
        tv_price.setText("￥ " + rowsBean.getItems().get(0).getPrice());
        tv_time.setText(rowsBean.getOrderTime());
        tv_chuan_pay.setText(rowsBean.getCoin()+"");
        tv_conut.setText(rowsBean.getTotalNum()+"");
        tv_all.setText("￥ " + rowsBean.getTotalFee());
        tv_cash.setText(Double.parseDouble(rowsBean.getTotalFee()) - rowsBean.getCoin() +"");
    }
    private void refund(int id) {
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(this,
                        TAG_cancelOrder,
                        Constant.REQUEST_GET,
                        new RequestParams(
                                UrlManager.reviewRefound(id + "")
                        ),
                        this,
                        "查看退款",
                        false));
    }

    @Override
    public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        Log.e("successCallBack: ",callBackMsg );
        tiuKuanMessage = GsonUtils.fromData(callBackMsg,TiuKuanMessage.class);
        TuiKuanJinDuAdapter tuiKuanJinDuAdapter = new TuiKuanJinDuAdapter(tiuKuanMessage.getData().getOrderRefundRecord(),TuiKuanActivity.this);
        lv_jindu.setAdapter(tuiKuanJinDuAdapter);
    }

    @Override
    public void failShowCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.failShowCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        Log.e("failShowCallBack: ", callBackMsg);
    }

    @Override
    public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
        super.failCallBack(arg0, resultTag, isShowDiolog);
        Log.e("failCallBack: ", arg0.getLocalizedMessage());
    }
}
