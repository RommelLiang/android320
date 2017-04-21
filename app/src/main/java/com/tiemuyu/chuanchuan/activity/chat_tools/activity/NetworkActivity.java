package com.tiemuyu.chuanchuan.activity.chat_tools.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.tiemuyu.chuanchuan.activity.MyApplication;
import com.tiemuyu.chuanchuan.activity.chat_tools.inter.NetResponses;
import com.tiemuyu.chuanchuan.activity.util.SPUtils;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Administrator on 2016/5/20.
 */

public class NetworkActivity extends FragmentActivity {
    public String tag;
    protected RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = ((MyApplication) getApplication()).getRequestQueue();
        System.out.println("~~~~~~"+requestQueue);
        SPUtils.init(this);
        tag = "MESSAGE";

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
//        {
//            @Override
//            public Map getHeaders() {
//                HashMap headers = new HashMap();
////                headers.put("Accept", "application/json");
////                headers.put("Content-Type", "application/json; charset=UTF-8");
////                if (!"".equals(session))
////                    headers.put(ConfigInfo.COOKIE, ConfigInfo.COOKIE_KEY + session);
//
//                return headers;
//            }
//        };
        // 设置该请求的标签
        jsonRequest.setTag(tag);
        // 将请求添加到队列中
        ((MyApplication) getApplication()).getRequestQueue().add(jsonRequest);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((MyApplication) getApplication()).getRequestQueue().cancelAll(tag);
    }

    private NetResponses netResponses;

    public void setNetResponses(NetResponses netResponses) {
        this.netResponses = netResponses;
    }

    @Override
    protected void onStop() {
        super.onStop();
        ((MyApplication) getApplication()).getRequestQueue().cancelAll(tag);
    }

}
