package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CC2.0 on 2017/2/11.
 */

public class DingZhiItemBean extends DataPacket {





    @SerializedName("id")
    private String id;

    @SerializedName("proname")
    private String proname;

    @SerializedName("firstXiJieImg")
    private String firstXiJieImg;

    @SerializedName("price")
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getFirstXiJieImg() {
        return firstXiJieImg;
    }

    public void setFirstXiJieImg(String firstXiJieImg) {
        this.firstXiJieImg = firstXiJieImg;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
