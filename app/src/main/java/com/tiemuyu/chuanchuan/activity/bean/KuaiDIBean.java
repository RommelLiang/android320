package com.tiemuyu.chuanchuan.activity.bean;

/**
 * Created by 梁文硕 on 2017/3/10.
 */

public class KuaiDIBean {

    /**
     * time : 2017-01-10 18:45:43
     * ftime : 2017-01-10 18:45:43
     * context : 已签收,感谢使用顺丰,期待再次为您服务
     * location : null
     */

    private String time;
    private String ftime;
    private String context;
    private Object location;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }
}
