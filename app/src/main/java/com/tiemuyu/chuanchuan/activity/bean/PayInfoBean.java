package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by CC2.0 on 2017/2/14.
 */

public class PayInfoBean  implements Serializable {


    @SerializedName("OrderId")
    private String OrderId;//

    @SerializedName("SignStr")
    private String SignStr;//




    @SerializedName("OrderType")
    private String OrderType;//



    public String getOrderType() {
        return OrderType;
    }

    public void setOrderType(String orderType) {
        OrderType = orderType;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getSignStr() {
        return SignStr;
    }

    public void setSignStr(String signStr) {
        SignStr = signStr;
    }



}
