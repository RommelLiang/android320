package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CC2.0 on 2017/1/20.
 */

public class BaoJiaWater extends DataPacket {

    @SerializedName("Id")
    private int Id;

    @SerializedName("ProductNo")
    private String ProductNo;

    @SerializedName("ProductName")
    private String ProductName;

    @SerializedName("PublishUser")
    private PublishUserBean PublishUser;

    @SerializedName("Description")
    private String Description;

    @SerializedName("OriginalPrice")
    private String OriginalPrice;

    @SerializedName("Price")
    private String Price;

    public String getPrice() {
        return Price;
    }

    public String getOriginalPrice() {
        return OriginalPrice;
    }

    public String getDescription() {
        return Description;
    }

    public String getProductName() {
        return ProductName;
    }

    public String getProductNo() {
        return ProductNo;
    }

    public int getId() {
        return Id;
    }

    public PublishUserBean getPublishUser() {
        return PublishUser;
    }

}
