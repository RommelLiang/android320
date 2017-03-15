package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @项目名： SLGWHandroid0123
 * @包名： com.tiemuyu.chuanchuan.activity.bean
 * @类描述：
 * @创建人： hr
 * @创建时间： 2017/1/25
 * @version：
 */

public class L1WaterItemBean extends DataPacket {

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getOrig_price() {
        return orig_price;
    }

    public void setOrig_price(String orig_price) {
        this.orig_price = orig_price;
    }

    @SerializedName("ProductName")
    private String prod_name;

    @SerializedName("MainImage")
    private String img_url;

    @SerializedName("OriginalPrice")
    private String orig_price;

}
