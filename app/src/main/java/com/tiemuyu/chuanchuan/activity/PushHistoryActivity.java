package com.tiemuyu.chuanchuan.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.tiemuyu.chuanchuan.activity.adapter.PushMessageAdapter;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.PushBean;
import com.tiemuyu.chuanchuan.activity.bean.PushBeanPlus;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.SPUtils;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.util.ToastHelper;

import org.xutils.http.RequestParams;

public class PushHistoryActivity extends BaseActivityG {

    private final String TAG_GET_PUSHHISTORY = "TAG_GET_PUSHHISTORY";
    private String device_token = "device_token";
    private PushBean pushBean;
    private ListView mListView;
    private TextView tv_edit;
    private boolean is_show_select = false;
    private PushBeanPlus mPushBeanPlus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_history);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String guideVer = sp.getString(device_token, "");
        String addTime = SPUtils.getAddTime();
        if (guideVer.equals("") || addTime.equals("")) {
            ToastHelper.show(this,"没有历史消息");
        }
        mListView = (ListView) findViewById(R.id.lv_message);
        tv_edit = (TextView) findViewById(R.id.tv_edit);
        Log.e("onCreate: ", guideVer+addTime);
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(this,
                        TAG_GET_PUSHHISTORY,
                        Constant.REQUEST_GET,
                        new RequestParams(
                                UrlManager.getPush(guideVer, addTime)),
                        this,
                        "拉取推送消息",
                        false));
    }

    @Override
    public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        Log.e("successCallBack: ",callBackMsg );
        if (resultTag.equals(TAG_GET_PUSHHISTORY)) {
            setView(callBackMsg);
        }
    }

    private void setView(String callBackMsg) {
        pushBean = GsonUtils.fromData(callBackMsg, PushBean.class);
        mPushBeanPlus = new PushBeanPlus(pushBean, is_show_select);
        final PushMessageAdapter pushMessageAdapter = new PushMessageAdapter(PushHistoryActivity.this,mPushBeanPlus);
        mListView.setAdapter(pushMessageAdapter);
        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_show_select) {
                    is_show_select = false;
                    mPushBeanPlus.setShou(is_show_select);
                    tv_edit.setText("编辑");
                } else {
                    is_show_select = true;
                    mPushBeanPlus.setShou(is_show_select);
                    tv_edit.setText("保存");
                }
                pushMessageAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
        super.failCallBack(arg0, resultTag, isShowDiolog);
        Log.e("failCallBack: ",arg0.getLocalizedMessage() );
        ToastHelper.show(this,"没有历史消息");
    }
}
