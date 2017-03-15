package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.GetPassKey;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.fragment.MineFragment;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.DataContoler;
import com.tiemuyu.chuanchuan.activity.util.JsonTools;
import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;

import org.xutils.http.RequestParams;

public class GetCashActivity extends BaseActivityG {

    private TextView tv_get,tv_all;
    private Button btnActivityForgetPasswordNext;
    private double money;
    private double getMoney;
    private final String TAG_GETPASSKEY = "TAG_GETPASSKEY";
    private String passKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_cash);
        tv_get = (TextView) findViewById(R.id.tv_get);
        tv_all = (TextView) findViewById(R.id.tv_all);
        btnActivityForgetPasswordNext = (Button) findViewById(R.id.btnActivityForgetPasswordNext);
        money = MineFragment.user.getAmounts() - MineFragment.user.getFrzAmounts();
        tv_all.setText(money + "");
        btnActivityForgetPasswordNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMoney = Double.parseDouble(String.valueOf(tv_get.getText()));
                if (getMoney == 0) {
                    Toast.makeText(GetCashActivity.this, "提现金额不能为0", Toast.LENGTH_SHORT).show();
                } else if(getMoney > money) {
                    Toast.makeText(GetCashActivity.this, "提现金额不能大于可提现金额", Toast.LENGTH_SHORT).show();
                } else {
                    getPassKey();
                }
            }
        });
        findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    /*private void checkOldPassword(String result) {
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(GetCashActivity.this,
                        TAG_GET_CASH,
                        Constant.REQUEST_POST,
                        ParamsTools.chexkPassword(
                                UrlManager.cashMoney(), result,v,"1"),
                        GetCashActivity.this,
                        "校验支付密码",
                        false));
    }*/

    private void getPassKey() {
        MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,
                TAG_GETPASSKEY, Constant.REQUEST_GET, new RequestParams(UrlManager
                .GET_PASSKEY()), this, "获取passkey", false));
    }

    @Override
    public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        if (resultTag.equals(TAG_GETPASSKEY)) {
            GetPassKey key = JsonTools.fromJson(callBackMsg, GetPassKey.class);
            passKey = key.getData().getPassKey();
            Intent intent = new Intent(GetCashActivity.this,GetCashPasswordAcyivity.class);
            intent.putExtra("passKey",passKey);
            intent.putExtra("count",getMoney+"");
            startActivity(intent);
            //checkOldPassword(passKey);
            /*User user = MineFragment.user;
            user.setAmounts(2.2);
            DBTools.loginDb(this, user);*/
        }
    }

    @Override
    public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
        super.failCallBack(arg0, resultTag, isShowDiolog);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }
}
