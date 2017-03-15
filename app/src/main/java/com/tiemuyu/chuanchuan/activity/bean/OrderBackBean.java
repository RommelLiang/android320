package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CC2.0 on 2017/2/14.
 */

public class OrderBackBean extends DataPacket {

    @SerializedName("PayMomeny")
    private String PayMomeny;//

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getPayMomeny() {
        return PayMomeny;
    }

    public void setPayMomeny(String payMomeny) {
        PayMomeny = payMomeny;
    }

    @SerializedName("OrderId")
    private String OrderId;//




}
