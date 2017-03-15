package com.tiemuyu.chuanchuan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tiemuyu.chuanchuan.activity.fragment.MineFragment;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.AppManager;

import org.xutils.view.annotation.ViewInject;

/**
 * Created by CC2.0 on 2017/1/23.
 */

public class MyInviteCode extends BaseActivityG {

    @ViewInject(R.id.myinvitecode_text)
    private TextView myinvitecode_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.invite_code_layout);
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        //  Constant.VERSION = Version.getAppVersionName(this);
        // _global = GlobalVariable.getInstance()
        initProcess();
    }

    /**
     * 加载的流程
     */
    protected void initProcess() {
        initData();
        initAppAccess();
        initUI();
        initListener();
    }

    /**
     * 实例化访问记录
     */
    protected void initAppAccess() {}

    protected void initData() {
//        // TODO Auto-generated method stub
//        c_type = getIntent().getStringExtra(ClassJumpTool.DATA_PACKET_NAME);
//        if (c_type != null)
//            coming_type = c_type;
//        isCollection = false;
    }

    protected void initUI() {
        myinvitecode_text=(TextView) findViewById(R.id.myinvitecode_text);
        myinvitecode_text.setText(MineFragment.user.getInviteCode());
    }

    protected void initListener() {
        // TODO Auto-generated method stub
//        bt_login.setOnClickListener(this);
//        bt_back.setOnClickListener(this);
//        //todo   添加 注册按钮和注册跳转。

    }
}
