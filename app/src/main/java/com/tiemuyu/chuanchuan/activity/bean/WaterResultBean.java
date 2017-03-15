package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CC2.0 on 2017/1/20.
 */

public class WaterResultBean extends BaseBean {



    @SerializedName("Data")
    private WaterBeanData data;

    public WaterBeanData getData() {
        return data;
    }

//    public void setData(TradeDetilBean data) {
//        this.data = data;
//    }
}
