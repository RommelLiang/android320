package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.VesionCodeBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.AppManager;
import com.tiemuyu.chuanchuan.activity.util.CheckVersion;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.SPUtils;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;

import org.xutils.http.RequestParams;

/**
 * Created by Weihao Gao on 2017/2/4.
 */

public class AboutUs extends BaseActivityG implements OnItemClickListener {
    public static final String TAG_VERSION ="TAG_VERSION";
    private TextView is_new_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_aboutus);


        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        is_new_version = (TextView) findViewById(R.id.is_new_version);
        if (SPUtils.getIsVersion()){
            is_new_version.setText("APP有新版本");
        }
        findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(this,
                        TAG_VERSION,
                        Constant.REQUEST_GET,
                        new RequestParams(
                                UrlManager.getVersion()),
                        this,
                        "获取版本信息",
                        false));
    }

    @Override
    public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        Log.e("successCallBack: ", callBackMsg);
        VesionCodeBean vesionCodeBean = GsonUtils.fromData(callBackMsg, VesionCodeBean.class);
        Log.e( "successCallBack: ",CheckVersion.check(vesionCodeBean.getData())+"" );
        Log.e( "successCallBack: ",vesionCodeBean.getData()+"" );
        if (CheckVersion.check(vesionCodeBean.getData())) {
            is_new_version.setText("有新版本:"+vesionCodeBean.getData());
            SPUtils.saveIsVersion(true);
            new AlertView.Builder().setContext(AboutUs.this)
                    .setStyle(AlertView.Style.ActionSheet)
                    .setTitle("您当前使用的不是最新版本")
                    .setMessage(null)
                    .setCancelText("取消")
                    .setDestructive("马上更新")
                    .setOthers(null)
                    .setOnItemClickListener(AboutUs.this)
                    .build().show();
        }
    }

    @Override
    public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
        super.failCallBack(arg0, resultTag, isShowDiolog);
        Log.e("failCallBack: ", arg0.getLocalizedMessage());
    }



    @Override
    public void onItemClick(Object o, int position) {
        if (position==0) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=" + getPackageName()));
            startActivity(intent);
        }
    }
}
