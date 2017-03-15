package com.tiemuyu.chuanchuan.activity.util;

//import com.lidroid.xutils.http.RequestParams;

import org.xutils.http.RequestParams;

/**
 * Created by CC2.0 on 2017/1/6.
 */
public abstract class ThreadPoolTask implements Runnable{

    //protected String url;//请求的地址
    protected String tag;//标识
    protected int action;//请求类型 GET ,POST
    protected RequestParams params;

    public ThreadPoolTask(String tag,int action,RequestParams params) {
        //this.url = url;
        this.tag=tag;
        this.action=action;
        this.params=params;

    }

    public abstract void run();


//	public String getUrl() {
//		return url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public RequestParams getParams() {
        return params;
    }

    public void setParams(RequestParams params) {
        this.params = params;
    }

}
