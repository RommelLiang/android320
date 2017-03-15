package com.tiemuyu.chuanchuan.activity.volley.volley;

import com.android.volley.VolleyError;

/**
 * description : 定义请求成功或失败的接口
 * create by   ：清风竹影
 * time        ：2015/10/25 12:30
 * package_name：com.csbe.volley
 * project_name：app_phone_2.0
 */
public interface RequestJsonListener<T> {
    /**
     * 成功
     *
     * @param <T>
     */
    public void requestSuccess(T result);

    /**
     * 错误
     */
    public void requestError(VolleyError e);
}
