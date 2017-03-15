package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CC2.0 on 2017/1/19.
 */

public class BannerResultBean extends BaseBean {


    @SerializedName("Data")
    private String dataBean;


    public String  getData() {
        return dataBean;
    }




}
