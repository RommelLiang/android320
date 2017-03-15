package com.tiemuyu.chuanchuan.activity;


import android.content.Intent;
import android.os.Bundle;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MyWallet extends BaseActivityG implements OnItemClickListener {

    private LinearLayout ll_one,ll_two,ll_three,ll_four,ll_five;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_wallet);
        ll_one = (LinearLayout) findViewById(R.id.ll_one);
        ll_two = (LinearLayout) findViewById(R.id.ll_two);
        ll_three = (LinearLayout) findViewById(R.id.ll_three);
        ll_four = (LinearLayout) findViewById(R.id.ll_four);
        ll_five = (LinearLayout) findViewById(R.id.ll_five);
        ll_one.setOnClickListener(this);
        ll_two.setOnClickListener(this);
        ll_three.setOnClickListener(this);
        ll_four.setOnClickListener(this);
        ll_five.setOnClickListener(this);
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.ll_one:
                start(ZiChanMingXiActivity.class);
                break;
            case R.id.ll_two:
                start(AcountManngerActivity.class);
                break;
            case R.id.ll_three:
                start(PasswordManngerActivity.class);
            case R.id.ll_four:
                new AlertView("温馨提示", "提现操作完成后，您的提现将在一个工\n作日内到达您设置的支付账户。\n\n" +
                        "如有问题请联系穿穿客服热线：\n0755-86625823(工作日9:00-18:00)",
                        null, new String[]{"确定"}, null, this, AlertView.Style.Alert, this).show();
                break;
            case R.id.ll_five:
                start(ExecuteChargeActivity.class);
                break;
        }
    }

    private void start(Class<?> cls) {
        Intent intent = new Intent(this,cls);
        startActivity(intent);
    }


    @Override
    public void onItemClick(Object o, int position) {
        start(GetCashActivity.class);
    }
}