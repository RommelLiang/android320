package com.tiemuyu.chuanchuan.activity;

import android.os.Bundle;

import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.AppManager;

/**
 * Created by Weihao Gao on 2017/2/4.
 */

public class AboutUs extends BaseActivityG {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_aboutus);


        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        //  Constant.VERSION = Version.getAppVersionName(this);
        // _global = GlobalVariable.getInstance();

//        init();
//        pd.show();
//
//        initProcess();


    }




}
