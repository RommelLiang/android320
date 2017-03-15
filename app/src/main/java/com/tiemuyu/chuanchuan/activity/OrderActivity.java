package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.squareup.picasso.Picasso;
import com.tiemuyu.chuanchuan.activity.adapter.KuaiDiAdapter;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.KuaiDIBean;
import com.tiemuyu.chuanchuan.activity.bean.Order;
import com.tiemuyu.chuanchuan.activity.bean.OrderBean;
import com.tiemuyu.chuanchuan.activity.bean.OrderDetail;
import com.tiemuyu.chuanchuan.activity.bean.User;
import com.tiemuyu.chuanchuan.activity.chat_tools.activity.MessageActivity;
import com.tiemuyu.chuanchuan.activity.chat_tools.bean.Contacts;
import com.tiemuyu.chuanchuan.activity.chat_tools.inter.NetResponses;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.DataSharedPress;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.LogUtil;
import com.tiemuyu.chuanchuan.activity.util.PreferenceUtils;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.Map;

public class OrderActivity extends BaseActivityG implements NetResponses {
    private ArrayList<Contacts> dataList = new ArrayList<>();
    public static String OWN_HEADER_URL = "";//gao 在这里更改有效
    public String tag = "MESSAGE";
    private DataSharedPress sharedPress;
    private boolean isLogIn = false;
    OrderBean.DataBean.RowsBean rowsBean;
    private Intent intent;
    private Order order;
    private TextView tv_one, tv_two, tv_three, tv_four, tv_miaoshu,
            tv_price, tv_conut, tv_time, tv_coin, tv_yunfei, tv_pay,
            name, phone, addressnext;
    private ImageView im_one, im_two, im_three, im_four, iv_order_image;
    private View view_one, view_two, view_three;
    private RelativeLayout rl_kefu;
    private int status;
    private Drawable drawable_circle_nomel;
    private Drawable drawable_circle_red;
    private ListView lv_jindu;
    private OrderDetail orderDetail;
    private String expressList1;
    private ArrayList<KuaiDIBean> kuaiDIBeen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        intent = getIntent();
        order = (Order) intent.getSerializableExtra("order");
        Log.e("onCreate: ",order.getEx() +"" );
        initView();
        orderDetail(order.getProId());
        Picasso.with(this)
                .load(order.getImage())
                .resize(100, 100).into(iv_order_image);
        tv_miaoshu.setText(order.getDetail());
        name.setText(order.getName());
        phone.setText(order.getPhone());
        addressnext.setText(order.getLocation());
        tv_time.setText("创建时间 " + order.getTime());
        tv_coin.setText(order.getCoin() + "");
        tv_yunfei.setText("0");
        tv_pay.setText(order.getTotal() + "");
        tv_price.setText("￥" + order.getPrice() + "");
        tv_conut.setText("x" + order.getCount());
        status = order.getStatus();
        setNetResponses(this);
        Resources resources = this.getResources();
        drawable_circle_red = resources.getDrawable(R.drawable.shape_point_red);
        drawable_circle_nomel = resources.getDrawable(R.drawable.shape_point_gray);
        Log.e("OrderActivity", "onCreate: "+status);
        findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        switch (status) {
            case 11:
                //未付款
                im_one.setBackground(drawable_circle_red);
                break;
            case 12:
                //已付款
                im_one.setBackground(drawable_circle_red);
                im_two.setBackground(drawable_circle_red);
                view_one.setBackgroundColor(getResources().getColor(R.color.ColorLaunchBackground));
                break;
            case 21:
                //已发货
                im_one.setBackground(drawable_circle_red);
                im_two.setBackground(drawable_circle_red);
                im_three.setBackground(drawable_circle_red);
                view_one.setBackgroundColor(getResources().getColor(R.color.ColorLaunchBackground));
                view_two.setBackgroundColor(getResources().getColor(R.color.ColorLaunchBackground));
                break;
            case 100:
                //交易完成
                im_one.setBackground(drawable_circle_red);
                im_two.setBackground(drawable_circle_red);
                im_three.setBackground(drawable_circle_red);
                im_four.setBackground(drawable_circle_red);
                view_one.setBackgroundColor(getResources().getColor(R.color.ColorLaunchBackground));
                view_two.setBackgroundColor(getResources().getColor(R.color.ColorLaunchBackground));
                view_three.setBackgroundColor(getResources().getColor(R.color.ColorLaunchBackground));
                break;
        }

    }

    private void initView() {
        lv_jindu = (ListView) findViewById(R.id.lv_jindu);
        name = (TextView) findViewById(R.id.name);
        phone = (TextView) findViewById(R.id.phone);
        addressnext = (TextView) findViewById(R.id.addressnext);
        tv_one = (TextView) findViewById(R.id.tv_one);
        tv_two = (TextView) findViewById(R.id.tv_two);
        tv_three = (TextView) findViewById(R.id.tv_three);
        tv_four = (TextView) findViewById(R.id.tv_four);
        tv_miaoshu = (TextView) findViewById(R.id.tv_miaoshu);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_conut = (TextView) findViewById(R.id.tv_conut);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_coin = (TextView) findViewById(R.id.tv_coin);
        tv_yunfei = (TextView) findViewById(R.id.tv_yunfei);
        tv_pay = (TextView) findViewById(R.id.tv_pay);
        im_one = (ImageView) findViewById(R.id.im_one);
        im_two = (ImageView) findViewById(R.id.im_two);
        im_three = (ImageView) findViewById(R.id.im_three);
        im_four = (ImageView) findViewById(R.id.im_four);
        iv_order_image = (ImageView) findViewById(R.id.iv_order_image);
        view_one = findViewById(R.id.view_one);
        view_two = findViewById(R.id.view_two);
        view_three = findViewById(R.id.view_three);
        rl_kefu = (RelativeLayout) findViewById(R.id.rl_kefu);
        rl_kefu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kefu();
            }
        });
    }

    @Override
    public void success(int type, JSONObject jsonObject) {
        Contacts contacts;
        if (type == 100) {
            JSONArray array = jsonObject.optJSONArray("kefulist");
            Log.e("array", "success: " + array);
            for (int i = 0; i < array.length(); i++) {
                contacts = new Contacts(Parcel.obtain());
                JSONObject object = array.optJSONObject(i);
                contacts.setName(object.optString("nickname"));
                contacts.setAccid(object.optString("accid"));
                contacts.setHeader(object.optString("userimg"));
                dataList.add(contacts);
            }
            kefu();
        } else if (type == 200) {
            System.out.println("*********" + jsonObject.toString());
            sharedPress.putString("accid", jsonObject.optString("accid"));
            sharedPress.putString("token", jsonObject.optString("token"));
            OWN_HEADER_URL = jsonObject.optString("userimg");
            login();
        }
    }

    @Override
    public void fail() {

    }

    public void login() {
        //LoginInfo loginInfo = new LoginInfo("tmy1", "68c58f02597daa4fdc3ab86ed103e0c6");//测试账号
        if (PreferenceUtils.getPrefBoolean(this, Constant.CC_IFLOGIN, false) == false) {
            return;
        }
        User now = DBTools.getUser();
        Toast.makeText(this, "######" + now.getAccid() + "    " + now.getAccToken(), Toast.LENGTH_SHORT).show();
        LoginInfo loginInfo = new LoginInfo(now.getAccid(), now.getAccToken());
//        System.out.println("accid after new LoginInfo: " + accid);
//        System.out.println("acctoken after new LoginInfo: " + token);
        String getinfo = now.getUserImg();
        ///sp.getString("userimag", "");
        //获取用户的im头像 gao
        OWN_HEADER_URL = getinfo;
        RequestCallback<LoginInfo> callback = new RequestCallback<LoginInfo>() {

            @Override
            public void onSuccess(LoginInfo loginInfo) {
                Toast.makeText(OrderActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                isLogIn = true;
                request(Request.Method.GET, "http://imserver.myappcc.com/api/Getuseracc", null, "", tag, 100);

            }

            @Override
            public void onFailed(int i) {
                System.out.println("login failed===" + i);
            }

            @Override
            public void onException(Throwable throwable) {
            }
        };
        NIMClient.getService(AuthService.class).login(loginInfo).setCallback(callback);
    }

    public void request(int method, final String URL, Map map, final String session, String tag, final int type) {
        this.tag = tag;
        JSONObject jsonObject;
        if (map != null)
            jsonObject = new JSONObject(map);
        else {
            jsonObject = null;
        }
        JsonRequest jsonRequest = new JsonObjectRequest(method, URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                netResponses.success(type, jsonObject);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                netResponses.fail();
            }
        });

        jsonRequest.setTag(tag);
        // 将请求添加到队列中
        ((MyApplication) getApplication()).getRequestQueue().add(jsonRequest);

    }

    private NetResponses netResponses;

    public void setNetResponses(NetResponses netResponses) {
        this.netResponses = netResponses;
    }

    private void kefu() {
        if (!PreferenceUtils.getPrefBoolean(OrderActivity.this, Constant.CC_IFLOGIN, false)) {
            Toast.makeText(OrderActivity.this, "请登录", Toast.LENGTH_SHORT).show();
            return;
        }
        if (isLogIn) {
            Intent intent = new Intent(OrderActivity.this, MessageActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("data", dataList);
            intent.putExtra("bundle", bundle);
            startActivity(intent);
        } else {
            login();
        }
    }

    private String TAG_GetOrder = "TAG_GetOrder";
    private void orderDetail(String id) {
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(this,
                        TAG_GetOrder,
                        Constant.REQUEST_GET,
                        new RequestParams(
                                UrlManager.orderDetail(id + "")
                        ),
                        this,
                        "查看订单",
                        false));
    }

    @Override
    public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        LogUtil.LogRommel(callBackMsg);
        orderDetail = GsonUtils.fromData(callBackMsg, OrderDetail.class);
        expressList1 = orderDetail.getData().getOrderExpress().getExpressList();
        Log.e("successCallBack: ", expressList1);
        kuaiDIBeen = GsonUtils.jsonToArrayList(expressList1, KuaiDIBean.class);
        KuaiDiAdapter kuaiDiAdapter = new KuaiDiAdapter(kuaiDIBeen,OrderActivity.this);
        lv_jindu.setAdapter(kuaiDiAdapter);
    }

    @Override
    public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
        super.failCallBack(arg0, resultTag, isShowDiolog);
        Log.e("failCallBack: ", arg0.getLocalizedMessage());
    }
}
