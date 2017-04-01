package com.tiemuyu.chuanchuan.activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.ExecuteBean;
import com.tiemuyu.chuanchuan.activity.bean.SignWeChat;
import com.tiemuyu.chuanchuan.activity.bean.User;
import com.tiemuyu.chuanchuan.activity.bean.WeChatExecute;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.fragment.MineFragment;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;

import org.xutils.http.RequestParams;

public class ExecuteChargeActivity extends BaseActivityG implements OnItemClickListener {

    private TextView tv_mine,tv_execute;
    private Button btnActivityForgetPasswordNext;
    private double money;
    private String TAG_EXCUTECharger = "TAG_EXCUTECharger";
    private String TAG_PAID = "TAG_PAID";
    private ExecuteBean executeBean;
    private User user;
    private String s;
    private int PayType = 2;
    private TextView tv_get;

    private static IWXAPI iwxapi;
    private int chargeId;
    private Receiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_execute_charge);
        regToWx();
        tv_mine = (TextView) findViewById(R.id.tv_mine);
        tv_execute = (TextView) findViewById(R.id.tv_execute);
        tv_get = (TextView) findViewById(R.id.tv_get);
        btnActivityForgetPasswordNext = (Button) findViewById(R.id.btnActivityForgetPasswordNext);
        tv_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new AlertView.Builder().setContext(ExecuteChargeActivity.this)
                        .setStyle(AlertView.Style.ActionSheet)
                        .setMessage(null)
                        .setCancelText("取消")
                        .setDestructive("微信", "支付宝")
                        .setOthers(null)
                        .setOnItemClickListener(ExecuteChargeActivity.this)
                        .build().show();
            }
        });
        btnActivityForgetPasswordNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = String.valueOf(tv_execute.getText());
                if (s.equals("")) {
                    Toast.makeText(ExecuteChargeActivity.this, "请输入正确的金额", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    initPd();
                    pd.show();
                    money = Double.parseDouble(s);
                    MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(ExecuteChargeActivity.this,
                            TAG_EXCUTECharger, Constant.REQUEST_GET, new RequestParams(UrlManager
                            .excuteCharger(money,PayType,"android")), ExecuteChargeActivity.this, "充值", false));
                }
            }
        });
        user = MineFragment.user;
        findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        Log.e("TAG_EXCUTECharger", "successCallBack: "+callBackMsg);
        if (resultTag.equals(TAG_EXCUTECharger)) {
            if (PayType == 2) {
                executeBean = GsonUtils.fromData(callBackMsg, ExecuteBean.class);
                pay(executeBean.getData().getSignStr(), String.valueOf(executeBean.getData().getChargeId()));
                chargeId = executeBean.getData().getChargeId();
            } else {
                Log.e("successCallBack: ", callBackMsg);
                WeChatExecute wechatExecute = GsonUtils.fromData(callBackMsg, WeChatExecute.class);
                chargeId = wechatExecute.getData().getChargeId();
                SignWeChat signWeChat = GsonUtils.fromData(wechatExecute.getData().getSignStr(), SignWeChat.class);
                weChatPay(signWeChat);
            }
        }
        if (resultTag.equals(TAG_PAID)){
            Log.e("TAG_PAID", "successCallBack: "+callBackMsg);
            Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
        super.failCallBack(arg0, resultTag, isShowDiolog);
        Log.e("TAG_EXCUTECharger", "failCallBack: "+arg0.getLocalizedMessage());
    }
    private void pay(final String payInfo, String orderid) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(ExecuteChargeActivity.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);
                //把result 按照；分开然后变成
                String[] resultlist = result.split(";");
                System.out.println("==============resultlist=============================="+resultlist[2]);
                String[] xxx=resultlist[0].split("=");
                String xxxafter=xxx[1].substring(1, xxx[1].length()-1);
                System.out.println("==============================xxxafter=============="+xxxafter);
                Message msg = new Message();
                if (xxxafter.equals("9000"))
                {
                    msg.what =1;
                }
                else          if(xxxafter.equals("4000")||xxxafter.equals("6002"))       msg.what =2;
                else msg.what=3;
                msg.obj =result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {
                    //Toast.makeText(getApplicationContext(), "支付成功!", Toast.LENGTH_SHORT).show();
                    MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(ExecuteChargeActivity.this,
                            TAG_PAID, Constant.REQUEST_GET, new RequestParams(UrlManager
                            .paid(1,executeBean.getData().getChargeId())), ExecuteChargeActivity.this, "支付回调成功", false));
                    double amounts = user.getAmounts();
                    amounts+=money;
                    user.setAmounts(amounts);
                    DBTools.loginDb(ExecuteChargeActivity.this, user);
                    tv_mine.setText(amounts+"");
                    break;
                }
                case 2: {
                    Toast.makeText(getApplicationContext(), "支付错误请重试!", Toast.LENGTH_SHORT).show();
                    break;
                }
                case 3: {
                    Toast.makeText(getApplicationContext(), "支付退出请重试!", Toast.LENGTH_SHORT).show();
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    public void onItemClick(Object o, int position) {
        switch (position) {
            case 0:
                tv_get.setText("微信");
                PayType = 1;
                break;
            case 1:
                tv_get.setText("支付宝");
                PayType = 2;
                break;
        }
    }
    private void weChatPay(SignWeChat signWeChat) {
        IntentFilter intentFilter = new IntentFilter("tiemuyu.cc.wechat.pay");
        receiver = new Receiver();
        registerReceiver(receiver,intentFilter);
        PayReq req = new PayReq();
        req.appId = signWeChat.getAppid();
        req.partnerId = signWeChat.getPartnerid();
        req.prepayId = signWeChat.getPrepayid();
        req.nonceStr = signWeChat.getNoncestr();
        req.timeStamp = signWeChat.getTimestamp();
        req.packageValue = "Sign=WXPay";
        req.sign = signWeChat.getSign();
        iwxapi.sendReq(req);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver!=null) {
            unregisterReceiver(receiver);
        }
    }

    private void regToWx() {
        //将微信支付接入应用
        iwxapi = WXAPIFactory.createWXAPI(this, Constant.WE_chat_APP_ID, true);
        iwxapi.registerApp(Constant.WE_chat_APP_ID);
    }
    public class Receiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            int code = intent.getIntExtra("code", 1);
            Log.e( "onReceive: ", code+"");
            if (code == 0) {
                MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(ExecuteChargeActivity.this,
                        TAG_PAID, Constant.REQUEST_GET, new RequestParams(UrlManager
                        .paid(1,executeBean.getData().getChargeId())), ExecuteChargeActivity.this, "支付回调成功", false));
                double amounts = user.getAmounts();
                amounts+=money;
                user.setAmounts(amounts);
                DBTools.loginDb(ExecuteChargeActivity.this, user);
                tv_mine.setText(amounts+"");
            }
        }
    }
    private ProgressDialog pd;

    private void initPd() {
        pd = new ProgressDialog(this);//加载的ProgressDialog
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);//选择加载风格 这里是圆圈 STYLE_HORIZONTAL 是水平进度条
        pd.setMessage("跳转中....");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (pd!=null) {
            pd.dismiss();
        }
    }
}
